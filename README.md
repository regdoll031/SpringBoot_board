# Spring 질문 게시판
## 개요
- SpringBoot를 활용한 질문 게시판

#### 만든 목적
- HTML, CSS, JavaScript, Java를 배운 내용을 바탕으로 SpringBoot를 활용하여 질문 게시판 구현
- Nav, 페이징, 게시물 번호 지정, 답변개수 표시, 스프링 시큐리티, 회원가입, 로그인, 수정 삭제, 추천버튼, 글쓴이 항목기능을 구현하기위한 목적

#### 일정
- 24.04.1 ~ 20.03.12
- 참여도 : 100% (개인 프로젝트)

## 사용 기술 및 개발 환경
- Language : HTML5, CSS3, JavaScript, Java, SpringBoot
- Tool : SpringB , GitHub, Git(SourceTree)

## 개발 전 USE-CASE, ERD 작성
### USE-CASE
![USECASE](https://github.com/regdoll031/ShopManagement/assets/145822755/1d170d2a-040b-40d9-a45d-e94a3b2a8702)

### ERD
![ERD](https://github.com/regdoll031/ShopManagement/assets/145822755/5eb1f655-19b2-41c8-b10a-c3c820873c89)


## 내용
### 구현 기능
### HTML, CSS
- Header, Nav, 표, footer

![FRONT](https://github.com/regdoll031/ShopManagement/assets/145822755/c59d3046-d5f9-479c-8194-34430dfe5c3b)

### 구현기능
### SQL(Oracle), Java, JSP

### 회원 매출 조회
![M_inquiry](https://github.com/regdoll031/ShopManagement/assets/145822755/955f1ea3-5700-42d4-91fe-4ee8b8714867)

### 회원목록 조회 및 수정
![inquiry](https://github.com/regdoll031/ShopManagement/assets/145822755/39695bb6-8265-45f3-add0-8a5e67df5391)

### 회원등록
### 회원번호, 가입일자 자동입력
![JOIN](https://github.com/regdoll031/ShopManagement/assets/145822755/8a0c9f99-2e28-412e-b16b-01587ca544b1)

### 회원정보 수정, 삭제
![Modify_Delete](https://github.com/regdoll031/ShopManagement/assets/145822755/c9413a50-83e5-4341-bc9b-dced8fc7c680)



# 결과
## SQL(Oracle), Java, JSP 사용
- Nav부분인 회원등록, 회원목록/수정, 회원매출조회, 홈을 누를 시 각 페이지로 이동
- SQL에서 CRUD를 사용한 데이터를 JSP에 연결하여 각 조회 페이지에서 데이터 불러오기
- 회원등록 페이지에 회원번호와 가입일자를 자동으로 생성되게 구현
- 변경할 내용은 회원목록조회/수정 페이지에서 회원번호 클릭 시 수정화면으로 이동 
