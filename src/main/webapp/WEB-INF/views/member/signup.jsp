<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-13
  Time: 오전 11:08
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
        font-weight: bold;
        box-sizing: border-box;
        user-select: none;
    }


    .enroll-body {
        display: flex;
        justify-content: center;
        height: 100vh;
        overflow: auto;
        margin: 0px;
        background: rgb(46, 46, 46);
    }

    .signup-wrapper {
        margin: auto;
        padding: 20px;
        background: rgb(46, 46, 46);
    }
    .signup-wrapper h2 {
        color: white;
        margin-bottom: 30px;
    }

    .signup-input, .signup-select{
        border: 0px solid gray;
        appearance:none;
        padding: 10px;
        background-color: rgb(46, 46, 46);
    }
    .signup-input{
        color: white;
    }

    .signup-input-unlock{
        color: white;
    }

    .icon{
        display: flex;
        width: 50px;
        height: 50px;
        align-items: center;
        justify-content: center;
        border: 0px;
        color: grey;

    }
    #emailAT{
        background-color: rgb(107, 107, 107);
        border: 0px;
        color: white;
        font-size: 18px;
        font-weight: bolder;
        position: absolute;
        top: 50%;
        right: 30%;
        transform: translateY(-50%);
    }

    .signup-input, .signup-select:focus{
        outline: none;
    }


    /*회원가입 폼 스타일 (상단 박스)*/
    .signup-box1, .signup-box2 {
        display: table;
        margin-top: 0px;
        margin-bottom: 10px;
        width: 100%;
        border-collapse: collapse;
    }


    .form-group {
        display: table-row;
        width: 100% ;
        height: 50px;
        border: 1px solid gray;
        position: relative;
    }
    #submit-btn-wrap{
        border: 0px;
    }


    .form-group>div{
        float: left;
    }

    /*회원가입 폼 스타일 (상단 박스 - 이메일)*/
    .form-group input{
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        height: 48px;
    }

    #email_id{
        width: 230px;
    }

    .signup-email-domain, .domain-input{
        width: 125px;
        height: 48px;
        position: absolute;
        top: 50%;
        right: 0px;
        transform: translateY(-50%);
    }
    .domain-input{
        background-color: rgb(46, 46, 46);
        color: white;
        border: 0px;
    }
    .domain-input::placeholder{
        color: white;
    }
    .domain-input:focus{
        outline: none;
        color: white;
    }

    /*비밀번호 입력란 스타일 */
    #password, #password-chk{
        width: 355px;
    }

    .pass-visibility-on{
        position: absolute;
        top: 50%;
        right: 0px;
        transform: translateY(-50%);
    }

    .pass-visibility-on:hover{
        cursor: pointer;
    }


    /*회원가입 폼 스타일 (하단 박스)*/

    .signup-box2 input{
        width: 405px;
    }

    /* 성별 입력 */
    .gender-btns{
        height: 50px;
    }

    .gender-btns ul{
        display: flex;
        list-style-type: none;
        flex-direction: row;
        padding: 10px;
    }

    .gender-btns li{
        display: flex;
        justify-content: center;
        align-items: center ;
    }

    .gender-btns input{
        display: none;
    }

    .gender{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 146px;
        height: 30px;
        cursor: pointer;
        color: gray;
    }

    .gender-clicked{
        border: 2px solid white;
        border-radius: 5px;
        color: white;
    }

    /*가입 버튼 */
    .submit-btn button{
        width: 460px;
        height: 50px;
        font-weight: bolder;
        font-size: 20px;
        color: white;
        border: none;
        background-color: rgb(153, 44, 144);
    }
    .submit-btn button:hover{
        cursor: pointer;
        background-color: rgb(238, 49, 221);
    }

    /*테두리 */
    .focus{
        border: 2px solid green;
    }
    .unfocus{
        border: 1px solid gray;
    }
    .wrong{
        border: 2px solid red;
    }

    /*유효성 검사 내용 노출 */
    .check, .check2{
        display: none;
        font-weight: bolder;
        color: red;
        margin-top: 10px;
        margin-bottom: 10px;
        font-size: 12px;
    }


