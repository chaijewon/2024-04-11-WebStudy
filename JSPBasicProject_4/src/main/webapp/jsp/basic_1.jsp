<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      JSP 동작 구조 
      1. jsp파일 요청 => URL 
         http://localhost/JSPBasicProject_4/jsp/basic_1.jsp
      2. 웹서버가 요청을 받는다 
         httpd 
         =>  해결 => .html , .xml 
         => .jsp , servlet => 처리를 못한다 
            ===============
            3. WAS(TomCat)로 전송
               ===========
               4. jsp파일을 => 자바로 변경 
                  a.jsp   => a_jsp.java
                  =====
                  코딩한 내용을 => public void _jspService()에 첨부  
                  out.write('\r');
			      out.write('\n');
			      out.write("\r\n");
			      out.write("<!DOCTYPE html>\r\n");
			      out.write("<html>\r\n");
			      out.write("<head>\r\n");
			      out.write("<meta charset=\"UTF-8\">\r\n");
			      out.write("<title>Insert title here</title>\r\n");
			      out.write("</head>\r\n");
			      out.write("<body>\r\n");
			      out.write("  <!-- 화면에 출력하는 태그 -->\r\n");
			      out.write("</body>\r\n");
			      out.write("</html>");
	    ==================================== 서블릿 
	    5. 컴파일 => a_jsp.class
	    6. 실행 (인터 프리터) 
	       out.write()에 있는 내용만 메모리 저장 
	    7. 저장된 메모리에 브라우저 읽어서 출력 
	     
                  
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <!-- 화면에 출력하는 태그 -->
</body>
</html>