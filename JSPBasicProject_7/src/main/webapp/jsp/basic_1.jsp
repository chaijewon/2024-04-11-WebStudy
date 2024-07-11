<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      JSP 
       1) 지시자 
          page 지시자 
            => 속성 
               contentType : 브라우저에 어떤 형식의 파일인지 알려준다 (HTML/XML/JSON)
               import : 라이브러리 추가 
               errorPage : 에러가 난 경우에 출력하는 파일 => web.xml
               buffer : 화면 크기가 클 경우 => 8kb => 16kb
          include 지시자 
            => file : 첨부할 파일명 지정 => 정적 (소스를 통합해서 컴파일)
               <jsP:include> : 가장 많이 사용되는 액션태그  
       2) HTML / Java 구분 
          <% %> : HTML안에 자바 코딩 (일반 메소드에서 사용되는 자바코드)
                                  지역변수 , 메소드 호출 , 연산자 , 제어문 
                  => Model 클래스 : 메소드 처리 
          <%= %> : out.print() => 화면 출력 
                  => ${}
                 ***  %는 사라지다 
                 2000년 ~ 2003년 => 이후에는 MVC로 변경 
                                  ===============
                                   스트럿츠 / 스프링 
                                   ======  =====
                                   자바전용   모든언어 
                 *** 흐름 : 화면 변경 (이동) , 데이터 전송 
                     숫자로 전송 mode
                     한개 출력 => 상세보기 : primary key 
                   
                 사용자 이벤트 (JSP) ==== 자바 ==== 오라클 연동 
                            JSP       자바
       3) 내장 객체 
             ***request : 사용자 요청 
                => 사용자가 보내준 값을 받는다 : getParameter() , getParameterValues()
                => 사용자가 요청 URI => getRequestURI()
                => 한글 변환 : setCharacterEncoding()
                => 추가 : setAttribute() , getAttribute()
                => 사용자 IP : getRemoteAddress()
                => 스프링:가급적이면 request를 사용하지 않는다 
                        ============================ 스프링 자체에서 값을 받아준다 
                         => Cookie
             ***response : 응답 정보 
                  => sendRedirect() : 화면 이동 
                  => setHeader() : JSON전송 , 다운로드 
                  => addCookie() : cookie전송 
             ***session : 서버에 일부 데이터를 저장(사이트 있는 동안)
                          === 
                => 장바구니 / 로그인 => 정보 저장
                => setAttribute() : 저장 
                => getAttribute() : 저장값 읽기
                => invalidate() : 저장 내용을 지운다 => 로그아웃 
                => removeAttribute() : 한개
                =========================================
             application : 서버관리 
                  => log() : 로그파일 
                  => getRealPath() : 실제 저장 경로 => 이미지 저장 
             out : 출력버퍼 관리 
                  => print(),write() => 화면 출력 <%= %> , ${}
             pageContext 
                  => include() => <jsp:include>
                  => forward() => <jsp:forward>
             => page(this) , config , exception
                                      => try~catch
                             => web.xml
       4) 액션 태그 
             ***<jsp:include> 
             <jsp:useBean> 
             <jsp:setProperty>
       5) 라이브러리 
              => JSP : 데이터 출력 
                 => DAO(SQL) / CSS
              10장/11장 => 데이터베이스 (DBCP) => DAO/VO
              12~19장 : 생략 
              ======= 
       6) EL / JSTL  (taglib 지시자) : 20장 
       7) MVC 
       ====================== Spring 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>