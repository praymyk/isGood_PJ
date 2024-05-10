<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-12
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Spring 커스텀 properties 파일 불러오기 위한 태그리브 선언--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<style>

    /* 구글 폰트 불러오기 */
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

    /* 전체 폰트 설정 */
    *{
        font-family: "Noto Sans KR", sans-serif;
        box-sizing: border-box;
    }

    .header-body{
        margin: 0;
    }
    .navbar-menu-left li {
        font-weight: bolder;
        font-size: 14px;
        color: white;
    }

    .navbar-menu-right {
        font-weight: bolder;
        font-size: 14px;
        color: white;
    }

    /*input 입력 시 테두리 스타리*/
    .search-bar-input{
        border-radius: 5px;
        border: 2px solid white;
    }

    .search-bar input {
        font-size: 14px;
        font-weight: bolder;
        color: white;
    }

    /* input 요소의 플레이스홀더 스타일 */
    .search-bar input::placeholder {
        font-size: 14px;
        font-weight: bolder;
        color: white;
    }

    /* 네비게이션 바 스타일 */
    .navbar-wrapper{
        display: flex;
        height: 55px;
        width: 100%;
    }
    .navbar-body {
        display: flex;
        align-items: center;
        margin: auto;
        justify-content: space-between;
        height: 100%;
        width: 1200px;
        min-width: 750px;
    }

    .navbar-wrapper{
        background: linear-gradient(90deg, rgb(51, 11, 51), rgb(0, 0, 32));
    }

    /* 네비게이션 바 내부 요소 스타일 */
    .navbar-menu-left,
    .navbar-menu-right {
        display: flex;
        list-style: none;
        align-items: center;
    }

    .navbar-menu-left{
        flex-grow: 1;
        margin-right : auto;
        margin-left: 10px;
        padding: 0;
    }
    .navbar-menu-right{
        margin-left : auto;
    }

    .navbar-menu-left li,
    .navbar-menu-right li {
        margin-right: 30px;
        caret-color: transparent;
    }

    .navbar-menu-left li,
    .navbar-menu-right li:hover{
        cursor: pointer;
    }
    .navbar-menu-right a{
        text-decoration: none;
    }

    /* 로고 이미지 스타일 */
    .nav-logo{
        display: flex;
        align-items: center;
        margin-left: 20px;
        margin-right: 20px;
    }

    .nav-logo img {
        background-color: white;
        border-radius: 90%;
        height: 30px;
        width: 30px;
    }

    /* 검색 폼 스타일 */
    .search-bar {
        display: flex;
        align-items: center;
        background-color: white;
        border-radius: 5px;
        padding: 5px;
        margin-left: 30px;
        background-color: #11010e;
        border: 2px solid black;
        height: 34px;
    }

    .search-form{
        margin : 0px;
    }

    /* 검색 입력 필드 스타일 */
    .search-bar > input {
        padding: 5px;
        outline: none;
        width: 90%;
        box-sizing: border-box;
        border: none;
        background-color: #000000;
        caret-color: white;
    }
    .search-bar i{
        width: 10%;
        box-sizing: border-box;
        border: none;
    }
    .search-bar-focus{
        border: 2px solid white;
    }


    /* 드롭다운 메뉴 스타일 */
    .dropdown-menu {
        display: none;
        position: absolute;
        border-radius: 5px;
        background-color: rgb(29, 0, 22);
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
        padding-top: 5px;
        padding-bottom: 5px;
        margin-top: 5px;
        border: 1px solid grey;
    }

    .dropdown-line{
        width: 100%;
        height: 1px;
        background-color: grey;
        margin-top: 5px;
        margin-bottom: 5px;
    }

    /* 드롭다운 메뉴 아이템 스타일 */
    .dropdown-menu a {
        display: block;
        padding: 10px 20px 10px 20px;
        text-decoration: none;
        color: white;
    }

    .dropdown-menu a:hover {
        background-color: rgb(65, 0, 57);
    }

    /* 드롭다운 메뉴클릭 시 .show 클래스 이름 부여*/
    .show {
        display: block;
    }

</style>
<body class="header-body">
<!--fontawesome icon cdn 연동-->
<script src="https://kit.fontawesome.com/22698b3d17.js" crossorigin="anonymous"></script>

