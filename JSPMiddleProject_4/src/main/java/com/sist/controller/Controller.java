package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
@WebServlet("*.do") // list.do , insert.do , detail.do
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map clsMap=new HashMap();
    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		clsMap.put("board/list.do", new BoardListModel());
		clsMap.put("board/detail.do", new BoardDetailModel());
		clsMap.put("board/insert.do", new BoardInsertModel());
		clsMap.put("board/insert_ok.do", new BoardInsertOkModel());
		clsMap.put("board/update.do", new BoardUpdateModel());
		clsMap.put("board/delete.do", new BoardDeleteModel());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// http://localhost/JSPMiddleProject_4/board/list.jsp => URL
		// /JSPMiddleProject_4/board/list.jsp => URI
		// /JSPMiddleProject_4 => ContextPath
		// +1
		String cmd=request.getRequestURI();
		cmd=cmd.substring(request.getContextPath().length()+1);
		String jsp="";
		
        Model model=(Model)clsMap.get(cmd);
        jsp=model.execute(request);
		// request를 해당 JSP로 전송 
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		rd.forward(request, response);
		
	}

}
