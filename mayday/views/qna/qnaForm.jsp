<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>1 대 1 문의게시판</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body>
	<div class="container text-center">
		<section class="py-5">
			<div class="container px-5 my-5 ">
				<div class="card-body">
					<h1 class="fw-bolder">MAYDAY</h1>
					<p class="lead fw-normal text-muted mb-0">글 작성중</p>
					<form:form
						action="/qna/${sessionScope.USER_INFO.userRole eq 'CLIENT'? 'question':'answer'}/qnaRegist"
						method="post" modelAttribute="qna">
						<article>
							<c:if test="${sessionScope.USER_INFO.userRole eq 'CLIENT'}">
								<input class="text-muted d-inline custom-text form-control mb-2"
									type="hidden" value="${sessionScope.USER_INFO.userId}"
									name="memId">
							</c:if>
							<c:if test="${sessionScope.USER_INFO.userRole ne 'CLIENT'}">
								<input class="text-muted d-inline custom-text form-control mb-2"
									type="hidden" value="${param.memId }" name="memId">
								<input class="text-muted d-inline custom-text form-control mb-2"
									type="hidden" value="${sessionScope.USER_INFO.userId}"
									name="ansMemId">
								<input class="text-muted d-inline custom-text form-control mb-2"
									type="hidden" value="${param.boNo}" name="boNo">
							</c:if>
							<section class="mb-5">
								<form:input path="boTitle" class="form-control d-inline"
									placeholder="제목을 입력하세요" />
								<hr>
								<div class="fs-5 mt-5 mb-5">
									<textarea class="form-control" rows="25" name="boContent"
										placeholder="내용을 입력하세요"></textarea>
								</div>
								<div class="row">
									<div class="col-md-8"></div>
									<div class="col-md-4">
										<a href="/qna/qnaList"
											class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
											class="fas fa-list"></i>&nbsp;목록</a>
										<button type="submit"
											class="btn btn-outline-warning bg-white link-light text-dark text-center">
											<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;작성</span>
										</button>
									</div>
								</div>
							</section>
						</article>
					</form:form>
				</div>
			</div>
		</section>
	</div>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>