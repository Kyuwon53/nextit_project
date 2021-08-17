<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>MAYDAY- 뉴스</title>
</head>

<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<%@include file="/WEB-INF/inc/floating.jsp"%>
	<div class="container-xxl">
		<div class="text-center">
			<div class="mt-4 mb-4">
				<h1	class="h1 mb-2 text-gray-800">MAYDAY</h1>
			</div>
			<div class="card  mb-4">
				<div class="card-body">
					<form action="newsList" name="search" method="post">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12 col-md-2">
									<input type="hidden" name="curPage"
										value="${searchVO.curPage }"> <input type="hidden"
										name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
									<select id="searchCate" name="searchCate"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--카테고리--</option>
										<c:forEach items="${codeList}" var="code" varStatus="codeList">
											<c:choose>
												<c:when test="${codeList.first }">
												</c:when>
												<c:otherwise>
													<option value="${code.codeCd}"
														${code.codeCd eq searchVO.searchCate ? "selected='selected'":""  }>${code.codeName }</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-12 col-md-2">
									<select id="searchCateType" name="searchCateType"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--전체--</option>
										<option value="title"
											${searchVO.searchCateType eq "title" ? "selected='selected'":"" }>기사제목</option>
										<option value="press"
											${searchVO.searchCateType eq "press" ? "selected='selected'":"" }>언론사</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-8">
									<div class="input-group mb-6">
										<input type="text" name="searchWord"
											class="form-control bg-light border-0 small"
											value="${searchVO.searchWord }" placeholder="검색어를 입력하세요">
										<button type="submit" class="btn btn-outline-primary"
											id="input-searchWord">
											<i class="fas fa-search fa-sm"></i>
										</button>
										<button type="submit" id="id_btn_reset"
											class="btn btn-outline-primary">
											<i class="fas fa-redo fa-sm"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10"></div>
							<div class="col-md-1 col-sm-offset-9 text-right">
								<input type="radio" name="searchOrder" value="Y"
									${searchVO.searchOrder eq "Y" ? "checked":""}>최신기사
							</div>
						</div>


					</form>

				</div>
			</div>


			<div class="card  mb-4">
				<div class="card-header py-3">
					<h4 class="m-0 font-weight-bold text-primary">뉴스</h4>
				</div>
				<div class="card-body">
					<div class="row" style="padding: 10px;">
						<div class="col-sm-12 col-md-3">
							전체 ${searchVO.totalRowCount }건 조회 <select id="id_rowSizePerPage"
								name="rowSizePerPage">
								<c:forEach var="i" begin="10" end="50" step="10">
									<option value="${i}"
										${i eq searchVO.rowSizePerPage? "selected='selected'":"" }>${i }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-12 col-md-7"></div>
						<div class="col-sm-12 col-md-2 d-flex justify-content-end">
							<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
								<a class="btn btn-sm btn-outline-info text-center" href="newsForm"><span
									aria-hidden="true"></span> &nbsp;기사쓰기</a>
							</c:if>
							&nbsp;
							<form action="/news/excelDown" method="post">
								<button class="btn btn-outline-primary" type="submit" value="엑셀 다운로드"><i class="far fa-file-excel"></i></button>
							</form>
						</div>
					</div>
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
									<th>번호</th>
									<th>구분</th>
									<th>기사제목</th>
									<th>언론사</th>
									<th>날짜</th>
								</tr>

								<c:forEach items="${newsList }" var="news">
									<tr>
										<td id="boNo">${news.rowNo}<input type="text"
											hidden="true" value="${news.boNo}"></td>
										<td>${news.boCdName}</td>
										<td><a href="${news.newsLink}" target="_blank">${news.newsTitle}</a></td>
										<td>${news.newsPress}</td>
										<td>${news.newsRegDate}</td>
										<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
											<td><i class="far fa-trash-alt del"></i></td>
										</c:if>
									</tr>
								</c:forEach>
							</thead>
						</table>
					</div>
				</div>
				<may:paging linkPage="/news/newsList" searchVO="${searchVO }" />
			</div>
		</div>
		<form:form action="newsRemove" method="post" modelAttribute="news"
			id="Removesubmit">
			<input type="text" value="" name="boNo" id="boNosub" hidden="true">
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
	$('ul.pagination li a[data-page]').click(function(e) {
		e.preventDefault(); // 기본 디폴트 막기 
		f.curPage.value = $(this).data('page');
		f.submit();
	});
	$('#id_btn_reset').click(function() {
		f.searchWord.value = "";
		f.searchCateType.options[0].selected = true;
		f.searchCate.options[0].selected = true;
		$("input:radio").prop("checked", false);
	});
	$(f).submit(function(e) {
		e.preventDefault();
		f.curPage.value = 1;
		f.submit();
	});

	$('#id_rowSizePerPage').change(function(e) {
		f.curPage.value = 1;
		f.rowSizePerPage.value = this.value;
		f.submit();
	});

	$('input[type=radio]').click(function(e) {
		f.curPage.value = 1;
		f.searchOrder.value = this.value;
		f.submit();
	});
</script>
</html>