<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>News - 등록</title>
</head>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<body>
	<div class="container text-center">
		<section class="py-5">
			<div class="container px-5 my-5 ">
				<div class="card-body">
					<h1 class="fw-bolder">MAYDAY</h1>
					<p class="lead fw-normal text-muted mb-0">글 작성중</p>
					<form:form action="newsRegist" method="post" modelAttribute="news">
						<header class="mb-4">
							<h1 class="fw-bolder mb-4">${faq.boTitle }</h1>
							<div class="row">
								<div class="col-md-3 text-muted d-inline fst-italic mb-2">
									구분 &nbsp;&gt;
									<form:select path="boCd">
										<form:option value="">-- 선택하세요--</form:option>
										<form:options items="${codeList}" itemLabel="codeName"
											itemValue="codeCd" />
									</form:select>
								</div>
							</div>
						</header>
						<section class="mb-5">
							<form:input path="newsTitle" class="form-control d-inline"	placeholder="제목을 입력하세요" />
							<hr>
							<form:input path="newsPress" class="form-control d-inline" placeholder="언론사를 입력하세요 " />
							<br>
							<form:input path="newsLink" class="form-control d-inline"	placeholder="뉴스 링크 입력하세요" />
							<hr>
							<div class="fs-5 mt-5 mb-5">
								<textarea class="form-control" rows="25" name="newsContent" placeholder="내용을 입력하세요">${faq.boContent }</textarea>
							</div>
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-4 text-right">
									<a href="newsList" class="btn btn-outline-warning bg-white link-light text-dark text-center">
									<i 	class="fas fa-list"></i>&nbsp;목록</a>
									<button type="submit" class="btn btn-outline-warning bg-white link-light text-dark text-center">
										<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;작성</span>
									</button>
								</div>
							</div>
						</section>
					</form:form>
				</div>
			</div>
		</section>
	</div>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>