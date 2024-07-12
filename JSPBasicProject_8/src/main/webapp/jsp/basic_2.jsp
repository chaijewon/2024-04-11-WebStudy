<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
   application : 서버 관리 => ServletContext 
   =============
     1. 서버 관련 정보  
        1) 서버 이름 : tomcat => getServerInfo()
        2) 버전 확인 : getMajorVersion , getMinorVersion()
           ======= servlet 버전을 읽어온다 
     2. log정보 
        => log파일 => 서버의 정보를 저장 
        => log() => 보안이 필요한 경우  => web.xml
     3. 자원 관리 => resource (파일...)
        => getRealPath() *** : 실제 경로명  
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h3>서버명:<%= application.getServerInfo() %></h3>
  <h3>서블릿버젼:<%=application.getMajorVersion() %></h3>
  <h3>서블릿버젼:<%=application.getMinorVersion() %></h3>
  <h3>서블릿버젼:<%=application.getMajorVersion()+"."+ application.getMinorVersion()%></h3>
  <h3>log읽기</h3>
  <%
      String driver=application.getInitParameter("driver");
      // web.xml에 있는 데이터 읽기                   <param-name>
      String url=application.getInitParameter("url");
      String username=application.getInitParameter("username");
      String password=application.getInitParameter("password");
      
      application.log("driver:"+driver);
      application.log("url:"+url);
      application.log("username:"+username);
      application.log("password:"+password);
  %>
  <h3>실제 프로젝트 저장위치:<%= application.getRealPath("/") %></h3>
</body>
</html>