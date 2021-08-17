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
<div class="container text-center">
	<section class="py-5">
	   <div class="container px-5 my-5 ">
	      <div class="card-body">
	           <h1 class="fw-bolder">MAYDAY</h1>
	           <p class="lead fw-normal text-muted mb-0"> 글 작성중</p>
				<form:form action="/faq/faqRegist" method="post" modelAttribute="faq" >
	      <article>
	      
			<c:if test="${e ne null }">
				<div class="">해당 글이 존재하지 않거나, 조회수 증가 실패. </div>
			</c:if>
				<c:if test="${e eq null }">
					<header class="mb-4">
						<h1 class="fw-bolder mb-4">${faq.boTitle }</h1>
						<div class="row">
							<div class="col-md-3 text-muted d-inline fst-italic mb-2">구분 &nbsp;&gt;
								<form:hidden path="codeParentCd" class="text-decoration-none" />
								<div class="text-decoration-none d-inline fst-italic mb-2">
								 > ${parentCodeName.codeName }
								</div>
								<form:select path="faqCd"  class="custom-select form-control ">
								<form:option value="">선택하세요</form:option>
								<form:options items="${codes}" itemLabel="codeName" itemValue="codeCd"></form:options>
								</form:select>
							</div>
						</div>
					</header>
					<section class="mb-5">
						<form:input path="boTitle"  class="form-control d-inline" placeholder="제목을 입력하세요"/><form:errors path="boTitle" cssStyle="color:red; font-size:small"></form:errors>
						<hr>
						<h5 class="mb-5 mt-5"><textarea class="form-control" rows="3" name="boQue" placeholder="질문을 입력하세요 ">${faq.boQue }</textarea><form:errors path="boQue" cssStyle="color:red; font-size:small"></form:errors></h5>
						<hr>
						<div class="fs-5 mt-5 mb-5">
							<textarea  class="form-control" rows="25" name="boContent" placeholder="내용을 입력하세요">${faq.boContent }</textarea><form:errors path="boContent" cssStyle="color:red; font-size:small"></form:errors>
						</div>
						<div class="row">
							<div class="col-md-8"></div>
							<div class="col-md-4 text-right">
							<a href="/faq/${faq.codeParentCd eq 'FAQ10 '?'precedents':
												  faq.codeParentCd eq 'FAQ20 '?'counsel':
												  	'question'}/faqList" class="btn btn-outline-warning bg-white link-light text-dark text-center"><i class="fas fa-list"></i>&nbsp;목록</a>
								<button type="submit" class="btn btn-outline-warning bg-white link-light text-dark text-center" >
								<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;작성</span></button>
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
<%@include file="/WEB-INF/inc/footer.jsp"%>s
</body>
</html>