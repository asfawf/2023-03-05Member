<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<title>회원가입</title>
</head>
<body>
<script>

	var msg2= "${alert}";
	if(msg2){
		alert(msg2);
	}

</script>
	<form action="signUp" method="post">
		<input type="text" name="id" placeholder="id">
		<input type="text" name="passwd" placeholder="pw">
		<input type="text" name="name" placeholder="name">
		<input type="text" name="email" placeholder="email">
		<button type="submit">제출</button>	
	</form>

</body>
</html>