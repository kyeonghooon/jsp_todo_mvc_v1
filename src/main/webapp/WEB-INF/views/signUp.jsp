<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
    <h2>회원가입</h2>
	<!-- 에러 메세지 출력 -->
	<%
		String message = (String) request.getParameter("message");
		if (message != null) {
	%>
		<p style="color: red;"> <%=message %>	</p>
	<% } %>
	<form action="/mvc/user/signUp" method="post">
		<label for="username">사용자 이름 : </label>
		<input type="text" name="username" id="username" value="야스오1">
		<label for="password">비밀번호 : </label>
		<input type="password" name="password" id="password" value="1234">
		<label for="email">이메일 : </label>
		<input type="text" name="email" id="email" value="abc@nate.com">
		<button type="submit">회원가입</button>
	</form>
</body>
</html>