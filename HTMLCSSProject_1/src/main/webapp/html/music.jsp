<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    // DAO로부터 데이터 받는다 
    MusicDAO dao=MusicDAO.newInstance();
    List<MusicVO> list=dao.musicAllData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<style type="text/css">
 table{
   margin: 0px auto;
   width: 880px;
 }
 h1{
   text-align: center;
 }
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<!-- <script src="https://code.jquery.com/jquery-3.7.1.js"></script> -->
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<script type="text/javascript">
$(function(){
	$('#keyword').keyup(function(){
		let sel=$('#sel').val();
		
		let k=$('#keyword').val();
		$('#user-table > tbody > tr').hide();
		if(sel=='곡명')
		{
		    let temp=$('#user-table > tbody > tr > td:nth-child(5n+4):contains("'+k+'")');
		    $(temp).parent().show()
		}
		else
		{
			let temp=$('#user-table > tbody > tr > td:nth-child(5n+5):contains("'+k+'")');	
			$(temp).parent().show()
		}
		
	})
	$('img').hover(function(){
		$(this).css("width","100px").css("height","100px");
	},function(){
		$(this).css("width","30px").css("height","30px");
	})
	
	$('.dataTr').click(function(){
		let key=$(this).attr("key-data");
		console.log(key)
		let key1=$('#movie').attr("src","http://youtube.com/embed/"+key)
		let title=$('#dialog').attr("title",$(this).attr("title-data"))
		$('#dialog').dialog({
					autoOpen:false,
					resizable:false,
					modal:true,
					width:510,
					height:500
				}).dialog('open')
		
	})
})
</script>
</head>
<body>
   <h1>뮤직 Top200</h1>
   <table class="table_content">
     <tr>
      <td>
       <select id="sel">
         <option>곡명</option>
         <option>가수</option>
       </select>
       <input type=text size=25 id=keyword>
      </td>
     </tr>
   </table>
   <table class="table_content" id="user-table">
    <thead>
     <tr>
       <th width=10%>순위</th>
       <th width=10%></th>
       <th width=20%></th>
       <th width=30%>곡명</th>
       <th width=30%>가수명</th>
     </tr>
    </thead>
    <tbody>
     <%
        for(MusicVO vo:list)
        {
     %>
            <tr class="dataTr" key-data="<%=vo.getKey()%>" title-data="<%=vo.getTitle()%>">
              <td width="10%" align="center"><%= vo.getMno() %></td>
              <td width="10%" align="center">
              
                <%
                    String temp="";
                    if(vo.getState().trim().equals("유지"))
                        temp="-";
                    else if(vo.getState().trim().equals("상승"))
                    	temp="<font color=blue>▲</font>&nbsp;"+vo.getIdcrement();
                    else if(vo.getState().trim().equals("하강"))
                    	temp="<font color=red>▼</font>&nbsp;"+vo.getIdcrement();
                %>
                <%=temp %>
              </td>
              <td width="20%" align="center"><img src="<%=vo.getPoster() %>" width=30 height="30"></td>
              <td width="30%"><%=vo.getTitle() %></td>
              <td width="30%"><%=vo.getSinger() %></td>
            </tr>
     <%
        }
     %>
     </tbody>
   </table>
   <div id="dialog" title="Basic dialog" style="display: none">
    <embed src="" id="movie" width=470 height=420></embed>
   </div>
 
</body>
</html>