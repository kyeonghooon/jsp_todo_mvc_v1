<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" type="text/css" href="../css/styles.css">
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
    .toggle-password {
        margin-top: -10px;
        margin-bottom: 20px;
        cursor: pointer;
        color: #007bff;
        text-decoration: underline;
    }
</style>
<script>
    function togglePassword() {
        var passwordField = document.getElementById("password");
        var toggleButton = document.getElementById("togglePasswordButton");
        if (passwordField.type === "password") {
            passwordField.type = "text";
            toggleButton.innerText = "비밀번호 숨기기";
        } else {
            passwordField.type = "password";
            toggleButton.innerText = "비밀번호 보기";
        }
    }
</script>
</head>
<body>
    <div class="container">
        <h2>회원가입</h2>
        <!-- 에러 메세지 출력 -->
        <%
            String message = (String) request.getParameter("message");
            if (message != null) {
        %>
            <p style="color: red;"><%= message %></p>
        <% } %>
        <form action="/mvc/user/signUp" method="post">
            <label for="username">사용자 이름</label>
            <input type="text" name="username" id="username" value="야스오1">
            <label for="password">비밀번호</label>
            <input type="password" name="password" id="password" value="1234">
            <span id="togglePasswordButton" class="toggle-password" onclick="togglePassword()">비밀번호 보기</span>
            <label for="email">이메일</label>
            <input type="text" name="email" id="email" value="abc@nate.com">
            <button type="submit">회원가입</button>
        </form>
    </div>
</body>
</html>
