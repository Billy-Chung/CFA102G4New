package com.promo_photo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.promotions.model.promotionsVO;

public class promo_photoDAO implements promo_photoDAO_interface {

	private static final String INSERT_STMT = "insert into PROMO_PHOTO(promot_no,promo_function,photo,mimetype) values ( ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "update PROMO_PHOTO set promo_function=?, photo=?, mimetype=? WHERE promot_no=?";
	private static final String DELETE_STMT = "delete from PROMO_PHOTO where promot_no = ?";
	private static final String FIND_BY_PK = "select * from PROMO_PHOTO where promot_no = ?";
	private static final String GET_ALL = "select * from PROMO_PHOTO";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(promo_photoVO promo_photo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(INSERT_STMT);// 新增
			int i = 1;
			pstmt.setInt(i++, promo_photo.getPromot_no());
			pstmt.setString(i++, promo_photo.getPromo_function());
			pstmt.setBytes(i++, promo_photo.getPhoto());
			pstmt.setString(i++, promo_photo.getMimetype());
			pstmt.executeUpdate();// 執行
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
	}

	@Override
	public void update(promo_photoVO promo_photo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(UPDATE_STMT);// 修改

			int i = 1;
			pstmt.setString(i++, promo_photo.getPromo_function());
			pstmt.setBytes(i++, promo_photo.getPhoto());
			pstmt.setString(i++, promo_photo.getMimetype());
			pstmt.setInt(i++, promo_photo.getPromot_no());// WHERE 條件
			pstmt.executeUpdate();// 執行
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
	public List<promo_photoVO> getAll() {
		// TODO Auto-generated method stub
		List<promo_photoVO> promo_photoList = new ArrayList<>();
		promo_photoVO promo_photo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei", "David", "123456");
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
		
		while (rs.next()) {
			promo_photo = new promo_photoVO();
			promo_photo.setPromot_no(rs.getInt("promot_no"));
			promo_photo.setPromo_function(rs.getString("promo_function"));
			promo_photo.setPhoto(rs.getBytes("photo"));
			promo_photo.setMimetype(rs.getString("mimetype"));
			promo_photoList.add(promo_photo);
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
		return promo_photoList;
	}

}
