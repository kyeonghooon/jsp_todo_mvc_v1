<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="com.tenco.model.TodoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할 일 목록</title>
<link rel="stylesheet" href="../css/styles.css">
</head>
<body>
	<%
		// 샘플 데이터
		List<TodoDTO> todoList = new ArrayList<TodoDTO>();
		for (int i = 1; i <= 3; i++){
		TodoDTO todo = TodoDTO.builder().id(i).title("할일" + i)
				.description("놀기" + i)
				.dueDate(Date.valueOf(LocalDate.now()))
				.completed(false)
				.userId(i)
				.build();
		todoList.add(todo);
		}
		if (todoList != null && !todoList.isEmpty()) {
	%>
    <h2>할 일 목록</h2>
    <a href="todoForm.jsp"> 새 할일 추가</a>
    <br><br>
    <table border="1">
        <tr>
            <th>제목</th>
            <th>설명</th>
            <th>마감일</th>
            <th>완료 여부</th>
            <th>(액션 - 버튼)</th>
        </tr>
        <% for (TodoDTO todo : todoList) { %>
        <tr>
            <td><%=todo.getTitle() %></td>
            <td><%=todo.getDescription() %></td>
            <td><%=todo.getDueDate() %></td>
            <td><%=todo.isCompleted() ? "완료" : "미완료" %></td>
            <td>
                <a href="detail?id=<%= todo.getId() %>">상세보기</a>
                <form action="delete">
                    <input type="hidden" name="id" value="<%= todo.getId() %>">
                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <% } else { %>
    <p>등록된 할 일이 없습니다.</p>
    <% } %>
</body>
</html>