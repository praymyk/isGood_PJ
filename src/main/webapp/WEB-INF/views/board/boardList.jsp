<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-05-09
  Time: 오후 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
                    <span>로스트아크 채널</span>
                    <div class="subscribe-btn">
                        <span>구독</span>
                    </div>
                </div>
                <div class="board-title-content">
                    <div>
                        <span>구독자 1000명 </span>
                    </div>
                    <div>
                        <span>MMORPG</span>
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
            <tr>
                <td>1</td>
                <td class="bTitle">리그오브레전드</td>
                <td>작성자1</td>
                <td>2024-05-07</td>
                <td>100</td>
            </tr>
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

<!-- 선택 게시판에 따라 다른 board 페이지를 ajax로 불러오기 -->
<script>
    $(function(){
        var gameCode = "${gameCode}";

        $.ajax({
            url: "boardList/"+gameCode,
            type: "GET",
            success: function(data){
                console.log(data);
                // tbody 요소 선택
                var tbody = document.querySelector(".board-tbody");
                // 게시글 리스트 내용을 담을 변수
                var boardList = "";

                data.forEach(function(board){
                    boardList += "<tr>";
                    boardList += "<td>"+board.boardNo+"</td>";
                    boardList += "<td class='bTitle'>"+board.boardTitle+"</td>";
                    boardList += "<td>"+board.nickName+"</td>";
                    boardList += "<td>"+board.boardEnrollDate+"</td>";
                    boardList += "<td>"+board.boardCount+"</td>";
                    boardList += "</tr>";
                });

                tbody.innerHTML = boardList;
            },
            error: function(){
                console.log("error");
            }

        });

    });

</script>



</body>
</html>