<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    /*
        1. 웹 출력 
           => 데이터를 가지고 온다 : 오라클 (자바)
                               SQL  JDBC 
        2. 데이터를 HTML 출력 
    */
    String type=request.getParameter("type");
    if(type==null)
    	type="1";
    String strPage=request.getParameter("page");
    if(strPage==null)
    	strPage="1";
    
    int curpage=Integer.parseInt(strPage);
    
    GoodsDAO dao=GoodsDAO.newInstance();
    List<GoodsVO> list=dao.goodsListData(Integer.parseInt(type), curpage);
    int totalpage=dao.goodsListTotalPage(Integer.parseInt(type));
    
    // 블럭별 페이지 나누기 
    /*
       이동 페이지 => 10
       현재페이지 => 1~10     1
                 11~20     11
    */
    final int BLOCK=10;
    int startPage=((curpage-1)/BLOCK*BLOCK)+1;
    /*
                   =================
                   curpage = 10  ==> 1
    */
    int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
    // JSP => _jspService(){}
    if(endPage>totalpage)
    	endPage=totalpage;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
	.container{
		margin-top:50px;
	}
	.row{
		width: 100%;
		margin:0px auto;
	}
	.a{
	    white-space:nowrap;
	    overflow: hidden;
	    text-overflow: ellipsis;
	}
</style>
<script type="text/javascript">
$(function(){
	$('.bclick').click(function(){
		let sel=$('#sel').val();
		let fd=$('#fd').val();
		if(fd=='')
		{
			$('#fd').focus();
			return;
		}
		
		$.ajax({
			type:'post',
			url:'goods_find.jsp',
			data:{"sel":sel,"fd":fd},
			success:function(result)
			{
				$('#dialog').html(result);
				$('#dialog').dialog({
					autoOpen:false,
					modal:true,
					width:800,
					height:600
				}).dialog("open");
			}
		})
	})
})
</script>
</head>
<body>
  <div class="container">
   <div class="row">
       <select id="sel" class="input-sm">
         <option value="goods_name">상품명</option>
         <option value="goods_sub">상품소개</option>
       </select>
       <input type="text" id="fd" size=20 class="input-sm">
       <input type="button" value="검색" class="btn-sm btn-danger bclick">
   </div>
   <div style="height: 10px"></div>
   <div class="row">
    <div class="text-center">
      <%-- Ajax : JSON --%>
      <a href="goods_list.jsp?type=1" class="btn btn-lg btn-success">전체상품</a>
      <a href="goods_list.jsp?type=2" class="btn btn-lg btn-info">신상품</a>
      <a href="goods_list.jsp?type=3" class="btn btn-lg btn-warning">특가상품</a>
      <a href="goods_list.jsp?type=4" class="btn btn-lg btn-primary">베스트상품</a>
    </div>
   </div>
   <div style="height: 20px"></div>
   <div class="row">
      <%-- 이미지 출력 --%>
     <%
        for(GoodsVO vo:list)
        {
     %>
	      <div class="col-sm-3">
	       <a href="goods_detail.jsp?no=<%=vo.getNo()%>&type=<%=type%>">
	        <div class="thumbnail">
	         <img src="<%=vo.getPoster() %>" style="width: 240px;height: 240px" class="img-rounded">
	         <p class="a"><%=vo.getName() %></p>
	        </div>
	       </a>
	      </div>
    <%
        }
    %>
   </div>
   <div style="height: 20px"></div>
   <div class="row">
      <div class="text-center">
        <ul class="pagination">
          <%
             if(startPage>1) //  1   11   21   31  41
             {
          %>
                <li><a href="goods_list.jsp?type=<%=type%>&page=<%=startPage-1%>">&lt;</a></li>
          <%
             }
             for(int i=startPage;i<=endPage;i++)
             {
          %>
                <li class="<%= i==curpage?"active":""%>"><a href="goods_list.jsp?type=<%=type%>&page=<%=i%>"><%=i %></a></li>
          <%
             }
             if(endPage<totalpage) // 10  20  30  40  50 
             {
          %>
               <li><a href="goods_list.jsp?type=<%=type%>&page=<%=endPage+1%>">&gt;</a></li>
          <%
             }
          %>
        </ul>
      </div>
   </div>
  </div>
  <div id="dialog" title="검색 결과">
   
  </div>
</body>
</html>