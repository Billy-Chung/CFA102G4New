package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productDAO implements productDAO_interface {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_SQL ="INSERT INTO PRODUCT VALUES (?,?,?,?,?,?,?,?)";
	public static final String UPDATE_SQL ="UPDATE PRODUCT SET PRODUCT_TYPE_NO=?,PRODUCT_NAME=?,PRODUCT_PRICE=?,PRODUCT_COMMENT=?,PRODUCT_STATUS=?,PRODUCT_ALL_STARS=?,PRODUCT_ALL_COMMENTS=? WHERE PRODUCT_NO=?";
	public static final String DELETE_SQL ="DELETE FROM PRODUCT WHERE PRODUCT_NO=?";
	public static final String FIND_BY_DEPTNO_SQL = "SELECT * FROM PRODUCT WHERE PRODUCT_NO = ?";
	public static final String GET_ALL_SQL = "SELECT * FROM PRODUCT";
	
	static {
		try {
			Class.forName(DRIVER);	
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(productVO pVO) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			pst = conn.prepareStatement(INSERT_SQL);
			pst.setInt(1,pVO.getProduct_no());
			pst.setInt(2,pVO.getProduct_type_no());
			pst.setString(3,pVO.getProduct_name());
			pst.setInt(4,pVO.getProduct_price());
			pst.setString(5,pVO.getProduct_comment());
			pst.setString(6,pVO.getProduct_status());
			pst.setInt(7,pVO.getProduct_all_stars());
			pst.setInt(8,pVO.getProduct_all_comments());
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
	public void update(productVO pVO) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			pst = conn.prepareStatement(UPDATE_SQL);
			
			pst.setInt(1,pVO.getProduct_type_no());
			pst.setString(2,pVO.getProduct_name());
			pst.setInt(3,pVO.getProduct_price());
			pst.setString(4,pVO.getProduct_comment());
			pst.setString(5,pVO.getProduct_status());
			pst.setInt(6,pVO.getProduct_all_stars());
			pst.setInt(7,pVO.getProduct_all_comments());
			pst.setInt(8,pVO.getProduct_no());
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
	public void delete(Integer PRODUCT_NO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setInt(1,PRODUCT_NO);
			
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
	public productVO findByPrimaryKey(Integer PRODUCT_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		productVO pVO = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_DEPTNO_SQL);
			pstmt.setInt(1,PRODUCT_NO);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pVO = new productVO();
				pVO.setProduct_no(PRODUCT_NO);
				pVO.setProduct_type_no(rs.getInt("PRODUCT_TYPE_NO"));
				pVO.setProduct_name(rs.getString("PRODUCT_NAME"));
				pVO.setProduct_price(rs.getInt("PRODUCT_PRICE"));
				pVO.setProduct_comment(rs.getString("PRODUCT_COMMENT"));
				pVO.setProduct_status(rs.getString("PRODUCT_STATUS"));
				pVO.setProduct_all_stars(rs.getInt("PRODUCT_ALL_STARS"));
				pVO.setProduct_all_comments(rs.getInt("PRODUCT_ALL_COMMENTS"));
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
		
		return pVO;
	}

	@Override
	public List<productVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <productVO> pList = new ArrayList<>();
		
		try {
			
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO pVO = new productVO();
				pVO.setProduct_no(rs.getInt("PRODUCT_NO"));
				pVO.setProduct_type_no(rs.getInt("PRODUCT_TYPE_NO"));
				pVO.setProduct_name(rs.getString("PRODUCT_NAME"));
				pVO.setProduct_price(rs.getInt("PRODUCT_PRICE"));
				pVO.setProduct_comment(rs.getString("PRODUCT_COMMENT"));
				pVO.setProduct_status(rs.getString("PRODUCT_STATUS"));
				pVO.setProduct_all_stars(rs.getInt("PRODUCT_ALL_STARS"));
				pVO.setProduct_all_comments(rs.getInt("PRODUCT_ALL_COMMENTS"));
				pList.add(pVO);
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

		return pList;
		
	}
}