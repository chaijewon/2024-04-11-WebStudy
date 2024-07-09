<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      <%
           주석 
           // 
           /* */  <c:for> => 태그형
       %>
      <%!
           자바 문법을 적용 
           // /* */
           문장이 종료 => ;
       %> 
       out.print("Hello");
                 | 
              <%="Hello" %> => ;을 사용하지 않는다  ${}
      
 --%>
<%!
    // 선언문 => 노출이 된다 => 거의 사용하지 않고 => 클래스를 직접 만들어서 사용 
    public int add(int a,int b)
    {
	   return a+b;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
       int res=add(10,20);
       out.print(res);// 서블릿 => 복잡한 자바코딩이 있는 경우
   %>
   <%= res %> <%-- JSP --%>
   <%--
       <% 중복할 수 없다 
       
       예)
         
          if()
          {
             out.print()
         
         <%
          }
          else if()
          {
         %>
         <%
          }
          
          else if()
          {
          %>
          <%
          }
          else
          {
          %>
          <%
          }
          >
          
    --%>
</body>
</html>