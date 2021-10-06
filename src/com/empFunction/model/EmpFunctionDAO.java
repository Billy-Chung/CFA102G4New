package com.empFunction.model;

import java.util.*;
import java.sql.*;

public class EmpFunctionDAO implements EmpFunctionDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO EMP_FUNCTION (FUNCTION_NAME) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT * FROM EMP_FUNCTION order by FUNCTION_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM EMP_FUNCTION where FUNCTION_NO = ?";
	private static final String DELETE = "DELETE FROM EMP_FUNCTION where FUNCTION_NO = ?";
	private static final String UPDATE = "UPDATE EMP_FUNCTION set FUNCTION_NAME=? where FUNCTION_NO = ?";

	@Override
	public void insert(EmpFunctionVO emp_function) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, emp_function.getFunction_name());

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

	public void update(EmpFunctionVO emp_function) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(1, emp_function.getFunction_name());
			pstmt.setInt(2, emp_function.getFunction_no());
			
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

	public void delete(Integer FUNCTION_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, FUNCTION_NO);

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
	public EmpFunctionVO findByFunctionNoPK(Integer FUNCTION_NO) {
		EmpFunctionVO EmpFunctionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, FUNCTION_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				EmpFunctionVO = new EmpFunctionVO();
				EmpFunctionVO.setFunction_no(rs.getInt("FUNCTION_NO"));
				EmpFunctionVO.setFunction_name(rs.getString("FUNCTION_NAME"));
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

		return EmpFunctionVO;
	}

	@Override
	public List<EmpFunctionVO> getemp_function() {
		List<EmpFunctionVO> list = new ArrayList<EmpFunctionVO>();
		EmpFunctionVO EmpFunctionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				EmpFunctionVO = new EmpFunctionVO();
				EmpFunctionVO.setFunction_no(rs.getInt("FUNCTION_NO"));
				EmpFunctionVO.setFunction_name(rs.getString("FUNCTION_NAME"));

				list.add(EmpFunctionVO); // Store the row in the list
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

		EmpFunctionDAO dao = new EmpFunctionDAO();

//		// 新增
//		EmpFunctionVO EmpFunctionVO1 = new EmpFunctionVO();
//		EmpFunctionVO1.setFunction_name("22222222222");
//		dao.insert(EmpFunctionVO1);

//		// 修改
//		EmpFunctionVO EmpFunctionVO2 = new EmpFunctionVO();
//		EmpFunctionVO2.setFunction_no(15);
//		EmpFunctionVO2.setFunction_name("111111");
//		dao.update(EmpFunctionVO2);

//		// 刪除
		dao.delete(14);
//
//		// 查詢
//		EmpFunctionVO EmpFunctionVO3 = dao.findByFunctionNoPK(2);
//		System.out.print(EmpFunctionVO3.getFunction_no() + ",");
//		System.out.print(EmpFunctionVO3.getFunction_name() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<EmpFunctionVO> list = dao.getemp_function();
//		for (EmpFunctionVO aEmp : list) {
//			System.out.print(aEmp.getFunction_no() + ",");
//			System.out.print(aEmp.getFunction_name() + ",");
//
//		}

	}
}
