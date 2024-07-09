<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 선언되는 변수 = 지역변수 => 초기화를 해서 사용한다 
    int a=10;
    int b=20;
    // import 없이 사용하는 방식 => 123page
    java.util.Date date=new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
      out.print(a+b);
   %>
   <%= a+b %>
   <br>
   <%= sdf.format(date) %>
   <%--
      155page => <%= 변수 | 메소드 %> => 일반문자열 / 정수 / 연산자:삼항연산자  
    --%>
    <br>
    <%= a %>&nbsp;<%=a*b %>
    <br>
    <%="Hello JSP"/*출력문*/ %>
    <br>
    <%=10 %>
    
    <%--
          주석 : JSP 전체 주석 
          <%
             // /* */ => 자바 주석 
          %>
     --%>
</body>
</html>