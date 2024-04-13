<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-12
  Time: 오후 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

</head>
<style>

    /*컨텐츠 기본 스타일*/
    *{
        box-sizing: border-box;
    }

    .content-wrapper{
        display: flex;
        justify-content: center;
        padding: 20px;
        background-color: rgb(7, 6, 7);
    }

    .content-box{
        display: flex;
        align-items: center;
        justify-content: center;
        width: 1000px;
        min-width: 1000px;
        background-color: rgb(31, 29, 31);;
        border: 1px solid gray;
        margin-right: 10px;
        color: white;
        height: auto;
        overflow: hidden;
    }

    /*사이드바 스타일*/
    .side-box{
        display: flex;
        align-items: center;
        flex-direction: column;
        height: 70vh;
        width: 300px;
        margin-top: 5px;
        position: sticky;
        top: 5px;
        display: block;
    }

    /* 가로 길이에 따라 사이드바 숨기기 */
    @media (max-width: 1250px){
        .side-box{
            display: none;
        }
    }

    .banner{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 65%;
        width: 300px;
        background-color: rgb(31, 29, 31);
        border: 1px solid gray;
        border-radius: 5px;
        margin-bottom: 10px;
    }
    .issue{
        display: flex;
        flex-direction: column;
        justify-content: center;
        width: 300px;
        background-color: rgb(31, 29, 31);
        border: 1px solid gray;
        border-radius: 5px;
        padding: 15px;
    }
    .issue h5{
        color: white;
        font-weight: bold;
    }
    .issue ul{
        list-style: none;
        padding: 0px;
        margin: 0px;
        color: white;
    }
    .issue li{
        margin-top: 5px;
        top: 50px;
    }

</style>
<body>
<!-- content -->
<div class="content-wrapper">

    <div class="content-box">

        <jsp:include page="main.jsp"/>

    </div>

    <div class="side-box">
        <div class="banner">
            <img src="">
        </div>
        <div class="issue">
            <h5>핫 이슈</h5>
            <ul>
                <li>게임1</li>
                <li>게임2</li>
                <li>게임3</li>
                <li>게임4</li>
                <li>게임5</li>
            </ul>
        </div>
    </div>
</div>
</script>
</body>
</html>
