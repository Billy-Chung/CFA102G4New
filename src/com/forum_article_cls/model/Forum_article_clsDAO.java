package com.forum_article_cls.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Forum_article_clsDAO implements Forum_article_clsDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FORUM_ARTICLE_CLS (FORUM_ARTICLE_CLS_NAME) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FORUM_ARTICLE_CLS order by FORUM_ARTICLE_CLS_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM FORUM_ARTICLE_CLS where FORUM_ARTICLE_CLS_NO = ?";
	private static final String DELETE = "DELETE FROM FORUM_ARTICLE_CLS where FORUM_ARTICLE_CLS_NO = ?";
	private static final String UPDATE = "UPDATE FORUM_ARTICLE_CLS set FORUM_ARTICLE_CLS_NAME=? where FORUM_ARTICLE_CLS_NO = ?";

	public Forum_article_clsVO insert(Forum_article_clsVO Forum_article_cls) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, Forum_article_cls.getForum_article_cla_name());

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
		return Forum_article_cls;

	}

	@Override
	public void update(Forum_article_clsVO FORUM_ARTICLE_CLS) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, FORUM_ARTICLE_CLS.getForum_article_cla_name());

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
	public void delete(Integer FORUM_ARTICLE_CLS_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, FORUM_ARTICLE_CLS_NO);

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
	public Forum_article_clsVO findByForum_article_cls_NoPK(Integer FORUM_ARTICLE_CLS_NO) {

		Forum_article_clsVO Forum_article_clsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, FORUM_ARTICLE_CLS_NO);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				Forum_article_clsVO = new Forum_article_clsVO();
				Forum_article_clsVO.setForum_article_cla_no(rs.getInt("FORUM_ARTICLE_CLS_NO"));
				Forum_article_clsVO.setForum_article_cla_name(rs.getString("FORUM_ARTICLE_CLS_NAME"));

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
		return Forum_article_clsVO;
	}

	@Override
	public List<Forum_article_clsVO> getforum_article_cls() {
		List<Forum_article_clsVO> list = new ArrayList<Forum_article_clsVO>();
		Forum_article_clsVO Forum_article_clsVO = null;

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
				Forum_article_clsVO = new Forum_article_clsVO();
				Forum_article_clsVO.setForum_article_cla_no(rs.getInt("FORUM_ARTICLE_CLS_NO"));
				Forum_article_clsVO.setForum_article_cla_name(rs.getString("FORUM_ARTICLE_CLS_NAME"));
				list.add(Forum_article_clsVO);
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

		Forum_article_clsDAO dao = new Forum_article_clsDAO();

//		// 新增
//		Forum_article_clsVO Forum_article_clsVO1 = new Forum_article_clsVO();
//		Forum_article_clsVO1.setForum_article_cla_name("生病問題");
//
//		dao.insert(Forum_article_clsVO1);

		// 修改
		Forum_article_clsVO Forum_article_clsVO2 = new Forum_article_clsVO();
		Forum_article_clsVO2.setForum_article_cla_no(14);
		Forum_article_clsVO2.setForum_article_cla_name("生病問題2");
		dao.update(Forum_article_clsVO2);
//
//		// 刪除
//		dao.delete(16);

//		 查詢單一
//		Forum_article_clsVO Forum_article_clsVO3 = dao.findByForum_article_cls_NoPK(6);
//		System.out.print(Forum_article_clsVO3.getForum_article_cla_no() + ",");
//		System.out.print(Forum_article_clsVO3.getForum_article_cla_name() + ",");
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Forum_article_clsVO> list = dao.getforum_article_cls();
//		for (Forum_article_clsVO aEmp : list) {
//			System.out.print(aEmp.getForum_article_cla_no() + ",");
//			System.out.print(aEmp.getForum_article_cla_name() + ",");
//			System.out.println();
//		}

	}
}