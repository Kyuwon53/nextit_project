<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%request.setCharacterEncoding("utf-8");%>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>MyPage</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body class="d-flex flex-column">
<div class="floatingmenu text-center">
	<img alt="" src="/resource/image/lawyer.png" width="60%">
	<nav class="floatnav ">
		<ul class="list-unstyled mb-0 py-3 text-center">
			<li><a class="nav-link " href="#"><i class="fas fa-chevron-up"></i></a></li>
			<li><a class="nav-link" href="#MyPage">개인 정보</a></li>
			<li><a class="nav-link" href="#MyWrite">내가 쓴 글</a></li>
			<li><a class="nav-link" href="#MyReply">내가 쓴 댓글</a></li>
			<li><a class="nav-link" href="#MyResv">예약 내역</a></li>
			<li><a class="nav-link" href="#MyPay">결제 내역</a></li>
			<li><a class="nav-link" href="#bottom"><i class="fas fa-chevron-down"></i></a></li>
		</ul>
	</nav>
</div>
<div class="container-xxl pt-5 px-5 mt-3 mb-3 my-1">
            <div class="row gx-5 justify-content-center">
                <div class="col-lg-8 col-xl-6">
                    <div class="text-center">
                        <h2 class="fw-bolder" id="MyPage" ><i class="far fa-user"></i>&nbsp; MY PAGE </h2>
                          <p class="lead fw-normal text-muted mb-5 small">개인 정보, 개인 정보 수정, 내가 쓴 글, 내가 쓴 덧글, 상담내역을 확인해보세요 </p>
                    </div>
                </div>
            </div>
          </div>
