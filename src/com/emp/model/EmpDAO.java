package com.emp.model;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class EmpDAO implements EmpDAO_interface{

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
		String userid = "David";
		String passwd = "123456";
	
		private static final String INSERT_STMT = 
			"INSERT INTO emp (EMP_ACCOUNT,EMP_PASSWORD,EMP_NAME,EMP_ON_JOB,EMP_IMG,EMP_MAIL,EMP_PHONE,EMP_GENDER,EMP_DAY,EMP_ADD,EMP_SCHOOL,EMP_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT EMP_NO,EMP_ACCOUNT,EMP_PASSWORD,EMP_NAME,EMP_ON_JOB,EMP_IMG,EMP_MAIL,EMP_PHONE,EMP_GENDER,EMP_DAY,EMP_ADD,EMP_SCHOOL,EMP_DATE FROM emp order by EMP_NO";
		private static final String GET_ONE_STMT = 
			"SELECT EMP_NO,EMP_ACCOUNT,EMP_PASSWORD,EMP_NAME,EMP_ON_JOB,EMP_IMG,EMP_MAIL,EMP_PHONE,EMP_GENDER,EMP_DAY,EMP_ADD,EMP_SCHOOL,EMP_DATE FROM emp where EMP_NO = ?";
		private static final String DELETE = 
			"DELETE FROM emp where EMP_NO = ?";
		private static final String UPDATE = 
			"UPDATE emp set EMP_ACCOUNT=?, EMP_PASSWORD=?, EMP_NAME=?, EMP_ON_JOB=?, EMP_IMG=?, EMP_MAIL=?, EMP_PHONE=?, EMP_GENDER=?, EMP_DAY=?, EMP_ADD=?, EMP_SCHOOL=?, EMP_DATE=? where EMP_NO= ?";
		
		public EmpVO insert(EmpVO emp) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
			
				pstmt.setString(1, emp.getEmp_account());
				pstmt.setString(2, emp.getEmp_password());
				pstmt.setString(3, emp.getEmp_name());
				pstmt.setString(4, emp.getEmp_on_job());
				pstmt.setBytes(5, emp.getEmp_img());
				pstmt.setString(6, emp.getEmp_mail());
				pstmt.setString(7, emp.getEmp_phone());
				pstmt.setString(8, emp.getEmp_gender());
				pstmt.setDate(9, emp.getEmp_day());
				pstmt.setString(10, emp.getEmp_add());
				pstmt.setString(11, emp.getEmp_school());
				pstmt.setDate(12, emp.getEmp_date());
					
				pstmt.executeUpdate();
			
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return emp;

		}
					
		
		public void update(EmpVO emp) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
			
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
			
				pstmt.setString(1, emp.getEmp_account());
				pstmt.setString(2, emp.getEmp_password());
				pstmt.setString(3, emp.getEmp_name());
				pstmt.setString(4, emp.getEmp_on_job());
				pstmt.setBytes(5, emp.getEmp_img());
				pstmt.setString(6, emp.getEmp_mail());
				pstmt.setString(7, emp.getEmp_phone());
				pstmt.setString(8, emp.getEmp_gender());
				pstmt.setDate(9, emp.getEmp_day());
				pstmt.setString(10, emp.getEmp_add());
				pstmt.setString(11, emp.getEmp_school());
				pstmt.setDate(12, emp.getEmp_date());
				pstmt.setInt(13, emp.getEmp_no());
				
				pstmt.executeUpdate();
			
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		
		
		public void delete(Integer EMP_NO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
			
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, EMP_NO);

				pstmt.executeUpdate();
			
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			
		
		public EmpVO findByEmpNoPK(Integer EMP_NO) {

			EmpVO EmpVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
			
				pstmt.setInt(1, EMP_NO);
				
				rs = pstmt.executeQuery();
			
				while (rs.next()) {
					// empVo 也稱為 Domain objects
					EmpVO = new EmpVO();
					EmpVO.setEmp_no(rs.getInt("EMP_NO"));
					EmpVO.setEmp_account(rs.getString("EMP_ACCOUNT"));
					EmpVO.setEmp_password(rs.getString("EMP_PASSWORD"));
					EmpVO.setEmp_name(rs.getString("EMP_NAME"));
					EmpVO.setEmp_on_job(rs.getString("EMP_ON_JOB"));
					EmpVO.setEmp_img(rs.getBytes("EMP_IMG"));
					EmpVO.setEmp_mail(rs.getString("EMP_MAIL"));
					EmpVO.setEmp_phone(rs.getString("EMP_PHONE"));
					EmpVO.setEmp_gender(rs.getString("EMP_GENDER"));
					EmpVO.setEmp_day(rs.getDate("EMP_DAY"));
					EmpVO.setEmp_add(rs.getString("EMP_ADD"));
					EmpVO.setEmp_school(rs.getString("EMP_SCHOOL"));
					EmpVO.setEmp_date(rs.getDate("EMP_DATE"));				
					
				}
				
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
						
			return EmpVO;
		}
		
		public List<EmpVO> getemp() {
			List<EmpVO> list = new ArrayList<EmpVO>();
			EmpVO emp = null;

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
					emp = new EmpVO();
					emp.setEmp_no(rs.getInt("EMP_NO"));
					emp.setEmp_account(rs.getString("EMP_ACCOUNT"));
					emp.setEmp_password(rs.getString("EMP_PASSWORD"));
					emp.setEmp_name(rs.getString("EMP_NAME"));
					emp.setEmp_on_job(rs.getString("EMP_ON_JOB"));
					emp.setEmp_img(rs.getBytes("EMP_IMG"));
					emp.setEmp_mail(rs.getString("EMP_MAIL"));
					emp.setEmp_phone(rs.getString("EMP_PHONE"));
					emp.setEmp_gender(rs.getString("EMP_GENDER"));
					emp.setEmp_day(rs.getDate("EMP_DAY"));
					emp.setEmp_add(rs.getString("EMP_ADD"));
					emp.setEmp_school(rs.getString("EMP_SCHOOL"));
					emp.setEmp_date(rs.getDate("EMP_DATE"));
					
					list.add(emp); // Store the row in the list
					
				}
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		
//		public static void main(String[] args) throws IOException {
//
//			EmpDAO dao = new EmpDAO();
		
			// 新增
