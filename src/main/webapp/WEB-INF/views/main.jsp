<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-12
  Time: 오후 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
</head>
<style>
    *{box-sizing: border-box;}

    div{border: 1px solid red;}

    .main-wrapper{
        display: flex;
        flex-direction: column;
        width: 100%;
        padding: 20px;
        margin: 10px;
    }

    /* 스와이퍼 제목 */
    .main-sub-title{
        margin-top:15px;
        margin-bottom: 5px;
    }

    /* 광고 스타일 */
    .banner-img{
        width: 90%;
        height: 100px;
        background-color: gray;
        margin: auto;
    }

    /* 구독 게임 바로가기 스타일 */
    .subscribe-bar{
        display: flex;
        align-items: center;
        padding: 10px;
        margin-top: 10px;
        margin-bottom: 10px;
    }
    .subscribe-bar i{
        margin-left: 10px;
        border: 1px solid gray;
        border-radius: 3px;
        padding: 2px 5px 2px 5px;
        font-size: 10px;
    }
    .subscribe-list-bar{
        display: flex;
        flex-wrap: wrap;
        width: 100%;
        justify-content: flex-start;
    }

    .subscribe-icon{
        background-color: azure;
        border: 1px solid gray;
        width: 80px;
        height: 100px;
        margin: 5px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .subscribe-icon img{
        width: 60px;
        height: 60px;
        border-radius: 50px;
    }
    .subscribe-icon p{
        text-align: center;
        font-size: 12px;
    }


    /* 게시글 스타일 */
    .board-list{
        border: 1px solid blue;
        min-height: 450px;
    }


    .board-row{
        display: flex;
        margin-top : 20px;
        margin-bottom: 20px;
        justify-content: space-between;
        position: relative;
    }

    .board-list-wrapper{
        display: flex;
        flex-direction: column;
        margin: 20px;
        width: 100%;
    }

    .board-list{
        display: flex;
        flex-direction: column;
    }

    .board-line {
        position: absolute;
        background-color: gray;
        height: 100%;
        top:50%; left:50%; transform:translate(-50%, -50%);
    }

    /* 스와이퍼 스타일 */
    .swiper {
        width: 100%;
        height: 400px;
    }

    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .swiper-slide img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

</style>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<body>

<div class="main-wrapper">
    <!--광고-->
    <img class="banner-img">
    <!-- 구독 게임 바로가기 -->
    <div class="subscribe-bar">
            <span>
                구독 게임 바로가기
            </span>
        <i>
            설정
        </i>
    </div>
    <div class="subscribe-list-bar">
        <a class="subscribe-icon">
            <img>
            <p>게임1</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임2</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
        <a class="subscribe-icon">
            <img>
            <p>게임3</p>
        </a>
    </div>

    <!-- 이미지 스와이퍼 & 링크 -->
    <div class="main-sub-title">
        <span>신작 게임</span>
    </div>
    <div class="swiper-wrapper">
        <!-- Slider main container -->
        <div class="swiper mySwiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide">Slide 1</div>
                <div class="swiper-slide">Slide 2</div>
                <div class="swiper-slide">Slide 3</div>
                <div class="swiper-slide">Slide 4</div>
                <div class="swiper-slide">Slide 5</div>
                <div class="swiper-slide">Slide 6</div>
                <div class="swiper-slide">Slide 7</div>
                <div class="swiper-slide">Slide 8</div>
                <div class="swiper-slide">Slide 9</div>
            </div>
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
            <div class="swiper-pagination"></div>
        </div>

    </div>

    <div class="main-sub-title">
        <span>특별 할인</span>
    </div>
    <div class="swiper-wrapper">

        <div class="swiper mySwiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide">Slide 1</div>
                <div class="swiper-slide">Slide 2</div>
                <div class="swiper-slide">Slide 3</div>
                <div class="swiper-slide">Slide 4</div>
                <div class="swiper-slide">Slide 5</div>
                <div class="swiper-slide">Slide 6</div>
                <div class="swiper-slide">Slide 7</div>
                <div class="swiper-slide">Slide 8</div>
                <div class="swiper-slide">Slide 9</div>
            </div>
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
            <div class="swiper-pagination"></div>
        </div>

    </div>


    <!--게시판-->
    <div class="board-wrapper">
        <div class="board-row">

            <div class="board-list-wrapper">
                <a><span>xxx 게시판 타이틀</span></a>
                <div class="board-list">
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                </div>
            </div>

            <div class="board-line"></div>

            <div class="board-list-wrapper">
                <a><span>yyy 게시판 타이틀</span></a>
                <div class="board-list">
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                </div>
            </div>
        </div>

        <div class="board-row">

            <div class="board-list-wrapper">
                <a><span>xxx 게시판 타이틀</span></a>
                <div class="board-list">
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                </div>
            </div>

            <div class="board-line"></div>

            <div class="board-list-wrapper">
                <a><span>yyy 게시판 타이틀</span></a>
                <div class="board-list">
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                    <a>
                        최신 게시물
                        <span><time>x분 전</time></span>
                    </a>
                </div>
            </div>
        </div>

    </div>

</div>
<!-- Swiper JS -->
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script>
    var swiper = new Swiper(".mySwiper", {
        cssMode: true,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        pagination: {
            el: ".swiper-pagination",
        },
        mousewheel: true,
        keyboard: true,
    });
</script>


</body>
</html>