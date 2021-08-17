<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>petition</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<style>

</style>
<body>

<div class="container text-center">
<section class="py-5">
   <div class="container px-5 my-5 ">
      <div class="card-body">
           <h1 class="fw-bolder">MAYDAY</h1>
           <p class="lead fw-normal text-muted mb-0"> 글 작성중</p>
			<form:form action="/petition/petitionRegist" method="post" modelAttribute="petition" >
      <article>
      <header class="mb-4">
      <div class="row">
	<div class="col-md-3 text-muted d-inline fst-italic mb-2">구분 &nbsp;&gt;
		<form:select path="petProgress" class="selectpicker">
								<form:option value="청원진행중">청원진행중</form:option>
								<form:option value="청원진행중">청원마감</form:option>
								<form:option value="답변완료">답변완료</form:option>
							</form:select>
					</div>
					</div>
					</header>
					<section class="mb-5">
						<form:input path="petTitle"  class="form-control d-inline" placeholder="제목을 입력하세요"/>
						<hr>
						<div class="row">
						<div class="col-md-2">
						<input type="text" disabled="disabled" class="form-control d-inline" value="청원시작" style="background-color: white;">
						</div>
						<div class="col-md-10">
						<input type="date" name="petStartDate" class="form-control d-inline" placeholder="청원시작 일자">
						</div>
						</div>
							<div class="row">
						<div class="col-md-2">
						<input type="text" disabled="disabled" class="form-control d-inline" value="청원마감" style="background-color: white;">
						</div>
						<div class="col-md-10">
						<input type="date" name="petEndDate" class="form-control d-inline" placeholder="청원시작 일자">
						</div>
						</div>
						
			<hr>
			<form:input path="petLink"  class="form-control d-inline" placeholder="청원링크 입력하세요"/>
			<hr>
						<div class="fs-5 mt-4 mb-5">
		<textarea  class="form-control" rows="25" name="petContent" placeholder="내용을 입력하세요">${petition.petContent }</textarea>
	</div>
				<div class="row">
	<div class="col-md-8"></div>
	<div class="col-md-4 text-right">		
				<a href="/petition/petitionList" class="btn btn-outline-warning bg-white link-light text-dark text-center"><i class="fas fa-list"></i>&nbsp;목록</a>
				<button type="submit" class="btn btn-outline-warning bg-white link-light text-dark text-center">
					<span aria-hidden="false"><i class="far fa-save"></i>&nbsp;작성</span>
				</button>
			</div>
		</div>
		</section>
		</article>
	</form:form>
	</div>
	</div>
	</section>
	</div>
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">
$(function () {
	$.datepicker.setDefaults({
	    dateFormat: 'yy-mm-dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    showMonthAfterYear: true,
	    yearSuffix: '년'
	  });
	$("#petStartDate").datepicker({
		onSelect: function (dateText, inst) {
			var stDate = dateText.split("-");
			var dt = new Date(stDate[0],stDate[1],stDate[2]);
			var year = dt.getFullYear();
			var month = dt.getMonth()+1;
			month = month+"";
			if(month.length == "1") month = "0"+month;
			var date = dt.getDate();
			date = date +"";
			if(date.length == "1") date = "0" + date;
			var endDate = year+"-" + month+"-" + date;
			$("#petEndDate").val(endDate);
		}
	});
	
});
</script>
</html>