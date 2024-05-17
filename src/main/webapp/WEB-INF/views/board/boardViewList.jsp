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
        font-weight: bold;
    }
    .subscribe-btn:hover{
        cursor: pointer;
    }

    .subscribe-btn-on{
        width: 80px;
        height: 100%;
        display: none;
        align-items: center;
        justify-content: center;
        border: 1px solid green;
        font-weight: bold;
    }

    .subscribe-btn-off{
        width: 80px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: red;
        border: 1px solid red;
    }
    .subscribe-btn-off:hover{
        cursor: pointer;
    }

    .write-btn{
        width: 60px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid gray;
    }

    .write-btn:hover{
        cursor: pointer;
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
    tbody tr:hover{
        cursor: pointer;
        background-color: rgba(0, 0, 0, 0.19);
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

    /* 게시글 조회 스타일 */
    .board-view-wrapper{
        margin-top: 20px;
        margin-bottom: 20px;
        padding: 20px;
    }

    .board-view-title{
        border-top: 1px solid gray;
        border-bottom: 1px solid gray;
        padding: 5px;
        font-size: 20px;
        font-weight: bold;
        background-color: rgba(0, 0, 0, 0.09);
        color: white;
        display: flex;
        justify-content: left;
    }
    .board-view-title button{
        width: 50px;
        height: 30px;
        border: 1px solid gray;
        background-color: rgba(0, 0, 0, 0.09);
        border-radius: 3px;
        font-weight: bold;
    }
    .board-view-title button:hover{
        cursor: pointer;
        background-color: rgba(0, 0, 0, 1.09);
    }
    #modifyBtn{
        margin-left: auto;
        margin-right: 10px;
    }

    .board-view-info {
        display: flex;
        padding: 5px;
        font-size: 14px;
        border-bottom: 1px solid gray;
    }

    .board-view-count{
        margin-left: auto;
        margin-right: 20px;
    }

    .board-view-link{
        display: flex;
        justify-content: flex-end;
        margin-left: auto;
        padding: 5px;
        font-size: 14px;
    }
    .board-view-link a{
        text-decoration: none;
    }
    .board-view-content{
        padding: 10px;
    }

    .board-post-list{
        padding: 10px;
    }
    .board-post-title{
        border-bottom: 1px solid gray;
        border-top: 1px solid gray;
        padding: 5px;
        font-size: 18px;
        font-weight: bold;
        margin-top: 30px;
        margin-bottom: 20px;
    }

    .post-wrapper{
        border: 1px solid gray;
        margin-bottom: 10px;
    }

    .post-title{
        display: flex;
        justify-content: left;
        border-bottom: 1px dotted gray;
        background-color: rgba(0, 0, 0, 0.36);
        color: white;
    }
    .post-date{
        margin-left: auto;
        padding: 10px;
        font-size: 14px;
    }
    .post-delete{
        padding: 10px;
        font-size: 14px;
    }
    .post-delete:hover{
        cursor: pointer;
    }

    .post-writer{
        padding: 10px;
        font-size: 14px;
    }
    .post-writer spans{
        margin-left: 20px;
    }
    .post-content{
        padding: 10px;
    }

    .board-post-up{
        border: 1px solid gray;
        border-radius: 3px;
        margin-top: 10px;
    }
    .post-textarea{
        display: flex;
        flex-direction: column;
        border-top: 1px solid gray;
    }
    .textarea-wrapper{
        min-height: 100px;
    }

    .textarea-wrapper textarea{
        padding: 10px;
        width: 100%;
        height: 100%;
        border: none;
        outline: none;
        resize: none;
        background-color: rgb(31, 29, 31);
    }

    .post-btn-wrapper{
        display: flex;
        justify-content: flex-end;
        padding: 5px;
    }
    .post-btn-wrapper button{
        width: 70px;
        height: 30px;
        border: 1px solid gray;
        background-color: rgba(0, 0, 0, 0.19);
        border-radius: 3px;
        color: white;
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
                    <c:if test="${not empty sessionScope.loginUser}">
                    <div class="subscribe-btn">
                        <span>구독</span>
                    </div>
                    </c:if>
                    <div class="subscribe-btn-on">
                        구독 중
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

    <!--게시글 상세 내용 조회시만 노출 시키기-->
    <div class="board-view-wrapper">

        <div class="board-view-title">
            <span>${board.boardTitle}</span>
            <c:if test="${sessionScope.loginUser.userNo == board.boardUserNo}">
                <button id="modifyBtn">수정</button>
                <button id="deleteBtn">삭제</button>
            </c:if>
        </div>
        <div class="board-view-info">
            <div class="board-view-nick">
                <span>${board.nickName}</span>
            </div>
            <div class="board-view-count">
                <span>| 조회수 ${board.boardCount} |</span>
            </div>
            <div class="board-view-date">
                <span>| 작성일 ${board.boardEnrollDate} |</span>
            </div>
        </div>
        <div class="board-view-link">
            <span>링크</span>
        </div>
        <div class="board-view-content">
            ${board.boardContent}
        </div>

        <div class="board-post-title">
            댓글
        </div>

        <div class="board-post-list">

            <div class="post-wrapper">
                <div class="post-title">
                    <span class="post-writer">작성자</span>
                    <span class="post-date">2024-05-08</span>
                </div>
                <div class="post-content">
                    입력 내용
                </div>
            </div>

        </div>

        <div class="board-post-up">
            <div class="post-writer">
                댓글 작성<spans>${sessionScope.loginUser.nickName}</spans>
            </div>
            <form action="${board.boardNo}/commentWrite" method="post">
            <div class="post-textarea">
                <c:if test="${not empty sessionScope.loginUser}">
                <input type="hidden" name="commentWriter" value="${sessionScope.loginUser.userNo}">
                <div class="textarea-wrapper"><textarea name="commentContent" placeholder="※ 내용 입력란 입니다."></textarea></div>
                <div class="post-btn-wrapper">
                    <button type="submit">등록</button>
                </div>
                </c:if>

                <c:if test="${empty sessionScope.loginUser}">
                    <div class="textarea-wrapper"><textarea name="commentContent" disabled placeholder="※ 로그인 후 댓글을 작성할 수 있습니다."></textarea></div>
                </c:if>
            </div>
            </form>
        </div>

    </div>
    <!-- 게시글 내용 끝 -->

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

<!-- 게시판 리스트 클릭 이벤트 스크립트 -->
<script>
    // 현재 페이지의 링크 추출 + 링크 노출
    $(function(){
        var currentUrl = window.location.href;
        var link = '<a href="' + currentUrl + '">' + currentUrl + '</a>';
        $(".board-view-link > span").html(link);
    });

    <!-- 게시판 호출용 게임 코드를 받아오기 -->
    var gameCode = "${gameCode}";

    // 게시글 상세 보기 스크립트 클릭 시 > 게시글 번호를 받아오기
    // 게시판 클릭시 해당 게시글 번호 추출
    $(".boardTr").click(function(){
        var boardNo = $(this).children().eq(0).text();
        console.log(boardNo);
        location.href = "${pageContext.request.contextPath}/b/${gameCode}/"+boardNo;
    });
</script>

<!--textarea 영역의 높이를 조절하는 스크립트-->
<script>
    // 텍스트 영역 요소를 가져옴
    var textarea = document.querySelector(".post-textarea textarea");

    // 텍스트 영역의 높이를 조절하는 함수
    function adjustHeight() {
        textarea.style.height = "auto"; // 높이를 자동으로 설정하여 내용에 맞게 조절
        textarea.style.height = textarea.scrollHeight + "px"; // 스크롤 높이를 텍스트 영역의 높이로 설정
    }

    // 텍스트 영역의 내용이 변경될 때마다 높이를 조절하는 이벤트 리스너 추가
    textarea.addEventListener("input", adjustHeight);

    // 초기에도 높이를 조절하기 위해 호출
    adjustHeight();
</script>

<!-- 구독 버튼 클릭 시 구독 처리 -->
<script>
    // 게시판 클릭시 해당 게시글 번호 추출 + 상세 페이지 이동
    $(".boardTr").click(function(){
        var boardNo = $(this).children().eq(0).text();
        location.href = "${pageContext.request.contextPath}/b/${gameCode}/"+boardNo;

    });

    $(function(){
        subscribeCheck();
    });

    // 구독 상태 확인용 AJAX ( 구동 상태에 따라 구독 버튼 보이기 )
    function subscribeCheck(){
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/subList.me/${sessionScope.loginUser.userNo}",
            dataType: "json",
            success: function(data){

                // @param x : 구독 여부 확인용 변수(반복문을 통해서 구독 여부 확인 하므로 구독체널이 있을경우 *0 / 없을경우 *1);
                //            최종 값이 0 일경우 구독 상태 / 1 일경우 비구독 상태
                var x = 1;
                console.log(data);
                for(var i = 0; i < data.length; i++){
                    if(data[i].gameCode == "${game.gameCode}"){
                        console.log(data[i].gameCode);
                        console.log("${game.gameCode}");

                        // 1. 구독 중인 경우 (구독 중/구독 해제 버튼 보이기)
                        x *= 0;
                        $(".subscribe-btn").attr("style", "display: none");
                        $(".subscribe-btn-on").attr("style", "display: flex");
                        $(".subscribe-btn-on").hover(function(){
                            $(".subscribe-btn-on").addClass("subscribe-btn-off")
                            $(".subscribe-btn-on").html("구독 취소");
                        },function(){
                            $(".subscribe-btn-on").removeClass("subscribe-btn-off")
                            $(".subscribe-btn-on").html("구독 중");
                        });
                    }
                }

                // 2. 비구독 상태 일 경우
                if(x != 0){
                    console.log("비구독 중");
                    $(".subscribe-btn").attr("style", "display: flex");
                    $(".subscribe-btn-on").attr("style", "display: none");
                }
            },
            error: function(){
                console.log("error");
            }
        });
    }

    // 구독 버튼 클릭 시 게임 구독
    $(".subscribe-btn, .subscribe-btn-on").click(function(){

        $.ajax({
            url : "${pageContext.request.contextPath}/subscribe",
            type : "POST",
            data : {
                userNo : "${sessionScope.loginUser.userNo}",
                gameCode : "${game.gameCode}"
            },
            success : function(data){
                console.log('구독 처리 결과' + data);
                subscribeCheck();
                location.reload();
            },
            error : function(){
                console.log("error");
            }
        })
    });

    // 글쓰기 버튼 스크립트
    $(".write-btn").click(function(){
        location.href = "${pageContext.request.contextPath}/b/${game.gameCode}/write";
    });

    // 수정 버튼 스크립트
    $("#modifyBtn").click(function(){
        location.href = "${pageContext.request.contextPath}/b/${game.gameCode}/${board.boardNo}/modify";
    });
    // 삭제 버튼 스크립트
    $("#deleteBtn").click(function(){
        location.href = "${pageContext.request.contextPath}/b/${game.gameCode}/${board.boardNo}/delete";
    });
