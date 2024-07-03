<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    /*
       1. 사용자로부터 요청값을 받는다 => request.getParameter()
                                =======================
                                  사용자가 보내준 값을 받을때
       2. 요청값을 이용해서 오라클 연결 
       3. 해당 데이터를 읽어 온다 
       4. 읽어온 데이터를 HTML를 이용해서 브라우저 출력 
       ### JSP파일은 컴파일이 되면 자동으로 자바는 사라지고 HTML를 메모리 출력이 된다 
    */
    // 오라클에서 데이터를 읽기 오기 
    FoodDAO dao=FoodDAO.newInstance(); // 싱글턴 
    // 사용자가 페이지 요청 
    // 요청한 페이지 받기 
    String strPage=request.getParameter("page");
    if(strPage==null)
    	// 처음 실행 화면 
    	strPage="1";
    int curpage=Integer.parseInt(strPage);
    
    List<FoodVO> list=dao.foodListData(curpage);
    
    for(FoodVO vo:list)
    {
    	String img=vo.getPoster();
    	img=img.replace("https", "http");
    	vo.setPoster(img);
    }
    int totalpage=dao.foodTotalPage(); // 총페이지 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top:50px;
}
.row{
   margin:0px auto;
   width:960px;
}
.a{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>
</head>
<body>
  <div class="container">
   <div class="row">
   <%
       for(FoodVO vo:list)
       {
   %>
	     <div class="col-sm-3">
		     <div class="thumbnail"><!-- 이미지 카드: 이미지+제목 -->
		      <a href="#">
		       <img src="<%= vo.getPoster() %>" style="width: 100%" class="img-rounded"><!-- height는 자동 설정 -->
		       <p class="a"><%=vo.getName() %></p>
		      </a>
		     </div>
		  </div>
	<%
       }
	%>
   </div>
   <div style="height: 10px"></div>
   <div class="row">
     <div class="text-center"><!-- 가운데 정렬 : text-left , text-right , text-center  -->
       <ul class="pagination">
         <li><a href="#">&lt;</a></li>
         <li class="active"><a href="food_list.jsp?page=1">1</a></li>
         <li><a href="food_list.jsp?page=2">2</a></li>
         <li><a href="food_list.jsp?page=3">3</a></li>
         <li><a href="food_list.jsp?page=4">4</a></li>
         <li><a href="food_list.jsp?page=5">5</a></li>
         <li><a href="food_list.jsp?page=6">6</a></li>
         <li><a href="food_list.jsp?page=7">7</a></li>
         <li><a href="food_list.jsp?page=8">8</a></li>
         <li><a href="food_list.jsp?page=9">9</a></li>
         <li><a href="food_list.jsp?page=10">10</a></li>
         <li><a href="#">&gt;</a></li>
       </ul>
     </div>
   </div>
  </div>
</body>
</html>