<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<%@include file="/WEB-INF/inc/top.jsp"%>
<style>
.page-header {
	margin-top: 50px;
	font-size: 25px;
	margin-left: 175px;
}

.row {
	margin-bottom: 30px;
}

.aLink {
	color: black;
	text-decoration: none;
}

.info {
	margin-left: 175px;
}
</style>
</head>

<body class="bg-gradient-default bg-yellow">
	<div class="container">
		<form:form action="/signup/resultpw" method="post"
			modelAttribute="member">
			<div class="row justify-content-center" style="margin-top: 150px">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="page-header">비밀번호찾기</div>
					<br>
					<div class="info">아이디와 가입시 등록하신 이메일 주소를 입력하고, 인증번호를 입력하세요.</div>
					<br> <br>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<label for="memId">아이디</label> <input type="text"
								class="form-control" id="memId" name="memId" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<label for="memMail">이메일</label> <input type="text"
								class="form-control" id="memMail" name="memMail" /><br>
							<button id="sendMailBtn" class="btn btn-default bg-yellow mb-2"
								onclick="fn_mail()" style="float: right">인증메일발송</button>
						</div>
					</div>
					<div class="row">
						<div class="col-md-9"></div>
						<div class="col-md-2">
							<a href="/" class="btn btn-default bg-yellow" id="cancleButton">취소</a>
							<button type="submit" class="btn btn-warning" id="nextButton">찾기</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<div class="mb-5"></div>
	</div>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script>
var time = new Date();
var check;
function fn_mail() {
	event.preventDefault();
	var memMail = document.getElementById("memMail"); // 사용자 이메일 입력 
	var sendMailBtn = document.getElementById("sendMailBtn");
	var mailCheckDiv = document.createElement("div");	// 인증 div
	var mailVail = document.createElement("input"); // 인증키 입력란
	var checkBtn = document.createElement("button"); // 인증키 입력 확인 체크
	var checkMessage = document.createElement("p"); 	// 인증 확인 메세지
	mailVail.setAttribute("class","form-control mb-2");
	$.ajax({
		type : "POST",
		url : 'mail',
		data : {
			"mail" :memMail.value 
		},// parameter, 사용자가 입력한 이메일
		success : function(data) {
			check = data;
			time.setTime(time.getTime() + (1000 * 60*3));
			var timeDiv = document.createElement("div"); // 인증시간
			sendMailBtn.parentNode.appendChild(mailCheckDiv);
			mailCheckDiv.appendChild(mailVail);
			mailCheckDiv.appendChild(checkBtn);
			mailVail.setAttribute('id', 'mailVail');
			checkBtn.innerHTML = '확인';
			checkBtn.setAttribute("class","btn btn-default bg-yellow");
			checkBtn.setAttribute("style","float:right");
			var timer = setInterval(function(){
				var reTime = new Date();
				var min = Math.floor((time.getTime()-reTime.getTime())/1000/60);
				var sec = Math.floor((time.getTime()-reTime.getTime())/1000%60);
				mailCheckDiv.appendChild(timeDiv);
				timeDiv.innerHTML="";
				timeDiv.innerHTML ="<p style='color:blue;'>"+ min + "분 " + sec + "초</p>";
			},1000);
			if(sendMailBtn.innerHTML == "재전송"){
				
				sendMailBtn.parentNode.removeChild(mailCheckDiv);
				time = new Date();
				time.setTime(time.getTime() + (1000 * 60*3));
			}
			setTimeout(() => {
				sendMailBtn.innerHTML == "";
				sendMailBtn.innerHTML == "인증메일발송";
				clearInterval(timer);
				sendMailBtn.parentNode.removeChild(mailCheckDiv);
			}, 1000*60*3);
			sendMailBtn.innerHTML="재전송";
			var mailInput = document.getElementById("memMail").value;
			var mailTd = document.getElementById("mailTd");
			var mailKey = data;
			console.log(mailKey)
			checkBtn.onclick = function() {
				event.preventDefault();
				var inputNum = mailVail.value;
				console.log("입력:"+inputNum+"메일: " +check);
				if (inputNum == check) {
					clearInterval(timer);
					mailCheckDiv.removeChild(timeDiv);
					mailCheckDiv.appendChild(checkMessage);
					checkMessage.innerHTML="인증번호가 일치합니다."
					checkMessage.setAttribute("style","color:blue; font-size:smaller");
				} else {
					if(checkMessage.innerHTML.length > 0){
						mailCheckDiv.removeChild(checkMessage);
					}
					mailCheckDiv.appendChild(checkMessage);
					checkMessage.innerHTML="인증번호가 다릅니다."
					checkMessage.setAttribute("style","color:red; font-size:smaller");
				}
		}}
	});
}
</script>
</html>