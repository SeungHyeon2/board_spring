<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 가입</title> 
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 
</head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'>
</script>
<body>
<div class = "container">
	<h3>회원 가입</h3>
	<form role="form" method="post" autocomplete="off">
		<div class = "form-group" >
			<label for="userId" class="col-sm-2 control-label">아이디</label>
			<input type="text" id="userId" name="userId" class="form-control"/>
			</br>
			<button type="button" id="idCheck" class="update_btn btn btn-primary">아이디 확인</button>
		</div>
		<div class="result">
			<span class="msg">아이디를 확인해주십시오.</span>
		</div>
		<br />
		<div class = "form-group" >
			<label for="userPass" class="col-sm-2 control-label">패스워드</label>
			<input type="password" id="userPass" name="userPass" class="form-control"/>
		</div>
		<div class = "form-group" >
			<label for="userName" class="col-sm-2 control-label">닉네임</label>
			<input type="text" id="userName" name="userName" class="form-control" />
		</div>
		<div>
			<button type="submit" id="submit" disabled="disabled" class="update_btn btn btn-primary">가입</button>  
		</div>
		<br />
		<div>
			<a href="/">처음으로</a>
		</div> 
	</form>
</div>
<script> 
$("#idCheck").click(function(){
 
	var query = {userId : $("#userId").val()};
 
	$.ajax({
  		url : "/member/idCheck",
		type : "post",
		data : query,
		success : function(data) {
  
		if(data == 1) {
		    $(".result .msg").text("사용 불가");
		    $(".result .msg").attr("style", "color:#f00");
		    
		    $("#submit").attr("disabled", "disabled");
   		} else {
    		$(".result .msg").text("사용 가능");
    		$(".result .msg").attr("style", "color:#00f");
    		
    		$("#submit").removeAttr("disabled");
   		}
 	 	}
	});  // ajax 끝
});

$("#userId").keyup(function(){
	$(".result .msg").text("아이디를 확인해주십시오.");
	$(".result .msg").attr("style", "color:#000");
	 
	$("#submit").attr("disabled", "disabled");
	 
});
</script>
</body>
</html>