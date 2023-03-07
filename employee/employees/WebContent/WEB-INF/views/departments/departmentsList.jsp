<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<title>departmentsList</title>
</head>
<body>
	<h1>부서 목록</h1>
	<div>
		<a href="${pageContext.request.contextPath }/"><button type="button" class="btn btn-outline-dark">홈으로</button></a>
	</div>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>부서 번호</th>
				<th>부서 이름</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="departments" items="${list }">
				<tr>
					<td>${departments.deptNo }</td>
					<td>${departments.deptName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>