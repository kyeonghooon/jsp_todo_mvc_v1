<%@page import="com.tenco.model.TodoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기 화면</title>
</head>
<body>
    <%
    	TodoDTO todo = (TodoDTO) request.getAttribute("todo");
    	if (todo != null) {
    %>
    <h1>상세 페이지</h1>

    <p> 제목 : <%=todo.getTitle() %> </p><br>
    <p> 설명 : <%=todo.getDescription() %> </p><br>
    <p> 마감일 : <%=todo.getDueDate() %> </p><br>
    <p> 완료여부 : <%=todo.isCompleted() ? "완료" : "미완료" %> </p><br>
    <hr><br>
	<form action="update" method="post">
        <input type="hidden" name="id" value="<%= todo.getId() %>">
        <label for="title">제목 : </label>
        <input type="text" name="title" id="title" value="<%=todo.getTitle() %>">
        <br><br>
        <label for="description">설명 : </label>
        <textarea rows="30" cols="50" name="description" id="description">
            <%=todo.getDescription() %>
        </textarea>
        <br><br>
        <label for="dueDate">마감일 : </label>
        <input type="date" name="dueDate" id="dueDate" value="<%=todo.getDueDate() %>">
        <br><br>
        <label for="completed">완료여부 : </label>
        <input type="checkbox" name="completed" id="completed" <%= todo.isCompleted() ? "checked" : "" %> >
        <br>
        <button type="submit">수정</button>
        <br>
    </form>
	
    <a href="todoForm"> 새 할일 추가</a>
    <br><br>
    <a href="list">목록으로 돌아가기</a>
    <br><br>
    <% } else { %>	
        <p> 잘못된 요청입니다 </p>
        <a href="list">목록으로 돌아가기</a>
    <% } %>
</body>
</html>