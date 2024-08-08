<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row1{
  margin: 0px auto;
  width: 960px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
 // Jquery => 자바스크립트 : 태그읽기  => $
 let bCheck=true; // 전역변수 
 $(function(){ // window.onload => 모든 프로그램은 시작점이 있다 => 일반 프로그램 : main()
	 // Vue : mounted() , React : componentDidMount()
	 // jquery : 서버 연결 => Ajax
	 // Vue/React/Next/Nuxt => 서버연결 => axios
	 // 변수 => 지역변수 
	 $('#delBtn').click(function(){
		 if(bCheck===true)
		 {
			 $('#delBtn').text("취소")
			 $('#delTr').show() // display:none , display:''
			 bCheck=false
		 }
		 else
		 {
			 $('#delBtn').text("수정")
			 $('#delTr').hide() // display:none , display:''
			 bCheck=true
		 }
	 })
	 $('#deleteBtn').on('click',function(){
		 let no=$('.del_no').text()
		 let pwd=$('#del_pwd').val()
		 if(pwd.trim()==="")
		 {
			 $('#del_pwd').focus()
			 return
		 }
		 console.log("no="+no+",pwd="+pwd)
		 $.ajax({
			type:'post',
			url:'../databoard/delete.do',
			data:{"no":no,"pwd":pwd},
			success:function(result)
			{
				// yes/no
				if(result==='yes')
				{
					// 이동 
					location.href="../databoard/list.do"
				}
				else
				{
				   alert("비밀번호가 틀립니다!!")
				   $('#del_pwd').val("")
				   $('#del_pwd').focus()
				}
			},
			error:function(request,status,error)
			{
				console.log(error)
			}
		 })
	 })
 })
 </script>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">내용 보기</h2>
   <div class="row1 row">
     <table class="table">
       <tr>
        <th width=20% class="text-center">번호</th>
        <td width=30% class="text-center del_no">${vo.no }</td>
        <th width=20% class="text-center">작성일</th>
        <td width=30% class="text-center">${vo.dbday }</td>
       </tr>
       <tr>
        <th width=20% class="text-center">이름</th>
        <td width=30% class="text-center">${vo.name }</td>
        <th width=20% class="text-center">조회수</th>
        <td width=30% class="text-center">${vo.hit }</td>
       </tr>
       <tr>
        <th width=20% class="text-center">제목</th>
        <td colspan="3">${vo.subject }</td>
       </tr>
       <c:if test="${vo.filesize>0}">
         <tr>
          <th width=20% class="text-center">첨부파일</th>
          <td colspan="3"><a href="../databoard/download.do?fn=${vo.filename }">${vo.filename }</a>(${vo.filesize}Bytes)</td>
         </tr>
       </c:if>
       <%--
            데이터베이스 : Update,Delete
            업로드된 파일       
        --%>
       <tr>
         <td colspan="4" class="text-left" valign="top" height="200">
          <pre style="white-space: pre-wrap;background-color: white;border:none">${vo.content }</pre>
         </td>
       </tr>
       <tr>
         <td colspan="4" class="text-right">
          <a href="../databoard/update.do?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
          <span class="btn btn-xs btn-warning" id="delBtn">삭제</span>
          <a href="../databoard/list.do" class="btn btn-xs btn-info">목록</a>
         </td>
       </tr>
       <tr id="delTr" style="display: none">
         <td colspan="4" class="text-right inline">
         비밀번호:<input type=password id="del_pwd" class="input-sm" size=10>
         <input type=button value="삭제" class="btn-warning btn-sm" id="deleteBtn">
         </td>
       </tr>
     </table>
   </div>
  </main>
</div>
</body>
</html>