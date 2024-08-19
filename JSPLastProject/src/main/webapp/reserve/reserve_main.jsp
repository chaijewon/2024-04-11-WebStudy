<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'../reserve/date_info.do',
		success:function(result)
		{
			$('#rdate').html(result)
		},
		error:function(request,status,error)
		{
			console.log(error)
		}
	})
})
</script>
<body>
<div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">맛집 예약</h2>
   <table class="table">
    <tr>
      <td width=30% class="danger" height="400">
       <table class="table">
        <caption><h4 class="text-center">맛집 정보</h4></caption>
       </table>
      </td>
      <td width=30% class="info" height="400">
       <table class="table">
        <caption><h4 class="text-center">예약일 정보</h4></caption>
        <tbody>
         <tr>
          <td id="rdate"></td>
         </tr>
        </tbody>
       </table>
      </td>
      <td width=20% rowspan="2" class="success" height="500">
       <table class="table">
        <caption><h4 class="text-center">예약정보</h4></caption>
       </table>
      </td>
    </tr>
    <tr>
      <td width=30% class="default" height=100>
        <table class="table">
        <caption><h4 class="text-center">시간정보</h4></caption>
       </table>
      </td>
      <td width=30% height=100>
       <table class="table">
        <caption><h4 class="text-center">인원정보</h4></caption>
       </table>
      </td>
    </tr>
   </table>
  </main>
</div>
</body>
</html>