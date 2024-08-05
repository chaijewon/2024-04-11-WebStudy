<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#box{
  width: 200px;
  height: 200px;
  border:1px solid black;
}
</style>
<script type="text/javascript">
window.onload=function(){
	let div=document.querySelector("#box")
	div.addEventListener('mousedown',()=>{
		div.style.background='pink'
	})
	div.addEventListener('mouseup',event=>{
		event.currentTarget.style.background='orange'
	})
	// $('#box').mouseup(function(e){})
	// $('#box').on('mouseup',function(){})
	// id명은 중복하면 안된다
	// => 선택한 태그 => $(this)
}
</script>
</head>
<body>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
</body>
</html>