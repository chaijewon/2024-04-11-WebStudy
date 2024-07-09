package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *   http://localhost/JSPBasicProject_3/MyServlet
 *                                     ===========
 *                                     서블릿은 URL=> 파일명 변경
 *   1. URL을 입력하고 => 웹서버에 요청 
 *                    ==========
 *                    HTML / XML / JSON => 바로 처리 (브라우저로 전송) 
 *                    Java(Servlet) , JSP => WAS(Web Application Server)
 *                                          ============================ Tomcat
 *                                          a.jsp
 *                                          ===== 
 *                                          1. 클래스로 변경 
 *                                          class a_jsp => a_jsp.java
 *                                          2. 컴파일 
 *                                             a_jsp.class => 서블릿 
 *                    =============
 *                    | .java ====> 컴파일 .class 
 *                    | 한줄씩 번역 => 인터프리터 
 *                                  1. 서블릿 파일 로딩 
 *                                  2. init()
 *                                  3. service() => doGet()/doPost()
 *                                  4. 서블릿이 종료 (새로고침,화면 이동) => destory() 호출
 *                      ========
 *                      out.write("") => 메모리에 출력 
 *                                       =========
 *                                        요청한 브라우저에서 읽어서 화면 출력 
 *                      파일한개에 데이터 변경이 가능 => 동적 페이지 
 *                      예) 페이지가 100페이지 
 *                         ==============
 *                         서블릿 / JSP는 한개의 파일로 제작 => 데이터만 변경 
 *                         HTML => 파일을 100개를 만든다 : 정적페이지 (데이터 변경이 안된다)
 *        서블릿 (Servlet) => 72page 
 *             => Server + let  => 서버에서 실행되는 가벼운 프로그램 
 *                                              (경량) => 스프링 
 *             => Server + Applet
 *                         ======= Application + let
 *                         |Applet => 자바가 발전 => Applet 
 *                          ====== 웹 => 정보 공유 목적 (1995이전 => 문자로만 출력) 
 *                          | 이미지 / 애니메이션 => 2000년 웹 => 전자상 거래 
 *            => 웹 서비스 기능을 해주는 자바 클래스 
 *               => 자바안에 HTML코드를 첨부 (JSP => HTML안에 Java 첨부)
 *                  out.write("<html>")  => <%
 *                                          %>
 *                                          <html>
 *               => 단점 
 *                  1. HTML을 사용하기 어렵다(복잡) => CSS/JavaScript => 화면 UI는 하지 않는다 
 *                     out.write("<html>")
 *                     out.write("<head>")
 *                     out.write("<script type=\"text/javascript\">")
 *                     out.write("function aaa(){")
 *                     out.write("alert(\"\");")
 *                     out.write("}")
 *                     out.write("</script>")
 *                     out.write("<style type=\"text/css\">")
 *                     out.write("#aaa{background-color:red;border:1px solid red}")
 *                     out.write("</head>")
 *                   2. HTML / CSS / JavaScript에 대한 에러 처리가 어렵다 
 *                   3. 확장자 java => 변경시마다 컴파일을 다시해야 된다 
 *                      => 톰캣연결 
 *                      => 톰캣프로젝트 => 변경 : 컴파일 => 톰캣에 올려준다
 *                      => AWS => 수정시마다 컴파일 => war => war를 변경해야 된다
 *                         =============================================
 *                           운영 (SE) => SI/SM 
 *                           ================= 통합 DevOps  => CI/CD
 *                      => 버튼 색상 변경 
 *                      => 개발자 => 수정시에 바로 확인 => JSP 
 *               => 장점 
 *                   자바 => 배포시에 .class파일만 전송 => 보안이 뛰어나다 
 *                          ==================== 전체 소스를 볼 수 없다 , 다른 사람이 변경이 불가능
 *                   주로 사용처 : HTML / 자바가 분리가 된 경우 => 연결한 역할 
 *                             =================================== Controller
 *                                                                 | 스프링은 이미 제작 (서블릿)
 *               => 단점을 보완 
 *                  => 수정과 동시 확인이 가능하게 만들어 준다 : JSP 
 *                  => HTML/CSS/JavaScript => 사용이 쉽게 
 *                     out.write("<html>")
 *                     ===========      ==  => <html>
 *                  => HTML 기반 => 필요한 자바 <% %> <%= %>
 *                  => 실행은 톰캣에 의해 실행 
 *                  => 실행 => 저장
 *                  => 보안이 취약하다 (소스를 전체를 전송)
 *                     ======== 서블릿을 병행 
 *                             ====== 웹과 괸련된 라이브러리는 서블릿으로 되어 있다 
 *         자바 => SUN => package => javax => tomcat 9
 *               ====
 *               | 오라클 => package => jakarata => tomcat 10이상 
 *               STS 3.9 => jdk 11 => 실무 1.8(호환성이 좋다)
 *               STS 4.x => jdk 17 
 *               => 전자정부 프레임워크 / ANY 프레임워크 
 *                  => 공기업         => 대기업  ==> STS 3.XXX
 *               => 서버 분산 (MSA) => Spring Cloud 
 *         서블릿의 실행 과정 
 *         =============
 *          URL 주소를 이용해서 전송 => 톰캣 
 *          => 톰캣에 의해 실행   
 *             init() => main() 
 *             doGet()/doPost() => 클라이언트 마다 따로 동작 => 쓰레드
 *             ================= run()  
 *             destory() 
 *             ================   
 *             
 *             자바 / 오라클 / HTML / CSS 
 *             ======================= 
 *                      
 */
