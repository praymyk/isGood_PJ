<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-13
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>

    /* 구글 폰트 불러오기 */
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

    /* 전체 폰트 설정 */
    *{
        font-family: "Noto Sans KR", sans-serif;
        box-sizing: border-box;
        color: silver;
    }

    body{
        background: rgb(7, 6, 7);
    }

    .login-wrraper{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        padding: 20px;
    }

    .login-case{
        display: flex;
        align-items: center;
        height: 700px;
        width: auto;
    }

    /* 로고 박스 스타일*/
    .login-box{
        border: 1px solid gray;
        height: 100%;
        min-width: 500px;
        padding: 60px;
        background-color: rgb(31, 29, 31);
    }

    .login-logo-box{
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 20px;
    }

    .login-logo{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 200px;
        height: 200px;
        border-radius: 90%;
        background-color: gray;
        margin-bottom: 30px;
        overflow: hidden;
    }
    .login-logo img{
        width: 100%;
        height: 100%;
    }

    .login-label{
        font-size: 30px;
        font-weight: bolder;
    }

    .login-sub-label{
        font-size: 20px;
        font-weight: bolder;
        color: gray;
    }

    /* 로그인 폼 스타일 */
    .login-form-group{
        display: flex;
        flex-direction: column;
        margin : 20px 20px 30px 20px;
        min-width: 350px;
    }
    .login-id-wraper{
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }
    .login-id-wraper input{
        margin-left: auto;
    }
    .login-id-wraper span{
        font-size: 12px;
    }

    .login-form-group label{
        font-size: 18px;
        font-weight: bolder;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .login-form-group input{
        height: 35px;
        border-radius: 0px;
        border: 1px solid gray;
        padding: 10px;
        font-size: 15px;
        background-color: rgb(7, 6, 7);
    }

    .login-form-group input:focus{
        border: 1px solid black;
    }

    .login-form-group input::placeholder{
        color: silver;
    }

    /* 계정생성/찾기, 로그인 버튼 스타일 */

    .login-form-group2{
        display: flex;
        align-items: center;
        padding: 0px 30px 0px 30px;
    }

    .login-form-group2 a{
        text-decoration: none;
        color: black;
        font-size: 15px;
        margin-right: 20px;
    }

    .login-form-group2 button{
        background-color: rgb(7, 6, 7);
        margin-left: auto;
        height: 40px;
        width: 80px;
        border-radius: 0px;
        border: none;
        padding: 10px;
        font-size: 15px;
        color: white;
    }
    .login-form-group2 button:hover{
        cursor: pointer;
    }

    /* sns 로그인 박스 스타일 */
    .line-box{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        width: 30px;
        flex-direction: column;
    }
    .line{
        background-color: gray;
    }

    .sns-login-box{
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100%;
        width: 300px;
        flex-direction: column;
        background-color: rgb(31, 29, 31);
        border: 1px solid gray;
    }

    .sns-login-box a{
        text-decoration: none;
        color: white;
        font-weight: bold;
        font-size: 18px;
        margin : 5px;
    }

    /* sns 로그인 버튼 스타일 */
    .naver-login{
        border-radius: 12px;
        background-color: #00C73C;
        height: 60px;
        width: 250px;
        margin: 5px;
    }
    .kakao-login{
        border-radius: 12px;
        background-color:  #FEE500;
        height: 60px;
        width: 250px;
        margin: 5px;
    }
    .google-login{
        border-radius: 12px;
        background-color: white;
        height: 60px;
        width: 250px;
        margin: 5px;
    }
    .sns-login-box a{
        display: flex;
        align-items: center;
        margin-left: 10px;
    }

    .sns-login-box img{
        width: 50px;
        height: 50px;
        margin-left: 10px;
    }
    .sns-login-box span{
        display: flex;
        margin: auto;
        align-items: center;
    }
    #kako-span{
        color: black;
    }
    #naver-span{
        color: white;
    }
    #google-span{
        color: black;
    }

