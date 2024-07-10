package com.tenco.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.tenco.model.TodoDAO;
import com.tenco.model.TodoDAOImpl;
import com.tenco.model.TodoDTO;
import com.tenco.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/*")
public class TodoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TodoDAO todoDAO;
	
	public TodoController() {
		todoDAO = new TodoDAOImpl();
	}

	// http://localhost:8080/mvc/todo/todoForm (권장 x)
	// http://localhost:8080/mvc/todo/list
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/todoForm":
			todoFormPage(request, response);
			break;
		case "/list":
			todoListPage(request, response);
			break;

		default:
			break;
		}
	}
	
	private void todoListPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 로그인한 사용자만 접근을 허용하도록 설계
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO) session.getAttribute("principal");
		// 인증 검사
		if (principal == null) {
			// 로그인을 안한 상태
			response.sendRedirect(request.getContextPath() + "/user/signIn?message=invalid");
			return;
		}
		
		// todoList.jsp 페이지로 내부에서 이동 처리
		request.getRequestDispatcher("/WEB-INF/views/todoList.jsp").forward(request, response);
	}

	private void todoFormPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 로그인한 사용자만 접근을 허용하도록 설계
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO) session.getAttribute("principal");

		// 인증 검사
		if (principal == null) {
			// 로그인을 안한 상태
			response.sendRedirect("/mvc/user/signIn?message=invalid");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/todoForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO  수정 예정
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO) session.getAttribute("principal");
		// principal -- null 이라면 ---> 로그인 페이지 이동 처리
		System.out.println("Request Parameters:");
	    request.getParameterMap().forEach((key, value) -> {
	        System.out.println(key + ": " + String.join(", ", value));
	    });
		todoDAO.addTodo(TodoDTO.builder().title(request.getParameter("title"))
				.description(request.getParameter("description"))
				.dueDate(Date.valueOf(LocalDate.now()))
				.build(), principal.getId());
	}

}
