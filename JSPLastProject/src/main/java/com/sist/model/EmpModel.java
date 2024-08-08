package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class EmpModel {
  @RequestMapping("emp/list.do")
  public String emp_list(HttpServletRequest request,HttpServletResponse response)
  {
	  List<EmpVO> list=EmpDAO.empListData();
	  request.setAttribute("list", list);
	  return "list.jsp";
  }
}
