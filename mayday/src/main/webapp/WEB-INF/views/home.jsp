<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>MAYDAY</title>
<%@include file="/WEB-INF/inc/top.jsp"%>
</head>

<body>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a05261e1754d691d95a17b2b38fcf1e2&libraries=services"></script>
	<header class="bg-yellow py-5">
		<div class="container-xxl px-5">
			<div class="row gx-5 align-items-center justify-content-center">
				<div class="col-lg-7 col-xl-6 col-xxl-5">
					<div class="my-5 text-xl-start">
						<h1 class="display-5 fw-bolder text-black mb-2">MAYDAY</h1>
						<p class="lead fw-normal text-black-50 mb-4">MAY DAY! MAYDAY!
							앞은 노동절, 뒤는 구조신호를 의미합니다. '나를 도우러 와달라'라는 프랑스어에서 따온 말이죠. 많은 근로자들이
							정당한 대우를 받지 못한채 회사의 대형로펌, 법을 몰라서 등의 이유로 그저 참고 또 일합니다. 이젠 '나를 도우러
							와달라!!' 구조 요청을 외치는 당신을 위해 함께 MAY DAY!</p>
						<div
							class="d-grid gap-3 d-sm-flex justify-content-sm-center justify-content-xl-start">
							<a class="btn btn-outline-info btn-lg px-4 me-sm-3" href="<%=request.getContextPath()%>/member/expertList">변호사 1:1 문의</a> 
							<a class="btn btn-outline-warning btn-lg px-4" href="<%=request.getContextPath()%>/board/question/boardList">고객 센터</a>
						</div>
					</div>
				</div>
				<form name="mapForm" class="col-xl-6 col-xxl-7 d-none d-xl-block " onsubmit="return false;">
				<div class="input-group mb-2">
				<input class="form-control" type="text"  id="searchMap" placeholder="검색어를 입력하세요."  aria-label="검색어를 입력하세요." aria-describedby="button-map" />
				<button class="btn btn-outline-primary bg-white" id="button-map" type="button"><i class="fas fa-search"></i></button>
				</div>
					<div id="map" style="width: 100%; height: 350px;" class="img-fluid rounded-3 my-5"></div>
					</form>
				
				</div>
			</div>
	</header>
	<section class="py-3">
		<div class="container-xxl px-3 my-3">
			<div class="row gx-5 justify-content-center">
				<div class="col-lg-8 col-xl-6 ">
					<div class="text-center">
						<h2 class="fw-bolder"><img alt="" src="/resource/image/mayday3.png" ></h2>
						<p class="lead fw-normal text-muted mb-5"> MAYDAY의 게시판들을 미리 만나보세요! </p>
					</div>
				</div>
			</div>
			<div class="row gx-5">
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/petition/petitionList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>국민청원</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%;">
								<c:forEach items="${petitionList}" var="petition">
									<li class="list-group-item" style="border: 0;"><a class="nav-link"
										href="${petition.petLink}">${petition.petTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/news/newsList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>뉴스</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%;">
								<c:forEach items="${newsList}" var="news">
									<li class="list-group-item" style="border: 0;"><a class="nav-link"
										href="${news.newsLink}">${news.newsTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/law/lawList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>법령</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%;">
								<c:forEach items="${lawList}" var="law">
									<li class="list-group-item" style="border: 0;"><a class="nav-link"
										href="${law.lawLink}">${law.lawName }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/board/notice/boardList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>공지사항</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%;">
								<c:forEach items="${noticeList}" var="notice">
									<li class="list-group-item" style="border: 0;">
									<a class="nav-link"	href="/board/boardView?boNo=${notice.boNo }">${notice.boTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/faq/precedents/faqList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>판례</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%;">
								<c:forEach items="${precedentsList}" var="precedents">
									<li class="list-group-item" style="border: 0;">
									<a class="nav-link" href="/faq/faqView?boNo=${precedents.boNo}">${precedents.boTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/faq/counsel/faqList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>상담 사례</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%; ">
								<c:forEach items="${counselList}" var="counsel">
									<li class="list-group-item" style="border: 0;">
									<a class="nav-link" href="/faq/faqView?boNo=${counsel.boNo}">${counsel.boTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/board/free/boardList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>자유게시판</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%; ">
								<c:forEach items="${freeList}" var="free">
									<li class="list-group-item" style="border: 0;">
									<a class="nav-link" href="/board/boardView?boNo=${free.boNo}">${free.boTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/board/question/boardList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>질문게시판</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%; ">
								<c:forEach items="${questionList}" var="question">
									<li class="list-group-item" style="border: 0;">
									<a class="nav-link" href="/board/boardView?boNo=${question.boNo}">${question.boTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 mb-5 mt-3">
					<a href="<%=request.getContextPath() %>/faq/question/faqList"><div class="badge bg-primary bg-gradient rounded-pill mb-2">더보기</div></a>
					<span>FAQ</span>
					<div class="card h-100 shadow border-0">
						<div class="d-flex align-items-end justify-content-between">
							<ul class="list-group a-link" style="width: 100%; ">
								<c:forEach items="${faqList}" var="faq">
									<li class="list-group-item" style="border: 0;">
									<a class="nav-link" href="/faq/faqView?boNo=${faq.boNo}">${faq.boTitle }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/inc/footer.jsp"%>
</body>

<script type="text/javascript" >
var container = document.getElementById('map');
var options = {
	center: new kakao.maps.LatLng(36.32684617354165, 127.40762938204813),
	level: 3
};

var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 지도를 생성합니다    
var map = new kakao.maps.Map(container, options);
 
// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(); 

// 키워드로 장소를 검색합니다
$('#button-map').click(function(e) {
	e.preventDefault();
	ps.keywordSearch($('#searchMap').val(), placesSearchCB);
});

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
            displayMarker(data[i]);    
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }       

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    } 
}

// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x) 
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
}

function chatting() {
	window.open('http://192.168.1.18:3000/','chat','top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no');
}
</script>
</html>