</script>

<!-- 댓글 리스트 조회용 스크립트 -->
<script>
    $(function(){
        commentList();
    });

    // 댓글 리스트 조회용 함수
    function commentList(){
        $.ajax({
            url : "${pageContext.request.contextPath}/b/${game.gameCode}/${board.boardNo}/commentList",
            type : "GET",
            data : {
                boardNo : "${board.boardNo}"
            },
            success : function(data){
                var comment = "";
                console.log("댓글리스트 받아오기 성공");
                console.log(data);
                for(var i = 0; i < data.length; i++){
                    comment += '<div class="post-wrapper">' +
                        '<div class="post-title">'+
                        '<span class="post-no" style="display: none;">' + data[i].commentNo + '</span>'+
                        '<span class="post-writer">'+ data[i].nickName + '</span>'+
                        '<span class="post-date" >' + data[i].commentEnrollDate + '</span>'

                    if(data[i].commentWriter == "${sessionScope.loginUser.userNo}"){
                        comment += '<span class="post-delete" onclick=deletePost("' + data[i].commentNo + '")>삭제</span>';
                    }

                    comment +=      '</div>'+
                        '<div class="post-content">'+
                        data[i].commentContent +
                        '</div>'+
                        '</div>';
                }
                $(".board-post-list").html(comment);
            },
            error : function(){
                console.log("error");
            }
        });
    }
</script>

<!-- 댓글 삭제 스크립트 -->
<script>
    function deletePost(commentNo){
        console.log("잘클릭되나?" + commentNo)
        console.log(commentNo);
        $.ajax({
            url : "deletePost",
            type : "GET",
            data : {
                commentNo : commentNo
            },
            success : function(data){
                alert(data);
                // 댓글 삭제 후 댓글 리스트 조회 함수 호출
                commentList();
            },
            error : function(){
                console.log("error");
            }
        });
    }


</script>

</body>
</html>