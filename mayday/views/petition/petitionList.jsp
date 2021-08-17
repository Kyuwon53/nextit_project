<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>Insert title here</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
<%@include file="/WEB-INF/inc/floating.jsp"%>
</head>
<body>
	<div class="container-xxl">
		<div class="text-center">
			<div class="mt-4 mb-4">
				<h1 class="h1 mb-2 text-gray-800">MAYDAY</h1>
			</div>
			<div class="card  mb-4">
				<div class="card-body">
					<form name="search" action="/petition/petitionList" method="post">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12 col-md-2">
									<input type="hidden" name="curPage"value="${searchVO.curPage }"> 
									<input type="hidden"	name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
									<select id="id_searchType" name="searchType"	class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="T"	${searchVO.searchType eq 'T' ? "selected='selected'":"" }>제목</option>
										<option value="C"	${searchVO.searchType eq 'C' ? "selected='selected'":"" }>내용</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-10">
									<div class="input-group mb-6">
										<input type="text" class="form-control bg-light border-0 small" name="searchWord" value="${searchVO.searchWord }" placeholder="검색어를 입력하세요">
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
									<input type="radio" value="S" name="searchOrder"	class="form-check-input" ${searchVO.searchOrder eq 'S' ? "checked='checked'":"" } /> 최신순
								</div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="E" name="searchOrder" class="form-check-input" ${searchVO.searchOrder eq 'E' ? "checked='checked'":"" } /> 오래된순
								</div>
							</div>
					</form>
				</div>
			</div>
			<div class="card  mb-4">
				<div class="card-header py-3">
					<h4 class="m-0 font-weight-bold text-primary">국민 청원</h4>
				</div>
				<div class="card-body">
					<div class="row" style="padding: 10px;">
						<div class="col-sm-12 col-md-3"> 전체 ${searchVO.totalRowCount }건 조회 
						<select id="id_rowSizePerPage"	name="rowSizePerPage">
								<c:forEach var="i" begin="10" end="50" step="10">
									<option value="${i}"	${i eq searchVO.rowSizePerPage? "selected='selected'":"" }>${i }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-12 col-md-7"></div>
						<div class="col-sm-12 col-md-2 d-flex justify-content-end">
							<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
								<a class="btn btn-sm btn-outline-info"	href="/petition/petitionForm"><span aria-hidden="true"></span>	&nbsp;새글쓰기</a>
							</c:if>
							&nbsp;
							<form action="/petition/excelDown" method="post">
								<button class="btn btn-outline-primary" type="submit" value="엑셀 다운로드"><i class="far fa-file-excel"></i></button>
							</form>
						</div>
					</div>
					<div class="table">
						<table class="table">
							<colgroup>
								<col width="10%" />
								<col width="60%" />
								<col width="10%" />
								<col width="10%" />
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th>글번호</th>
									<th>제목</th>
									<th>시작일</th>
									<th>마감일</th>
									<th>진행사항</th>
								</tr>
								<c:forEach items="${petitionList}" var="petition">
									<tr>
										<td id="boNo">${searchVO.totalRowCount - petition.rowNo +1}<input type="text" hidden="true" value="${petition.boNo}"></td>
										<td><a	href="/petition/petitionView?boNo=${petition.boNo }"	class="link-dark">${petition.petTitle}</a></td>
										<td>${petition.petStartDate}</td>
										<td>${petition.petEndDate}</td>
										<td>${petition.petProgress}</td>
										<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
											<td><i class="far fa-trash-alt del"></i></td>
										</c:if>
									</tr>
								</c:forEach>
							</thead>
						</table>
					</div>
				</div>
				<may:paging linkPage="/petition/petitionList"	searchVO="${searchVO }" />
			</div>
		</div>
	</div>
	<form:form action="/petition/petitionDelete" method="post" modelAttribute="petition" id="Removesubmit">
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
		console.log('리셋')
		f.searchWord.value = "";
		f.searchType.options[0].selected = true;
		f.rowSizePerPage.value = 10;
		$("input:radio").prop("checked", false);
		f.submit();
	});
</script>
</html>