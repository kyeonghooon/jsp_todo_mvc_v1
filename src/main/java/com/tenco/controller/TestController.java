package com.tenco.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.tenco.model.TodoDAO;
import com.tenco.model.TodoDAOImpl;
import com.tenco.model.TodoDTO;
import com.tenco.model.UserDAO;
import com.tenco.model.UserDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test/*")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private TodoDAO todoDAO;
	
	public TestController() {
		super();
	}
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
		todoDAO = new TodoDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/byId":
			// http://localhost:8080/mvc/test/byId
			// userDAO.getUserById(1);
			System.out.println(todoDAO.getTodoById(1));
			break;
		case "/byUserId":
			// http://localhost:8080/mvc/test/byUsername
			// userDAO.getUserByUsername("홍길동");
			System.out.println(todoDAO.getTodosByUserId(1));
			break;
		case "/all":
			// http://localhost:8080/mvc/test/all
//			List<UserDTO> list = userDAO.getAllUsers();
//			if (list.isEmpty()) {
//				
//			}
			List<TodoDTO> list = todoDAO.getAllTodos();
			if (!list.isEmpty()) {
				for (TodoDTO todoDTO : list) {
					System.out.println(todoDTO);
				}
			}
			break;
		case "/delete":
			// http://localhost:8080/mvc/test/delete
			// userDAO.deleteUser(6);
			todoDAO.deleteTodo(3, 1);
			break;
		case "/update":
			// http://localhost:8080/mvc/test/update
//			UserDTO dto = UserDTO.builder().password("999").email("h@gmail.com").build();
//			int count = userDAO.updateUser(dto, 5);
//			System.out.println(count);
			TodoDTO todo = TodoDTO.builder().id(3).title("할일" + 1)
			.description("놀기" + 1)
			.dueDate(Date.valueOf(LocalDate.now()))
			.completed(false)
			.build();
			todoDAO.updateTodo(todo, 2);
			break;

		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
