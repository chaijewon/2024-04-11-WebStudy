<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%--
       JSP : Java Server Page => 서버에서 실행하는 자바 파일 => Back-End 
       Back-End : Java , JSP , Servlet , Spring , Oracle
                  =====                           ======= 
       Front-End : HTML , CSS , JavaScript
                                =========== Jquery(Ajax) , VueJS , ReactJS , NextJS
                   =========== 
       JSP 
         1. HTML + Java => 구분 
            자바 => <% %> , <%= %> , <%! %>
                   =====   ======  =======
                           | 표현식  | 선언문 
                   | 스크리트릿 
         2. 동작방식 => 정적페이지 / 동적페이지 
                     =======   =======
                               | 한개의 파일에 여러개의 데이터를 출력 (JSP,PHP,ASP,CGI)
                     | 한파일에 한개만 사용(HTML)
         3. 지시자 
            page : JSP파일에 대한 정보를 저장 
                   = 변환  contentType
                     브라우저에서 실행 => HTML / XML / JSON
                     => HTML => text/html 
                     => XML  => text/xml 
                     => JSON => text/plain : JavaScript Object Nontation
                                자바스크립트 객체 표현법 => VO 
                                => Ajax , VueJS , ReactJS , Redux , Next..
                                VO = {}
                                List = [{},{}...]
                   = 라이브러리 추가 : import 
                   = 한글 변환 : pageEncoding => UTF-8 (호환성)
                   = 에러페이지 : errorPage="파일명"
                     1) 404 : 파일이 없는 경우 
                              <a href="파일명">
                     2) 500 : 자바 컴파일 에러 
                     ====================================================
                     3) 403 : => 접근 거부 (Spring-Security) 
                     4) 412 : 한글변환 코드문제 (UTF-8 => UFT-8)
                     5) 400 : bad request => 전송값과 받는 변수 데이터형 틀리는 경우 
                   = JSP의 시작점 
            include : <%@ include file="" %> => <iframe>
                      JSP안에 다른 JSP를 포함해서 사용 
                      ==============================
                            로고 / 메뉴 / 검색 => 변경(X) => 모든 파일에 고정 
                      ==============================
                          |
                          |
                          |
                      ==============================
                            개인정보 방침  => 변경(X) => 모든 파일에 고정 
                      ==============================
            taglib : jsp의 단점을 보완 ====> JSTL ===> 자바 / HTML을 분리할때 사용 
                     자바 => 태그형으로 변경             ===================== MVC
                     <%                             Spring에서는 MVC만 사용한다 
                         if(조건문)                   1차 프로젝트 => MVC
                         {                          ReactJS ==> Redux
                     %>                             VueJS   ==> Vuex 
                               HTML
                     <%
                         }
                     %>
                     
                     => <c:if test="">
                         HTML
                        </c:if>
         4. 자바 코딩 
            <% %> : 스크립트릿 : 일반자바 (메소드안에 코딩) 
                              지역변수 선언 , 제어문 , 메소드 호출 
                    => 자바문법을 그대로 사용 
                    => 주석 // /* */
            <%= %> : 표현식 => 데이터를 화면에 출력 => out.write()
                                               out.println()
            <%! %> : 선언식 => 메소드 , 멤버변수 
         5. 지원라는 라이브러리 (내장객체)
            request / response / out / session / application
            ===================        =======   =========== 
            config / exception / page / pageContext
                                        =========== include / foreward : 흐름 
         6. Cookie VS Session
                      서버에 데이터 저장  
            브라우저에 데이터 저장 
         7. JSP 액션 태그 / 빈즈
                         ===== VO (데이터를 모아서 한번에 전송할 목적) 
            <jsp:include> 
            <jsp:useBean> 
         8. JSTL / EL => 제어문 / 메소드 호출 => 태그로 제작 => 교재 
            ======================================== MVC 
         9. MVC 
         10. Spring 
         
         
         JSP => 실행 => 톰캣 => 클래스화  
         a.jsp      => class a_jsp extends HttpServlet 
                       {
                          <%! %> 
                          public void _jspInit()
                          {
                          }
                          public void _jspDestory()
                          {
                            
                          }
                          // JSP에서 코딩하는 영역 => 메소드안 들어가는 내용 
                          public void _jspService()
                          {
                             <% %>
                             <%= %>
                          }                       
                       }
                       
                       => 컴파일 => a_jsp.class => 한줄씩 번역 
                                                 ========
                                                 | 메모리에 HTML만 출력 
                                                   ================
                                                    | 브라우저가 읽어서 출력 
        18page 
        1) 웹 동작 
           ===== 클라이언트 요청 (request) , 서버에서 요청에 대한 응답 (response) 
                 => C/S => Client / Server
           요청시 => URL주소를 이용한다 
                   브라우저 => 주소창 (유일하게 서버와 연결)
             http://localhost /JSPBasicProject_1/jsp/basic_1.jsp
             ================ ==================================
               서버주소(서버정보)      사용자가 요청 => URI => 파일을 보여달라 
               *** 생략하는 경우 => WelCome파일 등록 
               *** 파일의 확장자는 개발자가 설정할 수 있다
                   .naver / .daum / .do 
                   PathVariable => 가장 사용 (파일명 , 변수값)
                   /admin/1234 => Spring/Vue/React
              => 나머지 요청시에는 반드시 파일명을 추가한다 
                                   ==== 중복할 수가 없다 
           19page 
           
              ==============================================================
              브라우저 (클라이언트) : 크롬 / IE / FF
              웹서버 : Apache / IIS 
              웹애플리케이션 / WAS : tomcat => JSP를 자바로 변경 => 브라우저 HTML를 전송
                                 ====== 테스트용 웹서버 (50명만 접근이 가능)
              데이터베이스 : 오라클 
              ==============================================================
           
                             파일 요청                   .jsp/.java
           client(브라우저) ===============> Web Server  ===========> WAS(tomcat)
                                         Apache / IIS                  |
                                               |                  .jsp => java로 변경
                                           요청 파일 확인                  |
                                           => .html/.xml              컴파일
                                           => 자체 처리                . class
                                                                       |
                                                                      한줄씩 번역
                                                                       |
                                                                      HTML만 메모리에 저장 
                                                                        |
                =========================================================
                                     브라우저가 읽어 갈 수 있게 만든다
         파일 
         정적 페이지 : HTML / CSS => 데이터 갱신이 불가능 
         동적 페이지 : JSP/JavaScript => 데이터를 갱신 
         ======== CGI , ASP , PHP 
         ======== 22page 
                  ======
                  Servlet / JSP 
                  =======
                  Server+let => 서버에서 실행되는 가벼운 프로그램 => .java 
                      = 자바에서 처음 웹프로그램의 시작 
                      = 단점 : 변경시에 컴파일 => 실행 
                      = 바로 변경된 소스를 확인이 불가능 
                        ======================= 바로 확인 => JSP
                      = 장점 : 보안 뛰어나다 (.class 배포) 
                              보안과 관련된 웹파일 = 서블릿으로 제작 
                              => JSP는 주로 화면 출력     
                      = 스프링이 서블릿으로 만들어져 있다 
                        ========================
                        => JSP : 배포시에 소스를 그대로 전송 (소스가 노출된다)
                      = MVC
                        Model : 자바 (DAO,VO...)
                        View : JSP (화면에만 출력 기능)
                        Controller : Model과 View연결 => Servlet
                => JSP 파일 1
                   =========
                   Java + HTML 분리 : 연결 => 서블릿 
                   ===============
                   
                => 서블릿 => JSP가 컴파일되면 서블릿으로 변경 
                           ======================== 톰캣 
                => 서블릿 => JSP => 서블릿+JSP
                                  ==== 스프링 (라이브러리가 서블릿)
                                  
                   서블릿 . JSP => Java+HTML 
                   =======================
                    | HTML만 이용해서 처리 => ThymeLeaf 
                    
                   ThymeLeaf => JSP보다 속도가 느리다 (?)
                    |
                    서버  클라이언트를 나눠서 작업 
                    ======================
                    서버 => BOOT 
                          |
                    클라이언트는 Vue/React 
                    
          서블릿 => JSP => MVC => Spring => Spring-Boot 
                             |
                           파이썬 => 장고 => React 
         
          
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
     int a=10/2;
  %>
</body>
</html>