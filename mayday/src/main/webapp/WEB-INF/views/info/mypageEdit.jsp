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
<!-- Modal change -->
<div class="modal" id="modal_pass_change" tabindex="-1" role="dialog" aria-labelledby="passChangeModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="passChangeModalLabel">비밀번호 변경</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        <form>
        	<div class="form-group" id="modal-input">
        		<label for="pass-check" class="control-label">비밀번호</label>
        		<input type="password" class="form-control check_pass" id="pass-check">
        		<input type="hidden" class="form-control" id="memId" name="memId" value="${member.memId}">
        		<input type="hidden" class="form-control state" id="state" name="" value="C">
        	</div>
        		<span id="result-box"></span>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="fn_pass_ck(this);" id="btn_check" value="C">비밀번호 확인</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> 
<!-- Modal leave -->
<div class="modal" id="modal_pass_ck" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">탈퇴- 비밀번호 확인</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        <form>
        	<div class="form-group" id="modal-input">
        		<label for="pass-check" class="control-label">비밀번호 확인</label>
      			<input type="password" class="form-control check_pass" id="leave_pass">
        		<input type="hidden" class="form-control" id="memId" name="memId" value="${member.memId}">
        		<input type="hidden" class="form-control state" id="state" name="" value="L">
        		<span id="result-box"></span>
        	</div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="fn_pass_ck(this);" id="btn_check_leave" value="L">비밀번호 확인</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> 
<!-- 개인정보 수정 -->
	<div class="container-xxl">
		<div class="container-xxl pt-3 pb-1 px-1 my-1">
			<div class="row gx-5 justify-content-center">
				<div class="col-lg-8 col-xl-6 pt-3">
					<div class="text-center">
						<h2 class="fw-bolder"><i class="far fa-user"></i>&nbsp;MY PAGE</h2>
						<p class="lead fw-normal text-muted mb-5 small">개인 정보 수정</p>
					</div>
				</div>
			</div>
		</div>
