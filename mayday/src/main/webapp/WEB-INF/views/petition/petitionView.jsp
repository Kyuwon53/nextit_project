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


				<c:if test="${e ne null }">
					<div>해당 글이 존재하지 않거나, 조회수 증가 실패.</div>
				</c:if>

				<c:if test="${e eq null }">
					<header class="mb-4">
						<h2 class="fw-bolder mb-4"><i class="fas fa-quote-left"></i>
						&nbsp;&nbsp;${petition.petTitle }&nbsp;&nbsp;<i class="fas fa-quote-right"></i>
						</h2>
						<hr>
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
						<div class="text-decoration-none d-inline fst-italic mb-2">${petition.petProgress }&nbsp;&nbsp;&nbsp;</div>
					</header>
					<hr>
					<section class="mb-5">
						<div class="fs-6 mt-5 mb-5">${fn:replace(petition.petContent,newLine, "<br/>")}</div>
						<div class="row">
							<div class="col-md-4 pr-1">
								<a href="/petition/petitionList"
									class="btn btn-outline-warning bg-white link-light text-dark text-center">
									<i class="fas fa-list"></i>&nbsp;&nbsp;목록
								</a> <a href="${petition.petLink } " target="_blank"
									class="btn btn-outline-warning bg-white link-light text-dark text-center">
									<i class="fas fa-university"></i>&nbsp;&nbsp;청원하러가기
								</a>
								<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
									<a
										class="btn btn-outline-warning bg-white link-light text-dark text-center"
										href="/petition/petitionEdit?boNo=${petition.boNo }"><i
										class="fas fa-list"></i>&nbsp;&nbsp;수정</a>
									</a>
								</c:if>
							</div>
						</div>
					</section>

				</c:if>
			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>