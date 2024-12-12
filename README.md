# SyncDay_Back

| 일정 관리 시스템


## 🤝TEAM
| <img src="https://github.com/user-attachments/assets/8720124e-a14f-4bac-bc28-766e0a74e667" width="200" height = "160"> |<img src="https://github.com/user-attachments/assets/20f1a6af-6a60-4ab9-8ed7-f184cebfada7" width="200" height = "160">|<img src="https://github.com/user-attachments/assets/ea36c628-e6bf-4355-b029-f75cd8f4583a" width="200" height = "160">|<img src="https://github.com/user-attachments/assets/7c630eb9-203e-444a-bda1-db66ef2ffc7b" width="200" height = "160">|<img src="https://github.com/user-attachments/assets/2975804b-29a6-43d5-8785-c75d9345232c" width="200" height = "160">|<img src="https://github.com/user-attachments/assets/b2c35ea7-3fcd-420f-89bd-d1046e647284" width="200" height = "160"> |<img src="https://github.com/user-attachments/assets/e6f33f3d-7171-4e13-8f7a-0565d94bfdea" width="200" height = "160"> |
|:---------------------------------------------------------------:| :-----------------------------------: | :-----------------------------------: | :--------------------------------------: | :-----------------------------------: | :------------------------------------------: | :------------------------------------------: |
|               [신예진 멘토님](docs/img/profile/멘토님.jpg)               | [김동혁](docs/img/profile/김동혁2.png) | [김서현](https://github.com/1etterh) | [김시우](docs/img/profile/김시우2.jpg) | [김정모](https://github.com/mojeeeeong) | [이우진](docs/img/profile/이우진2.png) | [이효진](https://github.com/jinjin0528) |

## 🛠️기술스택
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![SpringBoot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![Vue.js](https://img.shields.io/badge/vue3-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)
![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## 📢협업 툴
![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-%235865F2.svg?style=for-the-badge&logo=discord&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)![JIRA sprint completion](https://img.shields.io/jira/sprint/:sprintId)




## 📋전체 프로젝트 일정
**프로젝트 일정 : 2024년 10월 22일 ~ 2024년 12월 12일**

### 목차

- [1. 프로젝트 개요](#1-프로젝트-개요)
- [2. 요구사항 명세서](#2-요구사항-명세서)
- [3. WBS](#3-WBS)
- [4. DDD](#4-DDD)
- [5. DB 모델링](#5-DB-모델링)
- [6. UI 설계](#6-UI-설계)
- [7. CI/CD 파이프라인](#7-CI-CD-파이프라인)
- [8. 프론트엔드 및 백엔드 테스트 결과](#8-프론트엔드-백엔드-테스트-결과)
- [9. CI/CD 테스트 결과](#9-CI-CD-테스트-결과)
- [10. 팀 회고](#10-팀-회고)

---
## 🍀1. 프로젝트 개요

뮤지컬 관련 정보를 확인하고 공유하며 소통하는 커뮤니티입니다.

### 1.1 프로젝트 소개

**뮤지컬에 대해 자유롭게 정보를 공유하고 소통해보세요.**

- ✔️ 관람했던 뮤지컬 티켓을 온라인으로 소장해보세요.
- ✔️ 다른 사람들이 좋아하는 뮤지컬을 확인해보세요.
- ✔️ 뮤지컬 리뷰를 확인하고 공유해보세요.
- ✔️ 뮤지컬 테마의 캘린더로 뮤지컬 일정을 확인하세요.


---

### 1.2 프로젝트 배경

뮤지컬 산업은 최근 몇 년간 빠르게 성장하며, 많은 관객들이 뮤지컬에 대한 정보를 찾고 소통하고자 하는 수요가 높아지고 있습니다. 그러나 기존의 정보 제공 플랫폼은 사용자가 뮤지컬에 대해 자유롭게 논의하고 소통할 수 있는 커뮤니티 기능이 부족한 상황입니다. 이에 따라, 뮤지컬을 사랑하는 사람들이 한데 모여 정보 공유, 리뷰 작성, 티켓 소장 등 다양한 활동을 할 수 있는 공간을 마련하고자 이 프로젝트를 기획하게 되었습니다.

![2023년 뮤지컬 통계자료](docs/img/2023_뮤지컬_통계.png)

[출처] 예술경영지원센터 <2023년 공연시장 티켓판매 현황 분석 보고서(총결산)>


---

### 1.3 국내외 유사 서비스와 차별성

#### 1.3.1 플레이DB
국내 대표적인 공연 정보 제공 서비스로, 뮤지컬 정보와 리뷰를 제공합니다. 하지만 UI가 사용자 친화적이지 않고, 커뮤니티 기능이 극히 제한적입니다. 뮤지컬에 대한 정보를 얻을 수는 있지만, 회원 간의 공유는 활발하지 않습니다.

![플레이디비_커뮤니티](./docs/img/플레이디비.png)

[플레이디비의 커뮤니티 - 회원 간 정보 공유를 위한 공간이 존재하지 않습니다.]

#### 1.3.2 뮤디엄 MUDIUM
뮤디엄은 MUSICAL과 MEDIUM, MUSEUM을 합성한 단어입니다. 박물관(Museum)은 예술, 역사, 문화의 중요한 매개체로서 과거와 현재를 연결하며 사람들에게 지식을 전달하죠. 이와 유사하게 뮤디움은 뮤지컬을 매개하는 플랫폼으로서, 뮤지컬이라는 예술 형태를 대중에게 소개하고 경험을 공유하는 역할을 합니다.

따라서, 뮤디움은 뮤지컬이라는 예술적 경험을 하나의 박물관처럼 보존하고, 다양한 정보를 사람들에게 제공하는 디지털 매체로 기능할 수 있습니다. 마치 박물관이 작품을 전시하고 공유하는 것 처럼, 뮤디움은 뮤지컬에 대한 정보, 현재의 흐름, 그리고 미래의 가능성을 모두 담아내는 뮤지컬의 박물관 같은 역할을 한다는 개념을 담고자 합니다.

또한 뮤디움은 매개체(MEDIUM)로서의 역할을 할 수 있습니다. 뮤지컬을 사랑하는 사람들이 모여 각자의 의견을 공유하고, 좋은 작품을 추천하며 소통합니다.

이렇게 뮤디움은 뮤지컬을 단순히 즐기는 것이 아니라, 뮤지컬에 대한 정보를 공유하고 의견을 나눌 수 있는 커뮤니티가 되고자 합니다.

## 🍀2. 요구사항 명세서

![요구사항명세서](./docs/img/요구사항명세서.png)


---

## 🍀3. WBS

![WBS](./docs/img/WBS.png)

---

## 🍀4. DDD
### 4.1 Domain Event Deduction
![Domain Event Deduction](./docs/img/ddd/1단계.png)

### 4.2 External System
![Domain Event Deduction](./docs/img/ddd/2단계.png)

### 4.3 Command
![Domain Event Deduction](./docs/img/ddd/3단계.png)

### 4.4 Actor
![Domain Event Deduction](./docs/img/ddd/4단계.png)

### 4.5 Aggregate
![Domain Event Deduction](./docs/img/ddd/5단계.png)

### 4.6 Bounded Context
![Domain Event Deduction](./docs/img/ddd/6단계.png)

### 4.7 Bounded context
![Domain Event Deduction](./docs/img/ddd/7단계.png)

#### 4.7.1 Bounded context - 1차 구현 목표
![Domain Event Deduction](./docs/img/ddd/7단계_1차목표.png)


---

## 🍀5. DB 모델링
---
### 5.1 논리 모델링

![논리모델링](./docs/img/논리.png)
--- 
### 5.2 물리 모델링
![물리모델링](./docs/img/물리.png)
---

## 🍀6. UI 설계
![피그마](./docs/img/figma.png)

---

## 🍀7. CI/CD 파이프라인
![파이프라인](./docs/img/아키텍처.png)
<details>
<summary>jenkins script code</summary>

   ```
   pipeline {
       agent any
   
       tools {
           gradle 'gradle'
           jdk 'openJDK17'
       }
   
       environment {
           DOCKERHUB_CREDENTIALS = credentials('DOCKERHUB_PASSWORD')
           DOCKERHUB_USERNAME = '1etterh'
           GITHUB_URL = 'https://github.com/three-ping/MUDIUM_DevOps.git'
       }
   
       stages {
           stage('Preparation') {
               steps {
                   script {
                       if (isUnix()) {
                           sh 'docker --version'
                       } else {
                           bat 'docker --version'
                       }
                   }
               }
           }
           stage('Source Build') {
               steps {
                   git branch: 'main', url: "${env.GITHUB_URL}"
                   script {
                       if (isUnix()) {
                           sh "chmod +x ./gradlew"
                           sh "./gradlew clean build"
                       } else {
                           bat "gradlew.bat clean build"
                       }
                   }
               }
           }
           stage('Container Build and Push') {
               steps {    
                   script {
                       withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                           if (isUnix()) {
                               sh "cp ./build/libs/*.jar ."
                               sh "docker build -t ${DOCKERHUB_USERNAME}/test-pipe2:latest ."
                               sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                               sh "docker push ${DOCKERHUB_USERNAME}/test-pipe2:latest"
                           } else {
                               bat "copy .\\build\\libs\\*.jar ."
                               bat "docker build -t ${DOCKERHUB_USERNAME}/test-pipe2:latest ."
                               bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                               bat "docker push ${DOCKERHUB_USERNAME}/test-pipe2:latest"
                           }
                       }
                   }
               }
           }
       }
   
       post {
           always {
               script {
                   if (isUnix()) {
                       sh 'docker logout'
                   } else {
                       bat 'docker logout'
                   }
               }
           }
           success {
               echo 'Pipeline succeeded!'
           }
           failure {
               echo 'Pipeline failed!'
           }
       }
   }
   ```

</details>


---

## 🍀8. 프론트엔드 및 백엔드 테스트 결과

---
### 8.1 자유게시글

<details>
  <summary>자유게시글</summary>

- <details>

    <summary>자유게시글 목록 조회</summary>

  ![테스트](./docs/gif/board/자유게시글목록.gif)

  </details>

- <details>

    <summary>자유게시글 검색</summary>

  ![테스트](./docs/gif/board/자유게시글검색.gif)

  </details>

- <details>
    <summary>자유게시글 상세 조회</summary>
   ![테스트](./docs/gif/board/자유게시글상세조회.gif)
  </details>

- <details>
    <summary>자유게시글 작성</summary>

  ![테스트](./docs/gif/board/자유게시글작성.gif)
  </details>

- <details>
   <summary>자유게시글 수정</summary>

  ![테스트](./docs/gif/board/자유게시글수정.gif)
  </details>

- <details>
   <summary>자유게시글 삭제</summary>

  ![테스트](./docs/gif/board/자유게시글삭제.gif)
  </details>

- <details>
   <summary>자유게시글 댓글 작성</summary>

  ![테스트](./docs/gif/board/자유게시글댓글작성.gif)
  </details>

- <details>
   <summary>자유게시글 댓글 수정</summary>

  ![테스트](./docs/gif/board/자유게시글댓글수정.gif)
  </details>

- <details>
   <summary>자유게시글 댓글 삭제</summary>

  ![테스트](./docs/gif/board/자유게시글댓글삭제.gif)
  </details>

- <details>
   <summary>자유게시글 대댓글 작성</summary>

  ![테스트](./docs/gif/board/자유게시글대댓글작성.gif)
  </details>

- <details>
   <summary>자유게시글 대댓글 수정</summary>

  ![테스트](./docs/gif/board/자유게시글대댓글수정.gif)
  </details>

- <details>
   <summary>자유게시글 대댓글 삭제</summary>

  ![테스트](./docs/gif/board/자유게시글대댓글삭제.gif)
  </details>

- <details>
   <summary>자유게시글 백엔드 </summary>

  ![테스트](./docs/gif/board/자유백엔드.gif)
  </details>

- <details>
   <summary>자유게시글 댓글 백엔드</summary>

  ![테스트](./docs/gif/board/자유댓글백엔드.gif)
  </details>

- <details>
   <summary>자유게시글 대댓글 백엔드</summary>

  ![테스트](./docs/gif/board/자유대댓글백엔드.gif)
  </details>

- <details>
   <summary>자유게시글 좋아요 백엔드</summary>

  ![테스트](./docs/gif/board/자유좋아요백엔드.gif)
  </details>

</details>

---

### 8.2 공지게시글

<details>
  <summary>공지게시글</summary>

- <details>

    <summary>공지게시글  조회</summary>

  ![테스트](./docs/gif/board/공지게시글조회.gif)

  </details>

- <details>

    <summary>공지게시글 백엔드</summary>

  ![테스트](./docs/gif/board/공지백엔드.gif)

  </details>

</details>

---

### 8.3 가이드북
<details>
  <summary>가이드북 메인(입문 작품 추천 / 뮤지컬 용어 안내 / 뮤지컬 관람 매너)</summary>

- <details>
   <summary>입문 작품 추천 백엔드</summary>

   <details>
      <summary>입문 작품 추천 전체 조회</summary>

  ![테스트](./docs/img/guidebook_img/작품추천목록.png)

   </details>

   <details>
     <summary>입문 작품 추천 상세 조회</summary>

  ![테스트](./docs/img/guidebook_img/작품추천상세조회.png)

   </details>

   <details>
     <summary>입문 작품 추천 작성</summary>

  ![테스트](./docs/img/guidebook_img/작품추천작성.png)

   </details>

   <details>
     <summary>입문 작품 추천 수정</summary>

  ![테스트](./docs/img/guidebook_img/작품추천수정.png)

   </details>

  </details>

- <details>
      <summary>뮤지컬 용어 안내 백엔드</summary>
      <details>
        <summary>뮤지컬 용어 안내 전체 조회</summary>

  ![테스트](./docs/img/guidebook_img/용어목록.png)
   </details>

   <details>
     <summary>뮤지컬 용어 안내 상세 조회</summary>

  ![테스트](./docs/img/guidebook_img/용어상세.png)
   </details>
   <details>
     <summary>뮤지컬 용어 안내 작성</summary>

  ![테스트](./docs/img/guidebook_img/용어작성.png)
   </details>
   <details>
     <summary>뮤지컬 용어 안내 수정</summary>

  ![테스트](./docs/img/guidebook_img/용어수정.png)
   </details>

- <details>
   <summary>관람 매너 안내 백엔드</summary>
   <details>
     <summary>관람 매너 안내 전체 조회</summary>

  ![테스트](docs/img/guidebook_img/매너목록.png)
   </details>
   <details>
     <summary>관람 매너 안내 상세 조회</summary>

  ![테스트](./docs/img/guidebook_img/매너상세조회.png)
   </details>
   <details>
     <summary>관람 매너 안내 작성</summary>

  ![테스트](./docs/img/guidebook_img/매너작성.png)
   </details>
   <details>
     <summary>관람 매너 안내 수정</summary>

  ![테스트](./docs/img/guidebook_img/매너수정.png)
   </details>
   </details>
   <details>
   <summary>가이드북 프론트</summary>

![테스트](./docs/gif/guidebook/가이드북.gif)

   </details>
</details>
</details>

---
### 8.4 리뷰

<details>
  <summary>리뷰</summary>

- <details>

    <summary>리뷰 전체 조회</summary>

  ![테스트](./docs/gif/review/review_total.gif)

  </details>

- <details>

    <summary>리뷰 상세 조회</summary>

  ![테스트](./docs/gif/review/review_detail.gif)

  </details>

- <details>

    <summary>리뷰 작성</summary>

  ![테스트](./docs/gif/review/review_regist.gif)

  </details>

- <details>

    <summary>리뷰 수정</summary>

  ![테스트](./docs/gif/review/review_update.gif)

  </details>

- <details>

    <summary>리뷰 삭제</summary>

  ![테스트](./docs/gif/review/review_delete.gif)

  </details>

- <details>

    <summary>리뷰 좋아요</summary>

  ![테스트](./docs/gif/review/review_like.gif)

  </details>

- <details>

    <summary>리뷰 백엔드</summary>

  ![테스트](./docs/gif/review/review_backend.gif)

  </details>

</details>

---

### 8.5 비밀리뷰

<details>
  <summary>비밀리뷰</summary>

- <details>

    <summary>비밀리뷰 조회</summary>

  ![테스트](./docs/gif/secret-review/secret_review_view.gif)

  </details>

- <details>

    <summary>비밀리뷰 작성</summary>

  ![테스트](./docs/gif/secret-review/secret_review_regist.gif)

  </details>

- <details>

    <summary>비밀리뷰 수정</summary>

  ![테스트](./docs/gif/secret-review/secret_review_update.gif)

  </details>

- <details>

    <summary>비밀리뷰 백엔드</summary>

  ![테스트](./docs/gif/secret-review/secret_review_backend.gif)

  </details>

</details>

---

### 8.6 캘린더

<details>
  <summary>캘린더</summary>

- <details>

    <summary>캘린더 테마 조회</summary>

  ![테스트](./docs/gif/calendar/캘린더테마.gif)

  </details>
</details>

---

### 8.7 커스텀 티켓
<details>
  <summary>커스텀티켓</summary>
   <details>
     <summary>커스텀티켓 생성</summary>
     <img src="./docs/gif/customticket/customticketCreate.gif" alt="테스트">

   </details>

   <details>
     <summary>커스텀티켓 생성 백엔드</summary>
     <img src="./docs/gif/customticket/customticketCreateTest.gif" alt="테스트">

   </details>

   <details>
     <summary>커스텀티켓 조회</summary>
      <img src="./docs/gif/customticket/customticket.gif" alt="테스트">

   </details>

   <details>
     <summary>커스텀티켓 조회 백엔드</summary>
     <img src="./docs/gif/customticket/customticketTest.gif" alt="테스트">

   </details>

   <details>
     <summary>커스텀티켓 삭제</summary>
     <img src="./docs/gif/customticket/customticketDelete.gif" alt="테스트">
   </details>

   <details>
     <summary>커스텀티켓 삭제 백엔드</summary>
     <img src="./docs/gif/customticket/customticketDeleteTest.gif" alt="테스트">
   </details>
</details>

---

### 8.8 뮤지컬 정보
<details>
  <summary>뮤지컬 정보</summary>
   <details>
     <summary>뮤지컬 조회</summary>
     <img src="./docs/gif/musicalInfo/musical-select.gif" alt="테스트">

   </details>

   <details>
     <summary>뮤지컬 조회 백앤드</summary>
     <img src="./docs/gif/musicalInfo/back-find.gif" alt="테스트">

   </details>

   <details>
     <summary>뮤지컬 검색</summary>
     <img src="./docs/gif/musicalInfo/musical-search.gif" alt="테스트">

   </details>

   <details>
     <summary>뮤지컬 검색 백엔드</summary>
     <img src="./docs/gif/musicalInfo/back-titlesearch.gif" alt="테스트">

   </details>
</details>

---

### 8.9 별점
<details>
  <summary>별점</summary>
   <details>
     <summary>별점 생성</summary>


   </details>

   <details>
     <summary>별점 생성 백엔드</summary>


   </details>

   <details>
     <summary>별점 수정</summary>


   </details>

   <details>
     <summary>별점 수정 백엔드</summary>


   </details>

   <details>
     <summary>평균 별점 조회</summary>

   </details>

   <details>
     <summary>평균 별점 조회 백앤드</summary>
 
   </details>
</details>


## 🍀9. CI/CD 테스트 결과
<details>
   <summary>결과</summary>



</details>

---

## 🍀10. 팀 회고
#### 김시우
> **이효진**: 
>

> **김서현**: 

> **이우진**: 
>

> **김동혁**: 
>

> **김정모**: 
>

#### 이효진
> **김시우**: 
>

> **김서현**: 


> **이우진**: 
>

> **김동혁**: 
>

> **김정모**: 
>

#### 김서현
> **이효진**: 
>

> **김시우**:  
>

> **이우진**:  
>

> **김동혁**: 
>

> **김정모**: 

#### 이우진
> **김시우**: 
>

> **이효진**: 
>

> **김서현**: 

> **김동혁**: 
>

> **김정모**: 
>

#### 김동혁
> **김시우**: 
>

> **이효진**: 
>

> **김서현**: 


> **이우진**: 
>

> **김정모**:  
>

#### 김정모
> **김시우**: 
>

> **이효진**: 
>

> **김서현**: 

> **이우진**: 
>

> **김동혁**: 
> 