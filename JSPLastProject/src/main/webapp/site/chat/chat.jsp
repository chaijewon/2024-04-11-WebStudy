<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
#chatArea{
   height: 450px;
   overflow-y:auto;
   border: 1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
// 서버연결 
function connection()
{
	// 소켓연결 
	websocket=new WebSocket("ws://localhost/JSPLastProject/site/chat/chat-ws")
	websocket.onopen=onOpen
	websocket.onclose=onClose
	websocket.onmessage=onMessage
}
// 연결처리 => Callback 
function onOpen(event)
{
	 alert("채팅 서버와 연결되었습니다...")
}
function onClose(event)
{
	  alert("채팅 서버와 연결 해제되었습니다...")
}
function onMessage(event)
{
	 let data=event.data // 전송된 데이터 
	 
	 if(data.substring(0,3)==="my:") // oto , makeroom  ==> 100 200 300...
	 // msg:[이름] 메세지
	 {
		 appendMyMessage(data.substring(3))
	 }
	 if(data.substring(0,4)==="you:") // oto , makeroom  ==> 100 200 300...
		 // msg:[이름] 메세지
     {
		 appendYouMessage(data.substring(4))
	 }
	 if(data.substring(0,4)==="msg:") // oto , makeroom  ==> 100 200 300...
		 // msg:[이름] 메세지
     {
		 appendMsgMessage(data.substring(4))
	 }
}
function disConnection()
{
	websocket.close()
}
// 퇴장처리 => Callback
// 메세지 전송 => Callback
function appendMsgMessage(msg)
{
	 $('#recvMsg').append("<font color=red>"+msg+"</font><br>")
	 let ch=$('#chatArea').height()
	 let m=$('#recvMsg').height()-ch
	 $('#chatArea').scrollTop(m)
}
function appendMyMessage(msg)
{
	 $('#recvMsg').append("<font color=blue>"+msg+"</font><br>")
	 let ch=$('#chatArea').height()
	 let m=$('#recvMsg').height()-ch
	 $('#chatArea').scrollTop(m)
}
function appendYouMessage(msg)
{
	 $('#recvMsg').append(msg+"<br>")
	 let ch=$('#chatArea').height()
	 let m=$('#recvMsg').height()-ch
	 $('#chatArea').scrollTop(m)
}
function send()
{
	/*let name=$('#name').val()
	if(name.trim()==="")
	{
		$('#name').focus()
		return
	}*/
	
	let msg=$('#sendMsg').val()
	if(msg.trim()==="")
	{
		$('#sendMsg').focus()
		return 
	}
	
	websocket.send(msg)
	$('#sendMsg').val("")
	$('#sendMsg').focus()
}
$(function(){
	$('#inputBtn').click(function(){
		connection() 
	})
	$('#outputBtn').click(function(){
		disConnection()
	})
	$('#sendBtn').click(function(){
		send()
	})
	$('#sendMsg').keydown(function(key){
		if(key.keyCode===13)//enter @keydown.13 => enter
		{
			send()
		}
	})
})
</script>
</head>
<body>
  <div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">실시간 채팅</h2>
   <div class="row">
     <table class="table">
      <tr>
        <td class="inline">
         <!-- 이름:<input type=text id="name" size=15 class="input-sm"> -->
         <input type=button value="입장" class="btn-danger btn-sm" id="inputBtn">
         <input type=button value="퇴장" class="btn-success btn-sm" id="outputBtn">
        </td>
      </tr>
      <tr>
       <td>
        <div id="chatArea">
          <div id="recvMsg"></div>
        </div>
       </td>
      </tr>
      <tr>
        <td class="inline">
          <input type=text id="sendMsg" size=80 class="input-sm">
          <input type=button id="sendBtn" value="전송" class="btn-sm btn-primary">
        </td>
      </tr>
     </table>
   </div>
   </main>
  </div>
</body>
</html>