<div class="container-xxl">
		<section class="py-5 bg-light">
				<div class="container px-5">
					<div class="row gx-5">
						<div class="col-xl-4">
							<div class="h6 fw-bolder text-center">profile</div>
							<div class="card border-0">
								<div class="card-body p-4">
									<div class="align-items-center justify-content-center" style="height: 550px; margin-top: 0px;">
											<img style="width: 250px; height: 400px; margin-top: 0px;" id="preview-img" src="/profile/${member.memId}_profile" onerror="this.src='/profile/non.png'">
											<form:form id="profileForm" action="/info/profile/upload" modelAttribute="member" method="post" enctype="multipart/form-data" >
												<label class="fileBtn" for="profile">프로필 등록</label>
												<input class="form-control input-sm" type="file" name="profile" id="profile" />
												<button type="submit" class="btn btn-outline-primary bg-white"><i class="far fa-image"></i></button>
											</form:form>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-8">
							<form:form action="/info/mypageModify" method="post"	modelAttribute="member" enctype="multipart/form-data">
							<h2 class="fw-bolder fs-5 mb-4 d-inline">개인 정보</h2>
							<!-- News item-->
							<div class="row mt-4 mb-2">
								<div class="text-muted d-inline fst-italic mb-2 col-2">아이디&nbsp;&gt;</div>
								<form:hidden path="memId" />
									<div class="text-decoration-none d-inline fst-italic mb-2 col-1">${member.memId}&nbsp;&nbsp;&nbsp;</div>
									<div class="text-muted d-inline fst-italic mb-2 col-2">비밀번호 &nbsp;&gt;</div>
									<div class="col-3">
										<button type="button" class="btn btn-outline-primary btn-sm" data-toggle="modal" data-target="#modal_pass_change" >비밀번호 변경</button>
									</div>
								<form:hidden class="form-control text-decoration-none d-inline fst-italic mb-2 col-3" path="memPass" />
							</div>
							<div class="row mb-3">
								<div class="text-muted d-inline fst-italic mb-2 col-2">이름	&nbsp;&gt;</div>
								<form:input class="form-control text-decoration-none d-inline fst-italic mb-2 col-4" path="memName" />
								<%-- <div class="text-decoration-none d-inline fst-italic mb-2 col-2">${member.memPosit eq 'ADMIN' ? '관리자' : member.memPosit eq 'CLIENT' ? '의뢰인' : '변호사/노무사' }&nbsp;님&nbsp;&nbsp;</div> --%>
							</div>
							<div class="row mb-3">
								<div class="text-muted d-inline fst-italic mb-2 col-2">생년월일	&nbsp;&gt;</div>
								<input type="date" class="form-control input-sm d-inline col-4" name="memBir" value="${member.memBir}">
							</div>
							<div class="row mb-3">
								<div class="text-muted d-inline fst-italic mb-2 col-2">핸드폰 번호 &gt;</div>
								<form:input class="form-control d-inline col-4" path="memPhone" />
							</div>
							<div class="row mb-3">
								<div class="text-muted d-inline fst-italic mb-2 col-2">이메일
									&nbsp;&gt;</div>
								<form:input class="form-control d-inline col-4" path="memMail" />
							</div>
							<div class="row mb-3">
								<div class="text-muted fst-italic mb-2 col-2">주소&nbsp;&gt;</div>
								<div class="col-8">
									<input type="button" class="btn btn-outline-warning " onclick="fn_Postcode();" value="우편번호 찾기">
									<form:input class="form-control " path="memAdd1" />
									<form:input class="form-control " path="memAdd2" />
								</div>
							</div>
							<div class="row mb-3">
								<div class="text-muted fst-italic mb-2 col-3">이력
									&nbsp;&gt;</div>
								<textarea class="form-control d-inline" rows="5"
									name="memCareer">${member.memCareer }</textarea>
							</div>
							<div class="text-end mb-5 mb-xl-0">
								<button type="submit" class="btn border-0 link-light text-center" >
										<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;저장</span></button>
								<button type="button" class="btn border-0 link-light text-center" id="btn_leave" data-toggle="modal" data-target="#modal_pass_ck" data-whatever="leave">
								<span aria-hidden="false"><i class="fas fa-user-slash"></i>&nbsp;탈퇴하기</span></button>
								<a class="text-decoration-none"
									href="<%=request.getContextPath()%>/info/mypage"> 목록으로 <i	class="bi bi-arrow-right"></i>
								</a>
							</div>
							</form:form>
						</div>
					</div>
				</div>
		</section>
		</div>
	</div>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-img")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}
