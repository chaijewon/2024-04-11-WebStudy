<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
    // _ok => 데이터를 받아서 데이터베이스 처리 => 이동 (화면 출력하는 JSP가 아니다)
    // 1. 사용자가 보내준 값을 받는다 
    String id=request.getParameter("id");
    String pwd=request.getParameter("pwd");
    
    // 2. 오라클로부터 결과값을 받는다 
    DiaryService dao=DiaryService.newInstance();
    MemberVO vo=dao.isLogin(id, pwd);
    
    if(vo.getMsg().equals("NOID"))
    {
%>
       <script type="text/javascript">
         alert("아이디가 존재하지 않습니다!!");
         history.back();// 이전 화면으로 이동 
       </script> 
<%
    }
    else if(vo.getMsg().equals("NOPWD"))
    {
%>
        <script type="text/javascript">
         alert("비밀번혹 틀립니다!!");
         history.back();// 이전 화면으로 이동 
       </script> 
<%  	
    }
    else
    {
    	// 데이터를 서버에 저장 => 지속적으로 관리 => 30분
        // 세션에 저장 
        session.setAttribute("id", vo.getId());
    	session.setAttribute("name", vo.getName());
    	session.setAttribute("sex", vo.getSex());
    	
    	// diary로 이동 
    	response.sendRedirect("middle.jsp");
    }
%>