</style>
<body class="enroll-body">
<!--회원가입 페이지 뒤로가기 막기-->
<script type="text/javascript">
    history.pushState(null, null, location.href);
    window.onpopstate = function (event) {
        history.forward();
    };
</script>

<!--구글 아이콘 불러오기-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<!-- jQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<div class="signup-wrapper">

    <h2>회원가입</h2>

    <form action="enroll.me" method="post">
        <!-- 회원가입 폼 스타일 (상단 박스) -->
        <div class="signup-box1">

            <!-- 이메일 입력 -->
            <div class="form-group" id="email-wrap">
                <div class="icon">
                        <span class="material-symbols-outlined">
                            person
                        </span>
                </div>
                <div class="signup-inputBox">
                    <input class="signup-input" type="text" id="email_id" name="email_id" placeholder="이메일" required>
                    <input type="hidden" name="email" id="real_email">
                </div>
                <div class="input-group-text icon" id="emailAT">
                    @
                </div>
                <div class="signup-email-domain">
                    <select class="signup-select" name="email_domain" id="email_domain" disabled>
                        <option value="">선택해주세요</option>
                        <option value="naver.com">naver.com</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="daum.net">daum.net</option>
                        <option value="nate.com">nate.com</option>
                        <option value="hotmail.com">hotmail.com</option>
                        <option value="yahoo.com">yahoo.com</option>
                        <option value="">직접입력</option>
                    </select>
                    <input class="domain-input" type="text" id="self_input" placeholder="클릭 후 직접입력">
                </div>
            </div>

            <!-- 이메일 + 도메인 합치기 -->
            <script>
                // 도메인 직접입력 inputBox 숨겨놓기
                $(function(){
                    $(".domain-input").hide();
                });

                //1. 이메일 입력 후 도메인 선택 잠금 해제
                $("#email_id").on("keyup", function(){
                    if($("#email_id").val() != ""){
                        $("#email_domain").removeAttr("disabled");
                        $("#email_domain").addClass("signup-input-unlock");
                    }
                });

                // 2. 이메일, 도메인 변경시 실시간으로 합치기 (직접 입력 상황 제외)
                //    페이지 뒤로가기로 도메인이 직접 입력 상태라면 emain_id 입력 시 실시간으로 합치기
                $("#email_id").on("change", ()=>{
                    if($("#email_domain").val() != ""){
                        $("#real_email").val($("#email_id").val() +"@"+ $("#email_domain").val());
                    } else {
                        $("#real_email").val($("#email_id").val() +"@"+ $(".domain-input").val());
                    }
                });
                $("#email_domain").on("change", ()=>{
                    if($("#email_domain").val() != ""){
                        $("#real_email").val($("#email_id").val() +"@"+ $("#email_domain").val());
                    }
                });

                // 3. 직접 입력 선택 > inputBox 보이기
                $("#email_domain").on("change", function(){
                    if($("#email_domain").val() == ""){
                        $(".domain-input").show();
                        $("#email_domain").hide();
                    }
                });

                // 4. 직접 입력 후 합치기
                $(".domain-input").on("change", function(){
                    $("#real_email").val($("#email_id").val() +"@"+ $(".domain-input").val());
                });


            </script>

            <!-- 비밀번호 입력 -->
            <div class="form-group" id="pass-wrap">
                <div class="icon">
                        <span class="material-symbols-outlined">
                            lock
                        </span>
                </div>
                <div class="signup-inputBox">
                    <input class="signup-input" type="password" id="password" name="userPwd" placeholder="비밀번호" required>
                </div>
                <div class="pass-visibility-on icon">
                        <span class="material-symbols-outlined">
                            visibility_off
                        </span>
                </div>
            </div>
            <!-- 비밀번호 확인 -->
            <div class="form-group" id="passchk-wrap">
                <div class="icon">
                        <span class="material-symbols-outlined">
                            enhanced_encryption
                        </span>
                </div>
                <div class="signup-inputBox">
                    <input class="signup-input" type="password" id="password-chk" name="password-chk" placeholder="비밀번호 확인" required>
                </div>
                <div class="pass-visibility-on icon">
                        <span class="material-symbols-outlined">
                            visibility_off
                        </span>
                </div>
            </div>
        </div>

        <!-- 유효성 검사 내용 노출 1-->
        <div class="check">
            유효성 검사1
        </div>

        <!-- 회원가입 폼 스타일 (하단 박스) -->
        <div class="signup-box2">
            <!-- 닉네임 입력 -->
            <div class="form-group" id="name-wrap">
                <div class="icon">
                            <span class="material-symbols-outlined">
                                person
                            </span>
                </div>
                <div class="signup-inputBox">
                    <input class="signup-input" type="text" id="username" name="nickName" placeholder="닉네임" required>
                </div>
            </div>
            <!-- 생년월일 입력 -->
            <div class="form-group" id="birth-wrap">
                <div class="icon">
                            <span class="material-symbols-outlined">
                                calendar_month
                            </span>
                </div>
                <div class="signup-inputBox">
                    <input class="signup-input" type="text" id="birthday" name="birthday" maxlength="8" placeholder="생녈월일 8자리" required>
                </div>
            </div>
            <!-- 성별 입력 -->
            <div class="form-group">
                <div class="gender-btns">
                    <ul>
                        <li>
                            <label class="gender" for="gender1">남</label>
                            <input type="radio" id="gender1" name="gender" value="M">
                        </li>
                        <li>
                            <label class="gender" for="gender2">여</label>
                            <input type="radio" id="gender2" name="gender" value="F">
                        </li>
                        <li>
                            <label class="gender gender-clicked" for="gender3">선택안함</label>
                            <input type="radio" id="gender3" name="gender" value="O" checked="checked">
                        </li>
                    </ul>
                </div>
            </div>
            <!-- 휴대번호 입력 -->
            <div class="form-group" id="phone-wrap">
                <div class="icon">
                            <span class="material-symbols-outlined">
                                smartphone
                            </span>
                </div>
                <div class="signup-inputBox">
                    <input class="signup-input" type="text" id="phone" name="phone" placeholder="휴대전화번호 ( -제외 )" required>
                </div>
            </div>
            <div class="check2">
                유효성 검사 2
            </div>

            <input type="hidden" value="" name="snsProfile">

            <div class="form-group submit-btn" id="submit-btn-wrap">
                <button id="submitbtn" type="button">가입하기</button>
            </div>
        </div>
    </form>
