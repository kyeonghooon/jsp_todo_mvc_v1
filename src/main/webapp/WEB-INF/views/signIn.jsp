<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/mvc/css/styles.css">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	background-color: white;
	border: 1px solid #e0e0e0;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 300px;
	text-align: center;
}

h2 {
	margin-bottom: 20px;
	color: #333;
}

.message {
	margin-bottom: 15px;
	font-size: 14px;
}

form {
	display: flex;
	flex-direction: column;
	align-items: center;
}

label {
	margin-bottom: 10px;
	color: #555;
	width: 100%;
	text-align: left;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #1ec800;
	border: none;
	border-radius: 5px;
	color: white;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background-color: #17a500;
}
</style>
</head>
<body>
	<div class="container">
		<h2>로그인</h2>
		<!-- 메세지 출력 -->
		<c:if test="${param.message != null}">
			<c:choose>
				<c:when test="${param.message == 'invalid'}">
					<p class="message" style="color: red;">로그인 오류</p>
				</c:when>
				<c:when test="${param.message == 'success'}">
					<p class="message" style="color: blue;">
						<c:out value="${param.message}" />
					</p>
				</c:when>
			</c:choose>
		</c:if>

		<form action="/mvc/user/signIn" method="post">
			<label for="username">사용자 이름</label> <input type="text" name="username" id="username" value="야스오1"> <label for="password">비밀번호</label> <input type="password"
				name="password" id="password" value="1234"
			>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>
