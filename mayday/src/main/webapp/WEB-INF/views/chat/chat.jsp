<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>Chatting</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
<header class="text-center mt-5 mb-4">
	<h1 class="fw-bolder mb-1">${sessionScope.CHAT_ROOM.expertName}님과 상담 채팅</h1>
</header>
	<div class="container mt-5 mb-5">
	<div id="id_"+${sessionScope.CHAT_ROOM.roomId}> 
	<div class="card mt-4  mb-4 chatscrollbar" style="height: 700px;" id="chatBody">
				<div class="card-body messages" id="messages">
				</div>
			</div>
		<div class="text-center">
			<div class="card mt-4  mb-4">
				<div class="card-body">
					<div class="input-group mb-2">
						<input type="hidden" class="form-control" id="sender" value="${sessionScope.USER_INFO.userName}"> 
						<input class="form-control" type="text" id="messageinput" placeholder="메세지를 입력하세요." 	aria-label="메세지를 입력하세요." aria-describedby="send-button" />
						<button class="btn btn-outline-primary bg-white" id="send-button"	type="button" >
							<i class="far fa-paper-plane"></i>
						</button>
						<button class="btn btn-outline-warning bg-white" type="button"	onclick="clearText();">
							<i class="fas fa-eraser"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="mb-4 mt-4">
				<i class="fas fa-times fa-5x" id="disconn"></i>
			</div>
			</div>
		</div>	
	</div><!--  end .container -->
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">
	var username = "${sessionScope.USER_INFO.userName}";
	var userid = "${sessionScope.USER_INFO.userId}";
	var roomid = "${sessionScope.CHAT_ROOM.roomId}";
	$(document).ready(function chatconnect() {
		
		$("#disconn").on("click", (e)=> {
				disconnect();
		});
		$("#send-button").on("click",(e)=>{
			send(this);
		});
		$('#messageinput').keydown(function(e){
	        if(e.keyCode == 13){
	        	$('#send-button').click();
	        }
		});
		//var websocket = new WebSocket("ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/chat/chatting");
		var sockJs = new SockJS("/chat/chatting",null,{transports:["websocket","xhr-streaming","xhr-polling"]});		
		
		sockJs.onmessage = onMessage;
		sockJs.onopen = onOpen;
		sockJs.onclose = onClose;

		function send(obj) {
			var msg = document.getElementById("messageinput");
			if(msg.value.length == 0){
				return false;
				msg.focus();
			}
			// 전송할 정보를 db에 저장 
			$.ajax({
				type: "post",
				url:"/chat/saveChat",
				dataType: 'text',
				data:{
					"memId"   : userid,
					"memName" : username,
					"message" : msg.value,
					"roomId"	: roomid
				},
				success: function () {
					var str = username +","+userid+","+msg.value+","+roomid+",c";
					console.log("str= ",str	)
					sockJs.send(str);
					msg.value = '';
				}
			});
			console.log(username +" : " +msg.value);
		}
		// 채팅에서 나갔을 때 
		function onClose(e) {
			var str =  username+","+userid+", 님이 방을 나가셨습니다.,"+roomid+",s";
			sockJs.send(str);
		}
		//채팅 들어왔을 때 
		function onOpen(e) {
			var str = username+","+userid+", 님이 입장하셨습니다.,"+roomid+",s";
			sockJs.send(str);
		}
		function onMessage(msg) {
			var now = new Date();
			var hour = now.getHours();
			var min = now.getMinutes();
			var amPm = now.getHours() > 12 ? "pm" : "am";
			var sessionId = null;
			// 메시지를 파싱해서 roomId 
			
			$room = $("#id_" + roomid);
			
			// 데이터를 보낸 사람
			var message = null;
			var arr = msg.data.split(':');
			console.log(arr);
			var cur_session = username.trim();
			// 현재 세션에 로그인 한 사람 
			sessionId = arr[0].trim();
			message = arr[1].trim();
			state = arr[2].trim();
			console.log("message: "+message);
			console.log("sessionID : " +sessionId+" , cur_session : " + cur_session);
			// 로그인 한 클라이언트와 타 클라이언트를 분류하기 위함 
			if(state=="s"){
				var str = "<div class='text-center'>"+sessionId+message+"</div>";
				$("#messages").append(str);
			}else{
				if(sessionId == cur_session){
					var str = "<div class='myMsg ' ><div class='msg text-center'>"+ message+"</div><span class='anotherName'>"+amPm+" "+hour+" : "+min+"</span></div>";
					$("#messages").append(str);
				}else if(sessionId != cur_session){
					var str = "<div class='mt-2 mb-2 anotherMsg '><span class='anotherName'>"+sessionId+"</span><span class='msg'>"+ message+"</span><span class='anotherName'>"+amPm+" "+hour+" : "+min+"</span></div>"
					$("#messages").append(str);
				}
			}
			$('#chatBody').scrollTop($('#chatBody').prop('scrollHeight'));
		}
	});
	function disconnect() {
		$.ajax({
			type: "post",
			url:"/chat/disconnect",
			dataType: 'text',
			data:{ "roomId":	roomid},
			success: function (data) {
				location.href=data
			}
		});
	}
	function clearText() {
		console.log('텍스트 지우기');
		$('#messages').html('');
	};
</script>
</html>