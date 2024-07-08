package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // <%! %>
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("InputServlet:init(ServletConfig config) Call...");
		// 생성자와 같은 역할 => 멤버변수의 초기화 , 드라이버 등록 ... , 쿠키 읽기 
		// 자동로그인 , 크롤링 (실시간 갱신)
	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("InputServlet:destroy() Call...");
		// 새로고침 , 화면이동 => 메모리 회수 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// JSP코딩 => 메소드 영역 => 사용자 요청에 따라 처리하는 코딩 
		// => 지역변수 <% %> <%= %> 
		System.out.println("사용자가 요청한 데이터를 받아서 처리 결과를 보내주는 역할");
		// default => 처음 실행 , <a> , sendRedirect() => GET/POST를 지정하지 않는 경우=> GET
		// 1. 변환 코드 => 컴파일하면 어떤 형식의 파일을 전송 : HTML/XML/JSON 
		response.setContentType("text/html;charset=UTF-8");
		// 2. 누구한테 보낼지 확인 
		PrintWriter out=response.getWriter();
		//              ====================== 해당 브라우저 정보를 읽어 온다 
		// HTML를 전송한다 
		out.write("<html>");
		out.write("<body>");
		out.write("<center>");
		out.write("<h3><a href=OutputServlet>OutputServlet이동</a></h3>");
		// 이동 => OutputServlet=> doGet() => doGet()이 없는 경우에는 405에러 발생 
		out.write("</center>");
		out.write("</body>");
		out.write("</html>");
		// => 사용자 데이터 전송시 처리 => 값을 받는 방법  (request)
		// => 데이터베이스 연결 => 데이터출력하는 방법 => Servlet 
		// => servlet : MVC(Controller) => 화면 출력은 JSP
		// Controller (스프링에서는 제작이 되어 있다)
	}

}
