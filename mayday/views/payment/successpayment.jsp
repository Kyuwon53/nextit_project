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
<%@ include file="/WEB-INF/inc/top.jsp"%>
<style>
	.page-header{
		margin-top: 50px;
		font-size: 25px;
		margin-left:175px;
	}
	.row{
		margin-bottom: 30px;
	}
	.aLink{
		color:black;
		text-decoration: none;
	}
	.info{
		margin-left: 175px;
	}
</style>
</head>
<body class="bg-gradient-default bg-yellow">	
	<div class="container">
		<div class="row justify-content-center" style="margin-top: 150px">
			<div class="card o-hidden border-0 shadow-lg my-5 ">
				<div class="page-header">
					결제 성공
				</div>
			<br>
			<div class="info">
					결제에 성공하셨습니다.	
			</div>
			<br>
			<br>
			<div class="row">
			<div class="col-md-9"></div>
			<div class="col-md-2">
				<a href="/" class="btn btn-default bg-yellow" id= "cancleButton">홈으로</a>
			</div>
			</div>
					<div class = "mb-5"></div>
			</div>
		</div>
	</div>	
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>