package com.forum_article.model;

import java.sql.*;
import java.util.*;



public class Forum_articleDAO implements Forum_articleDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FORUM_ARTICLE (FORUM_ARTICLE_CLS_NO,GEN_MEB_NO,FORUM_COMM_COM_NAME,FORUM_COMM_COM,FORUM_COMM_DATE,FORUM_COMM_STATE) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT FORUM_ARTICLE_NO,FORUM_ARTICLE_CLS_NO,GEN_MEB_NO,FORUM_COMM_COM_NAME,FORUM_COMM_COM,FORUM_COMM_DATE,FORUM_COMM_STATE FROM FORUM_ARTICLE order by FORUM_ARTICLE_NO";
	private static final String GET_ONE_STMT = "SELECT FORUM_ARTICLE_NO,FORUM_ARTICLE_CLS_NO,GEN_MEB_NO,FORUM_COMM_COM_NAME,FORUM_COMM_COM,FORUM_COMM_DATE,FORUM_COMM_STATE FROM FORUM_ARTICLE where FORUM_ARTICLE_NO = ?";
	private static final String DELETE = "DELETE FROM FORUM_ARTICLE where FORUM_ARTICLE_NO = ?";
	private static final String UPDATE = "UPDATE FORUM_ARTICLE set FORUM_ARTICLE_CLS_NO=?,GEN_MEB_NO=?,FORUM_COMM_COM_NAME=?,FORUM_COMM_COM=?,FORUM_COMM_DATE=?,FORUM_COMM_STATE=? where FORUM_ARTICLE_NO = ?";

	@Override
	public void insert(Forum_articleVO FORUM_ARTICLE_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, FORUM_ARTICLE_NO.getForum_article_cls_no());
			pstmt.setInt(2, FORUM_ARTICLE_NO.getGen_meb_no());
			pstmt.setString(3, FORUM_ARTICLE_NO.getForum_comm_com_name());
			pstmt.setString(4, FORUM_ARTICLE_NO.getForum_comm_com());
			pstmt.setDate(5, FORUM_ARTICLE_NO.getForum_comm_date());
			pstmt.setString(6, FORUM_ARTICLE_NO.getForum_comm_state());
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
	public void update(Forum_articleVO FORUM_ARTICLE_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, FORUM_ARTICLE_NO.getForum_article_cls_no());
			pstmt.setInt(2, FORUM_ARTICLE_NO.getGen_meb_no());
			pstmt.setString(3, FORUM_ARTICLE_NO.getForum_comm_com_name());
			pstmt.setString(4, FORUM_ARTICLE_NO.getForum_comm_com());
			pstmt.setDate(5, FORUM_ARTICLE_NO.getForum_comm_date());
			pstmt.setString(6, FORUM_ARTICLE_NO.getForum_comm_state());
			pstmt.setInt(7, FORUM_ARTICLE_NO.getForum_article_no());
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
	public void delete(Integer FORUM_ARTICLE_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, FORUM_ARTICLE_NO);

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
	public Forum_articleVO findByforum_article_noPK(Integer forum_article_no) {

		Forum_articleVO Forum_articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, forum_article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				Forum_articleVO = new Forum_articleVO();
				Forum_articleVO.setForum_article_no(rs.getInt("FORUM_ARTICLE_NO"));
				Forum_articleVO.setForum_article_cls_no(rs.getInt("FORUM_ARTICLE_CLS_NO"));
				Forum_articleVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				Forum_articleVO.setForum_comm_com_name(rs.getString("FORUM_COMM_COM_NAME"));
				Forum_articleVO.setForum_comm_com(rs.getString("FORUM_COMM_COM"));
				Forum_articleVO.setForum_comm_date(rs.getDate("FORUM_COMM_DATE"));
				Forum_articleVO.setForum_comm_state(rs.getString("FORUM_COMM_STATE"));
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
		return Forum_articleVO;
	}

	public List<Forum_articleVO> getforum_article() {

		List<Forum_articleVO> list = new ArrayList<Forum_articleVO>();
		Forum_articleVO Forum_articleVO = null;

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
				Forum_articleVO = new Forum_articleVO();
				Forum_articleVO.setForum_article_no(rs.getInt("FORUM_ARTICLE_NO"));
				Forum_articleVO.setForum_article_cls_no(rs.getInt("FORUM_ARTICLE_CLS_NO"));
				Forum_articleVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				Forum_articleVO.setForum_comm_com_name(rs.getString("FORUM_COMM_COM_NAME"));
				Forum_articleVO.setForum_comm_com(rs.getString("FORUM_COMM_COM"));
				Forum_articleVO.setForum_comm_date(rs.getDate("FORUM_COMM_DATE"));
				Forum_articleVO.setForum_comm_state(rs.getString("FORUM_COMM_STATE"));
				list.add(Forum_articleVO); // Store the row in the list
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

		Forum_articleDAO dao = new Forum_articleDAO();

		// 新增
//		Forum_articleVO Forum_articleVO1 = new Forum_articleVO();
//		Forum_articleVO1.setForum_article_cls_no(2);
//		Forum_articleVO1.setGen_meb_no(1);
//		Forum_articleVO1.setForum_comm_com_name("領養寵物問題");
//		Forum_articleVO1.setForum_comm_com("請問OOXX");
//		Forum_articleVO1.setForum_comm_date(java.sql.Date.valueOf("2021-01-01"));
//		Forum_articleVO1.setForum_comm_state("0");
//		dao.insert(Forum_articleVO1);
//
//		// 修改
//		Forum_articleVO Forum_articleVO2 = new Forum_articleVO();
//		Forum_articleVO2.setForum_article_no(13);
//		Forum_articleVO2.setForum_article_cls_no(1);
//		Forum_articleVO2.setGen_meb_no(1);
//		Forum_articleVO2.setForum_comm_com_name("領養寵物問題11111");
//		Forum_articleVO2.setForum_comm_com("請問OOXX111111");
//		Forum_articleVO2.setForum_comm_date(java.sql.Date.valueOf("2021-01-01"));
//		Forum_articleVO2.setForum_comm_state("0");
//		dao.update(Forum_articleVO2);
//
		// 刪除
		dao.delete(13);
//
//		// 查詢單一
//		Forum_articleVO Forum_articleVO3 = dao.findByforum_article_noPK(2);
//		System.out.print(Forum_articleVO3.getForum_article_no() + ",");
//		System.out.print(Forum_articleVO3.getForum_article_cls_no() + ",");
//		System.out.print(Forum_articleVO3.getGen_meb_no() + ",");
//		System.out.print(Forum_articleVO3.getForum_comm_com_name() + ",");
//		System.out.print(Forum_articleVO3.getForum_comm_com() + ",");
//		System.out.print(Forum_articleVO3.getForum_comm_date() + ",");
//		System.out.println(Forum_articleVO3.getForum_comm_state());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Forum_articleVO> list = dao.getforum_article();
//		for (Forum_articleVO aEmp : list) {
//			System.out.print(aEmp.getForum_article_no() + ",");
//			System.out.print(aEmp.getForum_article_cls_no() + ",");
//			System.out.print(aEmp.getGen_meb_no() + ",");
//			System.out.print(aEmp.getForum_comm_com_name() + ",");
//			System.out.print(aEmp.getForum_comm_com() + ",");
//			System.out.print(aEmp.getForum_comm_date() + ",");
//			System.out.print(aEmp.getForum_comm_state());
//			System.out.println();
//
//		}

	}
}
