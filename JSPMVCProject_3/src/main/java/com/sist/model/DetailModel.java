package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
public class DetailModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		// DAO연동 
		BoardDAO dao=BoardDAO.newInstance();
		// 데이터를 가지도 온다
		BoardVO vo=dao.boardDetailData(Integer.parseInt(no));
		// 모든 화면은 => Controller
		// detail.jsp로 전송 
		request.setAttribute("vo", vo);
		return "detail.jsp";
	}

}
