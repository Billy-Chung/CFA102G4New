package com.shpping_information.model;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;

public class Shpping_informationDAO implements Shpping_informationDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO SHOPPING_INFORMATION (SHOPPING_IMG,SHOPPING_DATA,SHOPPING_TEL,SHOPPING_FAX,SHOPPING_ADD) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOPPING_INFORMATION order by SHOPPING_INFORMATION_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM SHOPPING_INFORMATION where SHOPPING_INFORMATION_NO = ?";
	private static final String DELETE = "DELETE FROM SHOPPING_INFORMATION where SHOPPING_INFORMATION_NO = ?";
	private static final String UPDATE = "UPDATE SHOPPING_INFORMATION set SHOPPING_IMG=?,SHOPPING_DATA=?,SHOPPING_TEL=?,SHOPPING_FAX=?,SHOPPING_ADD=? where SHOPPING_INFORMATION_NO = ?";

	@Override
	public void insert(Shpping_informationVO shopping_information) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setBytes(1, shopping_information.getShopping_img());
			pstmt.setString(2, shopping_information.getShopping_date());
			pstmt.setString(3, shopping_information.getShopping_tel());
			pstmt.setString(4, shopping_information.getShopping_fax());
			pstmt.setString(5, shopping_information.getShopping_add());

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
	public void update(Shpping_informationVO shopping_information) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setBytes(1, shopping_information.getShopping_img());
			pstmt.setString(2, shopping_information.getShopping_date());
			pstmt.setString(3, shopping_information.getShopping_tel());
			pstmt.setString(4, shopping_information.getShopping_fax());
			pstmt.setString(5, shopping_information.getShopping_add());
			pstmt.setInt(6, shopping_information.getShopping_information_no());
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
	public void delete(Integer shopping_information) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, shopping_information);

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
	public Shpping_informationVO findByShopping_informationPK(Integer SHOPPING_INFORMATION_NO) {

		Shpping_informationVO shpping_informationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, SHOPPING_INFORMATION_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				shpping_informationVO = new Shpping_informationVO();
				shpping_informationVO.setShopping_information_no(rs.getInt("Shopping_information_no"));
				shpping_informationVO.setShopping_img(rs.getBytes("Shopping_img"));
				shpping_informationVO.setShopping_date(rs.getString("Shopping_data"));
				shpping_informationVO.setShopping_tel(rs.getString("Shopping_tel"));
				shpping_informationVO.setShopping_fax(rs.getString("Shopping_fax"));
				shpping_informationVO.setShopping_add(rs.getString("Shopping_add"));
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
		return shpping_informationVO;
	}

	@Override
	public List<Shpping_informationVO> getshopping_information_no() {

		List<Shpping_informationVO> list = new ArrayList<Shpping_informationVO>();
		Shpping_informationVO shpping_informationVO = null;

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
				shpping_informationVO = new Shpping_informationVO();
				shpping_informationVO.setShopping_information_no(rs.getInt("Shopping_information_no"));
				shpping_informationVO.setShopping_img(rs.getBytes("Shopping_img"));
				shpping_informationVO.setShopping_date(rs.getString("Shopping_data"));
				shpping_informationVO.setShopping_tel(rs.getString("Shopping_tel"));
				shpping_informationVO.setShopping_fax(rs.getString("Shopping_fax"));
				shpping_informationVO.setShopping_add(rs.getString("Shopping_add"));

				list.add(shpping_informationVO); // Store the row in the list
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

	public static void main(String[] args) throws IOException {

		Shpping_informationDAO dao = new Shpping_informationDAO();

//		// 新增
//		Shpping_informationVO shpping_informationVO1 = new Shpping_informationVO();
//		byte[] pic = getPictureByteArray("images/1.jpg");
//		shpping_informationVO1.setShopping_img(pic);
//		shpping_informationVO1.setShopping_date("測試");
//		shpping_informationVO1.setShopping_tel("09111111");
//		shpping_informationVO1.setShopping_fax("09111111");
//		shpping_informationVO1.setShopping_add("桃園市中壢區xx路");
//		dao.insert(shpping_informationVO1);

//		// 修改
//		Shpping_informationVO shpping_informationVO2 = new Shpping_informationVO();
//		byte[] pic1 = getPictureByteArray("images/1.jpg");
//		shpping_informationVO2.setShopping_information_no(2);
//		shpping_informationVO2.setShopping_img(pic1);
//		shpping_informationVO2.setShopping_date("測試");
//		shpping_informationVO2.setShopping_tel("092222");
//		shpping_informationVO2.setShopping_fax("092222");
//		shpping_informationVO2.setShopping_add("桃園市中壢區xx路");
//		dao.update(shpping_informationVO2);
//
//		// 刪除
//		dao.delete(2);
//
		// 查詢
//		Shpping_informationVO shpping_informationVO3 = dao.findByShopping_informationPK(1);
//		System.out.print(shpping_informationVO3.getShopping_information_no() + ",");
//		System.out.print(shpping_informationVO3.getShopping_img() + ",");
//		System.out.print(shpping_informationVO3.getShopping_date() + ",");
//		System.out.print(shpping_informationVO3.getShopping_tel() + ",");
//		System.out.print(shpping_informationVO3.getShopping_fax() + ",");
//		System.out.print(shpping_informationVO3.getShopping_add() + ",");
//		System.out.println("---------------------");

//		// 查詢
		List<Shpping_informationVO> list = dao.getshopping_information_no();
		for (Shpping_informationVO aEmp : list) {
			System.out.print(aEmp.getShopping_information_no() + ",");
			System.out.print(aEmp.getShopping_img() + ",");
			System.out.print(aEmp.getShopping_date() + ",");
			System.out.print(aEmp.getShopping_tel() + ",");
			System.out.print(aEmp.getShopping_fax() + ",");
			System.out.print(aEmp.getShopping_add() + ",");

		}
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
