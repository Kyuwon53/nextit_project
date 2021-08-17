<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<%@ include file="/WEB-INF/inc/header.jsp"%>
	<%@include file="/WEB-INF/inc/top.jsp"%>
<style>
	.page-header{
			margin-top: 100px;
			margin-bottom: 50px;
	}
	.btn{
		margin-top: 10px;
		margin-bottom: 10px;
	}
	.form-control{
		margin : 10px;
	}
	.sign_warning{
		margin-left: 5%;
		font-size: 0.75em;
		font-style:italic;
		font: bolder;
		color: red;
		float:right;
	}
</style>
</head>
	<body>
	<div class="container">
		<div class="page-header">
			<center><h1>일반회원 회원가입</h1></center>
		</div>
		<form:form action="/signup/registMember" method="post">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<input type="hidden" id = "idCheckOk" name = "idCheckOk"/>
					<label for="memId">아이디</label>
					<input type="text" class="form-control" id="memId" name="memId" onblur="fn_validate()" />
					<div id = "info" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memPass">비밀번호</label>
					<input type="password" class="form-control" id="memPass" name="memPass" onblur="fn_check()"/>
					<div id = "passInfo" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memPassCk">비밀번호 확인</label>
					<input type="password" class="form-control" id="memPassCk" name="memPassCk" onblur="fn_check()"/>
					<div id = "passCk" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memName">성명</label>
					<input type="text" class="form-control" id="memName" name="memName" onblur="fn_check()"/>
					<div id = "nameInfo" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memBir">생년월일</label>
					<input type="date" class="form-control" id="memBir" name="memBir" onblur="fn_check()"/>
					<div id = "birInfo" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memPhone">전화번호</label>
					<input type="text" class="form-control" id="memPhone" name="memPhone" onblur="fn_check()"/>
					<div id = "phoneInfo" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memMail">이메일</label>
					<input type="text" class="form-control" id="memMail" name="memMail" onblur="fn_check()"/>
					<div id = "mailInfo"  class="sign_warning"></div>
					<button id="sendMailBtn" class="btn btn-default bg-yellow" onclick="fn_mail()" style="float:right">인증메일발송</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memAdd1">주소</label>
					<input type="button" class="btn btn-default bg-yellow" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="button" class="form-control text-start" id="btn_add" onblur="fn_check()" onclick="sample4_execDaumPostcode()"/>
					<input type="hidden" id="memAdd1" name="memAdd1"/>
					<div id = "add1Info" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<label for="memAdd2">상세 주소</label>
					<input type="text" class="form-control" id="memAdd2" name="memAdd2" onblur="fn_check()"/>
					<div id = "add2Info" class="sign_warning"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-7"></div>			
				<div class="col-md-1" >
					<a href="/" class="btn btn-default bg-yellow" style="float:right;" id= "cancleButton" >취소</a>
				</div>
				<div class="col-md-1" >
					<button type="submit" class="btn btn-warning" id = "nextButton" style="float:right">가입</button>
				</div>
			</div>
			<br>
			<br>
			<br>
			<br>
		</form:form>
		</div>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</body>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript">
	var now = new Date(); //.substring(0, 10);
	var year = now.getFullYear();
	var date = year-15+" "+now.getMonth()+" "+now.getDate();
	var maxDate = date.split(' ');
	var max = new Date(maxDate[0],maxDate[1],maxDate[2]).toISOString().substring(0, 10);
		$('#memBir').attr('max',max)
		function fn_check() {
			event.preventDefault();
			var passPattern= /[a-z0-9]{4,15}/;
				if($("#memPass").val()==""){
					$("#passInfo").text("비밀번호를 입력해주세요.")
					return false;
				}else if (!passPattern.test($("#memPass"))){
					$("#memPass").val("")
					$("#passInfo").text("비밀번호 형식이 틀렸습니다. 다시 입력해주세요.")
					return false;	
				}else if ($("#memPass").val()!=""){
					$("#passInfo").text("사용 가능")
				}
				if($("#memPassCk").val()!=$("#memPass").val()){
					$("#passCk").text("비밀번호를 다시 확인해주세요.")
					 document.getElementById("memPassCk").value="";
					return false;
				}else if ($("#memPassCk").val()==$("#memPass").val()){
					$("#passCk").text("사용 가능")
				}
				if($("#memName").val()==""){
					$("#nameInfo").text("이름을 입력해주세요.")
					return false;
				}else if ($("#memName").val()!=""){
					$("#nameInfo").text("사용 가능")
				}
				if($("#memBir").val()==""){
					$("#birInfo").text("생년월일을 입력해주세요.")
					return false;
				}else if ($("#memBir").val()!=""){
					$("#birInfo").text("사용 가능")
				}
				if($("#memPhone").val()==""){
					$("#phoneInfo").text("전화번호를 입력해주세요.")
					return false;
				}else if ($("#memPhone").val()!=""){
					$("#phoneInfo").text("사용 가능")
				}
				if($("#memMail").val()==""){
					$("#mailInfo").text("메일을 입력해주세요.")
					return false;
				}else if ($("#memMail").val()!=""){
					$("#mailInfo").text("사용 가능")
				}
				if($("#btn_add").val()==""){
					$("#add1Info").text("주소를 입력해주세요.")
					return false;
				}else if ($("#btn_add").val()!=""){
					$("#memAdd1").val($("#btn_add").val()) 
					$("#add1Info").text("사용 가능")
				}
				if($("#memAdd2").val()==""){
					$("#add2Info").text("상세주소를 입력해주세요.")
					return false;
				}	else if ($("#memAdd2").val()!=""){
					$("#add2Info").text("사용 가능")
				}
		}
		
		function fn_validate() {
			event.preventDefault();
			var id = document.getElementById("memId").value
			var pattern= /^[a-z]+[a-z0-9]{3,19}$/g;
			$.ajax({
				type : "POST",
				url : 'idVal',
				data : {
					"id" : id
				},
				success : function(data) {
					if (id == ""){
						$("#idCheckOk").val("none")
						$("#info").text("아이디를 입력해주세요.")
					}else if (data != "success") {
						if(!pattern.test(id)){
							$("#idCheckOk").val("")
							$("#info").text("아이디 사용불가")
							$("#memId").val("")
						}else{
							$("#idCheckOk").val("ok") 
							$("#info").text("사용가능")
						}
					} 
					else {
						$("#idCheckOk").val("")
						$("#info").text("아이디 중복")
						$("#memId").val("")
					}
				},
				error : function(e) {
					
				}
			})
		}
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
			mailVail.setAttribute("class","form-control");
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
		 function sample4_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var roadAddr = data.roadAddress; // 도로명 주소 변수
		                var extraRoadAddr = ''; // 참고 항목 변수

		                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                    extraRoadAddr += data.bname;
		                }
		                // 건물명이 있고, 공동주택일 경우 추가한다.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		           
		               

		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		               if(roadAddr == ' '){
		            	   
		                document.getElementById("btn_add").value = data.jibunAddress;
		            	   
		               }else {						
		                document.getElementById("btn_add").value = roadAddr;
					}
		                
		               document.getElementById("memAdd2").focus();
		             
		            }
		        }).open();
		    }
	</script>
</html>