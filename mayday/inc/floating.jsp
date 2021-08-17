<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	 #wrap {margin:0 auto;text-align:center;}
	 #quick_bg {margin:0 auto;text-align:center;width:1400px;position:relative;}
	 #quick {position:absolute;z-index:2;top:150px;width:10px;right:0px;}
	 #container {position:relative;}
</style>
	
</head>
<body>
	<div id="wrap">
		<div id="container">
			<div id="quick_bg">
				<div id="quick">
					<a href="/member/expertList"><img style = "width:100px; height:100px; "src="<c:url value = '/resource/image/consult.png'/>"/></a>
					<a href="/board/question/boardList"><img style = "width:100px; height:100px; "src="<c:url value = '/resource/image/service.png'/>"/></a>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var quick_menu = $('#quick');
var quick_top = 430;

quick_menu.css('top', $(window).height() );
$(document).ready(function(){
quick_menu.animate( { "top": $(document).scrollTop() + quick_top +"px" }, 200 ); 
$(window).scroll(function(){
quick_menu.stop();
quick_menu.animate( { "top": $(document).scrollTop() + quick_top + "px" }, 500 );
});
});
</script>
</html>