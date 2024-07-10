<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/mvc/css/styles.css">
</head>
<body>
    <h2>회원가입</h2>
	<!-- 메세지 출력 -->
	<%
		String message = (String) request.getParameter("message");
		if (message != null) {
			if (message.equalsIgnoreCase("invalid")){
	%>
		<p style="color: red;"> 로그인 오류</p>
		<% } else if (message.equalsIgnoreCase("success")){ %>
		<p style="color: blue;"> <%=message %>	</p>
		<% } %>
	<% } %>
	<form action="/mvc/user/signIn" method="post">
		<label for="username">사용자 이름 : </label>
		<input type="text" name="username" id="username" value="야스오1">
		<label for="password">비밀번호 : </label>
		<input type="password" name="password" id="password" value="1234">
		<button type="submit">로그인</button>
	</form>
</body>
</html>