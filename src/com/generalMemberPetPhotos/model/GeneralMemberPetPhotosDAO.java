package com.generalMemberPetPhotos.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.adoptMemberReport.model.AdoptMemberReportVO;
import com.adoptPetPhoto.model.AdoptPetPhotoVO;

public class GeneralMemberPetPhotosDAO implements GeneralMemberPetPhotosDAO_Interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_SQL ="INSERT INTO GENERAL_MEMBER_PET_PHOTOS (GEN_MEB_NO,GEN_MEB_PET_PHOTO,GEN_PET_COVER_STATE,GEN_PET_COVER_CHANGE_TIME) VALUES (?, ?,?,?)";
	public static final String UPDATE_SQL ="UPDATE GENERAL_MEMBER_PET_PHOTOS SET GEN_PET_COVER_STATE=?,GEN_PET_COVER_CHANGE_TIME=? WHERE GEN_MEB_PET_PHOTO_NO=?";
	public static final String DELETE_SQL ="DELETE FROM GENERAL_MEMBER_PET_PHOTOS WHERE GEN_MEB_PET_PHOTO_NO=?";
	public static final String FIND_BY_PET_SQL = "SELECT * FROM GENERAL_MEMBER_PET_PHOTOS WHERE GEN_MEB_NO = ?";
	public static final String FIND_BY_PK ="SELECT * FROM GENERAL_MEMBER_PET_PHOTOS WHERE GEN_MEB_PET_PHOTO_NO = ?";
	public static final String GET_ALL_SQL = "SELECT * FROM GENERAL_MEMBER_PET_PHOTOS";
	private static final String FIND_GEN_PET_COVER_STATE = "SELECT * FROM GENERAL_MEMBER_PET_PHOTOS WHERE GEN_MEB_NO = ? and GEN_PET_COVER_STATE = 1 order by gen_pet_cover_change_time desc limit 1";
	
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
	public GeneralMemberPetPhotosVO insert(GeneralMemberPetPhotosVO gmppVO) {
		Connection conn = null;
		
		
		try {
			conn = ds.getConnection();
			String[] cols = {"GEN_MEB_PET_PHOTO_NO"};
			PreparedStatement pst = createInsertPreparedStatement(conn,gmppVO,INSERT_SQL,cols);
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()) {
				int key = rs.getInt(1);
				gmppVO.setGen_meb_photo_no(key);
			}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured :" +se.getMessage());
		} finally {
			if(conn!=null) {
				try {
					conn.close();
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
		return gmppVO;
	}

	@Override
	public void update(GeneralMemberPetPhotosVO gmppVO) {
		Connection conn = null;
		
		
		try {
			conn = ds.getConnection();
			PreparedStatement pst = createUpdatePreparedStatement(conn,gmppVO,UPDATE_SQL);
			pst.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured :" + se.getMessage());
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}	
			}
			
			
		}
		
	}

	@Override
	public void delete(Integer gmppno) {
		Connection conn = null;
		
		
		try {
			conn = ds.getConnection();
			PreparedStatement pst = createDeletePreparedStatement(conn,gmppno,DELETE_SQL);
			pst.executeUpdate();
			
		}catch(SQLException e) {
			throw new RuntimeException("A database error occured :"+e.getMessage());
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
			}
			}
			
		}
		
	}
	

	@Override
	public GeneralMemberPetPhotosVO findByPrimaryKey(Integer gmppno) {
		Connection con = null;
		GeneralMemberPetPhotosVO gmppVO = null;
		
		try {
			con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(FIND_BY_PK);
			pst.setInt(1, gmppno);
			ResultSet rs = pst.executeQuery();
			gmppVO = selectOneByPK(rs);
			
			
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occures :" +se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}	
		}
		
		return gmppVO;
	}

	@Override
	public List<GeneralMemberPetPhotosVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <GeneralMemberPetPhotosVO> gmppList = new ArrayList<>();
		
		try {
			
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			//�脖�誨銵冽��鞈��,撠梁銝��ept��ean靘���閰Ｗ靘���鞈��
				GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
				gmppVO.setGen_meb_photo_no(rs.getInt("GEN_MEB_PET_PHOTO_NO"));
				gmppVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmppVO.setGen_meb_pet_photo(rs.getBytes("GEN_MEB_PET_PHOTO"));
				
				gmppList.add(gmppVO);
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
		
		
		return gmppList;
	}

	



	@Override
	public List<GeneralMemberPetPhotosVO> findByGeneralMemberPetNo(Integer gen_meb_pet_no) {
		Connection con  = null;
		List<GeneralMemberPetPhotosVO> gmppList = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(FIND_BY_PET_SQL);
			pst.setInt(1,gen_meb_pet_no);
			ResultSet rs = pst.executeQuery();
			gmppList = selectGeneralMemberPetPhotos(gmppList,rs);
		} catch(SQLException se) {
			throw new RuntimeException("A database error orccured :" +se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return gmppList;
	}
	
	private PreparedStatement createInsertPreparedStatement(Connection con,GeneralMemberPetPhotosVO gmppVO,String SQL,String[] cols) throws SQLException {
		PreparedStatement pst = con.prepareStatement(SQL,cols);
		pst.setInt(1,gmppVO.getGen_meb_no());
		pst.setBytes(2,gmppVO.getGen_meb_pet_photo());
		pst.setString(3, gmppVO.getGen_pet_cover_state());
		pst.setTimestamp(4, gmppVO.getGen_pet_cover_change_time());
		return pst;
	}
	
	private PreparedStatement createUpdatePreparedStatement(Connection con, GeneralMemberPetPhotosVO gmppVO, String SQL)
			throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, gmppVO.getGen_pet_cover_state());
		pstmt.setTimestamp(2, gmppVO.getGen_pet_cover_change_time());
		pstmt.setInt(3, gmppVO.getGen_meb_photo_no());
		return pstmt;
	}
	
	private PreparedStatement createDeletePreparedStatement(Connection con, Integer gmppno, String SQL)
			throws SQLException {
		PreparedStatement pst = con.prepareStatement(SQL);
		pst.setInt(1, gmppno);
		return pst;
	}
	
	private List<GeneralMemberPetPhotosVO> selectGeneralMemberPetPhotos(List<GeneralMemberPetPhotosVO> gmppList,ResultSet rs) {
		try {
			while(rs.next()) {
				GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
				gmppVO.setGen_meb_photo_no(rs.getInt("GEN_MEB_PET_PHOTO_NO"));
				gmppVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmppVO.setGen_pet_cover_state(rs.getString("GEN_PET_COVER_STATE"));
				gmppVO.setGen_pet_cover_change_time(rs.getTimestamp("GEN_PET_COVER_CHANGE_TIME"));
				gmppList.add(gmppVO);
			}
		} catch(SQLException e) {
			throw new RuntimeException("A database method error occured :" +e.getMessage());
		}
		return gmppList;
	}
	
	private GeneralMemberPetPhotosVO selectOneByPK(ResultSet rs) {
		GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
		try {
			while(rs.next()) {
				gmppVO.setGen_meb_photo_no(rs.getInt("GEN_MEB_PET_PHOTO_NO"));
				gmppVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmppVO.setGen_meb_pet_photo(rs.getBytes("GEN_MEB_PET_PHOTO"));
				gmppVO.setGen_pet_cover_state(rs.getString("GEN_PET_COVER_STATE"));
				gmppVO.setGen_pet_cover_change_time(rs.getTimestamp("GEN_PET_COVER_CHANGE_TIME"));
			}
		} catch(SQLException e) {
			throw new RuntimeException("A database method error occured :" + e.getMessage());
		}
		return gmppVO;
	}

	@Override
	public GeneralMemberPetPhotosVO findByPhotoCover(Integer gmppno) {
		Connection con = null;
		GeneralMemberPetPhotosVO gmppVO = null;
		try {
			con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(FIND_GEN_PET_COVER_STATE);
			pst.setInt(1,gmppno);
			ResultSet rs = pst.executeQuery();
			gmppVO = selectPhotoCover(rs);
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured :" +se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return gmppVO;
	}
	
	private GeneralMemberPetPhotosVO selectPhotoCover(ResultSet rs) {
		GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
		try {
			while (rs.next()) {
				gmppVO.setGen_meb_photo_no(rs.getInt("GEN_MEB_PET_PHOTO_NO"));
				gmppVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmppVO.setGen_meb_pet_photo(rs.getBytes("GEN_MEB_PET_PHOTO"));
				gmppVO.setGen_pet_cover_state(rs.getString("GEN_PET_COVER_STATE"));
				gmppVO.setGen_pet_cover_change_time(rs.getTimestamp("GEN_PET_COVER_CHANGE_TIME"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return gmppVO;
	}
	
	
}
