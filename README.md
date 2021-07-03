# Minishop
<img src="https://img.shields.io/badge/Spring-6DB33F.svg?&style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/Vue.js-4FC08D.svg?&style=for-the-badge&logo=Vue.js&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Bootstrap-7952B3.svg?&style=for-the-badge&logo=Bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/IntelliJ-000000.svg?&style=for-the-badge&logo=IntelliJIDEA&logoColor=white">

> 스프링 프레임워크를 적용한 쇼핑몰 프로젝트

[Spring-Servlet](https://github.com/oognuyh/minishop-servlet)에 스프링 프레임워크를 적용하면서 기존과 다르게 아래의 기능이 추가
* Oracle에서 MySQL로 데이터베이스를 변경
* Mybatis에서 JPA로 변경
* 회원 관련 기능에 Spring Security를 적용
* hibernate-validator를 사용한 유효성 검사
* ControllerAdvice를 통한 예외처리
* 에러 페이지 추가

## Screen flow
<img src="https://user-images.githubusercontent.com/48203569/124355566-bfe4cc00-dc4c-11eb-89df-cfeb035dd2bd.jpg">
  
## Database
<img src="https://user-images.githubusercontent.com/48203569/124355571-c115f900-dc4c-11eb-89ae-dc4d2c1dbd9e.png">

## Features
* 상품 
  * 상품 목록 조회
  * 상품 상세정보 조회
* 주문
  * 주문
  * 주문내역 조회
* 장바구니
  * 장바구니 조회/추가/삭제/수정
* 회원
  * 로그인/로그아웃
  * 회원가입
  
## Screens  
| 상품목록 | 상품상세 | 장바구니 |
|:--:|:--:|:--:|
|![home](https://user-images.githubusercontent.com/48203569/124356378-b52c3600-dc50-11eb-959a-7a43f745f6a5.png)|![details](https://user-images.githubusercontent.com/48203569/124356068-2bc83400-dc4f-11eb-9367-88c944d2d703.png)|![cart](https://user-images.githubusercontent.com/48203569/124356101-531f0100-dc4f-11eb-9141-962d89824478.png)|

| 주문 | 주문내역 | 로그인 |
|:--:|:--:|:--:|
|![checkout](https://user-images.githubusercontent.com/48203569/124356102-53b79780-dc4f-11eb-8a7d-63c5aa3b335b.png)|![orderhisory](https://user-images.githubusercontent.com/48203569/124356099-52866a80-dc4f-11eb-847c-4072d59b1887.png)|![signin](https://user-images.githubusercontent.com/48203569/124356103-53b79780-dc4f-11eb-96a5-e1d46d0570f9.png)

| 회원가입 | 404 | 500 |
|:--:|:--:|:--:|
|![signup](https://user-images.githubusercontent.com/48203569/124356097-51edd400-dc4f-11eb-9566-4b67cf188abb.png)|![404](https://user-images.githubusercontent.com/48203569/124356637-f6711580-dc51-11eb-916c-9fec940730a6.png)|![500](https://user-images.githubusercontent.com/48203569/124356640-f7a24280-dc51-11eb-80b6-3fcfb35db6fe.png)|

## What i learned
* Spring MVC
* Spring Security
* Spring Data JPA
* Hibernate Validator

## Library used
* [Material Icons](https://fonts.google.com/icons)
* [SweetAlert](https://sweetalert.js.org/)