//			EmpVO empVO1 = new EmpVO();
//			byte[] pic = getPictureByteArray("images/1.jpg");
//			empVO1.setEmp_account("11111111");
//			empVO1.setEmp_password("11111111");
//			empVO1.setEmp_name("111111");
//			empVO1.setEmp_on_job("1");
//			empVO1.setEmp_img(pic);
//			empVO1.setEmp_mail("111@yahoo.com.tw");
//			empVO1.setEmp_phone("0963111111");
//			empVO1.setEmp_gender("1");
//			empVO1.setEmp_day(java.sql.Date.valueOf("2005-01-01"));
//			empVO1.setEmp_add("桃園市中壢區");
//			empVO1.setEmp_school("tibame");
//			empVO1.setEmp_date(java.sql.Date.valueOf("2005-01-01"));
//			dao.insert(empVO1);
			
			
			// 修改
//			EmpVO empVO2 = new EmpVO();
//			byte[] pic2 = getPictureByteArray("images/1.jpg");
//			empVO2.setEmp_no(11);
//			empVO2.setEmp_account("22222");
//			empVO2.setEmp_password("22222");
//			empVO2.setEmp_name("222");
//			empVO2.setEmp_on_job("1");
//			empVO2.setEmp_img(pic2);
//			empVO2.setEmp_mail("222@yahoo.com.tw");
//			empVO2.setEmp_phone("096312221");
//			empVO2.setEmp_gender("1");
//			empVO2.setEmp_day(java.sql.Date.valueOf("2005-12-02"));
//			empVO2.setEmp_add("桃園市中壢區");
//			empVO2.setEmp_school("tibame2");
//			empVO2.setEmp_date(java.sql.Date.valueOf("2005-01-02"));
//			dao.update(empVO2);
//			
//			// 刪除
//			dao.delete(11);
//			
//			// 查詢
//			EmpVO empVO3 = dao.findByEmpNoPK(3);
//			System.out.print(empVO3.getEmp_no() + ",");
//			System.out.print(empVO3.getEmp_account() + ",");
//			System.out.print(empVO3.getEmp_password() + ",");
//			System.out.print(empVO3.getEmp_name() + ",");
//			System.out.print(empVO3.getEmp_on_job() + ",");
//			System.out.print(empVO3.getEmp_img() + ",");
//			System.out.print(empVO3.getEmp_mail() + ",");
//			System.out.print(empVO3.getEmp_phone() + ",");
//			System.out.print(empVO3.getEmp_gender() + ",");
//			System.out.print(empVO3.getEmp_day() + ",");
//			System.out.print(empVO3.getEmp_add() + ",");
//			System.out.print(empVO3.getEmp_school() + ",");
//			System.out.print(empVO3.getEmp_date() + ",");
//			System.out.println("---------------------");
			
	
//			// 查詢
//			List<EmpVO> list = dao.getemp();
//			for (EmpVO aEmp : list) {
//				System.out.print(aEmp.getEmp_no() + ",");
//				System.out.print(aEmp.getEmp_account() + ",");
//				System.out.print(aEmp.getEmp_password() + ",");
//				System.out.print(aEmp.getEmp_name() + ",");
//				System.out.print(aEmp.getEmp_on_job() + ",");
//				System.out.print(aEmp.getEmp_img() + ",");
//				System.out.print(aEmp.getEmp_mail() + ",");
//				System.out.print(aEmp.getEmp_phone() + ",");
//				System.out.print(aEmp.getEmp_gender() + ",");
//				System.out.print(aEmp.getEmp_day() + ",");
//				System.out.print(aEmp.getEmp_add() + ",");
//				System.out.print(aEmp.getEmp_school() + ",");
//				System.out.print(aEmp.getEmp_date() + ",");
//				System.out.println();
//			}
//			}

		public static byte[] getPictureByteArray(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			return buffer;
		}
}