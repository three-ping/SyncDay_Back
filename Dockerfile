# 빌드 단계: Gradle을 사용해 애플리케이션을 빌드합니다
FROM gradle:8.5-jdk17 AS builder

# 작업 디렉토리를 설정합니다 - 이곳에서 모든 빌드 작업이 이루어집니다
WORKDIR /app

# 프로젝트 파일들을 컨테이너로 복사합니다
COPY . .

# Gradle을 사용해 프로젝트를 빌드합니다 (테스트는 제외)
RUN gradle build -x test

# 실행 단계: 실제로 애플리케이션이 실행될 환경을 설정합니다
FROM openjdk:17-slim

# 실행 환경의 작업 디렉토리를 설정합니다
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일만 실행 환경으로 복사합니다
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너가 리스닝할 포트를 명시합니다 (Elastic Beanstalk의 요구사항)
EXPOSE 5000

# 애플리케이션 실행 명령을 설정합니다
ENTRYPOINT ["java", "-jar", "app.jar"]