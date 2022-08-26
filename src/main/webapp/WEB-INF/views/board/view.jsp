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
	<title>게시물 조회</title>
	<style>
		h3{
			text-align : center;
		}
		th{
			text-align : center;
		}
		tr, td{
			text-align : center;
		}
		.in{
			text-indent : 50px;
		}
	</style>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<script type="text/javascript">
	function fn_fileDown(fileNo){
		var formObj = $("form[name='readForm']");
		$("#file_no").attr("value", fileNo);
		formObj.attr("action", "/board/fileDown");
		formObj.submit();
	}
</script>

<body>

<div id="nav">
	<%@ include file="../include/nav.jsp" %>
</div>
<div id="container">
<!--  <form method="post"> -->
<hr />
<h3>게시물</h3>
<table class="table table-hover">
	<tr>
		<th>제목</th>
		<th>작성자</th>
		<th>내용</th>
	</tr>
	<tr>
		<td>${view.title }</td>
		<td>${view.writer }</td>
		<td>${view.content }</td>
	</tr>

</table>
<br />
<div>
<a href="/board/modify?bno=${view.bno}">게시물 수정</a>, 
<a href="/board/delete?bno=${view.bno}">게시물 삭제</a>
</div>
<br />
</div>
<!-- 첨부파일 시작 -->
<hr />
<h4>첨부된 파일</h4>
<form name="readForm" role="form" method="post">
<input type="hidden" id="file_no" name="file_no" value=""> 
	
<div>
	<c:forEach var="file" items="${file}">
		<a href="#" onclick="fn_fileDown('${file.file_no}'); return false;">${file.org_file_name}</a>(${file.file_size}kb)<br>
	</c:forEach>
</div>
</form>
<!-- 첨부파일 끝 -->
<!-- 댓글 시작 -->
<hr />
<h4>댓글</h4>
<!-- 원 댓글 작성 -->
<section id="container">
	<form method="post" action="/reply/write">
		<div class="form-group">
			<label class="col-sm-2 control-label">댓글 작성자</label> <br />
			<input class="form-control" type="text" name="writer">
		</div>
		<div class="form-group">
			<textarea rows="5" cols="50" name="content" class="form-control"></textarea>
		</div>
		<div class="form-group">
			<input type="hidden" name="bno" value="${view.bno}">
			<button type="submit" class="update_btn btn btn-primary">댓글 작성</button>
		</div>
	</form>
</section>
<!-- 원댓글 확인 및 대댓글 작성 파트 -->
<!-- 원댓글 확인 파트 -->
<div class="container">
	<%-- level이 0이면 들여쓰기 안함 --%>
	<c:forEach items="${reply}" var="reply">
		<c:choose>
			<c:when test="${reply.level == 0}">
				<div>
					<div style="height: auto; width:100%">
						<p>${reply.writer} / <fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd" /></p>
					 	<p>REF : ${reply.ref}, STEP : ${reply.step}, LEVEL : ${reply.level}</p>
					 	<p>${reply.content}</p>
					</div>
					
					<%-- 대댓글 작성 파트 --%>
					<div>
						<details>
					 	<summary>댓글 작성</summary>
						 	<form class="form-horizontal" action="/reply/reWrite" method="post"> 
									<input type="hidden" name="bno" value="${view.bno}">
									<input type="hidden" name="ref" value="${reply.ref}">
									<input type="hidden" name="ref" value="${reply.step}">
									<input type="hidden" name="ref" value="${reply.level}">
						 		<div class="form-group">
						 			<label>댓글 작성자</label><br/>
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<input type="text" name="writer">
						 		</div>
						 		<div class="form-group">
						 			<textarea rows="5" cols="50" name="content"></textarea>
						 		</div>
						 		<button type="submit">댓글 작성</button>
						 	</form>
					 	</details>
					</div>
				</div>
			</c:when>
			<%-- level이 1이면 들여쓰기 함 --%>
			<c:otherwise>
				<div class="in">
					<div style="height: auto; width:100%">
						<p>${reply.writer} / <fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd" /></p>
					 	<p>REF : ${reply.ref}, STEP : ${reply.step}, LEVEL : ${reply.level}</p>
					 	<p>${reply.content}</p>
					</div>
					
					<%-- 대댓글 작성 파트 --%>
					<div>
						<%-- <details>
					 	<summary>댓글 작성</summary>
						 	<form class="form-horizontal" action="/reply/reWrite" method="post"> 
									<input type="hidden" name="bno" value="${view.bno}">
									<input type="hidden" name="ref" value="${reply.ref}">
									<input type="hidden" name="ref" value="${reply.step}">
									<input type="hidden" name="ref" value="${reply.level}">
						 		<div class="form-group">
						 			<label>댓글 작성자</label><br/>
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<input type="text" name="writer">
						 		</div>
						 		<div class="form-group">
						 			<textarea rows="5" cols="50" name="content"></textarea>
						 		</div>
						 		<button type="submit">댓글 작성</button>
						 	</form>
					 	</details> --%>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		
		<br />
	</c:forEach>
</div>

		

<!-- <ul> -->
<%-- 	 <c:forEach items="${reply}" var="reply">
	     <div style="border: 1px solid gray; width: 800px; padding: 5px; margin-top: 5px;
	           margin-left: <c:out value="${20*reply.depth}"/>px; display: inline-block">
	             <c:out value="${reply.writer}"/> <c:out value="${reply.regDate}"/>
	         <a href="#" onclick="fn_replyDelete('<c:out value="${reply.rno}"/>')">삭제</a>
	         <a href="#" onclick="fn_replyUpdate('<c:out value="${reply.rno}"/>')">수정</a>
	         <a href="#" onclick="fn_replyReply('<c:out value="${reply.rno}"/>')">댓글</a>
	         <br/>
	         <div id="reply<c:out value="${reply.rno}"/>"><c:out value="${reply.content}"/></div>
	     </div><br/>
	 
	 <li>
	 	<div>
	 		<p>${reply.writer} / <fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd" /></p>
	 		<p>${reply.content}</p>
	 		<p><button>댓글 작성</button></p>
	 		<hr>
	 	</div>
	 </li>
	 </c:forEach>
<!-- </ul>  -->
<!-- </form>  -->
 --%>
<%-- <div id="replyDialog" style="width: 99%; display:none">
	<form name="form3" action="replywrite" method="post">
    	<input type="hidden" name="bno" value="<c:out value="${boardInfo.bno}"/>">
    	<input type="hidden" name="rno">
		<input type="hidden" name="parent">
    	작성자: <input type="text" name="writer" size="20" maxlength="20"> <br/>
    	<textarea name="content" rows="3" cols="60" maxlength="500"></textarea>
    	<a href="#" onclick="fn_replyReplySave()">저장</a>
    	<a href="#" onclick="fn_replyReplyCancel()">취소</a>
	</form>
</div> --%>
<!-- 댓글 끝 -->
</body>
</html>