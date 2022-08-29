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
<form role="form" method="post" autocomplete="off">
	<div class = "form-group" >
	  <label for="userName" class="col-sm-2 control-label">닉네임</label>
	  <input type="text" id="userName" name="userName" value="${member.userName}" class="form-control"/>
	</div>
	<div class = "form-group">
		<label for="userPass" class="col-sm-2 control-label">새로운 패스워드</label>
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
</body>
</html>