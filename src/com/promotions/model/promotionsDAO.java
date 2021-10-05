package com.promotions.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//此類別實作DAO interface，並將資料庫操作細節封裝起來
public class PromotionsDAO implements PromotionsDAO_interface {// implements實作

//	private static final String SQL = "";
	private static final String INSERT_STMT = "insert into PROMOTIONS(promot_name, promot_date_start, promot_date_end, promot_status, promot_type, PROMOT_DISCOUNT_TYPE, promot_discount, promot_reduce, promot_comment , promot_photo) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	promot_no,自動生成流水號不用
	private static final String UPDATE_STMT = "update PROMOTIONS set promot_name=?, promot_date_start=?, promot_date_end=?, promot_status=?, promot_type=?, PROMOT_DISCOUNT_TYPE=?, promot_discount=?, promot_reduce=?, promot_comment=?, promot_photo=? WHERE promot_no=?";
//  圖片
	private static final String DELETE_STMT = "delete from PROMOTIONS where promot_no = ?";
	private static final String FIND_BY_PK = "select * from PROMOTIONS where promot_no = ?";
	private static final String GET_ALL = "select * from PROMOTIONS order by PROMOT_NO desc";
	
	private static final String FIND_CURRENT_PROMOTION = "SELECT * FROM CFA_102_04.PROMOTIONS where PROMOT_STATUS = '0' order by PROMOT_NO desc";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// Util.DRIVER
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public Integer add(PromotionsVO promotions) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer nextPromoNo = null;
		try {
			// URL //USER //PASSWORD
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			// 先新增訂單order_form
			String cols[] = { "promo_no" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);// 新增
			int i = 1;
//			pstmt.setInt(i++, promotions.getPromot_no()); //自動生成流水號不可用set
			pstmt.setString(i++, promotions.getPromot_name());
			pstmt.setDate(i++, promotions.getPromot_date_start());
			pstmt.setDate(i++, promotions.getPromot_date_end());
			pstmt.setString(i++, promotions.getPromot_status());
			pstmt.setString(i++, promotions.getPromot_type());
			pstmt.setString(i++, promotions.getPromot_discount_type());
			pstmt.setInt(i++, promotions.getPromot_discount());
			pstmt.setInt(i++, promotions.getPromot_reduce());
			pstmt.setString(i++, promotions.getPromot_comment());
			pstmt.setBytes(i++, promotions.getPromot_photo());
			pstmt.executeUpdate();// 執行

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextPromoNo = rs.getInt(1);
				promotions.setPromot_no(nextPromoNo);
				System.out.println("自增主鍵值= " + nextPromoNo + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
		return nextPromoNo;
	}

	@Override
	public void update(PromotionsVO promotions) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(UPDATE_STMT);// 修改

			int i = 1;

			pstmt.setString(i++, promotions.getPromot_name());
			pstmt.setDate(i++, promotions.getPromot_date_start());
			pstmt.setDate(i++, promotions.getPromot_date_end());
			pstmt.setString(i++, promotions.getPromot_status());
			pstmt.setString(i++, promotions.getPromot_type());
			pstmt.setString(i++, promotions.getPromot_discount_type());
			pstmt.setInt(i++, promotions.getPromot_discount());
			pstmt.setInt(i++, promotions.getPromot_reduce());
			pstmt.setString(i++, promotions.getPromot_comment());
			pstmt.setBytes(i++, promotions.getPromot_photo());
			pstmt.setInt(i++, promotions.getPromot_no());// WHERE 條件
			pstmt.executeUpdate();// 執行
			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void delete(Integer promot_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(DELETE_STMT);// 刪除
			pstmt.setInt(1, promot_no);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public PromotionsVO findByPromotNoPk(Integer promot_no) {
		PromotionsVO promotions = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(FIND_BY_PK);// 主鍵
			pstmt.setInt(1, promot_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotions = new PromotionsVO();
				promotions.setPromot_no(rs.getInt("promot_no"));
				promotions.setPromot_name(rs.getString("promot_name"));
				promotions.setPromot_date_start(rs.getDate("promot_date_start"));
				promotions.setPromot_date_end(rs.getDate("promot_date_end"));
				promotions.setPromot_status(rs.getString("promot_status"));
				promotions.setPromot_type(rs.getString("promot_type"));
				promotions.setPromot_discount_type(rs.getString("PROMOT_DISCOUNT_TYPE"));
				promotions.setPromot_discount(rs.getInt("promot_discount"));
				promotions.setPromot_reduce(rs.getInt("promot_reduce"));
				promotions.setPromot_comment(rs.getString("promot_comment"));
				promotions.setPromot_photo(rs.getBytes("promot_photo"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
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

		return promotions;
	}

	@Override
	public List<PromotionsVO> getAll() {// 列表
		List<PromotionsVO> promotionsList = new ArrayList<>();
		PromotionsVO promotions = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotions = new PromotionsVO();
				promotions.setPromot_no(rs.getInt("promot_no"));
				promotions.setPromot_name(rs.getString("promot_name"));
				promotions.setPromot_date_start(rs.getDate("promot_date_start"));
				promotions.setPromot_date_end(rs.getDate("promot_date_end"));
				promotions.setPromot_status(rs.getString("promot_status"));
				promotions.setPromot_type(rs.getString("promot_type"));
				promotions.setPromot_discount_type(rs.getString("PROMOT_DISCOUNT_TYPE"));
				promotions.setPromot_discount(rs.getInt("promot_discount"));
				promotions.setPromot_reduce(rs.getInt("promot_reduce"));
				promotions.setPromot_comment(rs.getString("promot_comment"));
				promotions.setPromot_photo(rs.getBytes("promot_photo"));
				promotionsList.add(promotions);
			}

		} catch (SQLException se) {
			se.printStackTrace();
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
		return promotionsList;
	}

	@Override
	public PromotionsVO getCurrentPromotion() {
		List<PromotionsVO> promotionsList = new ArrayList<>();
		PromotionsVO promotions = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(FIND_CURRENT_PROMOTION);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotions = new PromotionsVO();
				promotions.setPromot_no(rs.getInt("promot_no"));
				promotions.setPromot_name(rs.getString("promot_name"));
				promotions.setPromot_date_start(rs.getDate("promot_date_start"));
				promotions.setPromot_date_end(rs.getDate("promot_date_end"));
				promotions.setPromot_status(rs.getString("promot_status"));
				promotions.setPromot_type(rs.getString("promot_type"));
				promotions.setPromot_discount_type(rs.getString("PROMOT_DISCOUNT_TYPE"));
				promotions.setPromot_discount(rs.getInt("promot_discount"));
				promotions.setPromot_reduce(rs.getInt("promot_reduce"));
				promotions.setPromot_comment(rs.getString("promot_comment"));
				promotions.setPromot_photo(rs.getBytes("promot_photo"));
				promotionsList.add(promotions);
			}

		} catch (SQLException se) {
			se.printStackTrace();
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
		if(promotionsList.size()!=0) {
			return promotionsList.get(0);
		}else {
			return null;
		}
		
	}

}
