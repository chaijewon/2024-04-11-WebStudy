<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
     request.setCharacterEncoding("UTF-8");
     String sel=request.getParameter("sel");
     String fd=request.getParameter("fd");
     
     GoodsDAO dao=GoodsDAO.newInstance();
     List<GoodsVO> list=dao.goodsFindData(sel, fd);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
	.aaa{
		margin-top:50px;
		width:650px;
	}
	.row{
	   margin: 0px auto;
	}
	.a{
	    white-space:nowrap;
	    overflow: hidden;
	    text-overflow: ellipsis;
	}
</style>
</head>
<body>
       <div class="row aaa">
        <h3 class="text-center">검색결과:<%=list.size() %></h3>
      <%-- 이미지 출력 --%>
	     <%
	        for(GoodsVO vo:list)
	        {
	     %>
		      <div class="col-sm-4">
		       <a href="#">
		        <div class="thumbnail">
		         <img src="<%=vo.getPoster() %>" style="width: 260px;height: 150px" class="img-rounded">
		         <p class="a"><%=vo.getName() %></p>
		        </div>
		       </a>
		      </div>
	    <%
	        }
	    %>
     </div>
</body>
</html>