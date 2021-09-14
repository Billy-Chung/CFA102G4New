package com.adoptMemberNews.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdoptMemberNewsDAO implements AdoptMemberNews_interface {

	private static final String INSERT_SQL = "insert into ADOPT_MEMBER_NEWS (ADOPT_MEB_NO,ADOPT_MEB_NEWS_TITLE,ADOPT_MEB_NEWS_COMMENT,ADOPT_MEB_NEWS_PHOTO,ADOPT_MEB_NEWS_STATE,ADOPT_MEB_NEWS_DATE) values(?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "update ADOPT_MEMBER_NEWS set ADOPT_MEB_NEWS_TITLE = ?, ADOPT_MEB_NEWS_COMMENT = ?, ADOPT_MEB_NEWS_PHOTO = ?, ADOPT_MEB_NEWS_STATE = ?, ADOPT_MEB_NEWS_DATE = ? where ADOPT_MEB_NEWS_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ADOPT_MEMBER_NEWS WHERE ADOPT_MEB_NEWS_NO = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM ADOPT_MEMBER_NEWS WHERE ADOPT_MEB_NEWS_TITLE like ?";
	private static final String SELECT_ALL = "SELECT * FROM ADOPT_MEMBER_NEWS ORDER BY ADOPT_MEB_NEWS_DATE DESC";

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
	public AdoptMemberNewsVo insert(AdoptMemberNewsVo adoptMemberNews) {
		Connection con = null;
		try {
			con = ds.getConnection();
			String[] cols = { "ADOPT_MEB_NEWS_NO" };
			PreparedStatement pstmt = createInsertPreparedStatement(con, adoptMemberNews, INSERT_SQL, cols);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int key = rs.getInt(1);
				adoptMemberNews.setAdopt_meb_news_no(key);
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
		return adoptMemberNews;
	}

	@Override
	public void update(AdoptMemberNewsVo adoptMemberNews) {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = createUpdatePreparedStatement(con, adoptMemberNews, UPDATE_SQL);
			pstmt.executeUpdate();
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

	}

	@Override
	public AdoptMemberNewsVo findByadoptMemberNewsNoPK(Integer adopt_meb_news_no) {
		Connection con = null;
		AdoptMemberNewsVo adoptMemberNews = null;

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, adopt_meb_news_no);
			ResultSet rs = pstmt.executeQuery();
			adoptMemberNews = selectOneadoptMemberNewsByNo(rs);
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
		return adoptMemberNews;
	}

	@Override
	public List<AdoptMemberNewsVo> findByAdoptMebNewsTitle(String adopt_meb_news_title) {
		Connection con = null;
		List<AdoptMemberNewsVo> adoptMemberNewsList = new ArrayList<>();

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_NAME);
			pstmt.setString(1, "%" + adopt_meb_news_title + "%");
			ResultSet rs = pstmt.executeQuery();
			adoptMemberNewsList = selectAdoptMebNewsByTitle(adoptMemberNewsList, rs);
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
		return adoptMemberNewsList;
	}

	@Override
	public List<AdoptMemberNewsVo> getAlladoptMemberNews() {
		Connection con = null;
		List<AdoptMemberNewsVo> adoptMemberNewsList = new ArrayList<>();

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			adoptMemberNewsList = selectallAdoptMemberNews(adoptMemberNewsList, rs);
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
		return adoptMemberNewsList;
	}

	private PreparedStatement createInsertPreparedStatement(Connection con, AdoptMemberNewsVo adoptMemberNews,
			String SQL, String[] cols) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL, cols);
		pstmt.setInt(1, adoptMemberNews.getAdopt_meb_no());
		pstmt.setString(2, adoptMemberNews.getAdopt_meb_news_title());
		pstmt.setString(3, adoptMemberNews.getAdopt_meb_news_comment());
		pstmt.setBytes(4, adoptMemberNews.getAdopt_meb_news_photo());
		pstmt.setString(5, adoptMemberNews.getAdopt_meb_news_state());
		pstmt.setDate(6, adoptMemberNews.getAdopt_meb_news_date());
		return pstmt;
	}

	private PreparedStatement createUpdatePreparedStatement(Connection con, AdoptMemberNewsVo adoptMemberNews,
			String SQL) throws SQLException {

		PreparedStatement pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, adoptMemberNews.getAdopt_meb_news_title());
		pstmt.setString(2, adoptMemberNews.getAdopt_meb_news_comment());
		pstmt.setBytes(3, adoptMemberNews.getAdopt_meb_news_photo());
		pstmt.setString(4, adoptMemberNews.getAdopt_meb_news_state());
		pstmt.setDate(5, adoptMemberNews.getAdopt_meb_news_date());
		pstmt.setInt(6, adoptMemberNews.getAdopt_meb_news_no());
		return pstmt;
	}

	private AdoptMemberNewsVo selectOneadoptMemberNewsByNo(ResultSet rs) {
		AdoptMemberNewsVo adoptMemberNews = new AdoptMemberNewsVo();
		try {
			while (rs.next()) {
				adoptMemberNews.setAdopt_meb_news_no(rs.getInt("ADOPT_MEB_NEWS_NO"));
				adoptMemberNews.setAdopt_meb_no(rs.getInt("ADOPT_MEB_NO"));
				adoptMemberNews.setAdopt_meb_news_title(rs.getString("ADOPT_MEB_NEWS_TITLE"));
				adoptMemberNews.setAdopt_meb_news_comment(rs.getString("ADOPT_MEB_NEWS_COMMENT"));
				adoptMemberNews.setAdopt_meb_news_photo(rs.getBytes("ADOPT_MEB_NEWS_PHOTO"));
				adoptMemberNews.setAdopt_meb_news_state(rs.getString("ADOPT_MEB_NEWS_STATE"));
				adoptMemberNews.setAdopt_meb_news_date(rs.getDate("ADOPT_MEB_NEWS_DATE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return adoptMemberNews;
	}

	private List<AdoptMemberNewsVo> selectallAdoptMemberNews(List<AdoptMemberNewsVo> adoptMemberNewsList,
			ResultSet rs) {
		try {
			while (rs.next()) {
				AdoptMemberNewsVo adoptMemberNews = new AdoptMemberNewsVo();
				adoptMemberNews.setAdopt_meb_news_no(rs.getInt("ADOPT_MEB_NEWS_NO"));
				adoptMemberNews.setAdopt_meb_no(rs.getInt("ADOPT_MEB_NO"));
				adoptMemberNews.setAdopt_meb_news_title(rs.getString("ADOPT_MEB_NEWS_TITLE"));
				adoptMemberNews.setAdopt_meb_news_comment(rs.getString("ADOPT_MEB_NEWS_COMMENT"));
				adoptMemberNews.setAdopt_meb_news_photo(rs.getBytes("ADOPT_MEB_NEWS_PHOTO"));
				adoptMemberNews.setAdopt_meb_news_state(rs.getString("ADOPT_MEB_NEWS_STATE"));
				adoptMemberNews.setAdopt_meb_news_date(rs.getDate("ADOPT_MEB_NEWS_DATE"));
				adoptMemberNewsList.add(adoptMemberNews);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return adoptMemberNewsList;
	}

	private List<AdoptMemberNewsVo> selectAdoptMebNewsByTitle(List<AdoptMemberNewsVo> adoptMemberNewsList,
			ResultSet rs) {

		try {
			while (rs.next()) {
				AdoptMemberNewsVo adoptMemberNews = new AdoptMemberNewsVo();
				adoptMemberNews.setAdopt_meb_news_no(rs.getInt("ADOPT_MEB_NEWS_NO"));
				adoptMemberNews.setAdopt_meb_no(rs.getInt("ADOPT_MEB_NO"));
				adoptMemberNews.setAdopt_meb_news_title(rs.getString("ADOPT_MEB_NEWS_TITLE"));
				adoptMemberNews.setAdopt_meb_news_comment(rs.getString("ADOPT_MEB_NEWS_COMMENT"));
				adoptMemberNews.setAdopt_meb_news_photo(rs.getBytes("ADOPT_MEB_NEWS_PHOTO"));
				adoptMemberNews.setAdopt_meb_news_state(rs.getString("ADOPT_MEB_NEWS_STATE"));
				adoptMemberNews.setAdopt_meb_news_date(rs.getDate("ADOPT_MEB_NEWS_DATE"));
				adoptMemberNewsList.add(adoptMemberNews);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}

		return adoptMemberNewsList;
	}
}
