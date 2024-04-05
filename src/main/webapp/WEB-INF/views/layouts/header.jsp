<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-03
  Time: 오후 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jstl 설정 -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <!-- Bootstrap 연동 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <!-- contextPath 설정 -->
    <% String contextPath = request.getContextPath(); %>
</head>

<body>

    <!-- 네비게이션 바 -->
    <nav class="navbar">

        <div class="navbar-logo">
            <a href="">
                <img src="<%=contextPath%>/resources/img/logo.png" alt="logo" width="50" height="50">
            </a>
        </div>

        <ul class="navbar-menu">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>

    </nav>



</body>
</html>
