<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      1. JSP의 실행과정 
                   실행요청 (톰캣)   자바로 변경 
         a.jsp  ============== a_jsp.java
                           public class a_jsp extends HttpJspBase
                           {
                               <%! %>
                               public void _jspInit(){}
                               public void _jspDestory(){}
                               public void _jspService(HttpServletRequest request,
                               HttpServletResponse response)
                               {
                                    PageContext pageContext;
								    HttpSession session = null;
								    ServletContext application;
								    ServletConfig config;
								    JspWriter out = null;
								    Object page = this;
                                  ========================= JSP의 소스코딩
                                  <% %> 
                                  out.write(HTML) 
                                  <%= %> => out.print()
                                  
                               }
                           }
                               컴파일 
                           =================>a_jsp.class
                                                 | 한줄씩 읽어서 메모리에 저장 
                                               out.write()에 있는 내용만 저장 
                                               =========== HTML태그 
                                                 | 저장된 HTML을 브라우저에서 읽어서 출력
                    jsp파일 요청 => URL  => request
           브라우저 ===============> 톰캣 
                 <================
                    요청 처리후에 응답 HTML => response
                    ======= 자바코딩 
                    
         JSP 사용 
           지시자 : page => JSP에 대한 정보를 저장 
                    1) 변환 => 브라우저에 알림 
                       contentType="text/html , text/xml , text/plain"
                                    =========              ========== 
                                    VO => {}  , List => [{},{}...] 
                    2) 외부 라이브러리 => import 
                       ========== java.lang,javax.http.servlet외의 모든 패키지는 import
                       => 사용자 정의도 포함 
                    3) errorPage : 에러가 있는 경우에 이동하는 파일 지정  
                  taglib => <% %>를 제거하기 위해 제작된 태그 
                            => for / if => 제어문을 태그로 제작
                            => 자바는 사용하지 말고 태그형 문법을 사용한다
            자바표현법 : <% 일반 자바 코딩 : 제어문 , 연산자 , 메소드 호출 , 지역변수 선언 %> 
                      <%= %> => 데이터 출력 out.print()
                     ====== =====
                      JSTL   EL => ${}
                      => 태그형 (제어문) <c:forEach> , <c:if> ...
            내장객체 : 
                 HttpServletRequest => request 
                   = 브라우저 정보 
                   http://localhost /JSPBasicTotalProject/total/total_1.jsp
                   ======================================================= URL
                   ==== protocol    ====================================== URI
                        =========== ===================== ContextPath
                          서버 IP
                     1) getRequestURI()
                     2) getContextPath()
                     3) getRequestURL()
                     4) getRemoteAddr() : 사용자의 IP를 얻어온다 
                   = 사용자 요청정보 
                     1) getParameter() : 사용자 보낸준 단일데이터값 
                     2) getParameterValues() : String[]
                                  => 한번에 여러개 값을 받을 경우 : checkbox
                     3) setCharacterEncoding() : 디코딩 
                                  => POST방식일때 한글 변환 
                   = 데이터 추가 정보 
                     ============================================
                     1) setAttribute() : request에 데이터를 추가 
                     2) getAttribute() : 추가된 데이터를 읽어 올때 사용 
                     ===============================${a} => 일반 변수 (X)
                        => <%= request.getAttribute("a")%>
                   = 기타 
                     getSession() : 생성된 session을 얻어오는 경우 
                     getCookies() : 생성된 쿠키의 데이터를 얻어 온다 
                 HttpServletResponse => response => 응답 
                   = 헤더정보 : 다운로드 => 실제 데이터 전송전에 먼저 보내는 값 
                     setHeader()
                   = 이동정보 
                     sendRedirect() => GET방식만 사용이 가능 
                   = 쿠키 저장 
                     addCookie()
                 HttpSession => session 
                   = 저장 => setAttribute()
                   = 읽기 => getAttribute()
                   = 일부 삭제 => removeAttribute()
                   = 전체 삭제 => invalidate() => 로그아웃 
                   = 세션 구분 => getId() => webchat
                   = 기간 설정 => setMaxInactiveInterval() : 기본 : 30분
                                                     === 초단위 (1800) 
                 ServletContext => application 
                   = 이미지 올릴때 (상품등록 , 갤러리 게시판) 
                     => 바로 확인 : getRealPath()
                 PageContext => pageContext : 페이지 흐름 
                                request 제어 => 공유 
                                ==================
                                include() / forward()
                                => ***<jsp:include>
                                => <jsp:forward> : MVC구조에서 사용 
                 기타 : Cookie => 사용자 브라우저에 저장 (문자열만 저장이 가능)
                       1. 생성 
                          Cookie cookie=new Cookie(키 , 값)
                       2. 기간 
                          cookie.setMaxAge(초)
                           => setMaxAge(0) : 삭제
                       3. 저장위치 
                          cookie.setPath("/")
                       4. 브라우저 전송 
                          response.addCookie(cookie) 
                       5. 쿠키읽기 
                          Cookie[] cookies=request.getCookies()
                       6. 키 읽기
                          cookie.getName()
                       7. 데이터 읽기  
                          cookie.getValue()
                     => 보안 취약 => 최근 방문 / 자동 로그인 (스프링 => remember-me)
                       
            JSP액션태그 
               => <jsp:include> , <jsp:useBean> : 객체 생성
                  ============= JSP안에 다른 JSP포함  
            데이터베이스 연동 
              => Oracle / MySQL,MariaDB
              JDBC => DBCP => ORM(MyBatis/JPA: DataSet) 
                      ====        |SQL    | 메소드로 표현 
                                            findByNo(int no)
                                            SELECT * FROM table_name 
                                            WHERE no=1
              1. 드라이버 등록 
              2. 오라클 연결 
              3. SQL문장 생성 ==> SQL문장만 작성 => 자동처리 
              4. 오라클로 SQL문장 전송 
              5. SQL문장 실행 요청 => 결과값
              6. 오라클 연결 해제 
            =====================================================
            DBCP : 데이터베이스 연결에 소모되는 시간을 줄인다 
                   Connection의 객체 생성 갯수를 조절 
                   => 미리 연결된 Connection을 POOL안에 저장
                      ======= 톰캣 => 톰캣에서 읽어가는 파일에 등록 => server.xml , web.xml 
                   => POOL안에서 Connection의 주소를 얻어온다 
                   => Connection 사용 
                   => POOL안으로 반환 
                   => 웹사이트 개발의 기본 
                   => Mybatis / JPA는 기본 설정 
           ================================= 12장 , 13장~19장 : 예제 
             20장         
                                                  
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