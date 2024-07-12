<%@page import="com.tenco.model.TodoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기 화면</title>
</head>
<body>
	<c:choose>
		<c:when test="${todo != null }">
			<h1>상세 페이지</h1>

			<p>
				제목 :
				<c:out value="${todo.title}" />
			</p>
			<br>
			<p>
				설명 :
				<c:out value="${todo.description}" />
			</p>
			<br>
			<p>
				마감일 :
				<c:out value="${todo.dueDate}" />
			</p>
			<br>
			<p>
				완료여부 :
				<c:out value="${todo.completed ? '완료' : '미완료'}" />
			</p>
			<br>
			<hr>
			<br>
			<form action="update" method="post">
				<input type="hidden" name="id" value="${todo.id}"> <label for="title">제목 : </label> <input type="text" name="title" id="title" value="${todo.title}"> <br>
				<br> <label for="description">설명 : </label>
				<textarea rows="30" cols="50" name="description" id="description">
            <c:out value="${todo.description}"/>
        </textarea>
				<br> <br> <label for="dueDate">마감일 : </label> <input type="date" name="dueDate" id="dueDate" value="${todo.dueDate}"> <br> <br> <label
					for="completed"
				>완료여부 : </label> <input type="checkbox" name="completed" id="completed" ${todo.completed ? 'completed' : ''}> <br>
				<button type="submit">수정</button>
				<br>
			</form>

			<a href="todoForm"> 새 할일 추가</a>
			<br>
			<br>
			<a href="list">목록으로 돌아가기</a>
			<br>
			<br>
		</c:when>
		<c:otherwise>
			<p>잘못된 요청입니다</p>
			<a href="list">목록으로 돌아가기</a>
		</c:otherwise>
	</c:choose>
</body>
</html>