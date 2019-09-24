<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<title>index.jsp</title>
</head>
<body>

	<!-- 
		1. 디렉티브 	- %@ 	- x
		2. 선언 		- %! 	- 사용x
		3. 스크립트릿 	- % 	- jstl(사용자 생성한 태그)
		4. 표현식 		- %= 	- el
	 -->
	<h1>Index</h1>
	<h2>테이블 정보</h2>
	<div>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th>테이블 이름</th>
					<th>전체 행의수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>departments</td>
					<td>${departmentsRowCount}</td>
				</tr>
				<tr>
					<td>employees</td>
					<td>${employeesRowCount}</td>
				</tr>
				<tr>
					<td>dept_manager</td>
					<td>${deptManagerRowCount}</td>
				</tr>
				<tr>
					<td>dept_emp</td>
					<td>${deptEmpRowCount}</td>
				</tr>
				<tr>
					<td>titles</td>
					<td>${titlesRowCount}</td>
				</tr>
				<tr>
					<td>salaries</td>
					<td>${salariesRowCount}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>
	
	<!-- WEB APP 네비게이션 -->
	<div>
		<ul>
			<li>
				<a href="${pageContext.request.contextPath }/departments/getDepartmentsList"><button type="button" class="btn btn-outline-success">부서 목록</button></a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath }/employees/getEmployeesList"><button type="button" class="btn btn-outline-success">사원 목록(limit 10)</button></a>
				<a href="${pageContext.request.contextPath }/employees/getEmployeesListOrderBy?order=asc"><button type="button" class="btn btn-outline-success">오름차순(limit 50)</button></a> 
				<a href="${pageContext.request.contextPath }/employees/getEmployeesListOrderBy?order=desc"><button type="button" class="btn btn-outline-success">내림차순(limit 50)</button></a>
			</li>
		</ul>
	</div>
	
	<div>
		<br>
		표현식 - employees table row count : <%=request.getAttribute("employeesRowCount") %>
		<br>
		EL - employees table row count : ${employeesRowCount }
	</div>
</body>
</html>