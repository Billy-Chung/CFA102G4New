package com.petClass.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PetClassDAO implements PetClasss_interface {

	private static final String INSERT_SQL = "insert into PET_CLASS (PET_CLASS_NAME,PET_CLASS_STATE) values(?,?)";
	private static final String UPDATE_SQL = "update PET_CLASS set PET_CLASS_NAME = ?,PET_CLASS_STATE = ? where PET_CLASS_NO = ?";
	private static final String FIND_BY_CLASS_NO = "SELECT * FROM PET_CLASS WHERE PET_CLASS_NO = ?";
	private static final String SELECT_ALL = "SELECT * FROM PET_CLASS";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PetClassVO insert(PetClassVO petClass) {
		Connection con = null;
		try {
			con = ds.getConnection();
			String[] cols = { "PET_CLASS_NO" };
			PreparedStatement pstmt = createInsertPreparedStatement(con, petClass, INSERT_SQL, cols);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int key = rs.getInt(1);
				petClass.setPet_class_no(key);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return petClass;
	}

	@Override
	public void update(PetClassVO petClass) {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = createUpdatePreparedStatement(con, petClass, UPDATE_SQL);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public PetClassVO findBypetClassNo(Integer pet_class_no) {
		Connection con = null;
		PetClassVO petClass = new PetClassVO();

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_CLASS_NO);
			pstmt.setInt(1, pet_class_no);
			ResultSet rs = pstmt.executeQuery();
			petClass = selectOneByPetClassNo(rs);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return petClass;
	}

	@Override
	public List<PetClassVO> getAllpetClass() {
		Connection con = null;
		List<PetClassVO> petClassList = new ArrayList<>();

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			petClassList = selectAllpetClass(petClassList, rs);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return petClassList;
	}

	private PreparedStatement createInsertPreparedStatement(Connection con, PetClassVO petClass, String SQL,
			String[] cols) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL, cols);
		pstmt.setString(1, petClass.getPet_class_name());
		pstmt.setString(2, petClass.getPet_class_state());
		return pstmt;
	}

	private PreparedStatement createUpdatePreparedStatement(Connection con, PetClassVO petClass, String SQL)
			throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, petClass.getPet_class_name());
		pstmt.setString(2, petClass.getPet_class_state());
		pstmt.setInt(3, petClass.getPet_class_no());
		return pstmt;
	}

	private PetClassVO selectOneByPetClassNo(ResultSet rs) {
		PetClassVO petClass = new PetClassVO();
		try {
			while (rs.next()) {
				petClass.setPet_class_no(rs.getInt("PET_CLASS_NO"));
				petClass.setPet_class_name(rs.getString("PET_CLASS_NAME"));
				petClass.setPet_class_state(rs.getString("PET_CLASS_STATE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return petClass;
	}

	private List<PetClassVO> selectAllpetClass(List<PetClassVO> petClassList, ResultSet rs) {
		try {
			while (rs.next()) {
				PetClassVO petClass = new PetClassVO();
				petClass.setPet_class_no(rs.getInt("PET_CLASS_NO"));
				petClass.setPet_class_name(rs.getString("PET_CLASS_NAME"));
				petClass.setPet_class_state(rs.getString("PET_CLASS_STATE"));
				petClassList.add(petClass);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return petClassList;

	}

}
