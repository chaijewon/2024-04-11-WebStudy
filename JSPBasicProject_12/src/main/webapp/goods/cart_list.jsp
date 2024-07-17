<%@page import="com.sist.dao.CartVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%
    List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
    List<CartVO> mList=new ArrayList<CartVO>();
    String id=(String)session.getAttribute("id");
    
    if(list!=null)
    {
	    for(CartVO vo:list)
	    {
	    	if(vo.getId().equals(id))
	    	{
	    		mList.add(vo);
	    	}
	    }
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 800px;
}
h3{
    text-align: center;
}
</style>
</head>
<body>
   <div class="container">
     <h3><%= session.getAttribute("name") %>님의 장바구니</h3>
     <div class="row">
      <table class="table">
        <thead>
          <tr>
            <th class="text-center">번호</th>
            <th class="text-center"></th>
            <th class="text-center">상품명</th>
            <th class="text-center">가격</th>
            <th class="text-center">수량</th>
            <th class="text-center"></th>
          </tr>
        </thead>
        <tbody>
          <%
             for(CartVO vo:mList)
             {
          %>
                 <tr>
		            <td class="text-center"><%=vo.getNo() %></td>
		            <td class="text-center">
		              <img src="<%=vo.getPoster() %>" style="width: 30px;height: 30px">
		            </td>
		            <td><%=vo.getName() %></td>
		            <td class="text-center"><%=vo.getPrice() %></td>
		            <td class="text-center"><%=vo.getAccount() %></td>
		            <td class="text-center">
		              <a href="cart_delete.jsp?no=<%=vo.getNo() %>" class="btn btn-sm btn-success">삭제</a>
		            </td>
		          </tr>
          <%
             }
          %>
          <tr>
            <td colspan="6" class="text-center">
             <a href="goods_list.jsp" class="btn btn-sm btn-info">목록</a>
            </td>
          </tr>
        </tbody>
      </table>
     </div>
   </div>
</body>
</html>