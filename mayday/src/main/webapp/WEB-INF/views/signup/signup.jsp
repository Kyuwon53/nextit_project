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
div#member {
	margin: 15px;
	margin-top: 150px;
}

.bgSizeCover {
	background-size: cover;
	width: 100%;
	height: 300px;
	resize: both;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-5 " id="member">
				<div class="card h-100 shadow border-0">
					<img id="memImg" class="card-img-top shadow bgSizeCover" alt="..." />
					<div class="card-body p-4">
						<p align="center" style="font-size: 25px; font: bolder;">의뢰인이신가요?</p>
						<p align="center">MAYDAY의 변호사님들 중</p>
						<p align="center">나에게 꼭 맞는 변호사를 찾아보세요.</p>
					</div>
					<div class="card-footer p-4 pt-0 bg-transparent border-top-0">
						<div class="d-flex justify-content-center">
							<a href="/signup/signup1-2"
								class="btn btn-class-default text-center bg-blue shadow">일반회원
								회원가입</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-5 " id="member">
				<div class="card h-100 shadow border-0">
					<img id="lawImg" class="card-img-top shadow bgSizeCover" alt="..." />
					<div class="card-body p-4">
						<p align="center" style="font-size: 25px; font: bolder;">변호사이신가요?</p>
						<p align="center">MAYDAY의 좋은 변호사가 되어</p>
						<p align="center">의뢰인들을 만나보세요.</p>
					</div>
					<div class="card-footer p-4 pt-0 bg-transparent border-top-0">
						<div class="d-flex justify-content-center">
							<a href="/signup/signup1"
								class="btn btn-class-default text-center bg-yellow shadow">
								변호사/노무사 회원가입</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script>
	var randomNum = Math.ceil(Math.random() * 25);
	var mem_Img = "/resource/image/randomimage/image" + randomNum + ".jpg";
	var law_Img = "/resource/image/randomimage/image" + (randomNum + 1)
			+ ".jpg";
	console.log(randomNum);
	$("#memImg").attr("src", mem_Img);
	$("#lawImg").attr("src", law_Img);
</script>
</html>