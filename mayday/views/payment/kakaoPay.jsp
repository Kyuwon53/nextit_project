<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 서비스</title>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<style>
input.underline {border:0;BORDER-BOTTOM: teal 1px solid;
BORDER-LEFT: medium none;
BORDER-RIGHT: medium none;
BORDER-TOP: medium none;text-align:right;}
input[type="text"]:disabled { background: white; }
.aLink{
		color:black;
		text-decoration: none;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<div style="margin-top: 50px"></div>
		<div style="text-align:center; font-size:40px">결제하기</div>
		<div style="margin-bottom: 50px"></div>
		<input id="lawyerId" name="lawyerId" type="hidden" ></input>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<input class="form-control text-end underline" id="memId" name="memId" value="${member.memId }" readonly="readonly"/>
				<div style="margin-bottom: 30px"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<input class="form-control text-end underline" id="memName" name="memName" value="${member.memName }" readonly="readonly"></input>
				<div style="margin-bottom: 30px"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
	<input class="form-control text-end underline" id="memPhone" name="memPhone" value="${member.memPhone }" readonly="readonly"></input>
	<div style="margin-bottom: 30px"></div>
	</div>
	</div>
	<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
	<select id="itemName" name="itemName" class="custom-select custom-select-sm form-control form-control-sm">
		<option value="">--상담내용--</option>
		<option value="Call">전화 상담</option>
		<option value="Message">카카오톡 상담</option>
		<option value="Meeting">방문 상담</option>
	</select>
	<div id = "itemInfo" class="sign_warning"></div>
	<div style="margin-bottom: 30px"></div>
	<div class="row">
			<label for="resDate">예약일</label>
			<input type="datetime-local" class="form-control" id="resDate" name="resDate"/>
	</div>
	<div id = "dateInfo" class="sign_warning"></div>
	<div style="margin-bottom: 30px"></div>
	</div>
	</div>
	<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-3">
	<input type = "number" class="form-control underline" id="quantity" placeholder="이용시간 (10분~ 120분)" min="10" max="120" step="10" />
	</div>
	<div class="col-md-1">
	<button id="check" class="btn btn-default bg-yellow">확인</button>
	<div id = "quantityInfo" class="sign_warning"></div>
	<div style="margin-bottom: 30px"></div>
	</div>
	</div>
	<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-3">
	<input class="form-control underline" id="totalAmount" disabled></input>
	</div>
	<div class="col-md-3">
	<p style="margin-top: 5px">원</p>
	</div>
	<div id = "itemInfo" class="sign_warning"></div>
	</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div style="margin-bottom: 20px"></div>
	<div style="text-align: center">*방문 상담을 선택하셨을 경우 추가 금액이 발생할 수 있습니다.*</div>
	<div style="margin-bottom: 30px"></div>
	</div>
	</div>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-5">
			<button class="btn btn-default bg-yellow" style="float: right" id="kakao">카카오페이로 결제하기</button>
		</div>
		<div class="col-md-2">
			<button onclick="cancel()" class="btn btn-default">취 소</button>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">

var now = new Date(); //.substring(0, 10);
var year = now.getFullYear();
var day = now.getDate()+1;
var hour = now.getHours();
var minute = now.getMinutes();
var date = year+" "+now.getMonth()+" "+day+" "+hour+" "+minute;
var minDate = date.split(' ');
var min = new Date(minDate[0],minDate[1],minDate[2],minDate[3],minDate[4],0).toISOString().substring(0, 19);//.substring(0, 10);
var maxDay = now.getDate()+8;
var lastDate = year+" "+now.getMonth()+" "+maxDay+" "+hour+" "+minute;
var maxDate = lastDate.split(' ');
var max = new Date(maxDate[0],maxDate[1],maxDate[2],maxDate[3],maxDate[4],0).toISOString().substring(0, 19);
	$('#resDate').attr('min',min);
	$('#resDate').attr('max',max);
	$(document).ready(
			function() {
				var lawyerId = location.href.substr(location.href
						.lastIndexOf('=') + 1);
				document.getElementById("lawyerId").value = lawyerId;
				$("#kakao").click(function() {
					console.log("resDate", $("#resDate").val().toString());			
					
					var params = {
						"ID" : $("#memId").val(),
						"quantity" : $("#quantity").val(),
						"itemName" : $("#itemName").val(),
						"totalAmount" : $("#totalAmount").val(),
						"lawyerId" : lawyerId,
						"resDate" : $("#resDate").val().toString()
					};
					if($("#memId").val() != "" && $("#quantity").val() != "" && $("#totalAmount").val() != "" && $("#resDate").val()!="" ){
					$.ajax({
						url : 'kakao',
						dataType : 'json',
						data : params,
						success : function(data) {
							var box = data.next_redirect_pc_url;
							var web = window.open(box);
							web.onunload= function() {
							var payWin = window.setInterval(function(){
								if(web.closed || web == null){
								$.ajax({
									url : 'successpayment',
									dataType : 'json',
									data : params,
									success : function(data) {
										window.location.href =  data.url
										console.log(data);
									}
								})
									
								}
							},1000);
									
							
							}
							
						},
						error : function(error) {
							alert(error);
						}
					})
					}else{
						alert("다시 입력해 주세요.");
					}

				})
				
			});

	$("#check").click(function(e) {
		e.preventDefault();
		$("#totalAmount").val(1000 * $("#quantity").val());
	})
	 var f = $('#form_kakao');
	function cancel()  {
		  window.location.href = '/member/expertList'
		}
</script>
</html>