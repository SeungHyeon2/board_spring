<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<meta charset="UTF-8">
	<title>게시물 목록</title>
 	<style>
		h1{
			text-align : center;
		}
		th{
			text-align : center;
		}
		tr, td{
			text-align : center;
		}
		.pagination{
			text-align : center;
		}
	</style>
</head>
<body>
<h1>게시판</h1>
<div class="container" id="nav">
	<%@ include file="../include/nav.jsp" %>
</div>
<div class="container">
		<select class="form-control" name="searchType">
		    <option value="title" <c:if test="${page.searchType eq 'title'}">selected</c:if>>제목</option>
	        <option value="content" <c:if test="${page.searchType eq 'content'}">selected</c:if>>내용</option>
		    <option value="title_content" <c:if test="${page.searchType eq 'title_content'}">selected</c:if>>제목+내용</option>
		    <option value="writer" <c:if test="${page.searchType eq 'writer'}">selected</c:if>>작성자</option>
		</select>
		
		<input type="text" name="keyword" value="${page.keyword}" class="form-control"/>
		<span class="input-group">
		<button type="button" id="searchBtn" class="btn btn-default">검색</button>
		</span>
	</div>
<br />
<table class="table table-hover">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>최초 작성일</th>
			<th>최근 수정일</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${list}" var="list">
			<c:choose>
				<c:when test="${list.delYN == 'N'}">
					<tr>
						<td>${list.bno}</td>
						<td>
							<a href="/board/view?bno=${list.bno}">${list.title}</a>
						</td>
						<td>${list.writer}</td>
						<td>${list.viewCnt}</td>
						<td>
							<fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd HH:mm:SS:sss" />				
						</td>
						<td>
							<fmt:formatDate value="${list.modDate}" pattern="yyyy-MM-dd HH:mm:SS:ssss" />
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6" align="left">
						삭제된 게시물입니다
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</tbody>

</table>

	<div class="pagination">

	<c:if test="${page.prev}">
		<span>[ <a href="/board/listPageSearch?num=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]</span>
	</c:if>
	
	<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
		<span>
		
			<c:if test="${select != num}">
				<a href="/board/listPageSearch?num=${num}${page.searchTypeKeyword}">${num}</a>
			</c:if> 			
			
			<c:if test="${select == num}">
				<b>${num}</b>
			</c:if>
	 			
		</span>
	</c:forEach>
	
	<c:if test="${page.next}">
		<span>[ <a href="/board/listPageSearch?num=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]</span>
	</c:if>
	
	
	<%-- <c:forEach begin="1" end="${pageNum}" var="num">
  		<span>
  			<a href="/board/listPage?num=${num}">${num}</a>
		</span>
	</c:forEach> --%>
	
	
	
	
</div>

<script>
	document.getElementById("searchBtn").onclick = function () {
		  
		let searchType = document.getElementsByName("searchType")[0].value;
		let keyword =  document.getElementsByName("keyword")[0].value;
		
		location.href = "/board/listPageSearch?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
	};
</script>

</body>
</html>