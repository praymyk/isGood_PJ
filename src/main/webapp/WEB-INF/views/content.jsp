<%--
  Created by IntelliJ IDEA.
  User: Myks
  Date: 2024-04-12
  Time: 오후 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- jQuery 라이브러리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <!-- 지도 API -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	d4d696f76b0d25013b27803ab58c4217&libraries=services"></script>

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
        flex-direction: column;
        height: 65%;
        width: 300px;
        background-color: rgb(31, 29, 31);
        border: 1px solid gray;
        border-radius: 5px;
        margin-bottom: 10px;
    }
    .mapSearchDiv{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 50px;
    }
    .mapSearch{
        width: 80%;
        height: 100%;
    }
    .mapSearchBtn{
        width: 20%;
        height: 100%;
        background-color: rgb(31, 29, 31);
        color: white;
        border: 1px solid gray;
        border-radius: 5px;
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
            <div id="map" style="width:100%;height:350px;"></div>
            <div class="mapSearchDiv">
                <input class="mapSearch" type="text" value=""><button class="mapSearchBtn">검색</button>
            </div>


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

<script>
    // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
    var infowindow = new kakao.maps.InfoWindow({zIndex:1});

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 장소 검색 객체를 생성합니다
    var ps = new kakao.maps.services.Places();

    // 검색 장소 키워드 변수를 선언합니다
    var keyword = "";

    // 마커 생서용 변수(초기화 위해 전역변수 선언)
    var markers = [];

    // 검색 버튼 클릭시 지도 검색 시작
    $('.mapSearchBtn').click(function(){
        searchPlace();
    });

    // 키워드로 장소를 검색합니다
    function searchPlace(){
        keyword = document.getElementsByClassName('mapSearch')[0].value;
        ps.keywordSearch(keyword, placesSearchCB);
    }

    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    function placesSearchCB (data, status, pagination) {

        if (status === kakao.maps.services.Status.OK) {

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            var bounds = new kakao.maps.LatLngBounds();

            // 이전 마커가 존재할 경우 삭제 호출
            if(markers.length != 0){
                removeMarker();
            }

            for (var i=0; i<data.length; i++) {
                displayMarker(data[i]);
                bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
            }

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
        }
    }

    // 지도에 마커를 표시하는 함수입니다
    function displayMarker(place) {

        // 검색된 장소 위치를 마커 요소에 저장
        var marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(place.y, place.x),

        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);

        // 생성된 마커를 배열에 추가합니다
        markers.push(marker);

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function() {
            // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
            infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
            infowindow.open(map, marker);
        });
    }

    // 마커 삭제용 함수
    function removeMarker(){
        for(var i=0; i<markers.length; i++){
            markers[i].setMap(null);
        }
    }
</script>

</body>
</html>
