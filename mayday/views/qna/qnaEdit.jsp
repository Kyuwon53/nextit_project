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
	<section class="py-5">
		<div class="container-xxl px-5 my-5">
			<div class="card-body">
				<article>

					<c:if test="${e ne null }">
						<div class="">해당 글이 존재하지 않거나, 조회수 증가 실패.</div>
					</c:if>
					<c:if test="${e eq null }">
					<form:form action="/qna/qnaModify" method="post" modelAttribute="qna">
						<header class="mb-4">
							<h2 class="fw-bolder mb-4"><textarea class="form-control" rows="5" name="boTitle">${qna.boTitle }</textarea></h2>
							<div class="text-muted d-inline fst-italic mb-2">글번호
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2"><form:hidden path="boNo"/>${qna.boNo}
								&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">작성날짜
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${qna.boRegDate }&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">상태
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${qna.boCheck eq "Y" ? "답변 완료" :"답변 처리중" }&nbsp;&nbsp;&nbsp;</div>
							<c:if test="${qna.expertName ne null }">
								<div class="text-muted d-inline fst-italic mb-2">작성자
									&nbsp;&gt;</div>
								<div class="text-decoration-none d-inline fst-italic mb-2">${qna.expertName }&nbsp;&nbsp;&nbsp;</div>
							</c:if>
						</header>
						<section class="mb-5">
							<hr>
							<h5 class="mb-5 mt-5">
								<i class="fas fa-search fa-lg"></i>&nbsp;&nbsp;&nbsp;${qna.boTitle }
							</h5>
							<hr>
							<div class="fs-5 mt-5 mb-5">
								<form:textarea  cssClass="form-control" rows="30" path="boContent"></form:textarea>
								<form:errors path="boContent" cssStyle="color:red; font-size:small" />
							</div>
							<div class="row">
								<div class="col-md-10"></div>
								<div class="col-md-2">
									<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
										<a href="/qna/qnaEdit?boNo=${qna.boNo }"
											class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
											class="far fa-edit"></i>&nbsp;&nbsp;수정</a>
									</c:if>
									<button type="submit" class="btn btn-outline-warning bg-white link-light text-dark text-center">
												<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;저장</span>
									</button>
									<a href="/qna/qnaList"
										class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
										class="fas fa-list"></i>&nbsp;&nbsp;목록</a>
								</div>
							</div>
						</section>
					</form:form>
					</c:if>
				</article>
			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>