<!-- 네비게이션 바 -->
<div class="navbar-wrapper">
    <nav class="navbar-body">

        <a class="nav-logo" href="${pageContext.request.contextPath}">
            <img src="${pageContext.request.contextPath}/resources/icons/logo.png" alt="">
        </a>

        <ul class="navbar-menu-left">
            <li> 구독 게임 <i class="fa-solid fa-angle-down"></i>
                <div class="dropdown-menu">
                    <a class="main-sub">구독중인게임</a>
                    <div class="dropdown-line"></div>
                    <a>리그오브레전드</a>
                    <a>배틀그라운드</a>
                    <a>오버워치</a>
                </div>
            </li>
            <li> 게시판 <i class="fa-solid fa-angle-down"></i>
                <div class="dropdown-menu">
                    <a>자유게시판</a>
                    <a>공지게시판</a>
                    <a>건의게시판</a>
                </div>
            </li>
            <li>
                <form class="search-form">
                    <div class="search-bar">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="search" placeholder="찾기" aria-label="Search">
                    </div>
                </form>
            </li>
        </ul>

        <ul class="navbar-menu-right">
            <li><a href="${pageContext.request.contextPath}/loginForm.me">로그인</a></li>
            <li><a href="${pageContext.request.contextPath}/enrollForm.me">회원가입</a></li>
        </ul>
    </nav>
</div>

<!-- 경고 메시지 출력용 스크립트 -->
<!-- 회원가입 메시지 출력 -->
<script>
    var msg = "${msg}";

    window.onload = function() {

        if (msg != "" && msg != null) {
            alert(msg);
        }
    };

</script>

<script>
    // @ 로그인 한 경우 로그인 메뉴바 변경
    if("${sessionScope.loginUser.userNo}" != ""){
        document.querySelector('.navbar-menu-right').innerHTML
            = '<li><a href="${pageContext.request.contextPath}/mypage.me">마이페이지</a></li>' +
            '<li id="logOut">로그아웃</li>';
    }

    // 로그아웃 버튼 클릭시 일반 로그아웃 & sns 로그아웃 처리
    document.getElementById('logOut').addEventListener('click', function() {
        // 일반계정 로그아웃
        if("${sessionScope.loginUser.snsType}" == ""){
            location.href = "${pageContext.request.contextPath}/logout.me";
        }

        // 카카오 로그아웃
        // Spring Framework의 spring:eval 태그를 사용하여 속성 값을 가져옴
        // 속성 값은 properties 파일에 저장되어 있음
        if("${sessionScope.loginUser.snsType}" == "kakao"){
            <spring:eval expression="@environment.getProperty('KAKAO-RESTAPI-KEY')" var="kakaoRestApi" />
            <spring:eval expression="@environment.getProperty('KAKAO-LOGOUT-REDIRECT-URI')" var="kakaoLogoutRedirectUri" />
            location.href = `https://kauth.kakao.com/oauth/logout?client_id=${kakaoRestApi}&logout_redirect_uri=${kakaoLogoutRedirectUri}`;
        }

        // 네이버 로그아웃
        if("${sessionScope.loginUser.snsType}" == "naver"){
            // 네이버는 별도 로그아웃 api를 제공하지 않으므로 일반 로그아웃과 동일 처리
            location.href = "${pageContext.request.contextPath}/logout.me";
        }

        // 구글 로그아웃
        if("${sessionScope.loginUser.snsType}" == "google"){
            // 구글은 별도 로그아웃 api를 제공하지 않으므로 일반 로그아웃과 동일 처리
            location.href = "${pageContext.request.contextPath}/logout.me";
        }
    });

</script>


<script>
    // @ 드롭다운 메뉴 스크립트  (1 ~ 4)
    // 1. 네비게이션 바 메뉴 요소 찾기
    const navMenu = document.querySelectorAll('.navbar-menu-left > li');

    // 2. 각 메뉴 요소 클릭시 dropdown-menu 보이기
    navMenu.forEach(function(item){

        item.addEventListener('click', function(event){
            // 현재 클릭된 li 요소 찾기 -> currentDropdownMenu 변수에 저장
            const currentDropdownMenu = event.currentTarget.querySelector('.dropdown-menu');
            // 클릭된 메뉴에 대해서만 show 클래스 토글
            if(currentDropdownMenu) {
                currentDropdownMenu.classList.toggle('show');
            }

            // 3. 메뉴 클릭 시 클릭되지 않은 메뉴는 드랍다운 메뉴 감추기
            document.querySelectorAll('.dropdown-menu').forEach(function(menu) {
                // 클릭된 li 요소가 아니라면 show 클래스 제거
                if(menu !== currentDropdownMenu) {
                    menu.classList.remove('show');
                }
            });
        });
    });

    // input 메뉴 스크립트
    document.querySelector('.search-bar input').addEventListener('focus', function(){
        document.querySelector('.search-bar').classList.toggle('search-bar-focus');
    });

    // 드롭다운 메뉴와 input foucs 이벤트 종료 스크립트
    // 4. 드랍다운 메뉴외의 영역 클릭시 닫기
    document.addEventListener('click', function(e){
        if(!e.target.closest('.navbar-menu-left > li')){
            document.querySelectorAll('.dropdown-menu').forEach(function(item){
                item.classList.remove('show');
            });
        }

        if(!e.target.closest('.search-bar input')){
            document.querySelector('.search-bar').classList.remove('search-bar-focus');
        }
    });


</script>

</body>
</html>
