<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>MAYDAY- 법령</title>
<%@ include file="/WEB-INF/inc/top.jsp"%>
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
					<form action="lawList" name="search" method="post">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12 col-md-1"></div>
								<div class="col-sm-12 col-md-2">
									<input type="hidden" name="curPage"
										value="${searchVO.curPage }"> <input type="hidden"
										name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
									<select id="searchCate" name="searchCate"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--법령종류--</option>
										<option value="법률"
											${searchVO.searchCate eq "법률" ? "selected='selected'":"" }>법률</option>
										<option value="대통령령"
											${searchVO.searchCate eq "대통령령" ? "selected='selected'":"" }>대통령령</option>
										<option value="고용노동부령"
											${searchVO.searchCate eq "고용노동부령" ? "selected='selected'":"" }>고용노동부령</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-2">
									<select id="searchClass" name="searchClass"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--재정.개정구분--</option>
										<option value="일부개정"
											${searchVO.searchClass eq "일부개정" ? "selected='selected'":"" }>일부개정</option>
										<option value="타법개정"
											${searchVO.searchClass eq "타법개정" ? "selected='selected'":"" }>타법개정</option>
										<option value="제정"
											${searchVO.searchClass eq "제정" ? "selected='selected'":"" }>제정</option>
										<option value="전부개정"
											${searchVO.searchClass eq "전부개정" ? "selected='selected'":"" }>전부개정</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-1">
									<select id="searchCateType" name="searchCateType"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--선택--</option>
										<option value="NM"
											${searchVO.searchCateType eq "NM" ? "selected='selected'":"" }>법령명</option>
										<option value="NO"
											${searchVO.searchCateType eq "NO" ? "selected='selected'":"" }>공포번호</option>
										<option value="DEP"
											${searchVO.searchCateType eq "DEP" ? "selected='selected'":"" }>소관부처</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-1">
									<select id="searchDateType" name="searchDateType"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--전체--</option>
										<option value="공포"
											${searchVO.searchDateType eq "공포" ? "selected='selected'":"" }>공포일자</option>
										<option value="시행"
											${searchVO.searchDateType eq "시행" ? "selected='selected'":"" }>시행일자</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-1"></div>
								<div class="col-sm-12 col-md-3">
									<input type="date" name="searchDateStart"
										class="form-control mt-3" value="${searchVO.searchDateStart}">
								</div>
								<div class="col-sm-12 col-md-3">
									<input type="date" name="searchDateEnd"
										class="form-control mt-3" value="${searchVO.searchDateEnd}">
								</div>
								<div class="col-sm-12 col-md-3">
									<input type="radio" name="searchDateOr" class="mt-4" value="공포"
										${searchVO.searchDateOr eq "공포" ? "checked":""}>공포일자최신순
									<input type="radio" name="searchDateOr" value="시행"
										${searchVO.searchDateOr eq "시행" ? "checked":""}>시행일최신순
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-1"></div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group mb-6">
									<input type="text" class="form-control bg-light border-0 small"
										name="searchWord" value="${searchVO.searchWord }"
										placeholder="검색어를 입력하세요">
									<button type="submit" class="btn btn-outline-primary">
										<i class="fas fa-search fa-sm"></i>
									</button>
									<button type="submit" id="id_btn_reset"
										class="btn btn-outline-primary">
										<i class="fas fa-redo fa-sm"></i>
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- search 끝 -->
			<div class="card  mb-4">
				<div class="card-header py-3">
					<h4 class="m-0 font-weight-bold text-primary">법률 게시판</h4>
				</div>
				<div class="card-body">
					<div class="row" style="padding: 10px;">
						<div class="col-sm-12 col-md-3">전체 ${searchVO.totalRowCount }건 조회</div>
						<div class="col-sm-12 col-md-1" style="padding: 0;">
							<select id="id_rowSizePerPage" name="rowSizePerPage"
								class="custom-select custom-select-sm form-control form-control-sm ">
								<c:forEach var="i" begin="10" end="50" step="10">
									<option value="${i}"
										${i eq searchVO.rowSizePerPage? "selected='selected'":"" }>${i }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-12 col-md-6"></div>
						<div class="col-sm-12 col-md-2">
						&nbsp;
							<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN' }">
							<form action="/law/excelDown" method="post">
								<button class="btn btn-outline-primary" type="submit" value="엑셀 다운로드"><i class="far fa-file-excel"></i></button>
							</form>
							</c:if>
						</div>
					</div>
					<div class="table">
						<table class="table">
						<colgroup>
								<col width="5%" />
								<col width="41%" />
								<col width="10%" />
								<col width="8%" />
								<col width="8%" />
								<col width="10%" />
								<col width="8%" />
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>법령명</th>
									<th>공포일자</th>
									<th>법령종류</th>
									<th>공포번호</th>
									<th>시행일자</th>
									<th>재정.개정</th>
									<th>소관부처</th>
								</tr>
								<c:forEach items="${lawList }" var="law">
									<tr>
										<td id="boNo">${searchVO.totalRowCount - law.rowNo + 1}<input
											type="text" hidden="true" value="${law.boNo}" /></td>
										<td><a href="${law.lawLink}" target="_blank">${law.lawName}</a></td>
										<td>${law.lawDate}</td>
										<td>${law.lawCate}</td>
										<td>${law.lawNo}</td>
										<td>${law.lawStartDate}</td>
										<td>${law.lawClass}</td>
										<td>${law.lawDep}</td>
										<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}">
											<td><i class="far fa-trash-alt del"></i></td>
										</c:if>
									</tr>
								</c:forEach>
							</thead>
						</table>
					</div>
				</div>
				<may:paging linkPage="/law/lawList" searchVO="${searchVO }" />
			</div>
		</div>
	</div>
	<form:form action="/law/lawFormRemove" method="post" modelAttribute="law"
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
		f.searchDateStart.value = "";
		f.searchDateEnd.value = "";
		f.searchWord.value = "";
		f.searchCateType.options[0].selected = true;
		f.searchClass.options[0].selected = true;
		f.searchCate.options[0].selected = true;
		f.searchDateType.options[0].selected = true;
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
		f.searchDateOr.value = this.value;
		f.submit();
	});
</script>
</html>