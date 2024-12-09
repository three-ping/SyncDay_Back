FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY app.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]