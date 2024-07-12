package com.tenco.controller;

import java.io.IOException;
import java.net.URLEncoder;

import com.tenco.model.UserDAO;
import com.tenco.model.UserDAOImpl;
import com.tenco.model.UserDTO;
import com.tenco.utils.Define;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 주소설계
// http://localhost:8080/mvc/user/xxx
@WebServlet("/user/*")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	public UserController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/signIn":
			// 로그인 페이지로 보내는 동작 처리
			request.getRequestDispatcher(Define.PATH_VIEWS + "signIn.jsp").forward(request, response);
			break;
		case "/signUp":
			// 회원 가입 페이지로 보내는 동작 처리
			request.getRequestDispatcher(Define.PATH_VIEWS + "signUp.jsp").forward(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	// 로그인 기능 요청 (자원의 요청 -- GET 방식 예외적인 처리_보안때문 )
	// POST 요청시 - 로그인 기능 구현, 회원 가입 기능 구현

	// http://localhost:8080/mvc/user/signIn
	// http://localhost:8080/mvc/user/signUp
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/signIn":
			signIn(request, response);
			break;
		case "/signUp":
			signUp(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	/**
	 * 로그인 처리 기능
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// URL, 인증검사, 유효성 검사, 서비스 로직, DAO --> 전달, 뷰를 호출
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null || password.trim().isEmpty()) {
			response.sendRedirect("signIn" + "?" + Define.MESSAGE_INVALID);
			return;
		}

		UserDTO user = userDAO.getUserByUsername(username);
		// 빠른 평가 -> null 이 뜨면 뒤는 연산하지 않기때문에 null 확인을 먼저함
		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute(Define.PRINCIPAL , user);
			response.sendRedirect(request.getContextPath() + "/todo/list");
		} else {
			response.sendRedirect("signIn" + "?" + Define.MESSAGE_INVALID);
		}
		// null <--- 회원가입 x

		// 비밀번호 == dto.getPassword();
	}

	/**
	 * 회원 가입 기능
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인증 검사 필요 없는 기능

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		// 방어적 코드 작성
		if (username == null || username.trim().isEmpty()) {
			request.setAttribute("message", "사용자 이름을 입력하시오");
			request.getRequestDispatcher(Define.PATH_VIEWS + "signUp.jsp").forward(request, response);
			return;
		}

		// 방어적 코드 작성 (password) - 생략
		// 방어적 코드 작성 (email) - 생략

		UserDTO userDTO = UserDTO.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
		int resultRowCount = userDAO.addUser(userDTO);
		if (resultRowCount == 1) {
			response.sendRedirect("signIn" + "?" + Define.MESSAGE_SUCCESS);
		} else {
			response.sendRedirect("signIn" + "?" + Define.MESSAGE_INVALID);
		}
	}

}
