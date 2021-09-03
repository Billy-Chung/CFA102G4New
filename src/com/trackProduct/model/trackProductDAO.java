package com.trackProduct.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class trackProductDAO implements trackProductDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_SQL ="INSERT INTO TRACK_PRODUCT VALUES (?, ?)";
	public static final String UPDATE_SQL ="UPDATE TRACK_PRODUCT SET PRODUCT_NO=? WHERE GEN_MEB_NO=?";
	public static final String DELETE_SQL ="DELETE FROM TRACK_PRODUCT WHERE GEN_MEB_NO=? and PRODUCT_NO=?";
	public static final String FIND_BY_DEPTNO_SQL = "SELECT * FROM TRACK_PRODUCT WHERE GEN_MEB_NO = ? and PRODUCT_NO=? ";
	public static final String GET_ALL_SQL = "SELECT * FROM TRACK_PRODUCT";
	
	static {
		try {
			Class.forName(DRIVER);	
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(trackProductVO tpVO) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			pst = conn.prepareStatement(INSERT_SQL);
			
			pst.setInt(1,tpVO.getGen_meb_no());
			pst.setInt(2,tpVO.getProduct_no());
			pst.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}	
			}
			
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(trackProductVO tpVO) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			pst = conn.prepareStatement(UPDATE_SQL);
			
			pst.setInt(1,tpVO.getProduct_no());
			pst.setInt(2,tpVO.getGen_meb_no());
			pst.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}	
			}
			
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer GEN_MEB_NO,Integer PRODUCT_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setInt(1,GEN_MEB_NO);
			pstmt.setInt(2,PRODUCT_NO);
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}

	@Override
	public trackProductVO findByPrimaryKey(Integer GEN_MEB_NO, Integer PRODUCT_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		trackProductVO tpVO = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_DEPTNO_SQL);
			pstmt.setInt(1,GEN_MEB_NO);
			pstmt.setInt(2,PRODUCT_NO);
			rs = pstmt.executeQuery();
			while(rs.next()) {

				tpVO = new trackProductVO();
				tpVO.setGen_meb_no(GEN_MEB_NO);
				tpVO.setProduct_no(PRODUCT_NO);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return tpVO;
	}

	@Override
	public List<trackProductVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <trackProductVO> tpList = new ArrayList<>();
		
		try {
			
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				trackProductVO tpVO = new trackProductVO();
				tpVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				tpVO.setProduct_no(rs.getInt("PRODUCT_NO"));
				tpList.add(tpVO);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return tpList;
	}
}