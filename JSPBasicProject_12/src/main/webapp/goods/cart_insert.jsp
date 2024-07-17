<%@page import="com.sist.dao.CartVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%
    // session => List
    List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
    if(list==null)
    	list=new ArrayList<CartVO>();
    
    String id=(String)session.getAttribute("id");
    
    request.setCharacterEncoding("UTF-8");
    String name=request.getParameter("name");
    String poster=request.getParameter("poster");
    String no=request.getParameter("no");
    String price=request.getParameter("price");
    String account=request.getParameter("account");
    
    CartVO vo=new CartVO();
    vo.setName(name);
    vo.setPoster(poster);
    vo.setPrice(price);
    vo.setId(id);
    vo.setNo(Integer.parseInt(no));
    vo.setAccount(Integer.parseInt(account));
    
    boolean bCheck=false;
    for(CartVO cvo:list)
    {
    	if(cvo.getNo()==vo.getNo())
    	{
    		int acc=cvo.getAccount()+vo.getAccount();
    		cvo.setAccount(acc);// 수량증가
    		bCheck=true;
    		break;
    	}
    }
    if(bCheck==false)
    {
    	list.add(vo);
    	session.setAttribute("cart", list); // session : Object
    }
    
    // 이동 
    response.sendRedirect("cart_list.jsp");
    
%>