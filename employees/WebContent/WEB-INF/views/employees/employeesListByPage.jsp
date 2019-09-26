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

<title>employees List By Page</title>
</head>
<body>
	<h1>회원 목록(10명씩 페이징)</h1>
	<div>
		<a href="${pageContext.request.contextPath }/"><button type="button" class="btn btn-outline-dark">홈으로</button></a>
	</div>
	<!-- 테이블 -->
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>사원 번호</th>
				<th>사원 생일</th>
				<th>사원 이름</th>
				<th>사원 성</th>
				<th>사원 성별</th>
				<th>입사 날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employees" items="${list }">
				<tr>
					<td>${employees.empNo }</td>
					<td>${employees.birthDate }</td>
					<td>${employees.firstName }</td>
					<td>${employees.lastName }</td>
					<td>${employees.gender }</td>
					<td>${employees.hireDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 이전 다음 -->
	<div>
		<c:if test="${currentPage > 1 }"> <!-- if(currentPage > 1) -->
			<a href="${pageContext.request.contextPath }/employees/getEmployeesListByPage?currentPage=${currentPage-1}"><button type="submit" class="btn btn-secondary">이전</button></a>
		</c:if>
		<c:if test="${currentPage < lastPage }">
			<a href="${pageContext.request.contextPath }/employees/getEmployeesListByPage?currentPage=${currentPage+1}"><button type="submit" class="btn btn-secondary">다음</button></a>
		</c:if>
	</div>
</body>
</html>