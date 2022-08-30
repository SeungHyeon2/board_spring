<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<!-- Title -->
	<title>Home</title>
	
	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
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
	<div class="container">
	<h3>게시판</h3>
	
	<p>
	<a href="/board/listPageSearch?num=1">게시물 목록</a><br />
	<a href="/board/write">게시물 작성</a><br />
	
	<a href="#" onclick="apitest()">api 호출</a><br />
	
	</p>
	<br />
	<c:if test="${member == null}">
		<div class="container">
			<h3>로그인</h3>
			<form role="form" method="post" autocomplete="off" action="/member/login" onsubmit='return checkSubmitValue(this)'>
				<div class = "form-group">
					<label for="userId" class="col-sm-2 control-label">아이디</label>
					<input type="text" id="userId" name="userId" class="form-control" />
				</div>
				<div class = "form-group">
					<label for="userPass" class="col-sm-2 control-label">비밀번호</label>
					<input type="password" id="userPass" name="userPass" class="form-control" />
				</div>
				<div><button type="submit" id="btn" class="update_btn btn btn-primary">로그인</button></div>
		 		<div><a href="/member/register">회원가입</a></div>
			</form>
			</div>
	</c:if>
	
	<c:if test="${msg == false}">
		<p style="color:#f00;">로그인에 실패했습니다. 아이디 또는 패스워드를 다시 입력해주십시오.</p>
	</c:if>
	
	<c:if test="${member != null}">
		<p>${member.userName}님 환영합니다.</p>
		<br />
		
		<a href="member/modify">회원정보 수정</a>,
		<a href="member/withdrawal">회원탈퇴</a>,
		<a href="member/logout">로그아웃</a>
	</c:if>
	</div>
	
<script>
function checkSubmitValue(frm) {
	var e = frm.elements;
	for ( var i = 0; i < e.length; i++ ) {
		if ( e[i].tagName == 'INPUT'  && e[i].value == '' ) {
			alert('아이디 혹은 비밀번호를 확인해주세요');
			return false;
		}
	}
	return true;
}
</script>
</body>
</html>
