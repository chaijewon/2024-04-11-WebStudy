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
  @RequestMapping("emp/find.do")
  public String emp_find(HttpServletRequest request,HttpServletResponse response)
  {
	  List<String> list=EmpDAO.empEnameList();
	  request.setAttribute("list", list);
	  return "find.jsp";
  }
  @RequestMapping("emp/find_ok.do")
  public String emp_find_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  String[] names=request.getParameterValues("names[]");
	  Map map=new HashMap();
	  map.put("nameArr", names);
	  System.out.println(Arrays.toString(names));
	  // 데이베이스 연동 
	  List<EmpVO> list=EmpDAO.empFindData(map);
	  // 결과값 전송 
	  request.setAttribute("list", list);
	  return "find_ok.jsp";
  }
  @RequestMapping("emp/emp_list.do")
  public String emp_list2(HttpServletRequest request,HttpServletResponse response)
  {
	  List<String> list=EmpDAO.empEnameList();
	  request.setAttribute("list", list);
	  return "emp_list.jsp";
  }
  @RequestMapping("emp/emp_find.do")
  public String emp_find2(HttpServletRequest request,HttpServletResponse response)
  {
	  // 다중 검색 => 검색 : 화면 이동 => 해당  JSP에서 바로 출력 => Ajax 
	  String[] names=request.getParameterValues("names[]");
	  // Ajax에서 배열로 보낸경우 처리 
	  Map map=new HashMap();
	  map.put("nameArr", names);
	  List<EmpVO> list=EmpDAO.empFindData(map);
	  request.setAttribute("list", list);
	  return "emp_find.jsp";
  }
}
