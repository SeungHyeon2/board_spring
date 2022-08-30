<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<meta charset="UTF-8">
	<title>게시물 작성</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>

<script type="text/javascript">

function apiwrite() {
	var form = $("#mainform")[0];
	var formData = new FormData(form);
	$.ajax({
		cache : false,
		type:'POST', // 메소드(get, post, put 등)
		url:'/board/api/write', //request 보낼 서버의 경로
		contentType : false,
	    processData : false,
	    async: true, //동기: false, 비동기(기본값): true
	    data:formData,
	    
	    success: function(data) {
			var jsonObj = JSON.parse(data);
	    }, // success
	    
	    error: function(xhr, status) {
	        alert(xhs + " : " + status);
	    }
	});
}

</script>

<body>

<div class="container">
<h1>게시물 작성</h1>
	<br />
	<div id="nav">
		<%@ include file="../include/nav.jsp" %>
	</div>
	<br />
	<c:if test="${msg != 'login_error'}">
	
		<!-- <form id="mainform" name="writeForm" method="post" enctype="multipart/form-data"> -->
		<form method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-sm-2 control-label">제목</label>
				<input class="form-control" type="text" name="title" /><br />
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">작성자</label>
				<input class="form-control" type="text" name="writer" value="${member.userName}" readonly="readonly" /><br />
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">내용</label>
				<textarea class="form-control" cols="50" rows="5" name="content" ></textarea><br />
			</div>	
			<br />
			
			
			<!-- 파일 첨부 시작 -->
			<hr />
			<h4>파일 첨부</h4>
			파일 : <input class="form-control" type="file" name="file" multiple="multiple"> <br />
			<hr /><br />
			<button class="update_btn btn btn-primary" onclick="apiwrite()">작성</button>
		</form>
	</c:if>
	
	<c:if test="${msg == 'login_error'}">
		<p>로그인이 필요합니다.</p>
		<p><a href="/">홈으로</a></p>
	</c:if>
</div>	

	
</body>
</html>