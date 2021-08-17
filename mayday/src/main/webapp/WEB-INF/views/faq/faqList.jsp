<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>FAQ</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
<%@include file="/WEB-INF/inc/floating.jsp"%>
</head>
<body>
	<div class="container-xxl">
		<div class="text-center">
			<div class="mt-4 mb-4">
				<h1	class="h1 mb-2 text-gray-800">MAYDAY</h1>
			</div>
			<div class="card  mb-4">
				<div class="card-body">
					<form name="search" action="/faq/${parentCd }/faqList"
						method="post">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12 col-md-2">
									<input type="hidden" name="curPage"
										value="${searchVO.curPage }"> <input type="hidden"
										name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
									<c:if test="${parentCd eq 'precedents' }">

										<select id="id_searchCate" name="searchCate"
											class="custom-select custom-select-sm form-control form-control-sm ">
											<option value="">--전체--</option>
											<c:forEach items="${codeList }" var="code">
												<option value="${code.codeCd }"
													${faq.faqCd eq code.codeCd ? "selected='selected'":""  }>${code.codeName }</option>
											</c:forEach>
										</select>

									</c:if>
								</div>
								<div class="col-sm-12 col-md-2">

									<select id="id_searchType" name="searchType"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="T"
											${searchVO.searchType eq 'T' ? "selected='selected'":"" }>제목</option>
										<option value="Q"
											${searchVO.searchType eq 'Q' ? "selected='selected'":"" }>질문</option>
										<option value="C"
											${searchVO.searchType eq 'C' ? "selected='selected'":"" }>내용</option>
									</select>

								</div>
								<div class="col-sm-12 col-md-8">
									<div class="input-group mb-6">
										<input class="form-control bg-light border-0 small"
											type="text" name="searchWord" value="${searchVO.searchWord }"
											placeholder="검색어를 입력하세요" aria-describedby="input-searchWord">
										<button type="submit" class="btn btn-outline-primary" id="input-searchWord">
											<i class="fas fa-search fa-sm"></i>
										</button>
										<button type="button" class="btn btn-outline-warning" id="id_reset">
										<i class="fas fa-redo fa-sm"></i>
										</button>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-10"></div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="B" name="searchOrder"
										class="form-check-input" ${searchVO.searchOrder eq 'B' ? "checked='checked'":"" } />오래된순
								</div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="H" name="searchOrder"
										class="form-check-input" ${searchVO.searchOrder eq 'H' ? "checked='checked'":"" } />조회순
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="card  mb-4">
				<div class="card-header py-3">
					<h4 class="m-0 font-weight-bold text-primary">${codeName.codeName}</h4>
				</div>
				<div class="card-body">
					<div class="row" style="padding: 10px;">
						<div class="col-sm-12 col-md-2">전체 ${searchVO.totalRowCount }건
							조회</div>
						<div class="col-sm-12 col-md-1" style="padding: 0;">
							<select id="id_rowSizePerPage" name="rowSizePerPage"
								class="custom-select custom-select-sm form-control form-control-sm ">
								<c:forEach var="i" begin="10" end="50" step="10">
									<option value="${i}"	${i eq searchVO.rowSizePerPage? "selected='selected'":"" }>${i }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-12 col-md-6"></div>
						<div class="col-sm-12 col-md-3 d-flex justify-content-end">
							<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
								<a class="btn btn-sm btn-outline-info"	href="/faq/${parentCd}/faqForm">
									<span aria-hidden="true"></span>
									<i class="fas fa-pencil-alt"></i>&nbsp;새글쓰기</a>
							</c:if>
							&nbsp;
							<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN' }">
							<form action="/faq/${parentCd}/excelDown" method="post">
								<button class="btn btn-outline-primary" type="submit" value="엑셀 다운로드"><i class="far fa-file-excel"></i></button>
							</form>
							</c:if>
						</div>
					</div>
					<c:if test="${searchVO.totalRowCount eq 0 }">
						<section class="py-5">
							<div class="container px-5 my-5">
								<div class="text-center mb-5">
									<h1 class="fw-bolder">
										<i class="far fa-lightbulb"></i>&nbsp;MAYDAY를 이용해주셔서 감사합니다!
									</h1>
									<div class="error mx-auto text-center" data-text="0">0</div>
								</div>
								<div class="row gx-5 justify-content-center ">
									<!-- Pricing card free-->
									<div class="col-lg-6 col-xl-4 ">
										<div class="card mb-5 mb-xl-0">
											<div class="card-body p-5">
												<div class="small text-uppercase fw-bold text-muted">result</div>
												<div class="mb-3">
													<span class="display-4 fw-bold">준비중</span> <span
														class="text-muted"></span>
												</div>
												<ul class="list-unstyled mb-4">
													<li class="mb-2"><i class="bi bi-check text-primary"></i>
														<strong>등록된 글이 없습니다.</strong></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>
					</c:if>
					<c:if test="${searchVO.totalRowCount ne 0 }">
						<div class="table">
							<table class="table">
								<colgroup>
									<col width="10%" />
									<col width="10%" />
									<col width="55%" />
									<col width="15%" />
									<col width="10%" />
								</colgroup>
								<thead>
									<tr>
										<th>글번호</th>
										<th>구분</th>
										<th>제목</th>
										<th>아이디</th>
										<th>조회수</th>
									</tr>
									<c:forEach items="${faqList}" var="faq">
										<tr>
											<td id="boNo">${searchVO.totalRowCount-faq.rowNo+1}<input
												type="text" hidden="true" value="${faq.boNo}"></td>
											<td>${faq.codeName}</td>
											<td><a class="link-dark"
												href="/faq/faqView?boNo=${faq.boNo}"> ${faq.boTitle}</a></td>
											<td>${faq.memName}</td>
											<td>${faq.boHit}</td>
											<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
												<td><i class="far fa-trash-alt del"></i></td>
											</c:if>
										</tr>
									</c:forEach>
								</thead>
							</table>
						</div>
					</c:if>
				</div>
				<may:paging linkPage="/faq/${parentCd}/faqList" searchVO="${searchVO }" />
			</div>
		</div>
	</div>
	<form:form action="/faq/faqDelete" method="post" modelAttribute="faq"
		id="Removesubmit">
		<input type="text" value="" name="boNo" id="boNosub" hidden="true">
		<input type="text" value="${faqList[0].codeParentCd} "
			name="codeParentCd" id="boNosub" hidden="true">
	</form:form>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">
	$('.del').click(function() {
		var check = confirm("삭제하시겠습니까?");
		if (check == true) {
			var $tr = $(this).closest('tr');
			var boNo = $tr.find('#boNo input').val();
			$('#boNosub').val(boNo);
			$('#Removesubmit').submit();
		}
	})

	var f = document.forms['search'];
	console.log(f);
	// paging ul class = pagination 각 페이징 번호 클릭
	$('ul.pagination li a[data-page]').click(function(e) {
		e.preventDefault(); // 기본 디폴트 막기 
		f.curPage.value = $(this).data('page');
		f.submit();
	});
	// 검색버튼 클릭시 
	$(f).submit(function(e) {
		e.preventDefault();
		f.curPage.value = 1;
		f.submit();
	});

	// 최신순, 조회순 오더바이
	$('input[type=radio]').click(function(e) {
		f.curPage.value = 1;
		f.searchOrder.value = this.value;
		f.submit();
	});

	// 페이징 목록 갯수 변경시 
	$('#id_rowSizePerPage').change(function(e) {
		f.curPage.value = 1;
		f.rowSizePerPage.value = this.value;
		f.submit();
	});
	$('#id_reset').click(function() {
		$("input:radio").prop("checked", false);
		f.searchWord.value = "";
		f.searchCate.options[0].selected = true;
		f.searchType.options[0].selected = true;
		f.rowSizePerPage.value = 10;
		f.submit();
	});
</script>
</html>