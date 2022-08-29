<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 탈퇴</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<body>
<div class="container">
<h3>회원 탈퇴</h3>

<form role="form" method="post" autocomplete="off">
	 <div class = "form-group">
		 <label for="userId" class="col-sm-2 control-label">아이디</label>
		 <input type="text" id="userId" name="userId" value="${member.userId}" class="form-control"/>
	 </div>  
	 <div class = "form-group">
		 <label for="userPass" class="col-sm-2 control-label">패스워드</label>
		 <input type="password" id="userPass" name="userPass" class="form-control"/>
	 </div>
	 <div class = "form-group">
	 	<button type="submit" class="update_btn btn btn-primary">회원 탈퇴</button>  
	 </div>
	 <div>
	 	<a href="/">처음으로</a>
	 </div>
</form>

<c:if test="${msg == false}">
	<p>입력한 비밀번호가 잘못되었습니다.</p>
</c:if>
</div>
</body>
</html>