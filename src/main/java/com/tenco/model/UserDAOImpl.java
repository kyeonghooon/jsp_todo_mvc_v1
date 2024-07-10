package com.tenco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tenco.utils.DBUtil;

public class UserDAOImpl implements UserDAO {

	private DataSource dataSource;

	public UserDAOImpl() {
		try {
			dataSource = DBUtil.getDataSource();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addUser(UserDTO userDTO) {
		int resultRowCount = 0;
		String sql = " INSERT INTO users (username, password, email) VALUES (?,?,?)";
		try (Connection conn = dataSource.getConnection()) {

			// 트랜잭션 시작
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, userDTO.getUsername());
				pstmt.setString(2, userDTO.getPassword());
				pstmt.setString(3, userDTO.getEmail());
				resultRowCount = pstmt.executeUpdate();

				// 트랜잭션 커밋
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} // end of PreparedStatement

		} catch (Exception e) {
			e.printStackTrace();
		} // end of Connection
		return resultRowCount;
	}

	/**
	 * SELECT 에서는 일단 트랜잭션 처리를 하지 말자
	 * 하지만 팬텀리드 현상 (정합성을 위해서 처리하는것도 옳은 방법이다.)
	 */
	@Override
	public UserDTO getUserById(int id) {
		String sql = " SELECT * FROM users WHERE id = ? ";
		UserDTO userDTO = null;
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setUsername(rs.getString("username"));
					userDTO.setPassword(rs.getString("password"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setCreatedAt(rs.getString("created_at"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} // end of PreparedStatement
			// TODO 삭제 예정
			System.out.println("UserDTO By Id : " + userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		} // end of Connection
		return userDTO;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		String sql = " SELECT * FROM users WHERE username = ? ";
		UserDTO userDTO = null;
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, username);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setUsername(rs.getString("username"));
					userDTO.setPassword(rs.getString("password"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setCreatedAt(rs.getString("created_at"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} // end of PreparedStatement
			// TODO 삭제 예정
			System.out.println("UserDTO By Username : " + userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		} // end of Connection
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		String sql = " SELECT * FROM users ";
		// 자료구조를 사용할 때 일단 생성 시키자
		List<UserDTO> userList = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					UserDTO userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setUsername(rs.getString("username"));
					userDTO.setPassword(rs.getString("password"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setCreatedAt(rs.getString("created_at"));
					userList.add(userDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} // end of PreparedStatement
			// TODO 삭제 예정
			System.out.println("UserDTO All : " + userList);
		} catch (Exception e) {
			e.printStackTrace();
		} // end of Connection
		return userList;
	}

	@Override
	public int updateUser(UserDTO user, int principalId) {
		String sql = " update users set password = ? , email = ? where id = ? ";
		int resultCount = 0;
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getEmail());
				pstmt.setInt(3, principalId);
				resultCount = pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCount;
	}

	@Override
	public int deleteUser(int id) {
		String sql = " DELETE FROM users WHERE id = ? ";
		int resultCount = 0;
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, id);
				resultCount = pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCount;
	}

}
