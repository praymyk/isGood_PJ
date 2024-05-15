<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-05-15
  Time: 오후 2:09
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
    }

    .write-wrapper {
        width: 1000px;
        margin: 0;
        padding: 0px;
        border: 1px solid red;
    }

    .board-header-wrapper {
        display: flex;
        justify-content: center;
        width: 1000px;
        padding: 10px;
        border-bottom: 1px solid gray;
        margin-bottom: 10px;
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
        width: 110%;
        height: 110%;
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

    .write-content-wrapper{
        padding: 10px;
    }


    .write-title input{
        width: 100%;
        height: 50px;
        margin-bottom: 10px;
        border-radius: 5px;
        border: 1px solid gray;
    }

    .write-btn-wrapper{
        display: flex;
        justify-content: flex-end;
        height: 30px;
    }

    .write-btn-wrapper button{
        width: 60px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid gray;
    }

</style>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- summernote cdn 방식 등록-->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>

<body>

<div class="write-wrapper">

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

    <form method="post" action="${pageContext.request.contextPath}/b/${game.gameCode}/${board.boardNo}/modify">

        <div class="write-content-wrapper">
            <div class="write-title">
                <input type="text" name="boardTitle" placeholder="제목" value="${board.boardTitle}">
                <input type="hidden" name="boardUserNo" value="${sessionScope.loginUser.userNo}">
            </div>

            <div >
                <textarea id="summernote" name="boardContent">
                    ${board.boardContent}
                </textarea>
            </div>

            <div class="write-btn-wrapper">
                <button type="submit">수정</button>
            </div>
        </div>

    </form>

    <!-- summernote 등록 스크립트  -->
    <script>
        $(document).ready(function() {

            var setting = {
                placeholder: '내용을 작성해주세요.',
                tabsize: 2,
                height: 500,
                minHeight : null,
                maxHeight : null,
                focus : true,
                lang : 'ko-KR',
                //콜백 함수
                callbacks : {   //'onImageUpload'함수는 '이미지를 업로드했을 때' 동작하는 섬머노트 함수
                                // 여러 개의 파일을 Drag And Drop 하거나, 파일 첨부에서 다중 선택 후 업로드할 때를 위해 for문으로 처리
                    onImageUpload : function(files, editor, welEditable) {
                        // 파일 업로드(다중업로드를 위해 반복문 사용)
                        for (var i = files.length - 1; i >= 0; i--) {
                            imageUploader(files[i],
                                this);
                        }
                    }
                }
            };

            $('#summernote').summernote(setting);

            // ajax로 서버에서 파일 업로드를 진행하는 함수
            function imageUploader(file, el) {
                data = new FormData();
                data.append("file", file);
                $.ajax({
                    data : data,
                    type : "POST",
                    url : "${pageContext.request.contextPath}/uploadSummernoteImageFile",
                    contentType : false,
                    enctype : 'multipart/form-data',
                    processData : false,
                    success : function(data) {
                        //var imgData = JSON.parse(data);
                        $(el).summernote('editor.insertImage', "${pageContext.request.contextPath}/"+data.url);
                        console.log("데이터"+data);
                        console.log("주소"+ data.url);
                    }
                });
            }
        });

    </script>

    <!-- 구독 버튼 스크립트 -->
    <script>
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

                    for(var i = 0; i < data.length; i++){
                        if(data[i].gameCode == "${game.gameCode}"){
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
    </script>

</div>


</body>
</html>