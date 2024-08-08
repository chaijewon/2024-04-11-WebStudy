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
.cotainer{
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 900px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('button').click(function(){
		let names=[]
		$('input[name="names"]').each(function(){
		    if($(this).is(":checked"))
		    {
		       console.log($(this).val())
		       names.push($(this).val())	
		    }
		})
		console.log(names)
		$.ajax({
			type:'post',
			url:'../emp/find_ok.do',
			data:{"names":names},
			success:function(result)
			{
				$('#print').html(result)
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
   <div class="container">
    <div class="row">
    <!-- <form method=post action="find_ok.do"> -->
     <table class="table">
      <tr>
       <td class="text-center">
         <c:forEach var="name" items="${list }">
           <input type=checkbox name="names" value="${name }">${name }
         </c:forEach>
       </td>
      </tr>
      <tr>
       <td class="text-center">
         <button class="btn-sm btn-success">검색</button>
       </td>
      </tr>
     </table>
     <!-- </form> -->
    </div>
    <div style="height: 10px"></div>
    <div class="row" id="print">
      
    </div>
   </div>
</body>
</html>