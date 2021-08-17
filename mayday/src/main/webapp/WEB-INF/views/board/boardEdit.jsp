<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>board</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body>
<div class="container text-center">
<section class="py-5">
   <div class="container px-5 my-5">
      <div class="card-body">
		<form:form action="boardModify" id="myForm" method="post" modelAttribute="board">
		 <article>
		 <c:if test="${e ne null }">
	<div class="">해당 글이 존재하지 않거나, 조회수 증가 실패. </div>
</c:if>
	<c:if test="${e eq null }">
	<header class="mb-4">
	<div class="row">
	<div class="col-md-12 d-flex justify-content-start">
	<h1 class="fw-bolder mb-1">${board.boTitle }</h1>
	</div>
	</div>
	<div class="row">
	<hr>
	<div class="col-md-2">
	<div class="text-muted d-inline fst-italic">글번호 &nbsp;&gt;</div>
	<div class="text-decoration-none d-inline fst-italic">${board.boNo}<form:hidden path="boNo" />&nbsp;&nbsp;&nbsp;</div>
	</div>
	<div class="col-md-1 text-muted d-inline fst-italic">구분 &nbsp;&gt;</div>
	<div class="col-md-2 text-decoration-none d-inline fst-italic">
	
	<c:if test="${board.codeParentCd eq 'BOD10 ' }">
							<form:select path="boCd" class="custom-select form-control ">
								<form:option value="">--선택하세요--</form:option>
								<form:options items="${codeList }" itemLabel="codeName"
									itemValue="codeCd"></form:options>
							</form:select>
						</c:if>
								 <form:hidden path="codeParentCd" /> 
						<c:if test="${board.codeParentCd ne 'BOD10 ' }">
									 ${board.codeName }<form:hidden path="codeName" />
									 <form:hidden path="boCd" />
						</c:if>
						<div id = "boCdInfo" class="fst-italic text-warning text-center col-md-10 pb-5 h-5"></div>
						</div>
						
						<div class="col-md-2">
	<div class="text-muted d-inline fst-italic mb-2">작성자 &nbsp;&gt;</div>
	<div class="text-decoration-none d-inline fst-italic mb-2">${board.memName }&nbsp;&nbsp;&nbsp;</div>
	</div>
	<div class="col-md-2">
	<div class="text-muted d-inline fst-italic mb-2">조회수 &nbsp;&gt;</div>
	<div class="text-decoration-none d-inline fst-italic mb-2">${board.boHit }&nbsp;&nbsp;&nbsp;</div>
	</div>
	</div>
	</header>
	<section class="mb-5">
	<hr>
	<div class="row-vh d-flex flex-row justify-content-start">
	<div class="col-md-2 d-inline mt-1">
	<h5>제목</h5>
	</div>
	<div class="col-md-6 d-inline">
	<textarea class="form-control" rows="1" name="boTitle" id="boTitle" placeholder="제목을 입력하세요">${board.boTitle }</textarea>
	</div>
	</div>
	<div class="row">
	<div class="col-md-3"></div>
	<div id = "boTitleInfo" class="col-md-3 text-warning d-inline text-reft d-flex justify-content-start"></div>
	</div>
	<hr>
	<div class="fs-5 mt-5 mb-5">
		<textarea  class="form-control" rows="30" name="boContent" id="boContent" placeholder="수정내용을 입력하세요">${board.boContent }</textarea>
		<div id = "boContentInfo" class="text-warning d-inline text-reft col-md-6 d-flex justify-content-start"></div>
	</div>
	<div class="row">
	<div class="col-md-7"></div>
	<div class="col-md-5">
	<a href="/board/${board.codeParentCd eq 'board10 '?'precedents':
					  board.codeParentCd eq 'board20 '?'counsel':
					  	'question'}/boardList" class="btn btn-outline-warning bg-white link-light text-dark text-center"><i class="fas fa-list"></i>&nbsp;목록</a>
	<a href="/board/boardView?boNo=${board.boNo}" class="btn btn-outline-warning bg-white link-light text-dark text-center"><i class="fas fa-chevron-left"></i>&nbsp;해당 뷰</a>
	<button id="btnSave" class="btn btn-outline-warning bg-white link-light text-dark text-center" >
	<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;저장</span></button>
	<button type="submit" formaction="/board/boardDelete" class="btn btn-outline-warning bg-white link-light text-dark text-center">
	<span aria-hidden="false"><i class="far fa-trash-alt"></i>&nbsp;삭제</span></button> 
	</div>
	</div>
	</section>
	</c:if>
	</article>
	</form:form>
	</div>
	</div>
</section>
<%@include file="/WEB-INF/inc/footer.jsp"%>
<script>
$(function() {
	$('#btnSave').click(function(e) {
		e.preventDefault();		
		if(fn_check()==true){
			document.forms[0].submit();			
		}
		else{
			alert('정보를 입력해주시기바랍니다.');
		}
	})	
		
})

function fn_check() {
	var isOk = true;
	event.preventDefault();		
		if($("#boCd").val()==""){
			$("#boCdInfo").text("선택해주세요.")			
			isOk = false;
		}else if ($("#boCd").val()!=""){
			$("#boCdInfo").text("")
		}
		
		if($("#boTitle").val()==""){
			$("#boTitleInfo").text("제목을 입력해주세요.")		
			isOk = false;
		}else if ($("#boTitle").val()!=""){
			$("#boTitleInfo").text("")
		}		
		if($("#boContent").val()==""){
			$("#boContentInfo").text("내용을 입력해주세요.")			
			isOk = false;
		}else if ($("#boContent").val()!=""){
			$("#boContentInfo").text("")
		}		
		return isOk;
		
}
</script>
</body>
</html>