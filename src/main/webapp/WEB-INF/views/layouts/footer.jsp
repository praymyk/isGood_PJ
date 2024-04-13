<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-03
  Time: 오후 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>footer</title>
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
    
    .footer-body{
        margin: 0px;
    }

    .footer-wrapper{
        background: rgb(7, 6, 7);
    }

    .footer-wrapper{
        display: flex;
        justify-content: space-around;
        align-items: center;
        height: 200px;
        background-color: rgb(31, 29, 31);
    }

    .footer-wrapper div{
        display: flex;
        flex-direction: column;
    }

</style>
<body class="footer-body">
    <div class="footer-wrapper">
        <div>
            홈페이지 주소 <img src="" alt="사용자로고"> 1234(회원수) <br>
            개발자 : 윤석 <br>
            praymyk@gmail.com <br>
            thank you for visiting our website
        </div>
        <div>
            사용 기술 <br>
            spring <br>
            DB <br>
            ide
        </div>
        <div>
            Application <br>
            연동할 api 아이콘1 <br>
            연동할 api 아이콘2 <br>
            this site is protected by nothing.
        </div>
    </div>
    
</body>
</html>