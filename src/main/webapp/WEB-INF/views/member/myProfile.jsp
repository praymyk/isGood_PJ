<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-14
  Time: 오후 5:56
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

    /*컨텐츠 기본 스타일*/
    * {
        font-family: "Noto Sans KR", sans-serif;
        box-sizing: border-box;
        user-select: none;
    }

    .mypage-wrapper{
        width: 100%;
    }

    .subscripe, .profile-card-wrapper, .pass-acount-wrapper{
        display: flex;
        flex-direction: column;
        margin: 20px 20px 40px 20px;
    }

    /* 마이페이지 구분자 */
    .mypage-line{
        height: 2px;
        border: 0px;
        background-color: silver;
        margin: 60px 20px 60px 20px;
    }
    /*
        프로필 카드 스타일
    */
    .profile-card{
        display: flex;
        flex-direction: column;
        border: 1px solid gray;
        border-radius: 5px;
        background-color: rgb(32, 32, 32);
        padding: 15px;
    }

    .profile-img{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 150px;
        height: 150px;
        border-radius: 50%;
        background-color: white;
    }
    .profile-img img{
        border-radius: 50%;
        width: 140px;
        height: 140px;
    }

    .profile-card-title{
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 20px;
    }
    .profile-card-title button{
        border: 0px;
        border-radius: 3px;
        background-color: rgb(95, 95, 238);
        color: white;
        font-weight: bold;
        margin: auto 10px 10px auto;
        width: 130px;
        height: 40px;
    }

    .profile-card-content{
        border: 1px solid darkgray;
        border-radius: 5px;
        background-color: rgb(70, 70, 70);
        margin: 20px;
    }

    .profile-list{
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
    }

    .profile-card-name{
        font-size: 25px;
        font-weight: bolder;
        color: white;
        margin: auto auto 10px 20px;
    }
    .profile-list-title{
        font-size: 12px;
        font-weight: bold;
        color: rgba(245, 245, 245, 0.712);
    }
    .profile-list input{
        font-size: 15px;
        font-weight: bold;
        color: white;

        padding: 0px;
        margin-top: 5px;
        border: 0px;
        background-color: rgb(70, 70, 70);
    }
    .profile-list button{
        border: 0px;
        border-radius: 2px;
        background-color: dimgrey;
        color: white;
        font-weight: bold;
        margin: 5px;
        width: 60px;
        height: 35px;
    }

    /*
        구독 게임 관리 테이블 스타일
    */
    .subscribe{
        display: flex;
        flex-direction: column;
        margin: 20px;}

    .subscribe-table{
        display: flex;
        flex-direction: column;
    }

    .subscribe-save{
        border: 0px;
        border-radius: 3px;
        background-color: rgb(95, 95, 238);
        color: white;
        font-weight: bold;
        width: 60px;
        height: 40px;
        margin: 20px 0px 0px auto;
    }

    .table-header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 100%;
        padding: 10px;
        border-bottom: 2px solid gray;
    }

    .table-body{
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 100%;
        padding: 10px;
        border-bottom: 1px solid silver;
    }

    .table-num{
        display: flex;
        align-items: center;
        justify-content: center;
        height: 30px;
        width: 50px;
    }
    .table-title{
        margin-left: 10px;
        margin-right: auto;
        align-items: center;
    }
    .table-cancle{
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100px;
        height: 30px;
    }
    .cancle-btn{
        color: red;
    }

    /*
        비밀번호 변경 / 회원탈퇴 스타일
    */
    .pass-acount-wrapper > button{
        border: 0px;
        border-radius: 3px;
        color: white;
        font-weight: bold;
        width: 200px;
        height: 40px;
    }

    .change-pass-btn{
        background-color: rgb(95, 95, 238);
    }
    .account-stop-btn{
        background-color: rgb(241, 53, 53);
    }

</style>
<!-- jQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<body>

