<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>FAQ</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body>
	<div class="container text-center">
		<section class="py-5">
			<div class="container px-5 my-5">
				<div class="card-body">
					<form:form action="/faq/faqModify" method="post"
						modelAttribute="faq">
						<article>
							<c:if test="${e ne null }">
								<div class="">해당 글이 존재하지 않거나, 조회수 증가 실패.</div>
							</c:if>
							<c:if test="${e eq null }">
								<header class="mb-4">
									<h1 class="fw-bolder mb-4">
										<form:input class="form-control" path="boTitle"></form:input>
									</h1>
									<div class="row">
										<div class="col-md-2">
											<div class="text-muted d-inline fst-italic mb-2">글번호
												&nbsp;&gt;</div>
											<div class="text-decoration-none d-inline fst-italic mb-2">${faq.boNo}<form:hidden
													path="boNo" />
												&nbsp;&nbsp;&nbsp;
											</div>
										</div>
										<div class="col-md-1 text-muted d-inline fst-italic mb-2">구분
											&nbsp;&gt;</div>
										<div class="col-md-2 text-decoration-none d-inline fst-italic mb-2">
											<form:hidden path="codeParentCd" class="text-decoration-none" />
											<form:hidden path="memName" class="text-decoration-none" />
											<c:if test="${faq.codeParentCd eq'FAQ10 ' }">
												<form:select path="faqCd" class="custom-select form-control ">
													<form:option value="">--선택하세요--</form:option>
													<form:options items="${codeList }" itemLabel="codeName"	itemValue="codeCd"></form:options>
												</form:select>
											</c:if>
											<c:if test="${faq.codeParentCd ne'FAQ10 ' }">	 ${faq.codeName }
												<form:hidden path="faqCd" />
											</c:if>
										</div>

										<div class="col-md-2">
											<div class="text-muted d-inline fst-italic mb-2">작성자&nbsp;&gt;</div>
											<div class="text-decoration-none d-inline fst-italic mb-2">${faq.memName }&nbsp;&nbsp;&nbsp;</div>
										</div>
										<div class="col-md-2">
											<div class="text-muted d-inline fst-italic mb-2">조회수 &nbsp;&gt;</div>
											<div class="text-decoration-none d-inline fst-italic mb-2">${faq.boHit }&nbsp;&nbsp;&nbsp;</div>
										</div>
									</div>
								</header>
								<section class="mb-5">
									<hr>
									<h5 class="mb-5 mt-5">
										<textarea class="form-control" rows="5" name="boQue">${faq.boQue }</textarea>
										<form:errors path="boQue" cssStyle="color:red; font-size:small" />
									</h5>
									<hr>
									<div class="fs-5 mt-5 mb-5">
										<form:textarea cssClass="form-control" rows="30"
											path="boContent"></form:textarea>
										<form:errors path="boContent" cssStyle="color:red; font-size:small" />
									</div>
									<div class="row">
										<div class="col-md-7"></div>
										<div class="col-md-5">
											<a	href="/faq/${faq.codeParentCd eq 'FAQ10 '?'precedents':  faq.codeParentCd eq 'FAQ20 '?'counsel':	'question'}/faqList"
												class="btn btn-outline-warning bg-white link-light text-dark text-center">
												<i	class="fas fa-list"></i>&nbsp;목록</a> 
												<a	href="/faq/faqView?boNo=${faq.boNo}" class="btn btn-outline-warning bg-white link-light text-dark text-center">
												<i class="fas fa-chevron-left"></i>&nbsp;해당 뷰</a>
											<button type="submit" class="btn btn-outline-warning bg-white link-light text-dark text-center">
												<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;저장</span>
											</button>
											<button type="submit" formaction="/faq/faqDelete" class="btn btn-outline-warning bg-white link-light text-dark text-center">
												<span aria-hidden="false"><i class="far fa-trash-alt"></i>&nbsp;삭제</span>
											</button>
										</div>
									</div>
								</section>
							</c:if>
						</article>
					</form:form>
				</div>
			</div>
		</section>
	</div>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>