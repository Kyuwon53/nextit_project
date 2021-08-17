<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>FAQ</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body>
<section class="py-5">
   <div class="container-xxl px-5 my-5">
      <div class="card-body">
	      <article>
		      
		<c:if test="${e ne null }">
			<div class="">해당 글이 존재하지 않거나, 조회수 증가 실패. </div>
		</c:if>
			<c:if test="${e eq null }">
				<header class="mb-4">
					<h2 class="fw-bolder mb-4">${faq.boTitle }</h2>
					<div class="text-muted d-inline fst-italic mb-2">글번호 &nbsp;&gt;</div>
					<div class="text-decoration-none d-inline fst-italic mb-2">${faq.boNo}  &nbsp;&nbsp;&nbsp;</div>
					<div class="text-muted d-inline fst-italic mb-2">구분 &nbsp;&gt;</div>
					<div class="text-decoration-none d-inline fst-italic mb-2">${faq.codeName}&nbsp;&nbsp;&nbsp;</div>
					<div class="text-muted d-inline fst-italic mb-2">작성자 &nbsp;&gt;</div>
					<div class="text-decoration-none d-inline fst-italic mb-2">${faq.memName }&nbsp;&nbsp;&nbsp;</div>
					<div class="text-muted d-inline fst-italic mb-2">조회수 &nbsp;&gt;</div>
					<div class="text-decoration-none d-inline fst-italic mb-2">${faq.boHit }&nbsp;&nbsp;&nbsp;</div>
				</header>
				<section class="mb-5">
					<hr>
					<h5 class="mb-5 mt-5"><i class="fas fa-search fa-lg"></i>&nbsp;&nbsp;&nbsp;${faq.boQue }</h5>
					<hr>
					<div class="">
						<div class="fs-6 mt-5 mb-5">${fn:replace(faq.boContent,newLine, "<br/>")}</div>
					</div>
					<div class="row">
						<div class="col-md-10"></div>
						<div class="col-md-2">
							<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}"  >
							<a href="/faq/faqEdit?boNo=${faq.boNo }" class="btn btn-outline-warning bg-white link-light text-dark text-center"><i class="far fa-edit"></i>&nbsp;&nbsp;수정</a>
							</c:if>
							<a href="/faq/${faq.codeParentCd eq 'FAQ10 '?'precedents':
											  faq.codeParentCd eq 'FAQ20 '?'counsel':
											  	'question'}/faqList" class="btn btn-outline-warning bg-white link-light text-dark text-center"><i class="fas fa-list"></i>&nbsp;&nbsp;목록</a>
						</div>
					</div>
				</section>
			</c:if>
		</article>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>