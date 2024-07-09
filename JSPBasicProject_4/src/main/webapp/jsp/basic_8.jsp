<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String color="";
   request.setCharacterEncoding("UTF-8");
   String sel=request.getParameter("color");
   if(sel==null)
	   sel="흰색";
   if(sel.equals("파란색"))
   {
	   color="blue";
   }
   else if(sel.equals("노란색"))
   {
	   color="yellow";
   }
   else if(sel.equals("빨간색"))
   {
	   color="red";
   }
   else if(sel.equals("검정색"))
   {
	   color="black";
   }
   else if(sel.equals("녹색"))
   {
	   color="green";
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="<%=color%>">
 <form method="post" action="basic_8.jsp">
  <select name="color">
    <option>파란색</option>
    <option>노랑색</option>
    <option>빨간색</option>
    <option>검정색</option>
    <option>녹색</option>
  </select>
  <button>전송</button>
  </form>
</body>
</html>