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
        width: 90%;
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

</style>
<body>
<!-- 로그인 실패시 메시지 -->
<script>
   window.onload = function (){
        var msg = "${msg}";

        if(msg != ""){
            alert(msg);
        }
    };
</script>

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
                            <input type="checkbox" name="loginType" value="normal">
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
            <a href="">
                <span>구글</span>
            </a>
            <a href="">
                <span>카카오</span>
            </a>
            <a href="">
                <span>네이버</span>
            </a>
        </div>

    </div>

</div>


</body>
</html>