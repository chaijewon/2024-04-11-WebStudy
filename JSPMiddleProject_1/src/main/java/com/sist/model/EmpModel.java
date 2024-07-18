package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

public class EmpModel {
   public void empListData(HttpServletRequest request)
   {
	    EmpDAO dao=EmpDAO.newInstance();
	    List<EmpVO> list=dao.empListData();
	    request.setAttribute("list", list);
   }
}
