<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>${parentCd }</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body>
	<div class="container text-center">
		<section class="py-5">
			<div class="container px-5 my-5 ">
				<div class="card-body">
					<h1 class="fw-bolder">MAYDAY</h1>
					<p class="lead fw-normal text-muted mb-0">글 작성중</p>
					<form:form action="/board/boardRegist" method="post"
						modelAttribute="board">
						<article>
							<c:if test="${e ne null }">
								<div class="">해당 글이 존재하지 않거나, 조회수 증가 실패.</div>
							</c:if>
							<c:if test="${e eq null }">
								<header class="mb-4">
									<h1 class="fw-bolder mb-4">${board.boTitle }</h1>
									<div class="row">
										<div class="col-md-1 text-muted d-inline mb-2 my-4">작성자&nbsp;&gt;</div>
										<div
											class="col-md-1	 text-muted d-inline fst-italic mb-2 my-4">
											<input
												class="text-muted d-inline custom-text form-control mb-2"
												type="hidden" value="${sessionScope.USER_INFO.userId}"
												name="memId">${sessionScope.USER_INFO.userId}</div>
										<div class="col-md-8"></div>
										<div class="col-md-2 text-muted d-inline fst-italic mb-2">
											구분 &nbsp;&gt;
											<form:hidden path="codeParentCd" class="text-decoration-none" />
											<div class="text-decoration-none d-inline fst-italic mb-2">
												> ${parentCodeName.codeName }</div>
											<form:hidden path="codeParentCd" />
											<form:hidden path="codeParentCd" />
											<form:select path="boCd" class="custom-select form-control ">
												<form:option value="">선택하세요</form:option>
												<form:options items="${codes}" itemLabel="codeName"
													itemValue="codeCd"></form:options>
											</form:select>
										</div>
										<div id="boCdInfo"
											class="fst-italic text-warning text-center col-md-12 pb-5 h-5 d-flex justify-content-end"></div>
									</div>
								</header>
								<hr>
								<section class="mb-5">
									<form:input path="boTitle" class="form-control d-inline"
										placeholder="제목을 입력하세요" />
									<div id="boTitleInfo"
										class="col-md-3 text-warning d-inline text-reft d-flex justify-content-start"></div>
									<hr>
									<div class="fs-5 mt-3 mb-3">
										<textarea class="form-control" id="boContent" rows="25"
											name="boContent" placeholder="내용을 입력하세요">${board.boContent }</textarea>
									</div>
									<div id="boContentInfo"
										class="text-warning d-inline text-reft col-md-6 d-flex justify-content-start"></div>
									<div class="row">
										<div class="col-md-8"></div>
										<div class="col-md-4 text-right">
											<a
												href="/board/${board.codeParentCd eq 'BOD10 ' ? 'question':
													board.codeParentCd eq 'BOD20 '? 'free':
													'notice'}/boardList"
												class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
												class="fas fa-list"></i>&nbsp;목록</a>
											<button id="btnSave"
												class="btn btn-outline-warning bg-white link-light text-dark text-center">
												<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;작성</span>
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
<script>
	$(function() {
		$('#btnSave').click(function(e) {
			e.preventDefault();
			if (fn_check() == true) {
				document.forms[0].submit();
			} else {
				alert('정보를 입력해주시기바랍니다.');
			}
		})

	})

	function fn_check() {
		var isOk = true;
		event.preventDefault();
		if ($("#boCd").val() == "") {
			$("#boCdInfo").text("선택해주세요.")
			isOk = false;
		} else if ($("#boCd").val() != "") {
			$("#boCdInfo").text("")
		}

		if ($("#boTitle").val() == "") {
			$("#boTitleInfo").text("제목을 입력해주세요.")
			isOk = false;
		} else if ($("#boTitle").val() != "") {
			$("#boTitleInfo").text("")
		}
		if ($("#boContent").val() == "") {
			$("#boContentInfo").text("내용을 입력해주세요.")
			isOk = false;
		} else if ($("#boContent").val() != "") {
			$("#boContentInfo").text("")
		}
		return isOk;

	}
</script>



</html>