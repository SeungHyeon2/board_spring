<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<meta charset="UTF-8">
	<title>게시물 수정</title>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<script>
function apimodify(){
	var params = jQuery("#boardform").serialize();
	$.ajax({
		url : 'api/board/modify/{bno}',
		type : 'POST',
		async : true,
		data : params,
        contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
        success: function(data) {
			console.log("성공");
	    },
	    error: function(err) {
	        console.log("에러");
	    }
	});
}
</script>

<body>
	<div class="container">
	<h1>게시물 수정</h1>
	<div id="nav">
		<%@ include file="../include/nav.jsp" %>
	</div>
	<section>
		<form name = "boardform" id ="boardform" action="/board/modify" method ="post">
			<div class="form-group">
				<label class="col-sm-2 control-label">제목</label>
				<input type="text" name="title" value="${view.title}"/><br />
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">작성자</label>
				<input type="text" name="writer" value="${view.writer}"/><br />
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">내용</label>
				<textarea class="form-control" cols="50" rows="5" name="content">${view.content}</textarea><br />
				
				<input type="hidden" name="bno" value="${view.bno}">
			
				<button class="update_btn btn btn-primary" type="submit" onclick="apimodify()">완료</button>
			</div>
		</form>
	</section>


	<!-- 첨부파일 시작 -->
	<hr />
	<h3>첨부된 파일</h3>
	<form id = "fileform" action = "/board/fileModify" method ="post">
		<input type="hidden" id="file_no" name="file_no" value=""> 
		<input type="hidden" name="bno" value="${view.bno}">

		<c:forEach var="file" items="${file}">
			<c:choose>
				<c:when test="${file.del_gb == N}">
					<p> ${file.org_file_name} (${file.file_size}kb) </p>
				</c:when>
				<c:when test="${file.del_gb == Y}">
					<p> 이미 삭제된 게시물입니다. </p>
				</c:when>
				<c:otherwise>
					<p>error<p>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<button type="submit">삭제</button>
	</form>
<!-- 첨부파일 끝 -->

	<hr />
</div>
</body>
</html>