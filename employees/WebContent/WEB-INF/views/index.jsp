<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	
	<!-- WEB APP 네비게이션 -->
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath }/departments/getDepartmentsList">부서 목록</a></li>
			<li><a href="<%=request.getContextPath() %>/employees/getEmployeesList">사원 목록</a></li>
		</ul>
	</div>
	
	<div>
		표현식 - employees table row count : <%=request.getAttribute("employeesRowCount") %>
		<br>
		EL - employees table row count : ${employeesRowCount }
	</div>
</body>
</html>