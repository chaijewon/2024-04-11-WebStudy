<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*,java.text.*"%>
<%
     request.setCharacterEncoding("UTF-8");
     String fd=request.getParameter("fd");
     String ss=request.getParameter("ss");
     BoardDAO dao=BoardDAO.newInstance();
     int count=dao.boardFindCount(fd, ss);
     List<BoardVO> list=dao.boardFindData(fd, ss);
     
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 30px;
}
.row{
   margin: 0px auto;
   width: 800px;
}
h3{
   text-align: center;
}
</style>
</head>
<body>
<div class="container">
    <h3>검색 결과</h3>
    <div class="row">
    <%
       if(count==0)
       {
    %>
           <table class="table">
		      <tr>
		       <td>
		         <h3 style="color:red">검색 결과가 없습니다</h3>
		       </td>
		      </tr>
		     </table>
    <%
       }
       else
       {
    %>
     <table class="table table-striped">
       <tr>
         <th width="10%" class="text-center">번호</th>
         <th width="45%" class="text-center">제목</th>
         <th width="15%" class="text-center">이름</th>
         <th width="20%" class="text-center">작성일</th>
         <th width="10%" class="text-center">조회수</th>
       </tr>
       <%
           for(BoardVO vo:list)
           {
       %>
               <tr>
		         <td width="10%" class="text-center"><%=count-- %></td>
		         <td width="45%">
		            <a href="board_detail.jsp?no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
		            <%
		                String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		                if(today.equals(vo.getDb_day()))
		                {
		            %>
		                    &nbsp;<sup style="color:red">new</sup>
		            <%
		                }
		            %>
		         </td>
		         <td width="15%" class="text-center"><%=vo.getName() %></td>
		         <td width="20%" class="text-center"><%=vo.getDb_day() %></td>
		         <td width="10%" class="text-center"><%=vo.getHit() %></td>
		       </tr>
       <%
           }
       %>
     </table>
     <%} %>
    </div>
   </div>
</body>
</html>