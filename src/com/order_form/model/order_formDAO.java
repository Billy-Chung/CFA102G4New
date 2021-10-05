package com.order_form.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.order_detail.model.order_detailDAO;
import com.order_detail.model.OrderDetailVO;


public class Order_formDAO implements Order_formDAO_interface {

	private static final String INSERT_STMT = "insert into ORDER_FORM(gen_meb_no, pay_no, logistics_no, promot_no, befort_amount, order_amount, order_name, order_phone, delivery_address, order_time, order_status) values (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
//	gen_meb_no,
	private static final String UPDATE_STMT = "update ORDER_FORM set gen_meb_no=?, pay_no=?, logistics_no=?, promot_no=?, befort_amount=?, order_amount=?, order_name=?, order_phone=?, delivery_address=?, order_time=?, order_status=? WHERE order_no =?";
//  gen_meb_no=?, 
	private static final String DELETE_STMT = "delete from ORDER_FORM where order_no=?";
	private static final String FIND_BY_PK = "select * from ORDER_FORM where order_no=?";
	private static final String GET_ALL = "select * from ORDER_FORM order by ORDER_NO desc";
	private static final String GET_ORDERLIST_BY_MEMBERNO = "SELECT * FROM ORDER_FORM where GEN_MEB_NO = ?";  

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// Util.DRIVER
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(OrderFormVO order_form) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(INSERT_STMT);// 新增
			int i = 1;
			pstmt.setInt(i++, order_form.getGen_meb_no());
			pstmt.setInt(i++, order_form.getPay_no());
			pstmt.setInt(i++, order_form.getLogistics_no());
			pstmt.setInt(i++, order_form.getPromot_no());
			pstmt.setInt(i++, order_form.getBefort_amount());
			pstmt.setInt(i++, order_form.getOrder_amount());
			pstmt.setString(i++, order_form.getOrder_name());
			pstmt.setString(i++, order_form.getOrder_phone());
			pstmt.setString(i++, order_form.getDelivery_address());
			pstmt.setTimestamp(i++, order_form.getOrder_time());
			pstmt.setString(i++, order_form.getOrder_status());
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
	public void update(OrderFormVO order_form) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(UPDATE_STMT);// 修改

