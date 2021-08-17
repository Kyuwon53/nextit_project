<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>${resultMessage.title }</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body >
	<section class="py-5">
		<div class="container px-5 my-5">
			<div class="text-center mb-5">
				<h1 class="fw-bolder"><i class="far fa-lightbulb"></i>&nbsp;MAYDAY를 이용해주셔서 감사합니다.</h1>
				<div class="error mx-auto text-center" data-text="${resultMessage.code}">${resultMessage.code}</div>
			</div>
			<div class="row gx-5 justify-content-center ">
				<!-- Pricing card free-->
				<div class="col-lg-6 col-xl-4 ">
					<div class="card mb-5 mb-xl-0">
						<div class="card-body p-5">
							<div class="small text-uppercase fw-bold text-muted">result</div>
							<div class="mb-3">
								<span class="display-4 fw-bold">${resultMessage.title }</span>
								 <span	class="text-muted"></span>
							</div>
							<ul class="list-unstyled mb-4">
								<li class="mb-2"><i class="bi bi-check text-primary"></i> <strong>${resultMessage.message }</strong></li>
							</ul>
							<div class="d-grid">
							<c:if test="${resultMessage.url ne null }">
								<a class="btn btn-outline-primary" href="<c:url value='${resultMessage.url}'/>">&nbsp;${resultMessage.urlTitle}</a>
							</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>