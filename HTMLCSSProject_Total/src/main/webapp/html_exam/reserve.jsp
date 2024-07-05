<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    // 출력할 데이터를 가지고 온다 
    FoodDAO dao=FoodDAO.newInstance();
    List<FoodVO> list=dao.foodListData();
    // JSP = Java  , .net = #C   장고 = python(*****)
    /*
        자바와 동일 => 문법 
    */
%>
<!-- 
       ====================== container
        =================== row
       
        =================== row 
        
        ===================
       ======================
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  /* 브라우저에 출력되는 HTML 영역*/
  margin-top: 50px; /* margin: top right bottom left */
}
.row{
   margin:0px auto; /* 가운데 정렬 */
   width:960px;
}
tr.infos:hover{
  cursor: pointer;
  background-color: #FC6;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.infos').click(function(){
		let name=$(this).attr("data-name");
		let poster=$(this).attr("data-poster");
		
		$('#name').text("업체명:"+name);
		$('#poster').attr("src",poster);
	})
})
</script>
</head>
<body>
   <div class="container">
    <div class="row">
     <table class="table">
       <tr>
         <td>
           <table class="table">
            <caption><h3>맛집정보</h3></caption> 
            <%--
                51page => 1. 내용을 가지고 있는 태그 <태그>내용</태그>
                          2. 내용이 없는 태그 <태그 속성=""> = <hr>,<img>,<br>,<input>
             --%>
             <tr>
              <th></th>
              <th>업체명</th>
             </tr>
             <%
                for(FoodVO vo:list)
                {
             %>
                    <tr class="infos" data-name="<%=vo.getName() %>" 
                        data-poster="<%=vo.getPoster()%>">
                        <%--
                           System.out.println(vo.getPoster())
                         --%>
                      <td class="text-center">
                       <img src="<%=vo.getPoster() %>" width="30" height="30">
                      </td>
                      <td><%= vo.getName() %></td>
                    </tr>
             <%
                }
             %>
           </table>
         </td>
         <td>
           <table class="table">
             <caption><h3>예약정보</h3></caption>
             <tr>
              <td>
                <span>
                 <img src="" style="width: 200px;height: 200px" id="poster">
                </span>
              </td>
             </tr>
             <tr>
              <td>
                <span id="name"></span>
              </td>
             </tr>
           </table>
         </td>
       </tr>
     </table>
    </div>
   </div>
</body>
</html>