<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>petition</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body>
<section class="py-5">
		<div class="container-xxl px-5 my-5">
			<div class="card-body">
				<article>
		<c:if test="${e ne null }">
			<div>해당 글이 존재하지 않거나, 조회수 증가 실패.</div>
		</c:if>
		<c:if test="${e eq null }">
			<form:form action="/petition/petitionModify" method="post" modelAttribute="petition">
				<header class="mb-4">
	<h2 class="fw-bolder mb-4">${petition.petTitle }</h2>
		<form:hidden path="boNo"/>
	<div class="text-muted d-inline fst-italic mb-2">글번호
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${petition.boNo }
								&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">청원 시작
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${petition.petStartDate }&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">청원 마감
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${petition.petEndDate }&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">상태
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">	<form:select path="petProgress">
							<form:option value="청원진행중">청원진행중</form:option>
							<form:option value="청원진행중">청원마감</form:option>
							</form:select></div>
						</header>
	
	<div class="form-group purple-border">
  <textarea disabled="disabled" name="boContent" class="form-control" id="exampleFormControlTextarea4" rows="20" style="width: 920px">${petition.petContent }</textarea>
</div>
				<div class="row">
	<div class="col-md-4 pr-1">
	<a href="/petition/petitionList" class="btn btn-outline-warning bg-white link-light text-dark text-center">
	<i class="fas fa-list"></i>&nbsp;&nbsp;목록</a>
	<a href="/petition/petitionView?boNo=${petition.boNo}" class="btn btn-outline-warning bg-white link-light text-dark text-center">
	<i class="fas fa-list"></i>&nbsp;&nbsp;해당 뷰</a>
	<button class="btn btn-outline-warning bg-white link-light text-dark text-center" type="submit">
						<span aria-hidden="true"><i class="fas fa-list"></i>&nbsp;&nbsp;저장</span>
					</button>
					<button class="btn btn-outline-warning bg-white link-light text-dark text-center" type="submit" formaction="/petition/petitionDelete">
						<span aria-hidden="true"><i class="fas fa-list"></i>&nbsp;&nbsp;삭제</span>
					</button>
	</div>
</div>	
			</form:form>
		</c:if>
	</div>
	</div>
	</section>
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>