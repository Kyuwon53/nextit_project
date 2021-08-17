<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>${parentCd }</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
<%@ include file="/WEB-INF/inc/floating.jsp" %>
</head>
<body class="mb-4">
	<div class="container-xxl mb-4">
		<div class="text-center mb-4">
			<div class="mt-4 mb-4">
				<h1 class="h1 mb-2 text-gray-800">MAYDAY</h1>
			</div>
			<div class="card  mb-4">
				<div class="card-body">
					<form name="search" action="/board/${parentCd }/boardList"
						method="post">
						
						<div class="form-group">
							<div class="row mt-3">
								<input type="hidden" name="curPage" value="${searchVO.curPage }">
								<input type="hidden" name="rowSizePerPage"
									value="${searchVO.rowSizePerPage }">
										<div class="col-md-2">
									<c:if test="${parentCd eq 'question'}">
									<select id="id_searchCd"
										name="searchCd"
										class="custom-select custom-select-sm form-control form-control-sm">
									<option value="">카테구분</option>
									<c:forEach items="${codeList}" var="code">
									<option value="${code.codeCd }" ${searchVO.searchCd eq code.codeCd ? "selected='selected'":"" }>${code.codeName}</option>
									</c:forEach>
									</select>
									</c:if>
									</div>
									
									<div class="col-md-2">
									<select id="id_searchType"
										name="searchType"
										class="custom-select custom-select-sm form-control form-control-sm">
											<option value="T"
												${searchVO.searchType eq 'T' ? "selected='selected'":"" }>제목</option>
											<option value="C"
												${searchVO.searchType eq 'C' ? "selected='selected'":"" }>내용</option>
									</select>
									</div>
								
								<div class="col-sm-12 col-md-8">
									<div class="input-group mb-2">
										<input class="form-control colors-yellow" type="text"
											name="searchWord" value="${searchVO.searchWord }"
											placeholder="검색어를 입력하세요" aria-describedby="input-searchWord">
										<button type="submit" class="btn btn-outline-warning"
											id="input-searchWord">
											<i class="fas fa-search fa-sm"></i>
										</button>
											<button type="submit" id="id_btn_reset"
										class="btn btn-outline-warning">
										<i class="fas fa-redo fa-sm"></i>
									</button>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-7"></div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="S" name="searchOrder"
										class="form-check-input" ${searchVO.searchOrder eq 'S' ? "checked='checked'":"" } /></i>글번호
								</div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="E" name="searchOrder"
										class="form-check-input" ${searchVO.searchOrder eq 'E' ? "checked='checked'":"" } /></i>조회순
								</div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="B" name="searchOrder"
										class="form-check-input" ${searchVO.searchOrder eq 'B' ? "checked='checked'":"" } /></i>최신순
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- 새글쓰기 -->
			<div class="card  mb-4">
				<div class="card-header py-3">
					<h4 class="m-0 font-weight-bold text-primary">${codeName.codeName}</h4>
				</div>
				<div class="card-body">
					<div class="row" style="padding: 10px;">
						<div class="col-sm-12 col-md-2">전체 ${searchVO.totalRowCount }건
							조회 </div>
						<div class="col-sm-12 col-md-1 d-flex justify-content-start"
							style="padding: 0;">
							<select id="id_rowSizePerPage" name="rowSizePerPage"
								class="custom-select custom-select-sm form-control form-control-sm ">
								<c:forEach var="i" begin="10" end="50" step="10">
									<option value="${i}"
										${i eq searchVO.rowSizePerPage? "selected='selected'":"" }>${i }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-12 col-md-6"></div>
						<div class="col-sm-12 col-md-3 d-flex justify-content-end">
							<%-- <c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}"> --%>
							<c:if test="${sessionScope.USER_INFO ne null }" >
								<c:if test="${parentCd eq 'notice' }" >
								<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN' }">
									<a class="btn btn-sm btn-outline-info" href="/board/notice/boardForm"><span aria-hidden="true"></span>
									<i class="fas fa-pencil-alt"></i>&nbsp;새글쓰기</a>
								</c:if>
								</c:if>
								<c:if test="${parentCd ne 'notice' }" >
								<a class="btn btn-sm btn-outline-info" href="/board/${parentCd}/boardForm"><span aria-hidden="true"></span>
								<i class="fas fa-pencil-alt"></i>&nbsp;새글쓰기</a>
								</c:if>
							</c:if>
							&nbsp;
							<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN' }">
							<form action="/board/${parentCd}/excelDown" method="post">
								<button class="btn btn-outline-primary" type="submit" value="엑셀 다운로드"><i class="far fa-file-excel"></i></button>
							</form>
							</c:if>
						</div>
					</div>
					<div class="table">
						<table class="table">
							<thead>
							<colgroup>
								<col width="10%" />
								<col width="10%" />
								<col width="50%"/>
								<col width="10%" />
								<col width="10%" />
								<col width="10%" />
							</colgroup>
								<tr>
									<th>글번호</th>
									<th>분류</th>
									<th>제목</th>
									<th>회원명</th>
									<th>등록일</th>
									<th>조회수</th>
								</tr>
							<tbody>

								<c:forEach items="${boardList}" var="board">
									<tr class="text-center">
										<td id="boNo">${searchVO.totalRowCount-board.rowNo+1}<input type="text" value="${board.boNo }" hidden="true"></td>
									<td>${board.codeName }</td>
										<td class="text-left"><a class="link-dark" href="/board/boardView?boNo=${board.boNo }">
												${board.boTitle } </a></td>
												<td>${board.memName }</td>
										<td>${board.boRegDate }</td>
										<td>${board.boHit }</td>
										<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
	<td><i class="far fa-trash-alt del"></i></td>	
	</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<may:paging linkPage="/board/${parentCd}/boardList"
					searchVO="${searchVO }" />
			</div>
		</div>
		<br><br><br><br><br><br>
	</div>
	<form:form action="/board/boardDelete" method="post" modelAttribute="board" id="Removesubmit">
<input type="text" value="" name="boNo" id="boNosub" hidden="true">
<input type="text" value="
<c:if test="${parentCd eq 'question'}">BOD10</c:if>
<c:if test="${parentCd eq 'free'}">BOD20</c:if>
<c:if test="${parentCd eq 'notice'}">BOD30</c:if>
" name="codeParentCd" id="boNosub" hidden="true">
</form:form>
</body>
<%@include file="/WEB-INF/inc/footer.jsp"%>
<script type="text/javascript">
$('.del').click(function(){
	var check =confirm("삭제하시겠습니까?");
	if (check==true) {
	var $tr = $(this).closest('tr');
	var boNo =$tr.find('#boNo input').val();
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
	//mayday클릭시 검색 초기2
	$('#id_reset').click(function() {
		f.searchWord.value = "";
		f.searchType.options[0].selected = true;
		f.searchCd.options[0].selected = true;
		f.rowSizePerPage.value = 10;
		f.submit();
	});
	// 검색 초기화 
	$('#id_btn_reset').click(function() {
		$("input:radio").prop("checked", false);
		f.searchWord.value = "";
		f.searchType.options[0].selected = true;
		f.searchCd.options[0].selected = true;
		f.rowSizePerPage.value = 10;
	});
	$("#id_searchCd").change(function(e) {
		f.searchCd = this.value;
		f.submit();
	})
</script>
</html>