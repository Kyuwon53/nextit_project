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
						<section class="py-5">
							<div class="container px-5 my-5">
								<div class="text-center mb-5">
									<h1 class="fw-bolder">
										<i class="far fa-lightbulb"></i>&nbsp;MAYDAY를 이용해주셔서 감사합니다!
									</h1>
									<div class="error mx-auto text-center"
										data-text="${resultMessage.code}">${resultMessage.code}</div>
								</div>
								<div class="row gx-5 justify-content-center ">
									<!-- Pricing card free-->
									<div class="col-lg-6 col-xl-4 ">
										<div class="card mb-5 mb-xl-0">
											<div class="card-body p-5">
												<div class="small text-uppercase fw-bold text-muted">result</div>
												<div class="mb-3">
													<span class="display-4 fw-bold">${resultMessage.title }</span>
													<span class="text-muted"></span>
												</div>
												<ul class="list-unstyled mb-4">
													<li class="mb-2"><i class="bi bi-check text-primary"></i>
														<strong>${resultMessage.message }</strong></li>
												</ul>
												<c:if test="${resultMessage.url ne null }">
													<a class="btn btn-outline-primary"
														href="<c:url value='${resultMessage.url}'/>">&nbsp;${resultMessage.urlTitle}</a>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>
					</c:if>
					<c:if test="${e eq null }">
						<header class="mb-4">
							<h2 class="fw-bolder mb-4">${qna.boTitle }</h2>
							<div class="text-muted d-inline fst-italic mb-2">글번호
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${qna.boNo}
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
							<c:if
								test="${qna.boCheck eq 'N' and sessionScope.USER_INFO.userRole ne 'CLIENT' and qna.groupLayer eq 0}">
								<a class="btn btn-sm btn-outline-info"
									href="/qna/qnaForm?memId=${qna.memId}&boNo=${qna.boNo}"><span
									aria-hidden="true"></span> <i class="fas fa-pencil-alt"></i>&nbsp;답글쓰기</a>
							</c:if>
						</header>
						<section class="mb-5">
							<hr>
							<h5 class="mb-5 mt-5">
								<i class="fas fa-search fa-lg"></i>&nbsp;&nbsp;&nbsp;${qna.boTitle }
							</h5>
							<hr>
							<div class="">
								<div class="fs-6 mt-5 mb-5">${fn:replace(qna.boContent,newLine, "<br/>")}</div>
							</div>
							<div class="row">
								<div class="col-md-10"></div>
								<div class="col-md-2">
									<c:if
										test="${(sessionScope.USER_INFO.userId eq qna.memId) or sessionScope.USER_INFO.userId eq qna.ansMemId}">
										<a href="/qna/qnaEdit?boNo=${qna.boNo }"
											class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
											class="far fa-edit"></i>&nbsp;&nbsp;수정</a>
										<button type="button" id="btn_delete"
											class="btn btn-outline-warning bg-white link-light text-dark text-center">
											<i class="far fa-trash-alt del"></i>&nbsp;&nbsp;삭제
										</button>
										<!-- <a href="/qna/qnaDel">삭제하기</a> -->
									</c:if>
									<a href="/qna/qnaList"
										class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
										class="fas fa-list"></i>&nbsp;&nbsp;목록</a>
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
<script type="text/javascript">
var params = {"boNo":${qna.boNo}, "groupLayer":${qna.groupLayer},"boCheck":"${qna.boCheck}"}
$(document).ready(function() {
	$('#btn_delete').on('click',function(e){
		if(confirm("글을 삭제하시겠습니까?")){
			$.ajax({
				type: 'POST'
				,url:'<c:url value="/qna/qnaDelete"/>'
				,data: params
				,success: function (data) {
					alert("삭제되었습니다.");
					location.href="/qna/qnaList";
				}//success
				,error: function (req, st,err) {
					console.log("req: "+req.state+"st: "+ "에러 "+err);
				}// error
			});//ajax
		}
	});
});
</script>
</html>