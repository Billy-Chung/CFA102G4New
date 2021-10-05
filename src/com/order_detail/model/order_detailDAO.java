package com.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.pay_method.model.pay_methodVO;

public class order_detailDAO implements order_detailDAO_interface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String INSERT_STMT = "insert into ORDER_DETAIL(order_no,product_no,order_product_number,product_price,product_name,product_pro_detail_no,promot_name) values (?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "update ORDER_DETAIL set order_no=?,product_no=?,order_product_number=?,product_price=?,product_name=?,product_pro_detail_no=?,promot_name=? WHERE order_detail_no=?";
	private static final String DELETE_STMT = "delete from ORDER_DETAIL where order_detail_no=?";
	private static final String FIND_BY_PK = "select * from ORDER_DETAIL where order_no=?";
	private static final String GET_ALL = "select * from ORDER_DETAIL";
	private static final String GET_DETAILLIST_BY_ORDERNO = "select * from ORDER_DETAIL where ORDER_NO = ?";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(OrderDetailVO order_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);// 新增
			int i = 1;
			pstmt.setInt(i++, order_detailVO.getOrder_no());
			pstmt.setInt(i++, order_detailVO.getProduct_no());
			pstmt.setInt(i++, order_detailVO.getOrder_product_number());
			pstmt.setInt(i++, order_detailVO.getProduct_price());
			pstmt.setString(i++, order_detailVO.getProduct_name());
			pstmt.setInt(i++, order_detailVO.getProduct_pro_detail_no());
			pstmt.setString(i++, order_detailVO.getPromot_name());
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
	public void update(OrderDetailVO order_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);// 修改

			int i = 1;
			pstmt.setInt(i++, order_detailVO.getOrder_no());
			pstmt.setInt(i++, order_detailVO.getProduct_no());
			pstmt.setInt(i++, order_detailVO.getOrder_product_number());
			pstmt.setInt(i++, order_detailVO.getProduct_price());
			pstmt.setString(i++, order_detailVO.getProduct_name());
			pstmt.setInt(i++, order_detailVO.getProduct_pro_detail_no());
			pstmt.setString(i++, order_detailVO.getPromot_name());
			pstmt.setInt(i++, order_detailVO.getOrder_detail_no());// WHERE 條件
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
	public void delete(Integer order_detail_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);// 刪除
			pstmt.setInt(1, order_detail_no);
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

	
	
//查詢一(訂單)對多(訂單明細)	
	@Override
	public OrderDetailVO findOrderDetailPk(Integer order_no) {
		OrderDetailVO order_detail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);// 主鍵
			pstmt.setInt(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detail = new OrderDetailVO();
				order_detail.setOrder_detail_no(rs.getInt("order_detail_no"));
				order_detail.setOrder_no(rs.getInt("order_no"));
				order_detail.setProduct_no(rs.getInt("product_no"));
				order_detail.setOrder_product_number(rs.getInt("order_product_number"));
				order_detail.setProduct_price(rs.getInt("product_price"));
				order_detail.setProduct_name(rs.getString("product_name"));
				order_detail.setProduct_pro_detail_no(rs.getInt("product_pro_detail_no"));
				order_detail.setPromot_name(rs.getString("promot_name"));
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

		return order_detail;
	}

	@Override
	public List<OrderDetailVO> getAllorder_detail() {
		List<OrderDetailVO> order_detailList = new ArrayList<>();
		OrderDetailVO order_detail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detail = new OrderDetailVO();
				order_detail.setOrder_detail_no(rs.getInt("order_detail_no"));
				order_detail.setOrder_no(rs.getInt("order_no"));
				order_detail.setProduct_no(rs.getInt("product_no"));
				order_detail.setOrder_product_number(rs.getInt("order_product_number"));
				order_detail.setProduct_price(rs.getInt("product_price"));
				order_detail.setProduct_name(rs.getString("product_name"));
				order_detail.setProduct_pro_detail_no(rs.getInt("product_pro_detail_no"));
				order_detail.setPromot_name(rs.getString("promot_name"));
				order_detailList.add(order_detail);
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
		return order_detailList;
	}

	@Override
	public void insert2 (OrderDetailVO order_detailVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);
     		int i = 1;
			pstmt.setInt(i++, order_detailVO.getOrder_no());
			pstmt.setInt(i++, order_detailVO.getProduct_no());
			pstmt.setInt(i++, order_detailVO.getOrder_product_number());
			pstmt.setInt(i++, order_detailVO.getProduct_price());
			pstmt.setString(i++, order_detailVO.getProduct_name());
			
			if(order_detailVO.getProduct_pro_detail_no() == null) {
				pstmt.setNull(i++, Types.NULL);
			}else {
				pstmt.setInt(i++, order_detailVO.getProduct_pro_detail_no());
			}
			
			pstmt.setString(i++, order_detailVO.getPromot_name());
						
			Statement stmt=	con.createStatement();
			//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();

			// Handle any SQL errors
			} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order_detail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
			} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public List<OrderDetailVO> findOrderDetailsByOrderFormNo(Integer orderFormNo) {
		List<OrderDetailVO> order_detailList = new ArrayList<>();
		OrderDetailVO order_detail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_DETAILLIST_BY_ORDERNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detail = new OrderDetailVO();
				order_detail.setOrder_detail_no(rs.getInt("order_detail_no"));
				order_detail.setOrder_no(rs.getInt("order_no"));
				order_detail.setProduct_no(rs.getInt("product_no"));
				order_detail.setOrder_product_number(rs.getInt("order_product_number"));
				order_detail.setProduct_price(rs.getInt("product_price"));
				order_detail.setProduct_name(rs.getString("product_name"));
				order_detail.setProduct_pro_detail_no(rs.getInt("product_pro_detail_no"));
				order_detail.setPromot_name(rs.getString("promot_name"));
				order_detailList.add(order_detail);
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
		return order_detailList;
	}
}