</div>
<!-- SNS 로그인으로 회원가입 안내를 받은 경우 -->
<script>
    $(function(){
        // sns 계정으로 로그인하고 회원가입 안내를 받은 경우
        if("${msg}" != "" && "${msg}" != null){
            window.alert("${msg}");

            // SessionScope에 저장딘 SNS 계정 정보를 json 형태로 변환
            var snsProfile = {
                snsId: "${sessionScope.snsProfile.snsId}",
                snsType: "${sessionScope.snsProfile.type}",
                snsEmail: "${sessionScope.snsProfile.email}",
                snsNickName: "${sessionScope.snsProfile.nickName}"
            };

            var jsonString = JSON.stringify(snsProfile);
            console.log("회원가입에 필요한 정보 : " + jsonString);
            document.getElementsByName("snsProfile")[0].value = jsonString;
        }




    });


</script>

<script>
    /*
    *  유효성 검사 변수 선언
    * @ email_id : 이메일 아이디 입력란
    * @ pass : 비밀번호 입력란
    * @ passChk : 비밀번호 확인 입력란
    * @ username : 이름 입력란
    * @ birthday : 생년월일 입력란
    * @ phone : 휴대전화번호 입력란
    * @ check : 유효성 검사 결과 메시지 출력란
    * @ check2 : 유효성 검사 결과 메시지 출력란2
    */
    const email_id = $("#email-wrap");
    const pass = $("#pass-wrap");
    const passChk = $("#passchk-wrap");
    const username = $("#name-wrap");
    const birthday = $("#birth-wrap");
    const phone = $("#phone-wrap");

    const check = $(".check");
    const check2 = $(".check2");

    // 유효성 검사 통과 여부 확인용 변수
    // @ checkResult : (이메일, 이메일 중복, 비밀번호, 비밀번호확인, 닉네임, 생년월일, 휴대전화번호) 유효성 검사 통과 여부
    //   0 : 미통과, 1 : 통과
    var checkResult = [0, 0, 0, 0, 0, 0, 0];

    // checkMsg 에 유효성 검사 최종 결과 메시지 저장용 변수
    var emailChkMsg = "";
    var emailChkMsg2 = "";
    var passMsg = "";
    var passChkMsg = "";
    var nameMsg = "";
    var birthMsg = "";
    var phoneMsg = "";

    $(function(){

        // 태두리 focus 이벤트 부여 (div가 강조)-->
        // 1. input 선택 시 태두리 색 변경
        $("input").focus(function(){
            $(this).parent().parent().removeClass("unfocus");
            $(this).parent().parent().addClass("focus");
        });

        // 2. input 선택 해제 시 태두리 색 복구
        $("input").blur(function(){
            $(this).parent().parent().removeClass("focus");
            $(this).parent().parent().addClass("unfocus");
        });

        // 2. 유효성 검사 결과에 따라 태두리 색 변경

        // 유효성 검사  - 이메일
        // DB 연동 후 AJAX를 이용해 중복체크를 진행할 수 있음

        // @param real_email : 이메일 아이디 + 도메인 + 직접 입력 도메인 합친 값
        var real_email;
        // 이메일 입력 시 실시간으로 아이디 + 도메인 주소 합치기
        $("#email_id").on("keyup", function(){
            // 도메인 주소 선택, 입력 시 이메일 아이디 + 도메인 주소 값 합치기
            real_email = $("#email_id").val() +"@"+ $("#email_domain").val() + $("#self_input").val();
            checkEmail(real_email);

            // 도메인 주소를 포함한 상태에서 중복 검사 진행
            if($("#email_domain").val() != "" || $("#self_input").val() != ""){
                duplicateCheck(real_email);
            }

        });
        // 도메인 선택 시 실시간으로 합치기
        $("#email_domain").on("change", function(){
            real_email = $("#email_id").val() +"@"+ $("#email_domain").val() + $("#self_input").val();
            duplicateCheck(real_email);
            checkEmail(real_email);
        });
        // 도메인 직접 입력 실시간으로 합치기
        $("#self_input").on("keyup", function(){
            real_email = $("#email_id").val() +"@"+ $("#email_domain").val() + $("#self_input").val();
            duplicateCheck(real_email);
            checkEmail(real_email);
        });

        // 유효성 검사 - 이메일(이메일 형식)
        function checkEmail(email){
            var regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if(regExp.test(email)){
                idResult = true;
                email_id.removeClass("wrong").addClass("focus");
                emailChkMsg = "";
                check.css("display", "none");
                checkResult[0] =  1;
            } else {
                idResult = false;
                email_id.removeClass("focus").addClass("wrong");
                emailChkMsg = "<li>올바른 이메일 형식으로 입력해주세요.</li>";
                checkResult[0] = 0;
            }
            updateCheckMsg();
            submitCheck()
        }
        // 중검 검사 - 이메일 중복 여부
        function duplicateCheck(){
            // AJAX로 중복체크 진행
            $.ajax({
                url: "emailCheck.me",
                type: "post",
                data: {email: real_email},
                dataType: "json",
                success: function(data){
                    if(data.check == "NNNN"){
                        emailChkMsg2 = "<li>이미 사용중인 이메일입니다.</li>";
                        email_id.removeClass("focus").addClass("wrong");
                        checkResult[1] = 0;
                        updateCheckMsg();
                        submitCheck();
                        // 이메일 중복이 되지 않은 상태일 뿐만 아니라 이메일 형식도 맞을 때
                    } else if(data.check == "YYYY" && idResult == true){
                        email_id.removeClass("wrong").addClass("focus");
                        emailChkMsg2 = "";
                        checkResult[1] = 1;
                        updateCheckMsg();
                        submitCheck();
                    }
                },
                error: function(){
                    console.log("중복 체크 실패");
                }
            });

        }

        // 유효성 검사  - 비밀번호(8~16자리, 영문, 숫자, 특수문자)
        $("#password").on("keyup", function(){
            var pw = $("#password").val();
            var regExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;

            if(regExp.test(pw)){
                passResult = true;
                pass.removeClass("wrong").addClass("focus");
                passMsg = "";
                check.css("display", "none");
                checkResult[2] = 1;
            } else if(pw == ""){
                passResult = false;
                pass.removeClass("focus").addClass("wrong");
                passMsg = "<li>반드시 입력해주세요.</li>";
                checkResult[2] = 0;
            } else {
                passResult = false;
                pass.removeClass("focus").addClass("wrong");
                passMsg = "<li>비밀번호는 영문, 숫자, 특수문자 8~16 자리로 작성해주세요.</li>";
                checkResult[2] = 0;
            }
            updateCheckMsg();
            submitCheck();
        });

        // 유효성 검사  - 비밀번호 확인
        $("#password-chk").on("keyup", function(){
            var pw = $("#password").val();
            var pwChk = $("#password-chk").val();

            if(pw == pwChk){
                passChkResult = true;
                passChk.removeClass("wrong").addClass("focus");
                passChkMsg = "";
                check.css("display", "none");
                checkResult[3] = 1;
            } else if(pwChk == ""){
                passChkResult = false;
                passChk.removeClass("focus").addClass("wrong");
                passChkMsg = "<li>비밀번호 확인은 반드시 입력해야합니다.</li>";
                checkResult[3] = 0;
            } else {
                passChkResult = false;
                passChk.removeClass("focus").addClass("wrong");
                passChkMsg = "<li>비밀번호가 일치하지 않습니다.</li>";
                checkResult[3] = 0;
            }
            updateCheckMsg();
            submitCheck();
        });

        // 유효성 검사  - 이름(한글, 영문, 2~10자리)
        $("#username").on("keyup", function(){
            var name = $("#username").val();
            var regExp = /^[가-힣a-zA-Z0-9]{2,10}$/;

            if(regExp.test(name)){
                passResult = true;
                username.removeClass("wrong").addClass("focus");
                nameMsg = "";
                check2.css("display", "none");
                checkResult[4] = 1;
            } else if(name == ""){
                passResult = false;
                username.removeClass("focus").addClass("wrong");
                nameMsg = "<li>닉네임은 반드시 입력해야 합니다.</li>";
                checkResult[4] = 0;
            } else {
                passResult = false;
                username.removeClass("focus").addClass("wrong");
                nameMsg = "<li>닉네임은 한글, 영문 2~10자리로 입력해야 합니다.</li>";
                checkResult[4] = 0;
            }
            updateCheckMsg2();
            submitCheck();
        });

        // 유효성 검사  - 생년월일(8자리, 숫자)
        $(function(){
            $("#birthday").on("keyup", function(){
                var birth = $("#birthday").val();
                var regExp = /^[0-9]{8}$/;

                if(regExp.test(birth)){
                    passResult = true;
                    birthday.removeClass("wrong").addClass("focus");
                    birthMsg = "";
                    check2.css("display", "none");
                    checkResult[5] = 1;
                } else if(birth == ""){
                    passResult = false;
                    birthday.removeClass("focus").addClass("wrong");
                    birthMsg = "<li>생년월일은 반드시 입력해야 합니다.</li>";
                    checkResult[5] = 0;
                } else {
                    passResult = false;
                    birthday.removeClass("focus").addClass("wrong");
                    birthMsg = "<li>생년월일은 8자리의 숫자로 입력해야 합니다.</li>";
                    checkResult[5] = 0;
                }
                updateCheckMsg2();
                submitCheck();
            });
        });

        // 유효성 검사  - 휴대전화번호(숫자, 10~11자리)
        $("#phone").on("keyup", function(){
            var phoneNum = $("#phone").val();
            var regExp = /^[0-9]{10,11}$/;

            if(regExp.test(phoneNum)){
                passResult = true;
                phone.removeClass("wrong").addClass("focus");
                phoneMsg = "";
                check2.css("display", "none");
                checkResult[6] = 1;
            } else if(phoneNum == ""){
                passResult = false;
                phone.removeClass("focus").addClass("wrong");
                phoneMsg = "<li>휴대전화번호는 반드시 입력해야 합니다.</li>";
                checkResult[6] = 0;
            } else {
                passResult = false;
                phone.removeClass("focus").addClass("wrong");
                phoneMsg = "<li>휴대전화번호는 10~11자리의 숫자로 입력해야 합니다.</li>";
                checkResult[6] = 0;
            }
            updateCheckMsg2();
            submitCheck();
        });


        // 유효성 검사 결과(이메일/비밀번호) 메시지 출력용 함수
        function updateCheckMsg(){
            var checkMsg = emailChkMsg + emailChkMsg2 + passMsg + passChkMsg;
            if (checkMsg != "") {
                check.html("<ul>" + checkMsg + "</ul>").css("display", "block");
            } else {
                check.css("display", "none").empty();
            }
        };

        // 유효성 검사 결과(이름/생년월일/휴대전화번호) 메시지 출력용 함수
        function updateCheckMsg2(){
            var checkMsg2 = nameMsg + birthMsg + phoneMsg;
            if (checkMsg2 != "") {
                check2.html("<ul>" + checkMsg2 + "</ul>").css("display", "block");
            } else {
                check2.css("display", "none").empty();
            }
        };

        // 가입버튼 클릭 이벤트
        var result = 1;

        function submitCheck(){
            result = 1;
            for(var i = 0; i < checkResult.length; i++){
                result *= checkResult[i];
            };

            if(result != 1){
                $("#submitbtn").attr("type", "button");
            } else {
                $("#submitbtn").attr("type", "submit");
            }
        };

        // submitCheck() 함수 안에 onclick 이벤트 추가하면 해당 함수를 호출할 때마다 이벤트 핸들러가 중첩되는 문제 발생
        $("#submitbtn").on("click", function(){
            if(result != 1) {
                alert("입력란을 확인해주세요.");
            }
        });
    });

</script>

<!--성별 선택 시 버튼 색 변경 -->
<script>

    $(function(){

        $(".gender-btns label").click(function(){
            $(".gender-btns label").removeClass("gender-clicked");
            $(this).addClass("gender-clicked");
        });

    });

</script>

<!-- 비밀번호 내용 보이기 토글 스크립트 -->
<script>

    $(function(){

        $(".pass-visibility-on").click(function(){
            if($(this).children().text() != "visibility"){
                $(this).children().text("visibility");
                $(this).siblings().children('input').attr("type", "text");

            } else {
                $(this).children().text("visibility_off");
                $(this).siblings().children('input').attr("type", "password");
            }
        });
    });

</script>



</body>
</html>