<div class="container-xxl">
	<section class="py-5 bg-light">
		<div class="container px-5">
			<div class="row gx-5" >
				<div class="col-xl-4">
					<div class="card border-0 h-100">
						<div class="card-body p-4">
							<div class="d-flex h-100 align-items-center justify-content-center">
								<div class="text-center">
									<div class="h5 fw-bolder text-center">Profile</div>
									<img style="width: 250px; height: 350px; margin-top: 0px;" id="preview-img" src="/profile/${member.memId}_profile" onerror="this.src='/profile/non.png'">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-8">
					<h2 class="fw-bolder fs-5 mb-4 d-inline" >개인 정보</h2>
					<!-- News item-->
					<div class="mt-4 mb-4">
						<div class="text-muted d-inline fst-italic mb-2">아이디
							&nbsp;&gt;</div>
						<div class="text-decoration-none d-inline fst-italic mb-2">${member.memId}&nbsp;&nbsp;&nbsp;</div>
						<span>
						<select class="custom-select custom-select-sm d-inline " style="width: 100px" id="id_state">
							<option value="L" ${member.memState eq 'L'?'selected="selected"':'' }>&nbsp;접속중&nbsp;</option>
							<option value="H" ${member.memState eq 'H'?'selected="selected"':'' }>&nbsp;자리 비움&nbsp;</option>
						</select>
						</span>
					</div>
					<div class="mb-3">
						<div class="text-muted d-inline fst-italic mb-2">이름
							&nbsp;&gt;</div>
						<div class="text-decoration-none d-inline fst-italic mb-2">${member.memName}&nbsp;</div>
						<div class="text-decoration-none d-inline fst-italic mb-2">${member.memPosit eq 'ADMIN' ? '관리자' : member.memPosit eq 'CLIENT' ? '의뢰인' : '변호사/노무사' }&nbsp;님&nbsp;&nbsp;</div>
					</div>
					<div class="mb-3">
						<div class="text-muted d-inline fst-italic mb-2">생년월일
							&nbsp;&gt;</div>
						<div class="text-decoration-none d-inline fst-italic mb-2">${member.memBir}&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="mb-3">
						<div class="text-muted d-inline fst-italic mb-2">핸드폰 번호
							&nbsp;&gt;</div>
						<div class="text-decoration-none d-inline fst-italic mb-2">
							&nbsp;&nbsp;${member.memPhone}&nbsp;&nbsp;&nbsp;
						</div>
					</div>
					<div class="mb-3">
						<div class="text-muted d-inline fst-italic mb-2">이메일
							&nbsp;&gt;</div>
						<div class="text-decoration-none d-inline fst-italic mb-2">
							&nbsp;&nbsp;${member.memMail}&nbsp;&nbsp;&nbsp;
						</div>
					</div>
					<div class="mb-3">
						<div class="text-muted fst-italic mb-2">주소 &nbsp;&gt;</div>
						<div class="text-decoration-none fst-italic mb-2">
							<i class="far fa-address-book"></i>&nbsp;&nbsp;${member.memAdd1}&nbsp;&nbsp;&nbsp;
						</div>
						<div class="text-decoration-none fst-italic mb-2">${member.memAdd2}&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="mb-3">
						<div class="text-muted fst-italic d-inline mb-2">가입일자 &nbsp;&gt;</div>
						<div class="text-decoration-none fst-italic d-inline mb-2">${member.memRegDate}&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="mb-3">
						<div class="text-muted fst-italic d-inline mb-2">최근 접속일 &nbsp;&gt;</div>
						<div class="text-decoration-none fst-italic d-inline mb-2">${member.memEndDate}&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="text-end mb-5 mb-xl-0">
						<a class="text-decoration-none" href="<%=request.getContextPath()%>/info/mypageEdit?memId=${member.memId}"> 개인정보수정 
						<i class="bi bi-arrow-right"></i>
						</a>
					</div>
				</div>
			</div>
			<div class="mb-3">
				<div class="text-muted fst-italic mb-2">이력 &nbsp;&gt;</div>
				<div class="text-decoration-none fst-italic mb-2 overflow-auto maydayscrollbar" style="height: 150px;">${fn:replace(member.memCareer,newLine, "<br/>")}&nbsp;&nbsp;&nbsp;</div>
			</div>
		</div>
	</section>
		<section class="py-3">
			<div class="container-xxl px-3 my-3">
				<div class="row gx-5 justify-content-center">
					<div class="col-lg-8 col-xl-6 ">
						<div class="text-center">
							<h2 class="fw-bolder">활동 내역</h2>
							<p class="lead fw-normal text-muted mb-5 small">내가 쓴 글 , 내가 쓴 댓글</p>
						</div>
					</div>
				</div>
				<div class="row gx-5" id="MyWrite">
					<div class="col-lg-12 mb-5 mt-3">
					<div class="row">
					<div class="col-9">
						<div class="badge bg-primary bg-gradient rounded-pill mb-2">board</div>
						<span  >내가 쓴 글</span>
					</div>
						<div class="col-3">
						<a href="<%=request.getContextPath()%>/board/free/boardList" class="text-muted fst-italic ms-5">&nbsp;&gt;자유게시판</a>
						<a href="<%=request.getContextPath()%>/board/question/boardList" class="text-muted fst-italic ms-5">&nbsp;&gt;질문게시판</a>
						</div>
					</div>
						<div class="card shadow border-0 overflow-auto maydayscrollbar" style="height: 400px;">
							<div class="d-flex align-items-end justify-content-between">
								<ul class="list-group a-link" style="width: 100%;">
									<c:forEach items="${userBoardList}" var="board">
										<li class="list-group-item row" style="border: 0;">
										<a class="nav-link d-inline" href="/board/boardView?boNo=${board.boNo }"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;${board.boTitle }</a>
											<span class="text-muted fst-italic mb-2 small ps-5 col-2" ><i class="far fa-calendar"></i>&nbsp;&nbsp;${board.boRegDate }</span>
											<span class="text-muted fst-italic mb-2 small ps-2 col-2" ><i class="far fa-hand-point-up"></i>&nbsp;&nbsp;${board.boHit }</span>
											</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="row gx-5" id="MyReply">
					<div class="col-lg-12 mb-5 mt-3 ">
					<div class="badge bg-primary bg-gradient rounded-pill mb-2 ">comment</div>
						<span >내가 쓴 댓글</span>
						<div class="card shadow border-0 overflow-auto maydayscrollbar" style="height: 400px;">
							<div class="d-flex align-items-end justify-content-between">
								<ul class="list-group a-link" style="width: 100%;">
									<c:forEach items="${userReplyList}" var="reply">
									<li class="list-group-item" style="border: 0;">
										<a class="nav-link d-inline" href="/board/boardView?boNo=${reply.reParentNo }"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;${reply.reContent }</a>
											<span class="text-muted fst-italic mb-2 small ps-5" ><i class="far fa-calendar"></i>&nbsp;&nbsp;${reply.reRegDate }</span>
											</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section class="py-3 pb-5 mb-5">
			<div class="container-xxl px-3 my-3">
				<div class="row gx-5 justify-content-center">
					<div class="col-lg-8 col-xl-6 ">
						<div class="text-center">
							<h2 class="fw-bolder">예약 /결제 내역</h2>
							<p class="lead fw-normal text-muted mb-5 small">변호사/노무사 상담 예약/결제 내역</p>
						</div>
					</div>
				</div>
				<div class="row gx-5">
					<div class="col-lg-6 mb-5 mt-3">
					<div class="row">
					<div class="col-sm-9">
						<div class="badge bg-primary bg-gradient rounded-pill mb-2"> reservation </div>
						<span id="MyResv" >예약 내역</span>
					</div>
						<div class="col-sm-3">
						<a href="<%=request.getContextPath()%>/member/expertList" class="text-muted fst-italic ms-5">&nbsp;&gt;변호사정보</a>
						</div>
					</div>
						<div class="card shadow border-0 overflow-auto maydayscrollbar" style="height: 400px;">
							<div class="d-flex align-items-end justify-content-between">
								<ul class="list-group a-link" style="width: 100%;">
									<c:forEach items="${paymentList}" var="payment">
										<li class="list-group-item row" style="border: 0;">
										<span class="text-muted fst-italic mb-2  ps-1 col-2" ></span>
										<c:if test="${sessionScope.USER_INFO.userRole eq 'EXPERT'}"  >
										   <span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-user"></i>&nbsp;&nbsp;${payment.clientName } 님 </span>
										   </c:if>
										   <c:if test="${sessionScope.USER_INFO.userRole eq 'CLIENT'}"  >
										   <span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-user"></i>&nbsp;&nbsp;${payment.lawyerName } 님 </span>
										   </c:if>
											<span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="fas fa-bars"></i>&nbsp;&nbsp;${payment.itemName } </span>
											<span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-clock"></i>&nbsp;&nbsp;${payment.quantity } 분</span>
											<span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-calendar"></i>&nbsp;&nbsp;${payment.resDate }</span>
											</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-5 mt-3">
					<div class="row">
					<div class="col-8">
						<div class="badge bg-primary bg-gradient rounded-pill mb-2"> Payment history </div>
						<span id="MyPay">결제 내역</span>
					</div>
						<div class="col-4">
						<span class="text-muted fst-italic ms-5"><i class="fas fa-coins"></i>&nbsp;&gt;&nbsp;${empty paymentTotal ? 0 : paymentTotal}&nbsp;&nbsp;&nbsp;<i class="fas fa-won-sign"></i></span>
						</div>
					</div>
						<div class="card shadow border-0 overflow-auto maydayscrollbar" style="height: 400px;">
							<div class="d-flex align-items-end justify-content-between">
								<ul class="list-group a-link" style="width: 100%;">
									<c:forEach items="${paymentList}" var="payment">
										<li class="list-group-item row" style="border: 0;">
										<span class="text-muted fst-italic mb-2  ps-1 col-2" ></span>
										  <c:if test="${sessionScope.USER_INFO.userRole eq 'EXPERT'}"  >
										   <span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-user"></i>&nbsp;&nbsp;${payment.clientName } 님 </span>
										   </c:if>
										   <c:if test="${sessionScope.USER_INFO.userRole eq 'CLIENT'}"  >
										   <span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-user"></i>&nbsp;&nbsp;${payment.lawyerName } 님 </span>
										   </c:if>
											<span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="fas fa-bars"></i>&nbsp;&nbsp;${payment.itemName } </span>
											<span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-clock"></i>&nbsp;&nbsp;${payment.quantity } 분</span>
											<span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="fas fa-won-sign"></i>&nbsp;&nbsp;${payment.totalAmount }</span>
											<span class="text-muted fst-italic mb-2  ps-1 col-2" ><i class="far fa-calendar"></i>&nbsp;&nbsp;${fn:substring(payment.resDate,0,10)}</span>
											</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body >
<script type="text/javascript">
	$('#id_state').on('change',function(){
		var params={"memId":"${member.memId}","memState":$('#id_state').val()};
		console.log(params);
		if(confirm("상태를 변경하시겠습니까?")){
			 $.ajax({
				type: 'post',
				url: '/member/updateState',
				data: params,
				success:function(data){
					alert(data);
				}
			});//ajax 
		}//confirm
	}); // on
</script>
</html>