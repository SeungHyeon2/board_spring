<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<!-- Title -->
	<title>Home</title>
	
	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	
</head>

<script type="text/javascript">
function apitest() {
	$.ajax({
	    url:'board/api/test', //request 보낼 서버의 경로
	    type:'get', // 메소드(get, post, put 등)
	    async: true, //동기: false, 비동기(기본값): ture
	    data:{msg:'hello'}, //보낼 데이터,
	    success: function(data) {
			alert('성공');
	    },
	    error: function(err) {
	        alert(err);
	    }
	});
}

</script>

<body>

	<p>게시판</p>
	
	<p>
	<a href="/board/listPageSearch?num=1">게시물 목록</a><br />
	<a href="/board/write">게시물 작성</a><br />
	
	<a href="#" onclick="apitest()">api 호출</a><br />
	
	<a href="/userauth/login">로그인</a>
	</p>
	
	
</body>
</html>