<div class="mypage-wrapper">

    <!-- 회원정보 수정 구간-->
    <div class="profile-card-wrapper">
        <h2>내 계정</h2>
        <div class="profile-card">
            <div class="profile-card-title">
                <div class="profile-img">
                    <img src="https://via.placeholder.com/150" alt="profile">
                </div>
                <div class="profile-card-name">${sessionScope.loginUser.nickName}</div>
                <button>프로필 사진 변경</button>
            </div>
            <div class="profile-card-content">
                <div class="profile-list">
                    <div>
                        <div class="profile-list-title">별명</div>
                        <input value="${sessionScope.loginUser.nickName}">
                    </div>
                    <button>수정</button>
                </div>
                <div class="profile-list">
                    <div>
                        <div class="profile-list-title">이메일</div>
                        <input value="${sessionScope.loginUser.email}">
                    </div>
                    <button>수정</button>
                </div>
                <div class="profile-list">
                    <div>
                        <div class="profile-list-title">연락처</div>
                        <input value="${sessionScope.loginUser.phone}">
                    </div>
                    <button>수정</button>
                </div>
                <div class="profile-list">
                    <div>
                        <div class="profile-list-title">성별</div>
                        <input value="${sessionScope.loginUser.gender}">
                    </div>
                    <button>수정</button>
                </div>
            </div>
        </div>
    </div>

    <div class="mypage-line"></div>

    <!-- 구독 게임 관리 구간-->

    <form>
        <div class="subscribe">
            <h3> 구독 게임 관리</h3>
            <h5> 구독 게임 순서를 변경하거나, 구독을 취소할 수 있습니다.</h5>
            <div class="subscribe-table">
                <div class="table-header">
                    <div class="table-num">공백</div>
                    <div class="table-title">게임 이름</div>
                    <div class="table-cancle">구독 취소</div>
                </div>
                <div class="table-body" draggable="true">
                    <div class="table-num">1</div>
                    <div class="table-title">냠냠</div>
                    <div class="table-cancle cancle-btn">취소</div>
                </div>
                <div class="table-body" draggable="true">
                    <div class="table-num">2</div>
                    <div class="table-title">쩝쩝</div>
                    <div class="table-cancle cancle-btn">취소</div>
                </div>
                <div class="table-body" draggable="true">
                    <div class="table-num">3</div>
                    <div class="table-title">늄늄</div>
                    <div class="table-cancle cancle-btn">취소</div>
                </div>
            </div>
            <button class="subscribe-save">저장</button>
        </div>
    </form>


    <div class="mypage-line"></div>

    <!-- 비밀번호 변경 / 회원탈퇴 구간-->
    <div class="pass-acount-wrapper">
        <h3> 비밀번호 관리 </h3>
        <button class="change-pass-btn">비밀번호 변경하기</button>

        <h3> 계정 관리 </h3>
        <p> 계정을 정지할 경우 복구 불가능 </p>
        <button class="account-stop-btn">계정 정지하기</button>
    </div>

</div>
<!-- 구독 게임 리스트 ajax 통신으로 불러오기 -->
<script>
    $(function(){
        console.log("구독 게임 리스트 불러오기 ajax 작동");
        console.log("${userNo}")
        console.log("${sessionScope.loginUser.userNo}");
        $.ajax({
            type: "GET",
            url: "subList.me/${sessionScope.loginUser.userNo}",
            dataType: "json",
            success: function(data){
                console.log(data);
            },
            error: function(){
                console.log("ajax 통신 실패");
            }
        })
    });
</script>

<!-- 구독 게임 순번 드래그&드랍 이벤트 스크립트 -->
<script>
    (()=>{
        /*
        * @param
        * @$: document.querySelectorAll -> jquery의 $와 같은 역할 수행
        * @containers: 구독 게임 리스트를 담고있는 div
        * @draggables: 구독 게임 리스트 div(이동 대상)
        */
        const $ = (select) => document.querySelectorAll(select);
        const containers = $(".subscribe-table");
        const draggables = $(".table-body");

        // 드래그 시작 이벤트
        // 드래그 상태 표기 > dragging 클래스 부여
        draggables.forEach(el => {
            el.addEventListener("dragstart", () => {
                el.classList.add("dragging");
            });
            el.addEventListener("dragend", () => {
                el.classList.remove("dragging");
            });
        });

        // dragging 요소를 다른 요소들 사이에 삽입하고자 할 때, 어느 위치에 삽입할지 결정 하는 용도 함수
        function getDragAfterElement(container, y){
            // 드래그 중인 요소를 제외한 나머지 요소 선택
            const draggableElements = [...container.querySelectorAll(".table-body:not(.dragging)")];

            // 드래그 중인 요소와 마우스 포인터의 y좌표를 비교하여 가장 가까운 요소를 반환
            return draggableElements.reduce((closest, child) => {
                // 요소의 위치와 크기 정보 변수
                const box = child.getBoundingClientRect();
                // 마우스 포인터와 중간 지점 사이의 거리 계산 값
                const offset = y - box.top - box.height / 2;

                // 가장 마우스 포인터에 가까운 아래 위치한 요소 반환
                if(offset < 0 && offset > closest.offset){
                    return {offset: offset, element: child};
                }else{
                    return closest;
                }
            }, {offset: Number.NEGATIVE_INFINITY}).element;
        };

        // 드롭 이벤트
        containers.forEach(container => {
            container.addEventListener("dragover", e => {
                e.preventDefault();
                // 마우스 포인터의 y좌표를 기준으로 드래그 중인 요소를 삽입할 위치
                const afterElement = getDragAfterElement(container, e.clientY);
                // 드래그중인 요소 선택
                const draggable = document.querySelector(".dragging");

                container.insertBefore(draggable, afterElement);

                // 변경된 순서를 DB에 업데이트
                updateNumber(container);
            });
        });

        // 변경된 순서 DB에 업데이트 함수
        function updateNumber(container){
            // 변경된 순서를 담을 배열
            const subscribeItems = [];

            container.querySelectorAll(".table-body").forEach((el, idx) => {

                subscribeItems.push({
                    subscribeNo: idx + 1,
                    title: el.querySelector(".table-title").textContent
                });

            });

        };

    })();

</script>
</body>
</html>
