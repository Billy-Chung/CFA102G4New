package com.news.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO implements NewsDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO NEWS (NEWS_DATE,NEWS_STATE,NEWS_DATA,NEWS_IMG) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT NEWS_NO,NEWS_DATE,NEWS_STATE,NEWS_DATA,NEWS_IMG FROM NEWS order by NEWS_NO";
	private static final String GET_ONE_STMT = "SELECT NEWS_NO,NEWS_DATE,NEWS_STATE,NEWS_DATA,NEWS_IMG FROM NEWS where NEWS_NO = ?";
	private static final String DELETE = "DELETE FROM NEWS where NEWS_NO = ?";
	private static final String UPDATE = "UPDATE NEWS set NEWS_DATE=?,NEWS_STATE=?,NEWS_DATA=?,NEWS_IMG=? where NEWS_NO = ?";

	@Override
	public void insert(NewsVO news) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setDate(1, news.getNews_date());
			pstmt.setString(2, news.getNews_state());
			pstmt.setString(3, news.getNews_data());
			pstmt.setBytes(4, news.getNews_img());

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
	public void update(NewsVO news) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setDate(1, news.getNews_date());
			pstmt.setString(2, news.getNews_state());
			pstmt.setString(3, news.getNews_data());
			pstmt.setBytes(4, news.getNews_img());
			pstmt.setInt(5, news.getNews_no());

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
	public void delete(Integer NEWS_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, NEWS_NO);

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
	public NewsVO findByNewsPK(Integer NEWS_NO) {

		NewsVO NewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, NEWS_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				NewsVO = new NewsVO();
				NewsVO.setNews_no(rs.getInt("NEWS_NO"));
				NewsVO.setNews_date(rs.getDate("NEWS_DATE"));
				NewsVO.setNews_state(rs.getString("NEWS_STATE"));
				NewsVO.setNews_data(rs.getString("NEWS_DATA"));
				NewsVO.setNews_img(rs.getBytes("NEWS_IMG"));
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
		return NewsVO;
	}

	@Override
	public List<NewsVO> getNews() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO NewsVO = null;

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
				NewsVO = new NewsVO();
				NewsVO.setNews_no(rs.getInt("NEWS_NO"));
				NewsVO.setNews_date(rs.getDate("NEWS_DATE"));
				NewsVO.setNews_state(rs.getString("NEWS_STATE"));
				NewsVO.setNews_data(rs.getString("NEWS_DATA"));
				NewsVO.setNews_img(rs.getBytes("NEWS_IMG"));
				list.add(NewsVO); // Store the row in the list
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

		NewsDAO dao = new NewsDAO();
		
//		// 新增
//		NewsVO NewsVO1 = new NewsVO();
//		byte[] pic = getPictureByteArray("images/1.jpg");
//		NewsVO1.setNews_date(java.sql.Date.valueOf("2005-01-01"));
//		NewsVO1.setNews_state("1");
//		NewsVO1.setNews_data("因疫情ooxx");
//		NewsVO1.setNews_img(pic);
//		dao.insert(NewsVO1);
//	
//		// 修改
//		NewsVO NewsVO2 = new NewsVO();
//		byte[] pic2 = getPictureByteArray("images/1.jpg");
//		NewsVO2.setNews_no(12);
//		NewsVO2.setNews_date(java.sql.Date.valueOf("2021-09-03"));
//		NewsVO2.setNews_state("1");
//		NewsVO2.setNews_data("1111555555");
//		NewsVO2.setNews_img(pic2);
//		dao.update(NewsVO2);

//		// 刪除
				dao.delete(12);
//
		// 查詢單一
//		NewsVO NewsVO3 = dao.findByNewsPK(3);
//		System.out.print(NewsVO3.getNews_no() + ",");
//		System.out.print(NewsVO3.getNews_date() + ",");
//		System.out.print(NewsVO3.getNews_state() + ",");
//		System.out.print(NewsVO3.getNews_data() + ",");
//		System.out.print(NewsVO3.getNews_img() + ",");
//		System.out.println("---------------------");

//		// 查詢全部
//		List<NewsVO> list = dao.getNews();
//		for (NewsVO aEmp : list) {
//			System.out.print(aEmp.getNews_no() + ",");
//			System.out.print(aEmp.getNews_date() + ",");
//			System.out.print(aEmp.getNews_state() + ",");
//			System.out.print(aEmp.getNews_data() + ",");
//			System.out.print(aEmp.getNews_img() + ",");
//			System.out.println();
//		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
