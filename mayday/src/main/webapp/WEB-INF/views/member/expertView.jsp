<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>변호사상세보기</title>
</head>
<style>
.martop{
margin-top: 20%
}
body{
background-color: #FDF4EA
}
         
</style>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<form:form action="/kakaoPay" method="get" modelAttribute="pay">
 <input type="hidden" value="${expert.memId}" name="memId">
<section class="py-5">
                <div class="container px-5 my-5 justify-content-left">
                    <div class="row gx-5">
                        <div class="col-lg-8 col-xl-6">
                            <div class="text-left">
                                <h2 class="fw-bolder">변호사 상세보기 및 이력 </h2><br><br>
                            </div>
                        </div>
                    </div>
                    <div class="row gx-5" >                  
                       <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <a class="hov" href="#" style="z-index: 2;"><img class="card-img-top" src="/profile/${expert.memId}_profile" onerror='this.src="/profile/non.png"'/></a>
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2">변호사</div>
                                   <h5 class="card-title mb-3">${expert.memName}</h5>
                                   <c:if test="${sessionScope.USER_INFO.userRole eq 'CLIENT' }">
                                   	<h5 class="card-title mb-3"><button type="button" id="chatting" class="btn btn-outline-primary">상담 채팅</button></h5>
                                   </c:if>
                                 		<button class="btn btn-block bg-yellow" style="float:center; color: black">상담 결제하기</button>
                                    <hr>
                                    <pre class="maydayscrollbar">
아이디  	${expert.memId}
생년월일  ${expert.memBir}
휴대폰 	${expert.memPhone}
이메일 	${expert.memMail}
주소 	${expert.memAdd1} ${expert.memAdd2}
                                    </pre>
                                   
                                </div>
                               
                            </div>                   
                    </div>
                    
                       <div class="col-lg-8 mb-5" style="padding-left: 50px;padding-right: 0px;">
                            <div class="card h-100 shadow border-0">
                            <div class="text-decoration-none link-dark mb-3 mt-3"><h5 class="card-title mb-3 text-center mt-3"><i class="fas fa-info-circle">변호사이력</i></h5></div>
                            <div class="maydayscrollbar" style="height: 500px" >
                            	<div class="text-decoration-none mb-2  ps-5 ">
		                            &nbsp;&nbsp;&nbsp;${fn:replace(expert.memCareer,newLine, "<br/>")}                 
                            	</div>
                           </div>
                            </div>
                            
                    </div>
                    </div>
                    
                    </div>
                    </section>
                    <input type="hidden" id="memberInput" value="${expert.memId}">
	</form:form>
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">
	var params ={
					"userId":"${sessionScope.USER_INFO.userId}",
					"userName":"${sessionScope.USER_INFO.userName}",
					"expertId":"${expert.memId}",
					"expertName":"${expert.memName}"}
	$('#chatting').on("click",function(e){
		if(confirm("${expert.memName} 님과 채팅 하시겠습니까?")){
			$.ajax({
				type: 'POST'
				,url:'<c:url value="/chat/createroom"/>'
				,data: params
				,success: function (data) {
					console.log(data)
					location.href='<c:url value="/chat/chatLogin"/>';
				}//success
				,error: function (req, st,err) {
					console.log("req: "+req+"st: "+ "에러 "+err);
					alert(req);
				}// error
			});//ajax
		}
	});
</script>
</html>