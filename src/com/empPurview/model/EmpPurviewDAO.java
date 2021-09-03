package com.empPurview.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class EmpPurviewDAO implements EmpPurviewDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO EMP_PURVIEW (EMP_NO,FUNCTION_NO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT EMP_NO,FUNCTION_NO FROM EMP_PURVIEW order by EMP_NO";
	private static final String GET_ONE_STMT = "SELECT EMP_NO,FUNCTION_NO FROM EMP_PURVIEW where EMP_NO = ?";
	private static final String DELETE = "DELETE FROM EMP_PURVIEW where EMP_NO = ?";
	private static final String UPDATE = "UPDATE EMP_PURVIEW set FUNCTION_NO =?  where EMP_NO =?,FUNCTION_NO =?";

	public void insert(EmpPurviewVO emp_purview) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, emp_purview.getEmp_no());
			pstmt.setInt(2, emp_purview.getFunction_no());

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
	public void update(EmpPurviewVO emp_purview) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, emp_purview.getFunction_no());
			pstmt.setInt(2, emp_purview.getEmp_no());

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

	public void delete(Integer emp_purview) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_purview);

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

		
		public List<EmpPurviewVO> findByFunctionNoPK(Integer FUNCTION_NO) {
//	public EmpPurviewVO findByFunctionNoPK(Integer FUNCTION_NO) {
		List<EmpPurviewVO> list = new ArrayList<EmpPurviewVO>();
		EmpPurviewVO empPurviewVO = null;

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
				empPurviewVO = new EmpPurviewVO();
				empPurviewVO.setEmp_no(rs.getInt("EMP_NO"));
				empPurviewVO.setFunction_no(rs.getInt("FUNCTION_NO"));
				list.add(empPurviewVO);
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

	@Override
	public List<EmpPurviewVO> getemp_purview() {
		List<EmpPurviewVO> list = new ArrayList<EmpPurviewVO>();
		EmpPurviewVO empPurviewVO = null;

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
				empPurviewVO = new EmpPurviewVO();
				empPurviewVO.setEmp_no(rs.getInt("EMP_NO"));
				empPurviewVO.setFunction_no(rs.getInt("FUNCTION_NO"));

				list.add(empPurviewVO); // Store the row in the list
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

		EmpPurviewDAO dao = new EmpPurviewDAO();

//	// 新增
//	EmpPurviewVO empPurviewVO1 = new EmpPurviewVO();
//	empPurviewVO1.setEmp_no(10);
//	empPurviewVO1.setFunction_no(2);
//	
//	dao.insert(empPurviewVO1);
//
//	// 修改
	EmpPurviewVO empPurviewVO2 = new EmpPurviewVO();
	empPurviewVO2.setEmp_no(10);
	empPurviewVO2.setFunction_no(10);
	dao.update(empPurviewVO2);

//	// 查詢
//		List<EmpPurviewVO> list = dao.findByFunctionNoPK(4);	
//		for (EmpPurviewVO ADD : list) {
//			System.out.print(ADD.getEmp_no() + ",");
//			System.out.print(ADD.getFunction_no() + ",");
//			System.out.println();
//		}

//		// 查詢
//		List<EmpPurviewVO> list = dao.getemp_purview();
//		for (EmpPurviewVO aEmp : list) {
//			System.out.print(aEmp.getEmp_no() + ",");
//			System.out.print(aEmp.getFunction_no() + ",");
//			System.out.println();
//
//		}
	}
}
