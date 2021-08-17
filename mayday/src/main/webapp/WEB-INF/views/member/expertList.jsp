<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>변호사 정보</title>
</head>
<style>
body{
background-color: #FDF4EA
}
</style>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>


	<div class="container">
		<div class="text-center">
			<div class="mt-4 mb-4">
					<h1	class="h1 mb-2 text-gray-800">MAYDAY</h1>
			</div>
			<div class="card  mb-4">
				<div class="card-body">
					<form action="expertList" name="search" method="post">
						<div class="form-group">
							<div class="row">
								<input type="hidden" name="curPage" value="${searchVO.curPage }">
								<input type="hidden" name="rowSizePerPage"
									value="${searchVO.rowSizePerPage }">
								<div class="col-sm-12 col-md-2">
									<select id="searchCateSt" name="searchCateSt"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--활동 상태--</option>
										<option value="L"
											${searchVO.searchCateSt eq "L" ? "selected='selected'":"" }>접속중</option>
										<option value="H"
											${searchVO.searchCateSt eq "H" ? "selected='selected'":"" }>자리비움</option>
										<option value="N"
											${searchVO.searchCateSt eq "N" ? "selected='selected'":"" }>로그아웃</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-2">
									<select id="searchCateType" name="searchCateType"
										class="custom-select custom-select-sm form-control form-control-sm ">
										<option value="">--검색--</option>
										<option value="name"	${searchVO.searchCateType eq "name" ? "selected='selected'":"" }>이름</option>
									</select>
								</div>
								<div class="col-sm-12 col-md-8">
									<div class="input-group mb-6">
										<input type="text" name="searchWord"
											class="form-control bg-light border-0 small"
											value="${searchVO.searchWord }" placeholder="검색어를 입력하세요">
										<button type="submit" class="btn btn-outline-primary" id="input-searchWord">
											<i class="fas fa-search fa-sm"></i>
										</button>
										<button type="submit" id="id_btn_reset" class="btn btn-outline-primary">
											<i class="fas fa-redo fa-sm"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-md-2">전체 ${searchVO.totalRowCount }건 조회</div>
						<div class="col-md-1">
							<select id="id_rowSizePerPage" name="rowSizePerPage"
								class="custom-select custom-select-sm form-control form-control-sm  ">
								<c:forEach var="i" begin="8" end="64" step="8">
									<option value="${i}"
										${i eq searchVO.rowSizePerPage? "selected='selected'":"" }>${i }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-7"></div>
							<div class="col-md-2">
								&nbsp;
								<form action="/member/expert/excelDown" method="post">
									<button class="btn btn-outline-primary" type="submit" value="엑셀 다운로드"><i class="far fa-file-excel"></i></button>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
		<section class="py-5">
			<div class="row gx-5 justify-content-center">
				<div class="col-lg-8 col-xl-6">
					<div class="text-center">
						<h2 class="fw-bolder mb-5">변호사정보</h2>
					</div>
				</div>
			</div>
			<div class="row gx-5">
				<c:forEach items="${expertList }" var="exp">
					<div class="col-lg-3 mb-5">
						<div class="card h-100 shadow border-0">
							<img class="card-img-top" style="width: 255px; height: 241px;"
								id="preview-img" src="/profile/${exp.memId}_profile"
								onerror="this.src='/profile/non.png'">

							<div class="card-body p-4">
								<div class="row">
									<div class="col-md-4">
										<div class="badge bg-primary bg-gradient rounded-pill mb-2">변호사</div>
									</div>
									<div class="col-md-4"></div>
									<div class="badge rounded-pill mb-2 col-md-4">
										<button type="button"
											class="btn btn-outline-primary btn-sm text-decoration-none link-dark"
											onclick="location.href='expertView?memId=${exp.memId}' ">이력보기</button>
									</div>
								</div>
								<div class="text-decoration-none link-dark">
									<h5 class="card-title mb-3">${exp.memName}</h5>
								</div>
								<p class="card-text mb-0">${exp.memPhone}<br>${exp.memMail}</p>
							</div>
							<div class="card-footer p-4 pt-0 bg-transparent border-top-0">
								<div class="d-flex align-items-end justify-content-between">
									<div class="d-flex align-items-center">
										<img class="card-img-top" style="width: 40px; height: 40px;"
											id="preview-img" src="/profile/${exp.memId}_profile"
											onerror="this.src='/profile/non.png'">
										<div class="small">
											<span class="font-weight-bold ${exp.memState eq 'N'?'text-danger': exp.memState eq 'H'?'text-success':'text-primary'} text-uppercase mb-1">&nbsp;&nbsp;${exp.memState eq 'N'?'로그아웃': exp.memState eq 'H'?'자리비움':'접속중'}</span>
											<div class="text-muted">${exp.endLogin}</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
				<may:paging linkPage="/member/expertList" searchVO="${searchVO }" />
		</section>
	</div>
	<!-- container -->
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">

var f = document.forms['search'];

$('ul.pagination li a[data-page]').click(function (e) {
	e.preventDefault();	// 기본 디폴트 막기 
	f.curPage.value=$(this).data('page');
	f.submit();
});
$('#id_btn_reset').click(function() {
	f.searchWord.value = "";
	f.searchCateSt.options[0].selected = true;
	f.searchCateType.options[0].selected = true;
	f.rowSizePerPage.value = 8;
});
$(f).submit(function (e) {
	e.preventDefault();
	f.curPage.value =1;
	f.submit();
});

$('#id_rowSizePerPage').change(function (e) {
	f.curPage.value = 1;
	f.rowSizePerPage.value = this.value;
	f.submit();
});
$('#searchCateSt').change(function (e){
	f.curPage.value = 1;
	f.submit();
})

</script>
</html>


