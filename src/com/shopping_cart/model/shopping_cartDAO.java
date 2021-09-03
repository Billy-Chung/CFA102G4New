package com.shopping_cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pay_method.model.pay_methodVO;

public class shopping_cartDAO implements shopping_cartDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "insert into SHOPPING_CART(gen_meb_no,product_no,cart_product_number) values (?,?,?)";
	private static final String UPDATE_STMT = "update SHOPPING_CART set product_no=?, cart_product_number=? where gen_meb_no=?";

	
	
	private static final String DELETE_STMT = "delete from SHOPPING_CART where gen_meb_no=?";
	private static final String FIND_BY_PK = "select * from SHOPPING_CART where gen_meb_no=?";
	private static final String GET_ALL = "select * from SHOPPING_CART";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}	
	

	@Override
	public void add(shopping_cartVO shopping_cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
            
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);//新增
			int i=1;
			pstmt.setInt(i++, shopping_cartVO.getGen_meb_no());
			pstmt.setInt(i++, shopping_cartVO.getProduct_no());
			pstmt.setInt(i++, shopping_cartVO.getCart_product_number());
			pstmt.executeUpdate();//執行		
			
			
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
	public void update(shopping_cartVO shopping_cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);//修改

			int i=1;
			pstmt.setInt(i++, shopping_cartVO.getProduct_no());
			pstmt.setInt(i++, shopping_cartVO.getCart_product_number());
			pstmt.setInt(i++, shopping_cartVO.getGen_meb_no());//WHERE 條件			                                             
			pstmt.executeUpdate();//執行		
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
	public void delete(Integer gen_meb_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);//刪除
			pstmt.setInt(1, gen_meb_no);			
			pstmt.executeUpdate();
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
	public shopping_cartVO findShoppingCartPk(Integer gen_meb_no) {
		shopping_cartVO shopping_cart = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);//主鍵
			pstmt.setInt(1, gen_meb_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				shopping_cart = new shopping_cartVO();
				shopping_cart.setGen_meb_no(rs.getInt("gen_meb_no"));			
				shopping_cart.setProduct_no(rs.getInt("product_no"));	
				shopping_cart.setCart_product_number(rs.getInt("cart_product_number"));	
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

		return shopping_cart;		
	}

	@Override
	public List<shopping_cartVO> getAllshopping_cart() {
		List<shopping_cartVO> shopping_cartList= new ArrayList<>();
		shopping_cartVO shopping_cart = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;				

		try {

			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				shopping_cart = new shopping_cartVO();
	
				
				shopping_cart.setGen_meb_no(rs.getInt("gen_meb_no"));			
				shopping_cart.setProduct_no(rs.getInt("product_no"));	
				shopping_cart.setCart_product_number(rs.getInt("cart_product_number"));					
				shopping_cartList.add(shopping_cart);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return shopping_cartList;
			}

}
