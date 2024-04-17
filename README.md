<h1 align="center"> IsGood 프로젝트</h1>

<h2>IsGood은 신작게임을 소개하고 게임별 게시판을 이용할수 있는 커뮤니티 사이트입니다. </h3> 
<pre>
사용자는 신작게임과 핫딜 게임을 확인하고 자신이 선호하는 게임 게시판에서 자유롭게 커뮤니티 활동을 할수 있는 사이트 입니다. <br> 
선호 게임을 구독하면 즐겨찾기와 같이 해당 게임 체널에 쉽게 접근할수 있고 마이페이지에서 간단하게 관리 가능합니다. <br> 
메인 페이지에서 신작 게임과 핫딜 게임 정보를 쉽게 확인하며 게임들의 게시판에 쉽게 접근 가능합니다. <br> 
게임의 게시글 수에 따라 단독 게시판을 생성하거나 종합 게시판으로서 관리 됩니다.
</pre>

<h2> 🟪 구현 화면/코드 : 구독게임 리스트 관리 기능</h3>
<img src="https://github.com/praymyk/isGood_PJ/blob/main/readmeIMG/updateList.gif">
<pre>
 html의 공백 열에는 해당 div의 Y축 값이 들어있습니다.
 Y축 값으로 각 구독 게임의 리스트의 순번을 정합니다. ( 공백 열의 숫자가 DB에 저장돼있던 각 리스트의 Y축 값 )
 각 구독게임 리스트에는 input type="hidden" 값에 화면상의 Y축 값이 저장되며, DB 컬럼의 상의 Y축 값을 업데이트 하는 방법으로 리스트 순서 기능을 구현했습니다.
  
 ajax를 통해 배열 형태로 jsp의 구독게임리스트 정보를 controller로 넘길필요가 있었지만 ajax data : 이름으로 @RequestParam 이 배열을 인식하지 못해 구현이 어려움이 있었습니다.
 배열은 @RequestParam(value="userNo[]") String[] userNo 형태로 value 값을 작성해야 인지함을 알수 있는 구현 파트였습니다.
</pre>

<img src="https://github.com/praymyk/isGood_PJ/blob/main/readmeIMG/changeListCode.png"> 

<br/>

<br/>
