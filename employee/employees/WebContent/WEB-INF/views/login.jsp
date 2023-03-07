<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body class="container">
	<h1>로그인 화면</h1>
	<form method="post" action="${pageContext.request.contextPath}/login">
		<div>
			first_name :
			<input type="text" name="firstName">
		</div>
		<div>
			last_name :
			<input type="text" name="lastName">
		</div>
		<div>
			emp_no :
			<input type="text" name="empNo">
		</div>
		<div>
			<button type="submit" class="btn btn-outline-danger">로그인</button>
		</div>
	</form>
</body>
</html>