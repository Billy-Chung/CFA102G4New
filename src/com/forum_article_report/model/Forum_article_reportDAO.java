package com.forum_article_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Forum_article_reportDAO implements Forum_article_reportDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO forum_article_report (FORUM_ARTICLE_NO,GEN_MEB_NO,FORUM_COMM_REPORT_COM,FORUM_COMM_REPORT_STATE,FORUM_COMM_REPORT_DATE) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ARTICLE_REPORT_NO,FORUM_ARTICLE_NO,GEN_MEB_NO,FORUM_COMM_REPORT_COM,FORUM_COMM_REPORT_STATE,FORUM_COMM_REPORT_DATE FROM forum_article_report order by ARTICLE_REPORT_NO";
	private static final String GET_ONE_STMT = "SELECT ARTICLE_REPORT_NO,FORUM_ARTICLE_NO,GEN_MEB_NO,FORUM_COMM_REPORT_COM,FORUM_COMM_REPORT_STATE,FORUM_COMM_REPORT_DATE FROM forum_article_report where ARTICLE_REPORT_NO = ?";
	private static final String DELETE = "DELETE FROM forum_article_report where ARTICLE_REPORT_NO = ?";
	private static final String UPDATE = "UPDATE forum_article_report set FORUM_ARTICLE_NO=?,GEN_MEB_NO=?,FORUM_COMM_REPORT_COM=?,FORUM_COMM_REPORT_STATE=?,FORUM_COMM_REPORT_DATE=? where ARTICLE_REPORT_NO = ?";

	@Override
	public void insert(Forum_article_reportVO forum_article_report) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forum_article_report.getForum_article_no());
			pstmt.setInt(2, forum_article_report.getGen_meb_no());
			pstmt.setString(3, forum_article_report.getForum_comm_report_com());
			pstmt.setString(4, forum_article_report.getForum_comm_report_state());
			pstmt.setDate(5, forum_article_report.getForum_comm_report_date());

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
	public void update(Forum_article_reportVO forum_article_report) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forum_article_report.getForum_article_no());
			pstmt.setInt(2, forum_article_report.getGen_meb_no());
			pstmt.setString(3, forum_article_report.getForum_comm_report_com());
			pstmt.setString(4, forum_article_report.getForum_comm_report_state());
			pstmt.setDate(5, forum_article_report.getForum_comm_report_date());
			pstmt.setInt(6, forum_article_report.getArticle_report_no());
			
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
	public void delete(Integer ARTICLE_REPORT_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ARTICLE_REPORT_NO);

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
	public Forum_article_reportVO findByArticle_report_noPK(Integer ARTICLE_REPORT_NO) {

		Forum_article_reportVO Forum_article_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ARTICLE_REPORT_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				Forum_article_reportVO = new Forum_article_reportVO();
				Forum_article_reportVO.setArticle_report_no(rs.getInt("article_report_no"));
				Forum_article_reportVO.setForum_article_no(rs.getInt("forum_article_no"));
				Forum_article_reportVO.setGen_meb_no(rs.getInt("gen_meb_no"));
				Forum_article_reportVO.setForum_comm_report_com(rs.getString("forum_comm_report_com"));
				Forum_article_reportVO.setForum_comm_report_state(rs.getString("forum_comm_report_state"));
				Forum_article_reportVO.setForum_comm_report_date(rs.getDate("forum_comm_report_date"));
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
		return Forum_article_reportVO;
	}

	@Override
	public List<Forum_article_reportVO> getforum_article_report() {

		List<Forum_article_reportVO> list = new ArrayList<Forum_article_reportVO>();
		Forum_article_reportVO Forum_article_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Forum_article_reportVO = new Forum_article_reportVO();
				Forum_article_reportVO.setArticle_report_no(rs.getInt("article_report_no"));
				Forum_article_reportVO.setForum_article_no(rs.getInt("forum_article_no"));
				Forum_article_reportVO.setGen_meb_no(rs.getInt("gen_meb_no"));
				Forum_article_reportVO.setForum_comm_report_com(rs.getString("forum_comm_report_com"));
				Forum_article_reportVO.setForum_comm_report_state(rs.getString("forum_comm_report_state"));
				Forum_article_reportVO.setForum_comm_report_date(rs.getDate("forum_comm_report_date"));
				list.add(Forum_article_reportVO);
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

		Forum_article_reportDAO dao = new Forum_article_reportDAO();

		// 新增
//		Forum_article_reportVO Forum_article_reportVO1 = new Forum_article_reportVO();
//		Forum_article_reportVO1.setForum_article_no(2);
//		Forum_article_reportVO1.setGen_meb_no(1);
//		Forum_article_reportVO1.setForum_comm_report_com("廣告");
//		Forum_article_reportVO1.setForum_comm_report_state("1");
//		Forum_article_reportVO1.setForum_comm_report_date(java.sql.Date.valueOf("2021-09-01"));
//		dao.insert(Forum_article_reportVO1);

//		// 修改
//		Forum_article_reportVO Forum_article_reportVO2 = new Forum_article_reportVO();
//		Forum_article_reportVO2.setArticle_report_no(2);
//		Forum_article_reportVO2.setForum_article_no(2);
//		Forum_article_reportVO2.setGen_meb_no(1);
//		Forum_article_reportVO2.setForum_comm_report_com("廣告");
//		Forum_article_reportVO2.setForum_comm_report_state("1");
//		Forum_article_reportVO2.setForum_comm_report_date(java.sql.Date.valueOf("2021-09-10"));
//		dao.update(Forum_article_reportVO2);

		// 刪除
		dao.delete(7);

//		// 查詢單一
//		Forum_article_reportVO Forum_article_reportVO3 = dao.findByArticle_report_noPK(2);
//		System.out.print(Forum_article_reportVO3.getArticle_report_no() + ",");
//		System.out.print(Forum_article_reportVO3.getForum_article_no() + ",");
//		System.out.print(Forum_article_reportVO3.getGen_meb_no() + ",");
//		System.out.print(Forum_article_reportVO3.getForum_comm_report_com() + ",");
//		System.out.print(Forum_article_reportVO3.getForum_comm_report_state() + ",");
//		System.out.print(Forum_article_reportVO3.getForum_comm_report_date() + ",");
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Forum_article_reportVO> list = dao.getforum_article_report();
//		for (Forum_article_reportVO aEmp : list) {
//			System.out.print(aEmp.getArticle_report_no() + ",");
//			System.out.print(aEmp.getForum_article_no() + ",");
//			System.out.print(aEmp.getGen_meb_no() + ",");
//			System.out.print(aEmp.getForum_comm_report_com() + ",");
//			System.out.print(aEmp.getForum_comm_report_state() + ",");
//			System.out.print(aEmp.getForum_comm_report_date() + ",");
//			System.out.println();
//		}
	}

}
