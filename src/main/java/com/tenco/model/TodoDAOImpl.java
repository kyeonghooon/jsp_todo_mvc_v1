package com.tenco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tenco.utils.DBUtil;

public class TodoDAOImpl implements TodoDAO {

	private DataSource dataSource;

	public TodoDAOImpl() {
		try {
			dataSource = DBUtil.getDataSource();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addTodo(TodoDTO dto, int principalId) {
		String sql = " INSERT INTO todos(user_id, title, description, due_date) VALUES (?,?,?,?) ";
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, principalId);
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getDescription());
				pstmt.setDate(4, dto.getDueDate());
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public TodoDTO getTodoById(int id) {
		String sql = " SELECT * FROM todos WHERE id = ? ";
		TodoDTO todoDTO = null;
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					todoDTO = TodoDTO.builder()
							.id(rs.getInt("id"))
							.userId(rs.getInt("user_id"))
							.title(rs.getString("title"))
							.description(rs.getString("description"))
							.dueDate(rs.getDate("due_date"))
							.completed(rs.getBoolean("completed"))
							.build();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} // end of PreparedStatement
		} catch (Exception e) {
			e.printStackTrace();
		} // end of Connection
		return todoDTO;
	}

	@Override
	public List<TodoDTO> getTodosByUserId(int userId) {
		String sql = " SELECT t.* FROM todos t JOIN users u ON t.user_id = u.id WHERE u.id = ? ";
		List<TodoDTO> todoList = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, userId);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					TodoDTO todoDTO = TodoDTO.builder()
							.id(rs.getInt("id"))
							.userId(rs.getInt("user_id"))
							.title(rs.getString("title"))
							.description(rs.getString("description"))
							.dueDate(rs.getDate("due_date"))
							.completed(rs.getBoolean("completed"))
							.build();
					todoList.add(todoDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} // end of PreparedStatement
		} catch (Exception e) {
			e.printStackTrace();
		} // end of Connection
		return todoList;
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		String sql = " SELECT * FROM todos ";
		List<TodoDTO> todoList = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					TodoDTO todoDTO = TodoDTO.builder()
							.id(rs.getInt("id"))
							.userId(rs.getInt("user_id"))
							.title(rs.getString("title"))
							.description(rs.getString("description"))
							.dueDate(rs.getDate("due_date"))
							.completed(rs.getBoolean("completed"))
							.build();
					todoList.add(todoDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} // end of PreparedStatement
		} catch (Exception e) {
			e.printStackTrace();
		} // end of Connection
		return todoList;
	}

	@Override
	public void updateTodo(TodoDTO dto, int principalId) {
		String sql = " UPDATE todos set title = ? , description = ? , due_date = ? , completed = ? where id = ? AND user_id = ? ";
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getDescription());
				pstmt.setDate(3, dto.getDueDate());
				pstmt.setBoolean(4, dto.isCompleted());
				pstmt.setInt(5, dto.getId());
				pstmt.setInt(6, principalId);
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTodo(int id, int principalId) {
		String sql = " DELETE FROM todos WHERE id = ? AND user_id = ? ";
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, id);
				pstmt.setInt(2, principalId);
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
