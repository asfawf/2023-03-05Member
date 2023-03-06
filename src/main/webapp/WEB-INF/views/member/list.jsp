<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>

<style>
	.f-3{
		display: flex;
		height: 50px;
		width: 300px;
	}
	.f-3 div{
		
		border: 1px black solid;
		width: 33% ;
	}

</style>
</head>
<body>
	<div class="f-3">
		<div>아이디</div>
		<div>비밀번호</div>
		<div>이름</div>
		<div>이메일</div>
	</div>
	<c:forEach items="${ membervolist}" var="member">
		<div class="f-3">
			<a href="<%=request.getContextPath()%>/member/info?id=${member.id}">
				<div>${ member.id}</div>
				<div>${ member.name}</div>
				<div>${ member.email}</div>
			</a>
		</div>
	</c:forEach>

</body>
</html>