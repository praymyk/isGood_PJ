<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-03
  Time: 오후 6:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .index-body{
        background-color: rgb(7, 6, 7);
        margin:0px;
    }
</style>
<body class="index-body">
<!-- header -->
<jsp:include page="../layouts/header.jsp"/>

<!-- content -->
<jsp:include page="myPageContent.jsp"/>

<!-- footer -->
<jsp:include page="../layouts/footer.jsp"/>
</body>

</html>
