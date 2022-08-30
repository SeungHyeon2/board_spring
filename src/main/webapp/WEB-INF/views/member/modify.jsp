<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 정보 수정</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 
</head>
<body>
<div class="container">
<h3>회원정보 수정</h3>
<form role="form" method="post" autocomplete="off" onsubmit='return checkSubmitValue(this)'>
	<div class = "form-group" >
	  <label for="userName" class="col-sm-2 control-label">사용자명</label>
	  <input type="text" id="userName" name="userName" value="${member.userName}" class="form-control"/>
	</div>
	<div class = "form-group">
		<label for="userPass" class="col-sm-2 control-label">새 패스워드</label>
	  	<input type="password" id="userPass" name="userPass" class="form-control"/>
	</div>
	 
	<div class = "form-group">
		<button type="submit" class="update_btn btn btn-primary">회원정보 수정</button>  
	</div>
	<div class = "form-group">
		<a href="/">처음으로</a>
	</div>
</form>
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