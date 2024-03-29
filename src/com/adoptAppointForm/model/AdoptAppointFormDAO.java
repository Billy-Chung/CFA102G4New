package com.adoptAppointForm.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdoptAppointFormDAO implements AdoptAppointForm_interface {

	private static final String INSERT_SQL = "insert into ADOPT_APPOINT_FORM (ADOPT_MEB_NO,APPOINT_DATE,FINIFH_APPOINT_NUM,APPOINT_LIMIT) values(?,?,?,?)";
	private static final String UPDATE_SQL = "update ADOPT_APPOINT_FORM set FINIFH_APPOINT_NUM = ?, APPOINT_LIMIT = ? where APPOINT_FORM_NO = ?";
	private static final String FIND_BY_PK = "select * from ADOPT_APPOINT_FORM where APPOINT_FORM_NO = ?";
	private static final String FIND_BY_ADOPTMEBNO = "select * from ADOPT_APPOINT_FORM where ADOPT_MEB_NO = ?";
	private static final String FIND_BY_DATE = "select * from ADOPT_APPOINT_FORM where APPOINT_DATE = ?";
	private static final String creatDate = "WITH RECURSIVE dates (APPOINT_DATE) AS (SELECT CURDATE() UNION ALL SELECT APPOINT_DATE + INTERVAL 1 DAY FROM dates WHERE APPOINT_DATE + INTERVAL 1 DAY <= ADDDATE(CURDATE(), INTERVAL 15 DAY)) SELECT b.ADOPT_MEB_NO, d.APPOINT_DATE, repeat('0',24) FINIFH_APPOINT_NUM,  IF(substr(b.ADOPT_MEB_HOLIDAY, weekday(d.APPOINT_DATE)+1,1) ='1', b.ADOPT_MEB_LIMIT, repeat('0',24))  APPOINT_LIMIT FROM dates d JOIN ADOPT_MEMBER b LEFT JOIN ADOPT_APPOINT_FORM r on (d.APPOINT_DATE = r.APPOINT_DATE AND b.ADOPT_MEB_NO = r.ADOPT_MEB_NO) WHERE r.APPOINT_DATE IS NULL";

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
	public AdoptAppointFormVO insert(AdoptAppointFormVO adoptAppointForm) {
		Connection con = null;
		try {
			con = ds.getConnection();
			String[] cols = { "adopt_meb_no" };
			PreparedStatement pstmt = createInsertPreparedStatement(con, adoptAppointForm, INSERT_SQL, cols);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int key = rs.getInt(1);
				adoptAppointForm.setAppoint_form_no(key);
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
		return adoptAppointForm;
	}

	@Override
	public void update(AdoptAppointFormVO adoptAppointForm, Connection con) {
		try {
			PreparedStatement pstmt = createUpdatePreparedStatement(con, adoptAppointForm, UPDATE_SQL);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured.111 " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}
	
	

	@Override
	public void setNoreserveDate(AdoptAppointFormVO adoptAppointForm) {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = createUpdatePreparedStatement(con, adoptAppointForm, UPDATE_SQL);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
	public AdoptAppointFormVO findByPK(Integer appoint_form_no) {
		AdoptAppointFormVO adoptAppointForm = new AdoptAppointFormVO();
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, appoint_form_no);
			ResultSet rs = pstmt.executeQuery();
			adoptAppointForm = selectOneAdoptAppointFormByNo(rs);
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
		return adoptAppointForm;
	}

	@Override
	public AdoptAppointFormVO findByDate(Date reserve_date) {
		AdoptAppointFormVO adoptAppointForm = new AdoptAppointFormVO();
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_DATE);
			pstmt.setDate(1, reserve_date);
			ResultSet rs = pstmt.executeQuery();
			adoptAppointForm = selectOneAdoptAppointFormByDate(rs);
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
		return adoptAppointForm;
	}

	@Override
	public List<AdoptAppointFormVO> findAdoptMebNo(Integer adopt_meb_no) {
		List<AdoptAppointFormVO> adoptAppointFormList = new ArrayList<>();
		Connection con = null;

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_ADOPTMEBNO);
			pstmt.setInt(1, adopt_meb_no);
			ResultSet rs = pstmt.executeQuery();
			adoptAppointFormList = selectAdoptAppointFormByAdoptMeb(adoptAppointFormList, rs);
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
		return adoptAppointFormList;
	}

	@Override
	public List<AdoptAppointFormVO> createDate() {
		Connection con = null;
		List<AdoptAppointFormVO> new15Date = new ArrayList<>();
		try {
			con = ds.getConnection();			
			PreparedStatement pstmt = con.prepareStatement(creatDate);
			ResultSet rs = pstmt.executeQuery();
			new15Date = create15Date(new15Date, rs);
			
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
		return new15Date;
	}

	private List<AdoptAppointFormVO> create15Date(List<AdoptAppointFormVO> new15Date, ResultSet rs) {
		try {
			while (rs.next()) {
				AdoptAppointFormVO adoptAppointForm = new AdoptAppointFormVO();				
				adoptAppointForm.setAdopt_meb_no(rs.getInt("ADOPT_MEB_NO"));
				adoptAppointForm.setAppoint_date(rs.getDate("APPOINT_DATE"));
				adoptAppointForm.setFinifh_appoint_num(rs.getString("FINIFH_APPOINT_NUM"));
				adoptAppointForm.setAppoint_limit(rs.getString("APPOINT_LIMIT"));
				new15Date.add(adoptAppointForm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new15Date;
	}

	private PreparedStatement createInsertPreparedStatement(Connection con, AdoptAppointFormVO adoptAppointForm,
			String SQL, String[] cols) throws SQLException {

		PreparedStatement pstmt = con.prepareStatement(SQL, cols);
		pstmt.setInt(1, adoptAppointForm.getAdopt_meb_no());
		pstmt.setDate(2, adoptAppointForm.getAppoint_date());
		pstmt.setString(3, adoptAppointForm.getFinifh_appoint_num());
		pstmt.setString(4, adoptAppointForm.getAppoint_limit());
		return pstmt;
	}

	private PreparedStatement createUpdatePreparedStatement(Connection con, AdoptAppointFormVO adoptAppointForm,
			String SQL) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, adoptAppointForm.getFinifh_appoint_num());
		pstmt.setString(2, adoptAppointForm.getAppoint_limit());
		pstmt.setInt(3, adoptAppointForm.getAppoint_form_no());
		return pstmt;
	}

	private AdoptAppointFormVO selectOneAdoptAppointFormByNo(ResultSet rs) {
		AdoptAppointFormVO adoptAppointForm = new AdoptAppointFormVO();

		try {
			while (rs.next()) {
				adoptAppointForm.setAppoint_form_no(rs.getInt("APPOINT_FORM_NO"));
				adoptAppointForm.setAdopt_meb_no(rs.getInt("ADOPT_MEB_NO"));
				adoptAppointForm.setAppoint_date(rs.getDate("APPOINT_DATE"));
				adoptAppointForm.setFinifh_appoint_num(rs.getString("FINIFH_APPOINT_NUM"));
				adoptAppointForm.setAppoint_limit(rs.getString("APPOINT_LIMIT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adoptAppointForm;
	}

	private AdoptAppointFormVO selectOneAdoptAppointFormByDate(ResultSet rs) {
		AdoptAppointFormVO adoptAppointForm = new AdoptAppointFormVO();

		try {
			while (rs.next()) {
				adoptAppointForm.setAppoint_form_no(rs.getInt("APPOINT_FORM_NO"));
				adoptAppointForm.setAdopt_meb_no(rs.getInt("ADOPT_MEB_NO"));
				adoptAppointForm.setAppoint_date(rs.getDate("APPOINT_DATE"));
				adoptAppointForm.setFinifh_appoint_num(rs.getString("FINIFH_APPOINT_NUM"));
				adoptAppointForm.setAppoint_limit(rs.getString("APPOINT_LIMIT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adoptAppointForm;
	}

	private List<AdoptAppointFormVO> selectAdoptAppointFormByAdoptMeb(List<AdoptAppointFormVO> adoptAppointFormList,
			ResultSet rs) {
		try {
			while (rs.next()) {
				AdoptAppointFormVO adoptAppointForm = new AdoptAppointFormVO();
				adoptAppointForm.setAppoint_form_no(rs.getInt("APPOINT_FORM_NO"));
				adoptAppointForm.setAdopt_meb_no(rs.getInt("ADOPT_MEB_NO"));
				adoptAppointForm.setAppoint_date(rs.getDate("APPOINT_DATE"));
				adoptAppointForm.setFinifh_appoint_num(rs.getString("FINIFH_APPOINT_NUM"));
				adoptAppointForm.setAppoint_limit(rs.getString("APPOINT_LIMIT"));
				adoptAppointFormList.add(adoptAppointForm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adoptAppointFormList;
	}

}