// input file에 change 이벤트 부여
const inputImage = document.getElementById("profile")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})
function fn_Postcode() {
     new daum.Postcode({
         oncomplete: function(data) {
             // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

             // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
             // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
             var roadAddr = data.roadAddress; // 도로명 주소 변수
             var extraRoadAddr = ''; // 참고 항목 변수

             // 법정동명이 있을 경우 추가한다. (법정리는 제외)
             // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
             if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                 extraRoadAddr += data.bname;
             }
             // 건물명이 있고, 공동주택일 경우 추가한다.
             if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
             }
        
            

             // 우편번호와 주소 정보를 해당 필드에 넣는다.
            if(roadAddr == ' '){
         	   
             document.getElementById("memAdd1").value = data.jibunAddress;
         	   
            }else {						
             document.getElementById("memAdd1").value = roadAddr;
}
             
            document.getElementById("memAdd2").focus();
          
         }
     }).open();
 }
 
 function fn_pass_ck(obj){
	 var state = $(obj).val();
	 if($('#leave_pass').val().length > 1){
		 console.log('탈퇴 비번',$('#leave_pass').val().split());
		 var memberVO = {"memId":$('#memId').val(),"memPass":$('#leave_pass').val(),"state":state};
	 }
	 if($('#pass-check').val().length > 1){
		 console.log('비번 변경',$('#pass-check').val().split());
		 var memberVO = {"memId":$('#memId').val(),"memPass":$('#pass-check').val(),"state":state};
	 }
/* 	var memberVO = {"memId":$('#memId').val(),"memPass":$('.check_pass').val(),"state":state}; */
	console.log("state",state);
	console.log(memberVO);
	$.ajax({
		type: "post",
		url: '<c:url value="/member/passCheck"/>', 
		data: memberVO,
		dataType: 'JSON',
		success: function (data) {
			if(data.result){
				var str = '<span style="font-size: smaller; color:blue;">'+data.message+'</span>'
				$('#result-box').html('');
				$('#result-box').append(str);
				if(data.state=='L'){
					$('#btn_check_leave').html('');
					$('#btn_check_leave').html('탈퇴하기');
					$('#btn_check_leave').attr('onclick','fn_leave()');
				}else if(data.state =='C'){
					$('#passChangeModalLabel').html('');
					$('#passChangeModalLabel').html('변경하실 비밀번호를 입력하세요');
					$('#pass-check').val('');
					$('#result-box').html('');
					var input = '<label for="pass-check-check" class="control-label">비밀번호 확인</label>';
	        			input += '<input type="password" class="form-control check" id="pass-check-check" >';
	        			$('#modal-input').append(input);
	        			$('#modal-input').on('blur','#pass-check-check',function (){
	        				var res = '';
	        				if($('#pass-check').val()==$('#pass-check-check').val()){
	        					res = '<span style="font-size: smaller; color:blue;">일치합니다.</span>'
        						$('#btn_check').attr('disabled',false);
	        				}else{
	        					res = '<span style="font-size: smaller; color:red;">일치하지않습니다.</span>'
        						$('#btn_check').attr('disabled',true);
        						$('#pass-check-check').val('');
        						$('#pass-check-check').focus();
	        				}
	        				$('#result-box').html('');
	        				$('#result-box').append(res);
	        			});
					$('#btn_check').html('');
					$('#btn_check').html('변경하기');
					$('#btn_check').attr('disabled',true);
					$('#btn_check').attr('onclick','fn_chage_pass()');
				}
			}else{
				var str = '<span style="font-size: smaller; color:red;">'+data.message+'</span>'
				$('#result-box').html('');
				$('#result-box').append(str);
				$('#pass-check').val('');
				$('#pass-check').focus();
			}
		}
	});
}
function fn_leave(){
	var memberVO = {"memId":$('#memId').val(),"memPass":$('#leave_pass').val()};
	
	if(confirm('정말로 탈퇴하시겠습니까?')){
		$.ajax({
			type:"post",
			url: '<c:url value="/member/leave"/>',
			data: memberVO,
			dataType: 'JSON',
			success: function (data) {
				if(data.result){
					alert(data.message)
					window.location.href='<c:url value="'+data.url+'" />';
				}else{
					alert(data.message)
					window.location.href='<c:url value="'+data.url+'" />';
				}
			},
			error(req,status,e){
				console.log(req.status);
			}
		});
	}
}
function fn_chage_pass() {
var memberVO = {"memId":$('#memId').val(),"memPass":$('#pass-check').val()};
	console.log('비밀번호 변경 member={}',memberVO);
	
	if(confirm('비밀번호를 변경하시겠습니까?')){
		$.ajax({
			type:"post",
			url: '<c:url value="/member/changePW"/>',
			data: memberVO,
			dataType: 'JSON',
			success: function (data) {
				if(data.result){
					alert(data.message)
					window.location.href='<c:url value="'+data.url+'" />';
				}else{
					alert(data.message)
					window.location.href='<c:url value="'+data.url+'" />';
				}
			},
			error(req,status,e){
				console.log(req.status);
			}
		});
	}
}
</script>
</html>