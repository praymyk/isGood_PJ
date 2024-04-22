<h1 align="center"> IsGood 프로젝트(진행중)</h1>

<h2>IsGood은 신작게임 소개 및 게시판 이용 커뮤니티 사이트입니다. </h3> 
<pre>
사용자는 신작게임과 핫딜 게임을 확인하고 자신이 선호하는 게임 게시판에서 자유롭게 커뮤니티 활동을 할수 있는 사이트 입니다. <br> 
선호 게임을 구독하면 즐겨찾기와 같이 해당 게임 체널에 쉽게 접근할수 있고 마이페이지에서 간단하게 관리 가능합니다. <br> 
메인 페이지에서 신작 게임과 핫딜 게임 정보를 쉽게 확인하며 게임들의 게시판에 쉽게 접근 가능합니다. <br> 
게임의 게시글 수에 따라 단독 게시판을 생성하거나 종합 게시판으로서 관리 됩니다.
</pre>
<br>

<h2> 🟪 프로젝트의 주요 목표</h2>
<p>
 
- 회원가입 + 게시판에 글쓰기(댓글) + 파일첨부 + 홈페이지 서비스 하기
 
- SNS 로그인 기능과 게시판 댓글 기능을 REST API 방식을 사용해 구현하고 익숙해지기
 
- 웹 서비스를 염두하여 프로젝트 외부 경로에 파일 업로드 및 서버에 불러오기 기능 구현
</p>
<br>


<h2> 🟪 구현 화면/코드 : 구독게임 리스트 관리 기능</h3>
<img src="https://github.com/praymyk/isGood_PJ/blob/main/readmeIMG/updateList.gif">
<img src="https://github.com/praymyk/isGood_PJ/blob/main/readmeIMG/changeListCode.png"> 
<pre>
✔️ 기능 설명 
- 자주 방문하는 게시판을 구독하고 드래그앤 드롭 방식으로 화면에 노출되는 순서를 수정합니다.
<br>
✔️ point
- 순서가 변경될때 마다 뒤바뀐 요소의 순번을 update 해야함.
- 이벤트가 발생한 요소마다 순번을 파악하고 update를 시하면 로직이 복잡하다고 판단 됨
- 구독 순서는 구독 리스트 Y축 값을 오름차순 값으로 파악하고 순서 변경 뒤 변경된 값을 update!  
   (html의 공백 열에 해당 div의 update 전 Y축 값을 표시하고 있습니다.)
<br>
✔️ 문제가 발생했던 내용
드래그앤 드롭 이벤트 방식은 검색을 통해 쉽게 파악할수 있었습니다.
그러나 첫 인덱스(맨 처음 로드 시 순서)를 이동시킨 뒤 인덱스로 변경하는 로직을 생각하는데 어려움이 있었고
화면 로드시의 Y축 값은 고정된다는 생각이 들어 해당 값을 기준으로 변경값을 update하도록 개발할수 있었습니다.</pre>
<br>



<h2> 🟪 구현 화면/코드 : 회원 가입 기능</h3>
<img src="https://github.com/praymyk/isGood_PJ/blob/main/readmeIMG/signUp.gif">
<pre>
✔️ 기능 설명 
- 회원가입 입력 내용에 따른 Div 박스 강조 표시
<br>
✔️ point
- input 태그를 div로 감싸고 유효성 겁사에따라 div를 강조 표시합니다.
- email, 닉네임은 입력시 마다 중복여부를 확인할수 있도록 ajax 비동기 통신
- 모든 유효성 겁사에 통과해야 최종적인 회원 가입이 가능하도록 구현
<br>
✔️ 문제가 발생했던 내용
프론트앤드 구현이 가장 어려웠던 경험이였습니다.
table이 아닌 div로 회원가입 폼을 설계했고 div요소가 겹치는 부분의 border가 중복되는 현상 처리가 어려웠습니다.
display: table 속성과 border-collapse: collapse 문제를 해결할수 있었지만 
display:flex 와 혼용해서 사용하니 매우 복잡한 CSS 결과물이 완성되버렸고
회원가입과 같이 고정된 내용의 경우 굳이 div를 사용하는 것이 아닌 table 태그를 이용하는것이 좋았겠다고 생각함.
<br>
또한, 시각효과 이벤트를 classname을 부여하고 삭제하는 방식으로 구현해봤는데 Css 설계가 복잡해지만
생각보다 이벤트 관리는 편하다는 생각이 들었고 다음에는 class 이름을 알아보기 쉽게 작성하도록 노력할 예정입니다. </pre>