</style>
<body>
<div class="login-wrraper">

    <div class="login-case">

        <div class="login-box">
            <div class="login-logo-box">
                <div class="login-logo">
                    <a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/icons/logo.png" alt="logo" width="50" height="50"></a>
                </div>
                <div class="login-label"> YSJOY </div>
                <div class="login-sub-label"> 로그인 </div>
            </div>

            <form class="login-form" action="login.me" method="post">
                <div class="login-form">
                    <div class="login-form-group">
                        <div class="login-id-wraper">
                            <label for="login-id">아이디</label>
                            <input type="checkbox" id="rememberId" name="rememberId" value="saveId">
                            <span> 이메일 저장 </span>
                        </div>
                        <input type="email" name="email" class="login-email">
                        <label for="login-pass">비밀번호</label>
                        <input type="password" name="userPwd" class="login-pass">
                    </div>


                    <div class="login-form-group2">
                        <a href="enrollForm.me"><span>계정 만들기</span></a>
                        <a href=""><span>아이디/비밀번호 찾기</span></a>
                        <button type="submit" class="login-button">로그인</button>
                    </div>

                </div>
            </form>
        </div>

        <div class="line-box">
            <div class="d-flex" style="height: 270px;">
                <div class="vr"></div>
            </div>
            <br>
            <span> O </span>
            <span> R </span>
            <br>
            <div class="d-flex" style="height: 270px;">
                <div class="vr"></div>
            </div>
        </div>

        <div class="sns-login-box">
            <a href="" class="google-login"><img src="${pageContext.request.contextPath}/resources/icons/btnG_google_login.png"><span id="google-span">구글 로그인</span></a>
            <a href="javascript:void(0);" onclick="kakaoLogin()" class="kakao-login"><img src="${pageContext.request.contextPath}/resources/icons/btnG_kakao_login.png"><span id="kako-span">카카오 로그인</span></a>
            <a href="" class="naver-login"><img src="${pageContext.request.contextPath}/resources/icons/btnG_naver_login.png"><span id="naver-span">네이버로 로그인</span></a>
        </div>

    </div>

</div>

<!-- 로그인 이메일 기억하기 용 스크립트 -->
<script>

    window.onload = function(){
        var msg = "${msg}";

        if(msg != ""){
            alert(msg);
        }

        // 1. 쿠키에 아이디가 존재할 경우 input에 불러오기
        console.log("쿠키 불러오기" + getCookie("savedEmail"));
        var savedEmail = getCookie("savedEmail");

        // 2. 쿠키에 아디가 존재할 경우 아이디 저장 체크박스 체크
        // *JavaScript에서는 falsy 값인 null, undefined, 빈 문자열(""), 숫자 0, false, NaN을 조건문에서 false로 간주
        if(savedEmail){
            document.getElementById("rememberId").checked = true;
        } else {
            document.getElementById("rememberId").checked = false;
        }

        // @param checkId : 아이디 저장 체크박스
        var checkId = document.getElementById("rememberId");

        // 3. 아이디 저장하기 체크박스 이벤트1 (체크 시 입력되어 있는 아이디 쿠키에 저장)
        checkId.onchange = function(){
            if(checkId.checked){
                // @param loginedEmail : 로그인한 이메일 값
                var loginedEmail = document.getElementsByClassName("login-email")[0].value;
                setCookie("savedEmail", loginedEmail, 30); // 30일 동안 쿠키 저장
            }else{ // 체크 해제 시 쿠키 삭제
                deleteCookie("savedEmail");
            }
        }

        // 4. 아이디 저장하기 체크박스 이벤트2 (체크를 먼저하고 아이디를 입력할 경우)
        // @param inputEmail : 이메일 입력 input
        var inputEmail = document.getElementsByClassName("login-email")[0];

        inputEmail.addEventListener("keyup", function(e){
            if(checkId.checked){
                var loginedEmail = document.getElementsByClassName("login-email")[0].value;
                setCookie("savedEmail", loginedEmail, 30);
            }
        });

    }

    // 쿠키 저장 함수 셋팅
    var setCookie = function(name, value, exp){
        var date = new Date();
        date.setTime(date.getTime() + exp*24*60*60*1000);
        document.cookie = name + "=" + value + ";expires=" + date.toUTCString() + ";path=/";
    }

    // 쿠키 가져오기 함수 셋팅
    var getCookie = function(name){
        var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
        return value? value[2] : null;
    }

    // 쿠키 삭제 함수 셋팅
    var deleteCookie = function(name){
        document.cookie = name + "=; expires=Thu, 01 Jan 1999 00:00:00 GMT; path=/;";
    }

</script>

<!-- SNS 로그인 함수 -->
<script>


    function kakaoLogin(){
        /* @param rest_api_key : 카카오 로그인 Rest API Key
           @param redirect_uri : 카카오 로그인 후 리다이렉트 될 주소
           @param kakaoURL : 카카오 로그인 URL
         */
        const rest_api_key = 'b625b2518b06664256b701e5bb1d0707';
        const redirect_uri = 'http://localhost:8081/isGood/kakaoLogin.me';

        //  jsp 엔진을 거치면 'page, request, session, context '에서 "rest_api_key"라는 이름표가 붙은 값을 찾음
        //  해당 영역에는 존재하지 않으므로 그냥 'rest_api_key 값을 찾지 못함
        const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${'${rest_api_key}'}&redirect_uri=${'${redirect_uri}'}&response_type=code`;

        window.location.href = kakaoURL;

    }



</script>


</body>
</html>