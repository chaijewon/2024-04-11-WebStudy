package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class FindModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "게시판 검색");
		return "view/find.jsp";
	}

}
