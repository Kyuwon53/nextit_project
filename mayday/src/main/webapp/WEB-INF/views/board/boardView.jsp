<%@page import="javax.xml.crypto.Data"%>
<%@page import="com.mayday.reply.vo.ReplySearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>MAYDAY</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>
<body>
	<section class="py-5">
		<div class="container-xxl px-5 my-5">
			<div class="card-body">
				<article>
					<c:if test="${e ne null }">
						<div class="alert alert-warning">해당 글이 존재하지 않습니다. 또는 조회수증가
							실패했습니다.</div>
						<a
							href="/board/${board.codeParentCd eq 'BOD10 ' ? 'question':
							board.codeParentCd eq 'BOD20 '? 'free':
							'notice'}/boardList"
							class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-list" aria-hidden="true"></span>
							&nbsp;목록
						</a>
					</c:if>

					<c:if test="${board ne null }">
						<header class="mb-4">
							<h2 class="fw-bolder mb-4">${board.boTitle }</h2>
							<div class="text-muted d-inline fst-italic mb-2">글번호
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${board.boNo}
								&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">구분
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${board.codeName}&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">작성자
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${board.memName }&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">조회수
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${board.boHit }&nbsp;&nbsp;&nbsp;</div>
							<div class="text-muted d-inline fst-italic mb-2">작성일
								&nbsp;&gt;</div>
							<div class="text-decoration-none d-inline fst-italic mb-2">${board.boRegDate }&nbsp;&nbsp;&nbsp;</div>
						</header>
						<section class="mb-5">
							<hr>
							<div class="">
								<div class="fs-6 mt-5 mb-5">${fn:replace(board.boContent,newLine, "<br/>")}</div>
							</div>
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-4 d-flex justify-content-end">
									<c:if test="${sessionScope.USER_INFO.userId eq board.memId}">
										<a href="/board/boardEdit?boNo=${board.boNo }"
											class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
											class="far fa-edit"></i>&nbsp;&nbsp;수정</a>
									</c:if>
									<a
										href="/board/${board.codeParentCd eq 'BOD10 ' ? 'question':
							board.codeParentCd eq 'BOD20 '? 'free':
							'notice'}/boardList"
										class="btn btn-outline-warning bg-white link-light text-dark text-center"><i
										class="fas fa-list"></i>&nbsp;&nbsp;목록</a>
								</div>
							</div>
						</section>
						</c:if>
				</article>
				
	
		

	<!-- container -->
	<!--  댓글 등록 -->
	<section class="py-5">
		<div class="card bg-light">
			<div class="card-body">
				<form name="reply_regist" action="/reply/replyRegist" method="post"
					class="mb-4">
					<input type="hidden" name="reParentNo" value="${board.boNo }">
					<input type="hidden" name="reCategory"
						value="${board.codeParentCd}">
					<div>
						<label class="col-2"><i class="far fa-comments"></i>&nbsp;댓글 총&nbsp;<span id="totalcount"></span>&nbsp;개</label>
						<label class="col-6"></label>
						<span class="col-2"><button class="btn btn-outline-dark bg-white link-light text-dark mb-1" type="button" name="btn_reRegDate" id="btn_reRegDate">등록일<i class="fas fa-arrow-up"></i></button></span>
						<span class="col-2"><button class="btn btn-outline-dark bg-white link-light text-dark mb-1" type="button" name="btn_reLikeNo" id="btn_reLikeNo">좋아요<i class="fas fa-arrow-up"></i></button></span>
						<div class="input-group mb-2">
							<textarea name="reContent" id="Contentarea" class="form-control" rows="3"
								aria-describedby="btn_reply_regist"><c:if test="${param.reContentYN eq 'Y'}">${sessionScope.reContent}</c:if></textarea>
							<button class="btn btn-outline-primary bg-white"
								id="btn_reply_regist" type="button">등록</button>
						</div>
						
					</div>
				</form>
				<!-- 댓글목록 프로필 :  class="d-flex mb-4"-->
				
				<div id="reply_list_area" class="ms-3"></div>
				
				<div class="row text-center" id="id_reply_list_more">
			<!-- 	<a id="btn_reply_list_more"
					class="btn btn-sm btn-default col-sm-10 col-sm-offset-1"> <span
					class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
					더보기
				</a> -->
				<nav class="text-center">
	<ul class="pagination justify-content-center">
		<!-- 첫 페이지 -->
		<li class="page-item"><a class="page-link" href="/board/boardView?boNo=${board.boNo }&curPage=1" data-page="1"><span
				aria-hidden="true">&laquo;</span></a></li>
		<!-- 이전 페이지 -->
		<c:if test="${searchVO.firstPage ne 1 }">
			<li class="page-item"><a class="page-link" href="/board/boardView?boNo=${board.boNo }&curPage=${searchVO.firstPage-1}"
				data-page="${searchVO.firstPage-1 }"><span aria-hidden="true">&lt;</span></a></li>
		</c:if>
		<!-- 페이지 넘버링 -->
		<c:forEach begin="${searchVO.firstPage }" end="${searchVO.lastPage }"	step="1" var="i">
			<c:if test="${searchVO.curPage ne i }">
				<li class="page-item"><a class="page-link" href="/board/boardView?boNo=${board.boNo }&curPage= ${i}" data-page="${i}">${i}</a></li>
			</c:if>
			<c:if test="${searchVO.curPage eq i }">
				<li class="page-item active"><a class="page-link" href="#">${i}</a></li>
			</c:if>
		</c:forEach>
		<!-- 다음 페이지 -->
		<c:if test="${searchVO.lastPage ne searchVO.totalPageCount }">
			<li class="page-item"><a class="page-link" href="/board/boardView?boNo=${board.boNo }&curPage=${searchVO.lastPage+1}"
				data-page="${searchVO.lastPage+1 }"><span aria-hidden="true">&gt;</span></a></li>
		</c:if>
		<!-- 마지막 페이지 -->
		<li class="page-item"><a class="page-link" href="/board/boardView?boNo=${board.boNo }&curPage=${searchVO.totalPageCount}" data-page="${searchVO.totalPageCount}"><span
				aria-hidden="true">&raquo;</span></a></li>
	</ul>
