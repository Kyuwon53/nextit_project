<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<style>
.page-header {
	margin-top: 25px;
	font-size: 100px;
}

.row {
	margin-bottom: 30px;
}

.aLink {
	color: black;
	text-decoration: none;
}

a:visited {
	color: #000000;
	text-decoration: none;
}

a:hover {
	color: #000000;
	text-decoration: none;
}
</style>
</head>
<body class="bg-gradient-default bg-yellow">
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="row justify-content-center">
					<div class="card o-hidden border-0 shadow-lg my-5">
						<div class="page-header text-center">
								<a href="/" class="aLink">MAYDAY</a>
						</div>
						<form:form action="/login/login" method="POST">
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<div class="form-group">
										<label for="exampleInputEmail1"> 아이디 </label> <input
											type="text" class="form-control" name="userId"
											value="${cookie.SAVED_ID.value}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<label for="exampleInputPassword1">비밀번호</label> <input
										type="password" class="form-control" name="userPass">
								</div>
							</div>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-6">
									<label> <input type="checkbox" name="saveId" value="Y"
										<c:if test="${cookie.SAVED_ID ne null }">checked="checked"</c:if>>아이디
										저장
									</label>
								</div>
								<div class="col-md-2">
									<button type="submit" class="btn btn-block bg-yellow"
										style="float: right">로그인</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-4">
									<a href="/signup/findid" class="aLink">아이디 찾기</a> <a
										href="/signup/findpw" class="aLink ms-3">비밀번호 찾기</a>
								</div>
								<div class="col-md-4">
									<a href="/signup/signup1" class="aLink">변호사/노무사 회원가입</a> <a
										href="/signup/signup1-2" class="aLink" style="float: right">일반회원
										회원가입</a>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4"></div>


								<br>
								<br>
								<br>
								<br>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	if(${fe ne null }){
	var confirm =confirm(" 회원 정보가 없습니다. \n 회원가입 하시겠습니까?");
	if(confirm){
		location.href = "/signup/signup"
	}
		
	}
	if(${pe ne null}){
		alert("비밀번호가 일치 하지 않습니다.");
	}
	if(${ee ne null}){
		alert("휴면계정입니다.");
	}
</script>
</html>