			int i = 1;
			pstmt.setInt(i++, order_form.getGen_meb_no());
			pstmt.setInt(i++, order_form.getPay_no());
			pstmt.setInt(i++, order_form.getLogistics_no());
			pstmt.setInt(i++, order_form.getPromot_no());
			pstmt.setInt(i++, order_form.getBefort_amount());
			pstmt.setInt(i++, order_form.getOrder_amount());
			pstmt.setString(i++, order_form.getOrder_name());
			pstmt.setString(i++, order_form.getOrder_phone());
			pstmt.setString(i++, order_form.getDelivery_address());
			pstmt.setTimestamp(i++, order_form.getOrder_time());
			pstmt.setString(i++, order_form.getOrder_status());
			pstmt.setInt(i++, order_form.getOrder_no());// WHERE 條件
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
	public void delete(Integer order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(DELETE_STMT);// 刪除
			pstmt.setInt(1, order_no);
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
	public OrderFormVO findOrderFormPk(Integer order_no) {
		OrderFormVO order_form = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(FIND_BY_PK);// 主鍵
			pstmt.setInt(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_form = new OrderFormVO();
				order_form.setOrder_no(rs.getInt("order_no"));
				order_form.setGen_meb_no(rs.getInt("gen_meb_no"));
				order_form.setPay_no(rs.getInt("pay_no"));
				order_form.setLogistics_no(rs.getInt("logistics_no"));
				order_form.setPromot_no(rs.getInt("promot_no"));
				order_form.setBefort_amount(rs.getInt("befort_amount"));
				order_form.setOrder_amount(rs.getInt("order_amount"));
				order_form.setOrder_name(rs.getString("order_name"));
				order_form.setOrder_phone(rs.getString("order_phone"));
				order_form.setDelivery_address(rs.getString("delivery_address"));
				order_form.setOrder_time(rs.getTimestamp("order_time"));
				order_form.setOrder_status(rs.getString("order_status"));
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
		return order_form;
	}

	@Override
	public List<OrderFormVO> getAllorderForm() {
		List<OrderFormVO> order_formList = new ArrayList<>();
		OrderFormVO order_form = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_form = new OrderFormVO();
				order_form.setOrder_no(rs.getInt("order_no"));
				order_form.setGen_meb_no(rs.getInt("gen_meb_no"));
				order_form.setPay_no(rs.getInt("pay_no"));
				order_form.setLogistics_no(rs.getInt("logistics_no"));
				order_form.setPromot_no(rs.getInt("promot_no"));
				order_form.setBefort_amount(rs.getInt("befort_amount"));
				order_form.setOrder_amount(rs.getInt("order_amount"));
				order_form.setOrder_name(rs.getString("order_name"));
				order_form.setOrder_phone(rs.getString("order_phone"));
				order_form.setDelivery_address(rs.getString("delivery_address"));
				order_form.setOrder_time(rs.getTimestamp("order_time"));
				order_form.setOrder_status(rs.getString("order_status"));
				order_formList.add(order_form);
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
		return order_formList;
	}

	@Override
	public Integer insertWithorder_detail(OrderFormVO order_formVO, List<OrderDetailVO> list) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			con.setAutoCommit(false);
			// 先新增訂單order_form
			String cols[] = { "ORDER_NO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			int i = 1;
			if (order_formVO.getGen_meb_no() == null) {
				pstmt.setNull(i++, Types.NULL);
			} else {
				pstmt.setInt(i++, order_formVO.getGen_meb_no());
			}

			if (order_formVO.getPay_no() == null) {
				pstmt.setNull(i++, Types.NULL);
			} else {
				pstmt.setInt(i++, order_formVO.getPay_no());
			}

			if (order_formVO.getLogistics_no() == null) {
				pstmt.setNull(i++, Types.NULL);
			} else {
				pstmt.setInt(i++, order_formVO.getLogistics_no());
			}

			if (order_formVO.getPromot_no() == null) {
				pstmt.setNull(i++, Types.NULL);
			} else {
				pstmt.setInt(i++, order_formVO.getPromot_no());
			}

			if (order_formVO.getBefort_amount() == null) {
				pstmt.setNull(i++, Types.NULL);
			} else {
				pstmt.setInt(i++, order_formVO.getBefort_amount());
			}
			pstmt.setInt(i++, order_formVO.getOrder_amount());

			pstmt.setString(i++, order_formVO.getOrder_name());
			pstmt.setString(i++, order_formVO.getOrder_phone());
			pstmt.setString(i++, order_formVO.getDelivery_address());

//		if(order_formVO.getOrder_time() == null) {
//			pstmt.setNull(i++, Types.NULL);
//		}else {
//			pstmt.setDate(i++, order_formVO.getOrder_time());
//		}

			pstmt.setTimestamp(i++, order_formVO.getOrder_time());

			if (order_formVO.getOrder_status() == null) {
				pstmt.setNull(i++, Types.NULL);
			} else {
				pstmt.setString(i++, order_formVO.getOrder_status());
			}

			Statement stmt = con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1;"); // 自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增

			pstmt.executeUpdate();
			// 掘取對應的自增主鍵值
			Integer next_order_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_order_no = rs.getInt(1);
				System.out.println("自增主鍵值= " + next_order_no + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			// 再同時新增訂單明細order_detail
			order_detailDAO dao = new order_detailDAO();
			System.out.println("list.size()-A=" + list.size());
			for (OrderDetailVO aorder_detail : list) {
				aorder_detail.setOrder_no(new Integer(next_order_no));
				dao.insert2(aorder_detail, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新增訂單編號" + next_order_no + "時,共有訂單明細" + list.size() + "筆同時被新增");
			return next_order_no;
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error. " + se.getMessage());
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
	public List<OrderFormVO> getOrderListByMemNo(Integer memNo) {
		List<OrderFormVO> order_formList = new ArrayList<>();
		OrderFormVO order_form = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei",
					"David", "123456");
			pstmt = con.prepareStatement(GET_ORDERLIST_BY_MEMBERNO);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_form = new OrderFormVO();
				order_form.setOrder_no(rs.getInt("order_no"));
				order_form.setGen_meb_no(rs.getInt("gen_meb_no"));
				order_form.setPay_no(rs.getInt("pay_no"));
				order_form.setLogistics_no(rs.getInt("logistics_no"));
				order_form.setPromot_no(rs.getInt("promot_no"));
				order_form.setBefort_amount(rs.getInt("befort_amount"));
				order_form.setOrder_amount(rs.getInt("order_amount"));
				order_form.setOrder_name(rs.getString("order_name"));
				order_form.setOrder_phone(rs.getString("order_phone"));
				order_form.setDelivery_address(rs.getString("delivery_address"));
				order_form.setOrder_time(rs.getTimestamp("order_time"));
				order_form.setOrder_status(rs.getString("order_status"));
				order_formList.add(order_form);
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
		return order_formList;
	}
}