//@WebServlet("/MyServlet")
// 구분자 => 인덱스 
// => 클래스 / 메소드 / 생성자 / 멤버변수 
/*
 *   1. 웹 분석 
 *      1) web.xml : 시작과 동시에 필요한 데이터를 넘겨준다  
 *      2) server.xml : port / 파일위치 확인 
 *      ========================= AWS => tomcat
 *      3) 클래스 분석
 *         => DAO : SQL 
 *      4) JSP분석 : 화면 출력 
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// _jspInit() : 초기화 (생성자 역할) => 멤버변수 초기화 , 시작과 동시에 처리 
		// 환경설정 (web.xml) => 등록된 내용을 읽어올때 
		// web.xml => 스프링은 라이브러리 => 환경 설정 => 등록 
		// => 서블릿등록 , 보안 설정 , 에러 설정 , 한글 처리 
		System.out.println("MyServlet:init(ServletConfig config) Call...");
		String path=config.getInitParameter("file_name");
		System.out.println(path);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		// 종료가 된 상태 => 메모리 해제 
		System.out.println("MyServlet:destroy() Call...");
	}

	/*
	 *   흐름 : 화면 이동 => 게시판 (CRUD)
	 *        ======== 
	 *        사용자 요청 => 요청시에 어떤 값을 전송  
	 *                    GET / POST 
	 *                     |     |
	 *                    <a>   <form> , Ajax 
	 *                    대부분
	 *                    
	 *                    URL뒤에 데이터 첨부  ?키=값 
	 *                                     ?키=값&키=값 
	 *                                      =========
	 *                    GET : 단순한 데이터 => 노출 (id , pwd) 
	 *                          =========
	 *                          page요청 , 상세보기 요청 , 단순 검색 ...
	 *                    POST : 데이터가 많거나 보안요구 : 노출이 안된다 
	 *                           ===========
	 *                           회원가입 , 글쓰기 , 댓글 올리기 
	 *                           로그인 / 아이디 찾기 / 비밀번호 찾기 
	 *                    
	 *        서버 응답 => 요청값을 받아서 오라클에 연결 ...
	 *                   request / response 
	 *                   요청값       응답할 경우 
	 *                              sendRedirect() , addCookie() 
	 *                   getParameter() 
	 *                   getParameterValues() 
	 *                   => 한글 = 인코딩 => 디코딩 변경 
	 *                      setCharacterEncoding()
	 *                  => 데이터출력 
	 *                  
	 *      // 사용자가 데이터 전송 GET  ==> doGet() 호출 
	 *      // 사용자가 데이터 전송 POST ==> doPost() 호출 
	 *      // 통합 : service() 
	 *      
	 *      // => JSON / XML => Ajax 
	 *                           javascript and xml
	 *      // 자바 / 오라클 / HTML / CSS / JavaScript 
	 *         => XML / Annotation  
	 *         => 디자인 패턴 : 싱글턴 => 22개 => 스프링을 이해  
	 *                              ===== 8개 
	 *      
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 디폴트 => 메인화면 => 화면 UI
		// 1. 변환 => 브라우저에 알려주는 내용 (HTML,XML,JSON) => JSP (page지시자) 
		response.setContentType("text/html;charset=UTF-8");
		// response => 1. HTML , 2. Cookie 
		// => 한개의 메소드에서 1개만 전송이 가능 
		// 2. 요청된 브라우저를 찾는다 => 결과값을 보내준다 
		PrintWriter out=response.getWriter();
		// 브라우저에서 HTML을 읽어가는 메모리 공간 => out => JSP에서는 내장 객체 
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>Hello Servlet</h1>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}
    /*
     * 
     *    1. 요청 => URL 주소로 요청 
     *    2. 톰캣이 해당 서블릿 클래스 읽기 
     *    3. 메모리 할당 
     *    4. 실행 
     *         init()
     *         doGet() / doPost()
     *         destory() 
     *    
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 요청에 대한 처리 
	}

}
