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
	<div class="row gx-5 mt-5">
		<div class="col-2 px-5 my-5" >
		<h3 class="fw-bolder mb-4"> 채팅 상담 리스트</h1>
			<header class="mb-4" id="chatList-container">
			</header>
		</div>
		<div class="container col-10 px-5 my-5">
			<header class="mb-4">
				<h1 class="fw-bolder mb-1"> 상담 채팅</h1>
			</header>
			<div class="row gx-5" id="chat-container">
			</div>
		</div><!--  end .container -->
	</div>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">
	var username = "${sessionScope.USER_INFO.userName}";
	var userid = "${sessionScope.USER_INFO.userId}";
	var chatRoomList = "${chatRoomList}";
	var count = "${chatRoomList.size()}"
	setInterval(fn_get_chatRoomList, 1000)
	function fn_get_chatRoomList() {
		$.ajax({
			type:"post"
			,url: '<c:url value="/chat/chatRoomList" />' 
			,success: function (data) {
				if(data.result){
					$('#chatList-container').html('');
						$.each(data.roomList, function (index, room) {
							var list = '<div class="card border-left-warning shadow h-100 py-2 mt-3 mb-3">';
		                     list += '<div class="card-body btn_chatting" >'
		                     		 + '<input type="hidden" class="cl_roomId" value="'+room.roomId+'">'
	                         	 + '<div class="row no-gutters align-items-center">'
	                             + '<div class="col mr-2">'
	                             +   ' <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">'+room.userId+'</div>'
	                             +   ' <div class="h5 mb-0 font-weight-bold text-gray-800">'+room.userName+' 의뢰인</div></div>'
	                             +  '<div class="col-auto">'
	                             +  '  <i class="fas fa-comments fa-2x text-gray-300"></i></div></div></div></div>';
						$('#chatList-container').append(list);
					});
				}
			}//success
		});
	}
	
	$('#chatList-container').on('click','.btn_chatting',function(){
		$btn=$(this);
		var roomId = $btn.find("input.cl_roomId").val();
		fn_get_chatList(roomId);
	})
	
	function fn_get_chatList(roomId){
		$.ajax({
			type:"post"
			,url: '<c:url value="/chat/chatRoom" />' 
			,data: {"roomId": roomId}
			,success: function (data) {
				console.log("data",data)
				if(data.result){
					//$("#chat-container").html('');
					 $div=$("#chat-container");
						var str = '<div class="col-md-4 mb-3 mt-3 chat-room " id="id_'+data.chatRoom.roomId+'">'; 
						str += '<span>'+data.chatRoom.userName+'님과 상담 </span>'
							+ '<div class="card shadow border-0 chatscrollbar chatBody" style="height: 600px;" id="">'
							+ '<div class="card-body messages" id=""></div></div>'
							+ '<div class="text-center">'
							+ '<div class="card mt-4  mb-4">'
							+	'<div class="card-body">'
							+		'<div class="input-group mb-2">'
							+			'<input type="hidden" class="form-control sender" id="sender" value="'+data.chatRoom.expertId+'">' 
							+			'<input class="form-control messageinput" type="text" id="input_'+data.chatRoom.roomId+'" placeholder="메세지를 입력하세요." 	aria-label="메세지를 입력하세요." aria-describedby="send-button" />'
							+			'<button class="btn btn-outline-primary bg-white send-button" id="send-button"	type="button" >'
							+				'<i class="far fa-paper-plane"></i></button>'
							+			'<button class="btn btn-outline-warning bg-white" type="button"	onclick="javascript:clearText(this);">'
							+				'<i class="fas fa-eraser"></i>'
							+			'</button></div></div></div>'
							+	'<div class="mb-4 mt-4 text-center disconn"><i class="fas fa-times fa-5x "></i></div></div></div>';
							console.log(str);
						$div.append(str);
						var $room=$('#id_'+data.chatRoom.roomId);
						
						$.each(data.messageList, function (index, message) {
							if(message.memName == username){
								var mess = "<div class='myMsg '><div class='msg text-center'>"+ message.message+"</div><span class='anotherName'>"+message.messageDate+"</span></div>";
								$("div.messages",$room).append(mess);
							}else if(message.memName != username){
								var mess = "<div class='mt-2 mb-2 anotherMsg '><span class='anotherName'>"+message.memId+"</span><span class='msg'>"+ message.message+"</span><span class='anotherName'>"+message.messageDate+"</span></div>";
								$("div.messages",$room).append(mess);
							}
						});
				}
			},//success
			error: function(e){
				console.log(e);
				alert("에러");
			}
		});
	}
	$(document).ready(
	function chatconnect() {
		$('#chatList-container').html('');
		fn_get_chatRoomList();
		
		$("#chat-container").on('click','.send-button',function(e){
			send(this);
		});
		$("#chat-container").on('click','div.disconn>i',function(e){
			disconn(this);
		});
		//var websocket = new WebSocket("ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/chat/chatting");
		var sockJs = new SockJS("/chat/chatting",null,{transports:["websocket","xhr-streaming","xhr-polling"]});		
		
		sockJs.onmessage = onMessage;
		sockJs.onopen = onOpen;
		sockJs.onclose = onClose;
		function disconnect() {
			sockJs.disconnet();
		}
		function send(obj) {
			$obj = $(obj);
			console.log('obj', $obj);
			var m = $obj.closest('div.chat-room').attr("id").split('id_');
			var roomid  = m[1];
			console.log('m', m);
			// alert(m);
			var msg =  $obj.prev()[0];
			console.log('msg',msg);
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
					var str = username +","+userid+","+msg.value+","+roomid+",C";
					console.log("str= ",str	)
					sockJs.send(str);
					msg.value = '';
				}
			});
			console.log(username +" : " +msg.value);
		}
		// 채팅에서 나갔을 때 
		function onClose(e) {
			var str =  username+","+userid+", 님이 퇴장하셨습니다.,"+chatRoomList.roomId+",s";
			sockJs.send(str);
		}
		//채팅 들어왔을 때 
		function onOpen(e) {
			var str = username+","+userid+", 님이 입장하셨습니다.,"+chatRoomList.roomId+",s";
			sockJs.send(str);
		}
		function onMessage(msg) {
			var now = new Date();
			var hour = now.getHours();
			var min = now.getMinutes();
			var sessionId = null;
			// 데이터를 보낸 사람
			var message = null;
			var arr = msg.data.split(':');
			var cur_session = username.trim();
			// 현재 세션에 로그인 한 사람 
			sessionId = arr[0].trim();
			message = arr[1].trim();
			state = arr[2].trim();
			roomId = arr[3];
			// 메시지를 파싱해서 roomId 
			var $room=$('#id_'+roomId);
			console.log("message: "+roomId);
			console.log("message: "+message);
			console.log("sessionID : " +sessionId+" , cur_session : " + cur_session);
			// 로그인 한 클라이언트와 타 클라이언트를 분류하기 위함 
			if(state=="s"){
				var str = "<div class='text-center'>"+sessionId+message+"</div>";
				$("div.messages").append(str);
			}else{
				if(sessionId == cur_session){
					var str = "<div class='myMsg '><div class='msg text-center'>"+ message+"</div><span class='anotherName'>"+hour+" : "+min+"</span></div>";
					console.log(str);
					$("div.messages",$room).append(str);
				}else if(sessionId != cur_session){
					var str = "<div class='mt-2 mb-2 anotherMsg '><span class='anotherName'>"+sessionId+"</span><span class='msg'>"+ message+"</span><span class='anotherName'>"+hour+" : "+min+"</span></div>"
					console.log(str);
					$("div.messages",$room).append(str);
				}
			}
			$('div.card.chatscrollbar.chatBody',$room).scrollTop($('div.card.chatscrollbar.chatBody',$room).prop('scrollHeight'));
		}
	});
	function clearText(obj) {
		$obj = $(obj);
		var m = $obj.closest('div.chat-room').attr("id").split('id_');
		var roomid  = m[1];
		var $room=$('#id_'+roomid);
		$('div.messages',$room).html("");
	}
	function disconn(obj) {
		$obj = $(obj);
		var m = $obj.closest('div.chat-room').attr("id").split('id_');
		var roomid  = m[1];
		var $room=$('#id_'+roomid);
		console.log('클릭');
		$room.remove();
	}
		
</script>
</html>