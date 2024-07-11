<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     151page 
       액션태그 
          동작/액션이 일어나는 시점에 페이지와 페이지 사이에 제어
       <jsp:include> : JSP안에 다른 JSP를 포함하는 경우 => 화면분할 (조립식) 
            => 속성 : page="" => 포함하는 파일 지정 
            => xml 형식으로 만들어져 있다 
               ======= 문법이 복잡하다 
               => 속성이나 태그명은 대소문자를 구분한다 
               => 속성에 값을 첨부할때는 반드시 ""
               => 반드시 여는태그와 닫는 태그가 동일 , 독립태그 사용
            => 동적으로 변경 (파일마다 따로 컴파일후에 HTML만 첨부)
       <jsp:useBean> : 객체 메모리 할당 
            => id="" : 객체명 
            => class="" : 클래스명 
            => scope="" : 언제 메모리 할당해서 사용하는 범위 
               ========
                 page / session / application / request
                                                => 사용자의 요청값이 있는 경우
                                  => 객체를 모든 JSP에서 사용하고 싶은 경우 
                        => 브라우저에 유지하고 있는 동안  
                 ==== default 
                 => JSP안에서 사용 
                 
            MemberVO vo=new MemberVO();
            <jsp:useBean id="vo" class="com.sist.bean.MemberVO">
                         ======= 객체명  ======================== 클래스 선언
                         
        <jsp:setProperty> => setter에 값을 설정 
            => name : 객체명 => useBean에서 설정된 id명과 일치 
            => property : 변수명 
               property="name" => setName()
               property="sex"  => setSex()
               property="*" => 모든 setter
               
        <jsp:forward page="파일명">
          서버에서 화면을 이동할 경우에 사용 
          = sendRedirect() : URL을 변경 => request가 초기화
          = forward():URL변경이 없다 => request를 가지고 있다 => MVC
               
 --%>
<%
    request.setCharacterEncoding("UTF-8");
    String mode=request.getParameter("mode");
    String jsp="";
    if(mode==null)
    	mode="0";
    int index=Integer.parseInt(mode);
    
    switch(index)
    {
    case 0:
    	jsp="home.jsp";
    	break;
    case 1:
    	jsp="join.jsp";
    	break;
    case 2:
    	jsp="idfind.jsp";
    	break;
    case 3:
    	jsp="passwordfind.jsp";
    	break;
    case 4:
    	jsp="send.jsp";
    	break;
    case 5:
    	jsp="mypage.jsp";
    	break;
    case 6:
    	jsp="recv.jsp";
    }
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.row{
  margin:0px auto;
  width:960px;
}
</style>
</head>
<body>
   <jsp:include page="nav.jsp"></jsp:include>
   <div class="container">
    <jsp:include page="<%=jsp %>"></jsp:include>
   </div>
</body>
</html>