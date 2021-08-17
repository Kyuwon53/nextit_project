<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-yellow">
              <div class="container">
                  <a class="navbar-brand" href="<%=request.getContextPath() %>/" id="top"><img alt="" src="/resource/image/mayday2.png" style="width: 80%;"></a>
                 <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                 <div class="collapse navbar-collapse" id="navbarSupportedContent">
                     <ul class="navbar-nav ms-auto mb-2 mb-lg-0" >
                         <%-- <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/">Home</a></li> --%>
                         <li class="nav-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/board/notice/boardList">공지사항</a></li>
                         <li class="nav-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/board/free/boardList">자유게시판</a></li>
                         <li class="nav-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/board/question/boardList">질문게시판</a></li>
                         <li class="nav-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/faq/question/faqList">FAQ</a></li>
                         <li class="nav-item dropdown">
                             <a class="nav-link dropdown-toggle text-size-m" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">정보</a>
                             <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                 <li><a class="dropdown-item" href="<%=request.getContextPath() %>/faq/precedents/faqList"> 판례 </a></li>
                                 <li><a class="dropdown-item" href="<%=request.getContextPath() %>/faq/counsel/faqList">상담 사례</a></li>
                                 <li><a class="dropdown-item" href="<%=request.getContextPath() %>/petition/petitionList">국민 청원</a></li>
                                 <li><a class="dropdown-item" href="<%=request.getContextPath() %>/news/newsList">뉴스</a></li>
                                 <li><a class="dropdown-item" href="<%=request.getContextPath() %>/law/lawList">법령</a></li>
                             </ul>
                         </li>
                         <li class="nav-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/member/expertList">변호사 정보</a></li>
                         <c:if test="${sessionScope.USER_INFO eq null }">
								<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
									<li class="nav-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/login/login">로그인</a></li>
									<li class="nav-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/signup/signup">회원가입</a></li>
								</ul>
							</c:if>
                         <c:if test="${sessionScope.USER_INFO ne null }">
                         <li class="nav-item dropdown">
                             <a class="nav-link dropdown-toggle text-size-m" id="navbarDropdownPortfolio" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">${sessionScope.USER_INFO.userName } 님</a>
                             <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
		                         <c:if test="${sessionScope.USER_INFO.userRole eq 'ADMIN'}"  >
		                         	<li class="dropdown-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/member/memberList"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;회원정보</a></li>
		                         	</c:if>
		                         	<c:if test="${sessionScope.USER_INFO.userRole ne 'ADMIN'}"  >
                                 <li class="dropdown-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/info/mypage"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;My Page</a></li>
	                					</c:if>
	                					<c:if test="${sessionScope.USER_INFO.userRole eq 'EXPERT'}"  >
                                 <li class="dropdown-item"><a class="nav-link text-size-m" href="<%=request.getContextPath() %>/chat/chatExpert"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;상담 채팅</a></li>
	                					</c:if>
                                 <li class="dropdown-item"><a class="nav-link text-size-m" href="<%= request.getContextPath()%>/qna/qnaList"> 1 : 1 문의게시판</a></li>
                                 <li class="dropdown-item"><a class="nav-link text-size-m" href="<%= request.getContextPath()%>/login/logout.wow"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;로그아웃</a></li>
                             </ul>
                         </li>
                         </c:if>
                     </ul>
                 </div>
             </div>
         </nav>
