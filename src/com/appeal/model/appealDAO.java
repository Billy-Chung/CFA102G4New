package com.appeal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class appealDAO implements appealDAO_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "insert into APPEAL(gen_meb_no, order_no, appeal_comment, appeal_state) values (?,?,?,?)";
	private static final String UPDATE_STMT = "update APPEAL set gen_meb_no=?, order_no=?, appeal_comment=?, appeal_state=? WHERE appeal_no=?";
	private static final String DELETE_STMT = "delete from APPEAL where appeal_no=?";
	private static final String FIND_BY_PK = "select * from APPEAL where appeal_no=?";
	private static final String GET_ALL = "select * from APPEAL";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}	
	
	@Override
	public void add(appealVO appealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
            
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);//新增
			int i=1;			
			pstmt.setInt(i++, appealVO.getGen_meb_no());
			pstmt.setInt(i++, appealVO.getOrder_no());
			pstmt.setString(i++, appealVO.getAppeal_comment());
			pstmt.setString(i++, appealVO.getAppeal_state());
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
	public void update(appealVO appealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);//修改

			int i=1;
			pstmt.setInt(i++, appealVO.getGen_meb_no());
			pstmt.setInt(i++, appealVO.getOrder_no());
			pstmt.setString(i++, appealVO.getAppeal_comment());
			pstmt.setString(i++, appealVO.getAppeal_state());			
			pstmt.setInt(i++, appealVO.getAppeal_no());//WHERE 條件			                                             
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
	public void delete(Integer appeal_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);//刪除
			pstmt.setInt(1, appeal_no);			
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
	public appealVO findAppealPk(Integer appeal_no) {
		appealVO appeal = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);//主鍵
			pstmt.setInt(1, appeal_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				appeal = new appealVO();
				appeal.setAppeal_no(rs.getInt("appeal_no"));
				appeal.setGen_meb_no(rs.getInt("gen_meb_no"));
				appeal.setOrder_no(rs.getInt("order_no"));
				appeal.setAppeal_comment(rs.getString("appeal_comment"));
				appeal.setAppeal_state(rs.getString("appeal_state"));				
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

		return appeal;		
	}

	@Override
	public List<appealVO> getAllappeal() {
		List<appealVO> appealList= new ArrayList<>();
		appealVO appeal= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;				

		try {

			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				appeal = new appealVO();
				appeal.setAppeal_no(rs.getInt("appeal_no"));
				appeal.setGen_meb_no(rs.getInt("gen_meb_no"));
				appeal.setOrder_no(rs.getInt("order_no"));
				appeal.setAppeal_comment(rs.getString("appeal_comment"));
				appeal.setAppeal_state(rs.getString("appeal_state"));				
				appealList.add(appeal);
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
		return appealList;
			}
}
