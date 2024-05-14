<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-05-09
  Time: 오후 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    /* 구글 폰트 불러오기 */
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

    /* 전체 폰트 설정 */
    *{
        font-family: "Noto Sans KR", sans-serif;
        box-sizing: border-box;
    }

    .board-wrapper{
        width: 1000px;
        margin: 0;
        padding: 0px;
    }
    .board-wrapper select{
        width: 100px;
        height: 30px;
        border: 1px solid gray;
        background-color: rgba(0, 0, 0, 0.19);
        color: white;
    }

    .board-wrapper option{
        background-color: white;
        color: black;
    }

    .board-header-wrapper {
        display: flex;
        justify-content: center;
        width: 1000px;
        padding: 20px;
        border-bottom: 1px solid gray;
    }

    .board-header {
        display: flex;
        height: 100%;
        width: 100%;
    }

    .board-icon-wrapper {
        height: 100%;
    }

    .board-icon {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 80px;
        height: 80px;
        border-radius: 50%;
        margin-right: 30px;
        overflow: hidden;
    }

    .board-icon img {
        width: 100%;
        height: 100%;
    }

    .board-header-content {
        width: 100%;
    }

    .board-title {
        display: flex;
        height: 40%;
        align-items: center;
        justify-content: space-between;
    }
    .board-title>span {
        font-size: 20px;
        font-weight: bold;
    }

    .subscribe-btn {
        width: 80px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid gray;
    }
    .subscribe-btn:hover{
        cursor: pointer;
    }
    .subscribe-btn-on:hover{
        background-color: red;
    }

    .write-btn{
        width: 60px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid gray;
    }

    .board-title-content {
        height: 60%;
    }

    .board-banner{
        padding: 20px;
    }

    .board-banner-menu{
        display: flex;
        justify-content: space-between;
    }

    .board-banner-img{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100px;
        padding: 20px;
    }

    .board-content{
        padding: 20px;
    }

    .board-content table{
        border-collapse : collapse;
        width: 100%;
    }

    .board-content tbody{
        text-align: center;
    }
    tbody tr:hover{
        cursor: pointer;
        background-color: rgba(0, 0, 0, 0.19);
    }

    .bNum{
        width: 10%;
    }
    .bTitle{
        text-align: left;
        width: 60%;
    }
    .bNick{
        width: 10%;
    }
    .bDate{
        width: 10%;
    }
    .bCount{
        width: 10%;
    }

    thead th {
        border-bottom: 2px solid gray;
        font-weight : bold;
        height: 40px;
    }

    tbody tr {
        border-bottom: 1px solid gray;
        height: 40px;
    }

    .board-search-wrapper{
        display: flex;
        width: 100%;
        justify-content: flex-end;
        margin-top: 10px;
    }
    .board-search-wrapper input{
        width: 200px;
        height: 30px;
        border: 1px solid gray;
        background-color: rgba(0, 0, 0, 0.19);
        color: white;
    }
    .board-search-wrapper button{
        width: 60px;
        height: 30px;
        border: 1px solid gray;
        background-color: rgba(0, 0, 0, 0.19);
        color: white;
    }

    .pagingbar-wrapper{
        display: flex;
        justify-content: center;
    }

    .pagingbar-wrapper li{
        display: inline;
        margin: 0px;
    }

    .pagingbar-wrapper a{
        text-decoration: none;
        border: 1px solid gray;
        width: 30px;
        height: 30px;
        padding: 5px 10px;
    }

</style>
<body>
<div class="board-wrapper">

    <div class="board-header-wrapper">
        <div class="board-header">

            <div class="board-icon-wrapper">
                <div class="board-icon">
                    <img src="http://localhost:8081/isGood/resources/icons/logo.png">
                </div>
            </div>

            <div class="board-header-content">
                <div class="board-title">
                    <span>${game.gameTitle} 채널</span>
                    <div class="subscribe-btn">
                        구독
                    </div>
                </div>
                <div class="board-title-content">
                    <div>
                        <span>구독자 ${game.enrollCount}명 </span>
                    </div>
                    <div>
                        <span>${game.gameTag}</span>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div class="board-banner">
        <div class="board-banner-menu">
            <select>
                <option>등록순</option>
                <option>조회순</option>
            </select>
            <div class="write-btn">
                글쓰기
            </div>
        </div>
        <div class="board-banner-img">
            <img src="${pageContext.request.contextPath}/<spring:eval expression='@environment.getProperty("displayBannerImg.path")'/>" class="banner-img">
        </div>
    </div>

    <div class="board-content">
        <table>
            <thead>
            <tr class="board-content-head">
                <th class="bNum" >번호</th>
                <th class="bTitle">제목</th>
                <th class="bNick">작성자</th>
                <th class="bDate">작성일</th>
                <th class="bCount">조회수</th>
            </tr>
            </thead>
            <tbody class="board-tbody">
            <c:forEach var="p" items="${boardList}">
                <tr class="boardTr">
                    <td>${p.boardNo}</td>
                    <td class="bTitle">${p.boardTitle}</td>
                    <td>${p.nickName}</td>
                    <td>${p.boardEnrollDate}</td>
                    <td>${p.boardCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="board-banner">

        <div class="board-banner-menu">
            <select>
                <option>등록순</option>
                <option>조회순</option>
            </select>
            <div class="write-btn">
                글쓰기
            </div>
        </div>

        <div class="board-search-wrapper">
            <select>
                <option>전체</option>
                <option>제목</option>
                <option>내용</option>
                <option>작성자</option>
            </select>
            <input type="text">
            <button>검색</button>
        </div>
    </div>

    <div class="pagingbar-wrapper">
        <nav>
            <ul>
                <li><a href="">1</a></li>
                <li><a href="">2</a></li>
                <li><a href="">3</a></li>
                <li><a href="">4</a></li>

                <li><a href="">></a></li>
                <li><a href="">>></a></li>
            </ul>
        </nav>
    </div>
</div>

<script>
    // 게시판 클릭시 해당 게시글 번호 추출 + 상세 페이지 이동
    $(".boardTr").click(function(){
        var boardNo = $(this).children().eq(0).text();
        location.href = "${pageContext.request.contextPath}/b/${gameCode}/"+boardNo;

    });

    // 구독 상태 확인용 AJAX
    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/subList.me/${sessionScope.loginUser.userNo}",
        dataType: "json",
        success: function(data){
            console.log(data);
            for(var i = 0; i < data.length; i++){
                if(data[i].gameCode == "${game.gameCode}"){
                    $(".subscribe-btn").addClass("subscribe-btn-on");
                    (".subscribe-btn").text("구독 중");
                }
            }
        },
        error: function(){
            console.log("error");
        }
    });

    // 구독 버튼 클릭시 게임 구독
    $(".subscribe-btn").click(function(){
        console.log("구독");

        $.ajax({
            url : "${pageContext.request.contextPath}/game/subscribe",
            type : "POST",
            data : {
                userNo : "${sessionScope.loginUser.userNo}",
                gameCode : "${game.gameCode}"
            },
            success : function(data){
                console.log(data);
            },
            error : function(){
                console.log("error");
            }
        })

    });
</script>
</body>

</html>