</nav>
			</div>
			<!-- // END : 댓글 목록 영역  -->
				<!-- 댓글 수정 모달창 -->
				<div class="modal fade" id="id_reply_edit_modal" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<form name="frm_reply_edit"
								action="<c:url value='/reply/replyModify' />" method="post"
								onclick="return false;">
								<div class="modal-header">
									<h4 class="modal-title">댓글수정</h4>
								</div>
								<div class="modal-body">
									<input type="hidden" name="reNo" value="">
									<textarea rows="3" name="reContent" class="form-control"></textarea>
								</div>
								<div class="modal-footer">
									<button id="btn_reply_modify" type="button"
										class="btn btn-sm btn-info">저장</button>
									<button type="button" id="btn_reply_modify_clo" class="btn btn-default btn-sm"
										data-dismiss="modal">닫기</button>
										
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	</div>
		</div>
		</section>
		<div>
		</div>
 <form:form action="/board/replyLike" method="post" modelAttribute="replylike" id="replylike" name="replylike">
<input type="hidden" value="" name="ReplyNo" id="ReplyNo">
<input type="hidden" value="${board.boNo }" name="boNo" id="boNo" >
<input type="text" value="${sessionScope.USER_INFO.userId}" name="memId" id="memId" hidden="true">
</form:form>
<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>
<script type="text/javascript">


var params = {"curPage": ${searchVO.curPage}, "rowSizePerPage" : 10, "reParentNo":${board.boNo}, "reLikeRank":""}

