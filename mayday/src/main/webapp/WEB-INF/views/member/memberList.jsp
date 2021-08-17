<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/inc/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="container-xxl">
		<div class="text-center">
			<div class="mt-4 mb-4">
				<h1	class="h1 mb-2 text-gray-800">MAYDAY</h1>
			</div>
			<div class="card  mb-4">
				<div class="card-body">
					<div class="form-group">
						<form action="memberList" name="search" method="post">
							<div class="row">
								<div class="col-sm-12 col-md-2">
									<input type="hidden" name="curPage" value="${searchVO.curPage }"> 
									<input type="hidden"	name="rowSizePerPage" value="${searchVO.rowSizePerPage }">

									<select id="searchCateMb" name="searchCateMb"	class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--회원구분--</option>
										<option value="CLIENT" ${searchVO.searchCateMb eq "CLIENT" ? "selected='selected'":"" }>고객</option>
										<option value="EXPERT" ${searchVO.searchCateMb eq "EXPERT" ? "selected='selected'":"" }>변호사</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-2">
									<select id="memsearchCateSt" name="memsearchCateSt"	class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--활동 상태--</option>
										<option value="Y"	${searchVO.memsearchCateSt eq "Y" ? "selected='selected'":"" }>활동</option>
										<option value="N"	${searchVO.memsearchCateSt eq "N" ? "selected='selected'":"" }>탈퇴</option>
										<option value="H"	${searchVO.memsearchCateSt eq "H" ? "selected='selected'":"" }>휴면</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-2">
									<select id="searchCateType" name="searchCateType" class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--검색--</option>
										<option value="id" ${searchVO.searchCateType eq "id" ? "selected='selected'":"" }>아이디</option>
										<option value="name" ${searchVO.searchCateType eq "name" ? "selected='selected'":"" }>이름</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-6">
									<div class="input-group mb-6">
										<input type="text" name="searchWord" class="form-control bg-light border-0 small" value="${searchVO.searchWord }" placeholder="검색어를 입력하세요">
										<button type="submit" class="btn btn-outline-primary" id="input-searchWord">
											<i class="fas fa-search fa-sm"></i>
										</button>
										<button type="submit" id="id_btn_reset" class="btn btn-outline-primary">
											<i class="fas fa-redo fa-sm"></i>
										</button>
									</div>
								</div>
						</form>
					</div>
				</div>
				<div class="card  mb-4">
					<div class="card-header py-3">
						<h4 class="m-0 font-weight-bold text-primary">회원리스트</h4>
					</div>
					<div class="card-body">
						<div class="row" style="padding: 10px;">
							<div class="col-md-3">전체 ${searchVO.totalRowCount }건
								조회</div>
							<div class="col-md-1" style="padding: 0;">
								<select id="id_rowSizePerPage" name="rowSizePerPage"
									class="custom-select custom-select-sm form-control form-control-sm">
									<c:forEach var="i" begin="10" end="50" step="10">
										<option value="${i}"
											${i eq searchVO.rowSizePerPage? "selected='selected'":"" }>${i }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-6"></div>
							<div class="col-md-2">
								&nbsp;
								<c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN' }">
								<form action="/member/counsel/excelDown" method="post">
									<button class="btn btn-outline-primary" type="submit" value="엑셀 다운로드"><i class="far fa-file-excel"></i></button>
								</form>
								</c:if>
							</div>
						</div>
						<div class="table">
							<table class="table">
								<thead>
									<tr>
										<th>아이디</th>
										<th>비밀번호</th>
										<th>이름</th>
										<th>생년원일</th>
										<th>핸드폰</th>
										<th>이메일</th>
										<th>주소</th>
										<th>멤버구분</th>
										<th>상태</th>
										<th>이력</th>
									</tr>
									<c:forEach items="${memberList }" var="mem">
										<tr>
											<td class="memId">${mem.memId}</td>
											<td>${mem.memPass}</td>
											<td>${mem.memName}</td>
											<td>${mem.memBir}</td>
											<td>${mem.memPhone}</td>
											<td>${mem.memMail}</td>
											<td>${mem.memAdd1}${mem.memAdd1}</td>
											<td><select
												class="Posit custom-select custom-select-sm form-control form-control-sm">
													<option value="CLIENT"
														${mem.memPosit eq "CLIENT" ? "selected='selected'" : ""}>CLIENT</option>
													<option value="EXPERT"
														${mem.memPosit eq "EXPERT" ? "selected='selected'" : ""}>EXPERT</option>
											</select></td>
											<td><select
												class="Cond custom-select custom-select-sm form-control form-control-sm">
													<option value="Y"
														${mem.memCond eq "Y" ? "selected='selected'" : ""}>활동</option>
													<option value="N"
														${mem.memCond eq "N" ? "selected='selected'" : ""}>탈퇴</option>
													<option value="H"
														${mem.memCond eq "H" ? "selected='selected'" : ""}>휴면,
														승인대기</option>
											</select></td>
											<c:choose>
												<c:when test="${mem.memPosit == 'EXPERT'}">
													<td><a href="expertView?memId=${mem.memId}">이력보기</a></td>
												</c:when>
												<c:otherwise>
													<td></td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
							</table>
						</div>
					</div>
					<may:paging linkPage="/member/memberList" searchVO="${searchVO }" />

				</div>
			</div>
		</div>
	</div>
	<form:form action="modify" method="post" modelAttribute="member"
		id="Modifysubmit">
		<input type="text" value="" name="memId" id="memIdsub" hidden="true">
		<input type="text" value="" name="memPosit" id="memPositsub"
			hidden="true">
		<input type="text" value="" name="memCond" id="memCondsub"
			hidden="true">
	</form:form>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">
	$('.Posit').change(function() {
		var check = confirm("수정하시겠습니까?");
		if (check == true) {
			var $tr = $(this).closest('tr');
			var memId = $tr.find('.memId').html();
			var memCond = $tr.find('.Cond').val();
			$('#memIdsub').val(memId)
			$('#memPositsub').val($(this).val())
			$('#memCondsub').val(memCond)
			$('#Modifysubmit').submit();
		} else {
			location.reload();
		}
	})
	$('.Cond').change(function() {
		var check = confirm("수정하시겠습니까?");
		if (check == true) {
			var $tr = $(this).closest('tr');
			var memId = $tr.find('.memId').html();
			var memPosit = $tr.find('.Posit').val();
			$('#memIdsub').val(memId)
			$('#memPositsub').val(memPosit)
			$('#memCondsub').val($(this).val())
			$('#Modifysubmit').submit();
		} else {
			location.reload();
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
		f.searchCateSt.options[0].selected = true;
		f.searchCateMb.options[0].selected = true;
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
	$('#searchCateMb').change(function(e){
		f.curPage.value = 1;
		f.submit();
	});
	$('#memsearchCateSt').change(function(e){
		f.curPage.value = 1;
		f.submit();
	});
</script>
</html>