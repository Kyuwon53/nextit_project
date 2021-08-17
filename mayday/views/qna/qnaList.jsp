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
	<div class="container-xxl mb-4">
		<div class="text-center mb-4">
			<div class="mt-4 mb-4">
				<a id="id_reset" class="link-page"><h1 class="h1 mb-2 text-gray-800">MAYDAY</h1></a>
			</div>
			<div class="card  mb-4">
				<div class="card-body">
					<form name="search" action="/qna/qnaList" method="post">
						<div class="form-group">
							<div class="row mt-3">
								<input type="hidden" name="curPage" value="${searchVO.curPage }">
								<input type="hidden" name="rowSizePerPage"
									value="${searchVO.rowSizePerPage }">
								<div class="col-sm-12 col-md-2 d-flex justify-content-end">
									<label id="id_searchType"> 
									<select id="id_searchType"	name="searchType"	class="custom-select custom-select-sm form-control form-control-sm">
											<option value="" selected="selected">--분류--</option>
											<option value="T"
												${searchVO.searchType eq 'T' ? "selected='selected'":"" }>제목</option>
											<option value="I"
												${searchVO.searchType eq 'I' ? "selected='selected'":"" }>아이디</option>
											<option value="C"
												${searchVO.searchType eq 'C' ? "selected='selected'":"" }>내용</option>
									</select>
									</label>
								</div>
								<div class="col-sm-12 col-md-8">
									<div class="input-group mb-2">
										<input class="form-control" type="text" name="searchWord"
											value="${searchVO.searchWord }" placeholder="검색어를 입력하세요"
											aria-describedby="input-searchWord">
										<button type="submit" class="btn btn-outline-info"
											id="input-searchWord">
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-7"></div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="N" name="searchCheck"  ${searchVO.searchCheck eq 'N' ? "checked='checked'":"" }	class="form-check-input" />미답변
								</div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="Y" name="searchCheck"  ${searchVO.searchCheck eq 'Y' ? "checked='checked'":"" }	class="form-check-input" />답변완료
								</div>
								<div class="col-md-1 col-sm-offset-9 text-right">
									<input type="radio" value="" name="searchCheck" class="form-check-input" />전체보기
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
							조회</div>
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
						<div class="col-sm-12 col-md-2">
							<c:if test="${sessionScope.USER_INFO.userRole eq 'CLIENT'}">
								<a class="btn btn-sm btn-outline-info" href="/qna/qnaForm"><span
									aria-hidden="true"></span> <i class="fas fa-pencil-alt"></i>&nbsp;문의하기</a>
							</c:if>
						</div>
					</div>
					<div class="table">
						<table class="table">
							<colgroup>
								<col width="10%" />
								<col width="50%" />
								<col width="15%" />
								<col width="15%" />
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th>글번호</th>
									<th>글제목</th>
									<th>작성자</th>
									<th>날짜</th>
									<th>확인여부</th>
								</tr>
								<c:forEach items="${ qnaBoardList}" var="qna">
									<tr>
										<td id="boNo">${qna.boNo}</td>
										<td class="text-start fs-6 "><a
											href="/qna/qnaView?boNo=${qna.boNo }"${qna.groupLayer ne 0 ? "class='link-primary'": "class='link-dark'"}">
												${fn:replace(qna.boTitle," ", "&nbsp;")} </a></td>
										<td><c:if test="${qna.groupLayer ne 0}">${qna.expertName} 변호사/노무사 님</c:if>
											<c:if test="${qna.groupLayer eq 0}">${qna.memName} 님</c:if>
										<td>${qna.boRegDate}</td>
										<td>${qna.boCheck}</td>
									</tr>
								</c:forEach>
							</thead>
						</table>
					</div>
				</div>
				<may:paging linkPage="/qna/qnaList" searchVO="${searchVO }"/>
			</div>
		</div>
	</div>
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

	// 답변여부 조회
	$('input[type=radio]').click(function(e) {
		f.curPage.value = 1;
		f.searchCheck.value = this.value;
		f.submit();
	});

	// 페이징 목록 갯수 변경시 
	$('#id_rowSizePerPage').change(function(e) {
		f.curPage.value = 1;
		f.rowSizePerPage.value = this.value;
		f.submit();
	});
	$('#id_reset').click(function() {
		f.searchWord.value = "";
		f.searchCheck.options[0].selected = true;
		f.searchType.options[0].selected = true;
		f.rowSizePerPage.value = 10;
		f.submit();
	});
</script>
</html>