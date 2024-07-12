<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="com.tenco.model.TodoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할 일 목록</title>
<link rel="stylesheet" href="../css/styles.css">
</head>
<body>
	<c:choose>
		<c:when test="${list != null && not empty list }">
	<h2>할 일 목록</h2>
	<a href="todoForm"> 새 할일 추가</a>
	<br>
	<br>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>설명</th>
			<th>마감일</th>
			<th>완료 여부</th>
			<th>(액션 - 버튼)</th>
		</tr>
		<c:forEach var="todo" items="${list}">
		<tr>
			<td><c:out value="${todo.title}"/></td>
			<td><c:out value="${todo.description}"/></td>
			<td><c:out value="${todo.dueDate}"/></td>
			<td><c:out value="${todo.completed ? '완료' : '미완료'}"/></td>
			<td>
				<a href="detail?id=${todo.id}">상세보기</a>
				<form action="delete" method="get">
					<input type="hidden" name="id" value="${todo.id}">
					<button type="submit">삭제</button>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
		</c:when>
		<c:otherwise>
	<p>등록된 할 일이 없습니다.</p>
		</c:otherwise>
	</c:choose>
</body>
</html>