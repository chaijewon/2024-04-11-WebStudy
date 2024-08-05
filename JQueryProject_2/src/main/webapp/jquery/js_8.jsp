<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      브라우저 내장 객체 = 계층형 구조 (트리) 
      =============
            window : 브라우저 
              |
        =============================
        |         |         |       |
      document   history  location  frame
      :화면출력 담당 이동기록    화면이동    화면 구성 
         |
     ===============
     |   |    |    |
    form img  a   ....
     |
    =====================
    |       |       |
   input select textarea  ==> 계층 (window생략) => document.form.input...
   
   window : close() : 닫기 , open() : 열기 , scrollbar .. 브라우저 관련 
            ============================
            setTimeout() , setInterval() : 계속 수행 => 날씨, 실시간 인기순위 ...
             => 실행 후 종료 => 한번만 실행 
             => 회원가입 = 이미지 (회원가입 축하) => main.jsp로 이동 
   document: <body>안에 있는 태그를 제어 
     => createElement() , write , querySelector , appendChild()..
   history : 브라우저 기록 정보 
     => back() , go(-1) ... forward()
   location : 주소 정보 => GET
     => href : 이동할 위치 지정 => sendRedirect()  
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=>{
	let btn=document.querySelector("button")
	btn.addEventListener('click',()=>{
		window.open('js_7.jsp','winname','width=500,height=700,scrollbars=yes')
	})
}
</script>
</head>
<body>
  <button>클릭</button>
</body>
</html>