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

<title>salariesStatistics</title>
</head>
<body>
	<h1>연봉 통계값(count, sum, avg, max, min, std)</h1>
	<div>
		<a href="${pageContext.request.contextPath }/"><button type="button" class="btn btn-outline-dark">홈으로</button></a>
	</div>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>count</th>
				<th>sum</th>
				<th>avg</th>
				<th>max</th>
				<th>min</th>
				<th>std</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${map.count }</td>
				<td>${map.sum }</td>
				<td>${map.avg }</td>
				<td>${map.max }</td>
				<td>${map.min }</td>
				<td>${map.std }</td>
		</tbody>
	</table>
</body>
</html>