function fn_reply_list(){
	$.ajax({
		type: "POST"
		,url: '<c:url value="/reply/replyList" />' 
		,traditional :true
		,dataType: 'JSON'
		,data : params
		,success: function (data) {
			if(data.result){
				 $div=$("#reply_list_area");
				$.each(data.data, function (index, element) {
					var str ='<div class="d-flex mt-2 pd-0 row">';
					 str += '<div class="col-2"><div class="rounded-pill d-inline"><i class="far fa-comment"></i>&nbsp;</div>'
				       + '<div class="text-decoration-none d-inline fst-italic mb-1 fw-bold">&nbsp;'+element.reMemName+'&nbsp;</div></div>'
					 	+ '<div class="col-6">'
				       + '<input type="hidden" class="reNo" value="'+element.reNo+'">'
				       + '<div class="text-decoration-none d-inline mb-2"><span class="content">'+ element.reContent +'</span></div></div>'
				       + '<div class="col-4 text-right mt-1">'
				       + '<div class="text-muted d-inline fst-italic mb-2 small">'+element.reRegDate+'<button name="btn_replylike" class="btn btn-outline" type="button">'
				       + '<i class="fa fa-heart"';
				       if(data.memLikeList != 0){
				    	   
				     $.each(data.memLikeList,function (idx, elem){
					    	  if(elem.memLikeNo == element.reNo){
					    		str += 'style="color:red;"';
					    	  }
					       }); 
				       }
				       str+= '></i> x '+element.likeHit;
				       //+ '<i class="far fa-heart"></i>'
				       	+ '</button></div></div>';	//나중에 mod가 널이 아니면 mod로 
				       // 로그인한 사람이 댓글 작성자면  레드 색상<i class="fa fa-heart" style="color: red;"></i>
				       if('${sessionScope.USER_INFO.userId}'==element.reMemId){
				       str += '<div class="col-2"><button name="btn_reply_edit" class="btn btn-outline" type="button"><i class="far fa-edit"></i></button>'
			                + '<button name="btn_reply_delete" class="btn btn-outline" type="button"><i class="far fa-trash-alt del"></i></button></div>';
				       }
				       str += '</div></div>';
			   
					$div.append(str)
					//$('#ReplyNo').val(element.reNo);
					//console.log(element.reNo);
				}) ; //each
				   
				$('#totalcount').text(data.searchVO.totalRowCount);
				
			}//if
			
		}//success
		,error: function(e){
			console.log(e);
			alert("에러");
		}
	})
}
var R = document.forms['replylike'];
$(document).ready(function(){
	
	$('#reply_list_area').on('click','button[name=btn_replylike]',function(e){
		$btn=$(this);
		$div = $btn.closest('.row');
		var reNo=$div.find('input[class=reNo]').val();
		$("#ReplyNo").val(reNo);
		R.submit();
			
	});

	$('#btn_reLikeNo').click(function(e){
		params.reLikeRank="R";
		console.log(params.reLikeRank);
		$('#reply_list_area').html('');
		fn_reply_list();
	});
	$('#btn_reRegDate').click(function(e){
		params.reLikeRank="L";
		console.log(params.reLikeRank);
		$('#reply_list_area').html('');
		fn_reply_list();
	});
	
	
	$('#reply_list_area').on('click','button[name=btn_reply_edit]',function(e){
		//modal 나타나게 할 때의 사전작업, 실제로 db에 수정되는거는 modal의 '저장'버튼 클릭 시
		//현재 버튼의 댓글div의 내용을 modal 수정칸에 복사
		//글번호 modal의 <input name=reNo> 에게 전달 , modal 'show'
		$btn=$(this);
		$div = $btn.closest('.row');  //버튼의 댓글 div
		$modal=$('#id_reply_edit_modal') // modal div
		//closest는 상위를 검색, find는 하위 검색(자식의 자손까지).children 하위검색(바로 자식만)
		$span=$div.find('.content'); //상위태그중 btn이랑 가장 가까운 div중 클래스가 row인거 찾고 그 다음 pre태그 (내용) 
		var content =$span.text(); //html()은 innerHTML값 return, html(' ') 은 innerHTML값 변경 
		
		$textarea=$modal.find('textarea');
		$textarea.html(content);
		var reNo=$div.find('input[class=reNo]').val(); // 글 번호
		$modal.find('input[name=reNo]').val(reNo); //modal에서 
		$modal.modal('show');

	});
	$("#btn_reply_modify").click(function(e) {
		//reNo, reContent을 파라미터로 아작스 호출
		// 컨트롤러단에서 db수정 후 success
		// success : 선택(모달내의 reContent, reNo 초기화)
		// modal 사라지기, 목록 영역에서 업데이트 적용하기(목록영억 제거 후 다시 fn_reply_list)
		e.preventDefault();
		$modal=$('#id_reply_edit_modal');
		var reNo= $modal.find('input[name=reNo]').val();
		var reContent = $modal.find('textarea').val();
	
		var res = confirm('글을 수정하시겠습니까?');
		if(res){
		$.ajax({
			type: "POST"
			, url: '<c:url value="/reply/replyModify" />'
			,data : {'reNo': reNo,'reContent' : reContent}
			,dataType: "JSON"
			,success: function(data){
				$('#reply_list_area').html('');
				fn_reply_list();
				$modal.modal('hide');
			}
			,error: function (req,st,err) {
				
			} 
		})
		}else {
			$modal.modal('hide');
		}
		
	})
		$("#btn_reply_modify_clo").click(function(e) {
		//reNo, reContent을 파라미터로 아작스 호출
		// 컨트롤러단에서 db수정 후 success
		// success : 선택(모달내의 reContent, reNo 초기화)
		// modal 사라지기, 목록 영역에서 업데이트 적용하기(목록영억 제거 후 다시 fn_reply_list)
		$modal=$('#id_reply_edit_modal');
				$modal.modal('hide');
	})
	
	$("#btn_reply_regist").click(function(e) {
		// 로그인이 안되어 있으면 로그인화면으로 가기 (interCeptor와 같이) 
		//등록버튼의 상위 div 찾기 , textarea찾기
		//textarea내용, reParentNo, reCategory 파라미터로 넘기기   -> 실제로 db에 저장
		// 등록영역 내용 지우기, 등록된 댓글 바로 등록 
		//로그인안하고 등록버튼 누르면 로그인 화면으로 
		
		
		
		if($('#Contentarea').val().replace(/\s|　/gi, '') ==''){
			swal("ERROR!", "내용을 입력해주세요!!", "error");
		}else{
		var contentarea=	$("#Contentarea").val();
		$btn=$(this); //자기 자신(등록버튼)
		$form=$btn.closest('form[name=reply_regist]');
		e.preventDefault();
		swal({
			  title: "글 등록 하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				//form 안에 reConent, reParentNo, reCategory
		    		$.ajax({
		    			type: "POST"
		    			, url : '<c:url value="/reply/replyRegist" />' 
		    			,data : $form.serialize()
		    			, dataType: 'JSON'
		    			, success  : function(data){
		    				$form.find('textarea[name=reContent]').val('');//children 바로 1단계 자식, find는  자식의 후손들까지..																			//textarea영역 내용 지우기 .
		    				//댓글 목룍영역 등록된 댓글 적용 = 댓글목록 지우고 db에서 다시 받기
		    				$('#reply_list_area').html('');
		    				fn_reply_list();
		    				var f = document.forms['search'];
		    				$('ul.pagination li a[data-page]').get(0).click();
		    			}//success
		    			,error: function(req,st,err){
		    				if(req.status==401){
		    					window.location.href='<c:url value="/login/login" />';
		    				}	//로그인하고 나서 어떻게 하면 다시 freeView화면으로 올까?? 원래는 로그인하면 무조건 홈화면 
		    				
		    				
		    			}//error
		    		});//ajax
			  }
			})
		};// #Contentarea
	}); // #btn_reply_regist.click
	//삭제
	$('#reply_list_area').on('click','button[name=btn_reply_delete]',function(e){
		$btn = $(this);
		$div = $btn.closest('div.row');
		swal({
			  title: "삭제하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  $.ajax({
						type: "POST"
						,url: "<c:url value='/reply/replyDelete' />"
						, data : {'reNo' : $div.find('input[class=reNo]').val()}	
						,dataType: 'JSON'
						,success: function(data){
							$div.remove();
							location.reload();
						}
					,error: function(req,st,err){
						console.log("삭제 부분 에러 ")
					}
					})
			    swal("삭제 완료", {
			      icon: "success",
			    });
			  }
			});
	}); // btn_reply_delete
});
	fn_reply_list();

</script>
</html>