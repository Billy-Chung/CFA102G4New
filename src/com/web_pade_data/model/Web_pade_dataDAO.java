package com.web_pade_data.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Web_pade_dataDAO implements Web_pade_data_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO WEB_PADE_DATA (WEB_PADE_DATA,WEB_PADE_DATE) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT WEB_PADE_DATA_NO,WEB_PADE_DATA,WEB_PADE_DATE FROM WEB_PADE_DATA order by WEB_PADE_DATA_NO";
	private static final String GET_ONE_STMT = "SELECT WEB_PADE_DATA_NO,WEB_PADE_DATA,WEB_PADE_DATE FROM WEB_PADE_DATA where WEB_PADE_DATA_NO = ?";
	private static final String DELETE = "DELETE FROM WEB_PADE_DATA where WEB_PADE_DATA_NO = ?";
	private static final String UPDATE = "UPDATE WEB_PADE_DATA set WEB_PADE_DATA=?,WEB_PADE_DATE=? where WEB_PADE_DATA_NO = ?";

	public Web_pade_dataVO insert(Web_pade_dataVO web_pade_data_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, web_pade_data_no.getWeb_pade_data());
			pstmt.setDate(2, web_pade_data_no.getWeb_pade_date());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return web_pade_data_no;

	}

	public void update(Web_pade_dataVO web_pade_data_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, web_pade_data_no.getWeb_pade_data());
			pstmt.setDate(2, web_pade_data_no.getWeb_pade_date());
			pstmt.setInt(3, web_pade_data_no.getWeb_pade_data_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer web_pade_data_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, web_pade_data_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Web_pade_dataVO findByWeb_pade_data_noPK(Integer WEB_PADE_DATA_NO) {

		Web_pade_dataVO Web_pade_dataVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, WEB_PADE_DATA_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Web_pade_dataVO = new Web_pade_dataVO();
				Web_pade_dataVO.setWeb_pade_data_no(rs.getInt("WEB_PADE_DATA_NO"));
				Web_pade_dataVO.setWeb_pade_data(rs.getInt("WEB_PADE_DATA"));
				Web_pade_dataVO.setWeb_pade_date(rs.getDate("WEB_PADE_DATE"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Web_pade_dataVO;
	}

	@Override
	public List<Web_pade_dataVO> getweb_pade_data_no() {

		List<Web_pade_dataVO> list = new ArrayList<Web_pade_dataVO>();
		Web_pade_dataVO Web_pade_dataVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				Web_pade_dataVO = new Web_pade_dataVO();
				Web_pade_dataVO.setWeb_pade_data_no(rs.getInt("WEB_PADE_DATA_NO"));
				Web_pade_dataVO.setWeb_pade_data(rs.getInt("WEB_PADE_DATA"));
				Web_pade_dataVO.setWeb_pade_date(rs.getDate("WEB_PADE_DATE"));
				list.add(Web_pade_dataVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		Web_pade_dataDAO dao = new Web_pade_dataDAO();

		// 新增
//		Web_pade_dataVO Web_pade_dataVO1 = new Web_pade_dataVO();
//		Web_pade_dataVO1.setWeb_pade_data(20);
//		Web_pade_dataVO1.setWeb_pade_date(java.sql.Date.valueOf("2021-09-01"));
//
//		dao.insert(Web_pade_dataVO1);

		// 修改
		Web_pade_dataVO Web_pade_dataVO2 = new Web_pade_dataVO();
		Web_pade_dataVO2.setWeb_pade_data_no(14);
		Web_pade_dataVO2.setWeb_pade_data(40);
		Web_pade_dataVO2.setWeb_pade_date(java.sql.Date.valueOf("2021-09-11"));
		dao.update(Web_pade_dataVO2);
//
//		// 刪除
//		dao.delete(13);

//		// 查詢單一
//		Web_pade_dataVO Web_pade_dataVO3 = dao.findByWeb_pade_data_noPK(11);
//		System.out.print(Web_pade_dataVO3.getWeb_pade_data_no() + ",");
//		System.out.print(Web_pade_dataVO3.getWeb_pade_data() + ",");
//		System.out.print(Web_pade_dataVO3.getWeb_pade_date() + ",");
//
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Web_pade_dataVO> list = dao.getweb_pade_data_no();
//		for (Web_pade_dataVO aEmp : list) {
//			System.out.print(aEmp.getWeb_pade_data_no() + ",");
//			System.out.print(aEmp.getWeb_pade_data() + ",");
//			System.out.print(aEmp.getWeb_pade_date() + ",");
//			System.out.println();
//		}
	}

}
