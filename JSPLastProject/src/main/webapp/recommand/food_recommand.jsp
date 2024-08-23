<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.rebtn').click(function(){
		let no=$(this).attr("data-no");
		$.ajax({
			type:'post',
			url:'../recommand/sub_menu.do',
			data:{"no":no},
			success:function(result)
			{
				$('#menu').html(result);
			}
		})
	})
})
</script>
</head>
<body>
<div class="wrapper row3 reserve_row">
  <main class="container clear">
    <h2 class="sectiontitle">맛집 추천</h2>
    <table class="table">
      <tr>
        <td class="text-center">
          <span class="btn btn-lg btn-success rebtn" data-no="1">상황</span> 
          <span class="btn btn-lg btn-info rebtn" data-no="2">감성</span> 
          <span class="btn btn-lg btn-primary rebtn" data-no="3">스타일</span> 
          <span class="btn btn-lg btn-danger rebtn" data-no="4">날씨/계절</span> 
        </td>
      </tr>
    </table>
    <div id="menu">
     
    </div>
    <div id="recommand">
      
    </div>
  </main>
</div>
</body>
</html>