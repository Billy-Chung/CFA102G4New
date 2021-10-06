package com.generalMemberPet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.adoptMemberReport.model.AdoptMemberReportVO;
import com.petClassList.model.PetClassListService;

public class GeneralMemberPetDAO implements GeneralMemberPetDAO_Interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA_102_04?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_SQL ="INSERT INTO GENERAL_MEMBER_PET(ADOPT_PET_NO,GEN_MEB_NO,GEN_MEB_PET_BREEDS,GEN_MEB_PET_GENDER,GEN_MEB_PET_CHIP,GEN_MEB_PET_STERILIZATION,GEN_MEB_PET_COLOR,GEN_PET_COMMENT,GEN_PET_STATE) VALUES (?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_SQL ="UPDATE GENERAL_MEMBER_PET SET ADOPT_PET_NO=?,GEN_MEB_PET_BREEDS=?,GEN_MEB_PET_GENDER=?,GEN_MEB_PET_CHIP=?,GEN_MEB_PET_STERILIZATION=?,GEN_MEB_PET_COLOR=?,GEN_PET_COMMENT=?,GEN_PET_STATE=? WHERE GEN_MEB_PET_NO=?";
	public static final String DELETE_SQL ="DELETE FROM GENERAL_MEMBER_PET WHERE GEN_MEB_PET_NO=?";
	public static final String FIND_BY_DEPTNO_SQL = "SELECT * FROM GENERAL_MEMBER_PET WHERE GEN_MEB_PET_NO = ?";
	public static final String GET_ALL_SQL = "SELECT * FROM GENERAL_MEMBER_PET";
	public static final String FIND_BY_PK = "SELECT * FROM GENERAL_MEMBER_PET WHERE GEN_MEB_PET_NO = ?";
	public static final String FIND_BY_FK = "SELECT * FROM GENERAL_MEMBER_PET WHERE ADOPT_PET_NO = ?";
	public static final String FIND_BY_FK2 = "SELECT * FROM GENERAL_MEMBER_PET WHERE GEN_MEB_NO = ?";
	
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
	public  GeneralMemberPetVO insert(GeneralMemberPetVO gmp , String[] petClassNoBox) {
		Connection conn = null;
		
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			String[] cols = {"GEN_MEB_PET_NO"};
			PreparedStatement pst = createInsertPreparedStatement(conn,gmp,INSERT_SQL,cols);
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()) {
				int key = rs.getInt(1);
				gmp.setGen_meb_pet_no(key);
			}
			
			PetClassListService petClassListSvc = new PetClassListService();
			for(String petClassNo : petClassNoBox) {
				Integer pNo = new Integer(petClassNo);
				petClassListSvc.insertPetClassListGen(null, pNo, gmp.getGen_meb_pet_no(), "1", conn);
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException se) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch(SQLException excep) {
					throw new RuntimeException("rollback error occured :" + excep.getMessage());
				}
			}
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}	
			}
			
		}
		
		return gmp;
		
	}

	@Override
	public void update(GeneralMemberPetVO gmp) {
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			PreparedStatement pst = createUpdatePreparedStatement(conn,gmp,UPDATE_SQL);
			pst.executeUpdate();
		}catch(SQLException se) {
			throw new RuntimeException("A datebase error occured :" + se.getMessage());
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
	
	
	public GeneralMemberPetVO findByGeneralMemberPetNoPK(Integer ger_meb_pet_no) {
		Connection conn = null;
		GeneralMemberPetVO gmp = null;
		try {
			conn = ds.getConnection();
			PreparedStatement pst = conn.prepareStatement(FIND_BY_PK);
			pst.setInt(1,ger_meb_pet_no);
			ResultSet rs = pst.executeQuery();
			gmp = selectOneGeneralMemberPetByNo(rs);
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured :" +se.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
				
			}
		}
		return gmp;	
		
	}
	
	public List<GeneralMemberPetVO> findByGeneralMemberPetNo(Integer adopt_pet_no) {
		Connection conn = null;
		List<GeneralMemberPetVO> gmpList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			PreparedStatement pst = conn.prepareStatement(FIND_BY_FK);
			pst.setInt(1,adopt_pet_no);
			ResultSet rs = pst.executeQuery();
			gmpList = selectGeneralMemberPetByMebNo(gmpList,rs);
		} catch(SQLException se) {
			throw new RuntimeException("A datebase error occured :" +se.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return gmpList;
	}
	
	public List<GeneralMemberPetVO> findByGeneralMemberNo(Integer gen_meb_no) {
		Connection conn = null;
		List<GeneralMemberPetVO> gmpList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			PreparedStatement pst = conn.prepareStatement(FIND_BY_FK2);
			pst.setInt(1,gen_meb_no);
			ResultSet rs = pst.executeQuery();
			gmpList = selectGeneralMemberPetByMebNo(gmpList,rs);
		} catch(SQLException se) {
			throw new RuntimeException("A datebase error occured :" +se.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return gmpList;
	}

	@Override
	public void delete(Integer gmpno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setInt(1,gmpno);
			
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
	public GeneralMemberPetVO findByPrimaryKey(Integer gmpno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GeneralMemberPetVO gmpVO = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_DEPTNO_SQL);
			pstmt.setInt(1,gmpno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			//�脖�誨銵冽��鞈��,撠梁銝��ept��ean靘���閰Ｗ靘���鞈��
				gmpVO = new GeneralMemberPetVO();
				gmpVO.setGen_meb_pet_no(gmpno);
				gmpVO.setAdopt_pet_no(rs.getInt("ADOPT_PET_NO"));
				gmpVO.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmpVO.setGen_meb_pet_breeds(rs.getString("GEN_MEB_PET_BREEDS"));
				gmpVO.setGen_meb_pet_gender(rs.getString("GEN_MEB_PET_GENDER"));
				gmpVO.setGen_meb_pet_chip(rs.getString("GEN_MEB_PET_CHIP"));
				gmpVO.setGen_meb_pet_sterilization(rs.getString("GEN_MEB_PET_STERILIZATION"));
				gmpVO.setGen_pet_color(rs.getString("GEN_MEB_PET_COLOR"));
				gmpVO.setGen_pet_comment(rs.getString("GEN_PET_COMMENT"));
				gmpVO.setGen_pet_state(rs.getString("GEN_PET_STATE"));
				
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
		
		return gmpVO;
	}

	@Override
	public List<GeneralMemberPetVO> getAll() {
		Connection conn = null;
		List<GeneralMemberPetVO> gmpList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			PreparedStatement pst = conn.prepareStatement(GET_ALL_SQL);
			ResultSet rs = pst.executeQuery();
			gmpList = selectAllGeneralMemberPet(gmpList,rs);

		} catch(SQLException se) {
			throw new RuntimeException("A database error occured :" +se.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		
		}
		
		
		return gmpList;
	
	}
	
	private List<GeneralMemberPetVO> selectAllGeneralMemberPet(List<GeneralMemberPetVO> gmpList,ResultSet rs) {
		
		try {
			while(rs.next()) {
				GeneralMemberPetVO gmp = new GeneralMemberPetVO();
				gmp.setGen_meb_pet_no(rs.getInt("GEN_MEB_PET_NO"));
				gmp.setAdopt_pet_no(rs.getInt("ADOPT_PET_NO"));
				gmp.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmp.setGen_meb_pet_breeds(rs.getString("GEN_MEB_PET_BREEDS"));
				gmp.setGen_meb_pet_gender(rs.getString("GEN_MEB_PET_GENDER"));
				gmp.setGen_meb_pet_chip(rs.getString("GEN_MEB_PET_CHIP"));
				gmp.setGen_meb_pet_sterilization(rs.getString("GEN_MEB_PET_STERILIZATION"));
				gmp.setGen_pet_color(rs.getString("GEN_MEB_PET_COLOR"));
				gmp.setGen_pet_comment(rs.getString("GEN_PET_COMMENT"));
				gmp.setGen_pet_state(rs.getString("GEN_PET_STATE"));
				gmpList.add(gmp);	
			}
			
		} catch(SQLException e) {
			throw new RuntimeException("A database method error occured :" + e.getMessage());
		}
		
		return gmpList;
	}
	
	private PreparedStatement createInsertPreparedStatement(Connection conn,GeneralMemberPetVO gmp,String SQL,String[] cols) throws SQLException {
		
		PreparedStatement pst = conn.prepareStatement(SQL,cols);
		
		if(gmp.getAdopt_pet_no()==0) {
			pst.setNull(1, Types.NULL);
		} else {
			pst.setInt(1,gmp.getAdopt_pet_no());
		}
		
		pst.setInt(2,gmp.getGen_meb_no());
		
		pst.setString(3, gmp.getGen_meb_pet_breeds());
		pst.setString(4, gmp.getGen_meb_pet_gender());
		pst.setString(5, gmp.getGen_meb_pet_chip());
		pst.setString(6, gmp.getGen_meb_pet_sterilization());
		pst.setString(7, gmp.getGen_pet_color());
		pst.setString(8, gmp.getGen_pet_comment());
		pst.setString(9, gmp.getGen_pet_state());
		return pst;
	}
	
	private PreparedStatement createUpdatePreparedStatement(Connection conn , GeneralMemberPetVO gmp,String SQL) throws SQLException {
		
		PreparedStatement pst = conn.prepareStatement(SQL);
		if(gmp.getAdopt_pet_no()==0) {
			pst.setNull(1, Types.NULL);
		} else {
			pst.setInt(1,gmp.getAdopt_pet_no());
		}
		pst.setString(2, gmp.getGen_meb_pet_breeds());
		pst.setString(3, gmp.getGen_meb_pet_gender());
		pst.setString(4, gmp.getGen_meb_pet_chip());
		pst.setString(5, gmp.getGen_meb_pet_sterilization());
		pst.setString(6, gmp.getGen_pet_color());
		pst.setString(7, gmp.getGen_pet_comment());
		pst.setString(8, gmp.getGen_pet_state());
		pst.setInt(9, gmp.getGen_meb_pet_no());
		return pst;
	}
	
	
	private GeneralMemberPetVO selectOneGeneralMemberPetByNo(ResultSet rs) {
		GeneralMemberPetVO gmp = new GeneralMemberPetVO();
		try {
			while(rs.next()) {
				gmp.setGen_meb_pet_no(rs.getInt("GEN_MEB_PET_NO"));
				gmp.setAdopt_pet_no(rs.getInt("ADOPT_PET_NO"));
				gmp.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmp.setGen_meb_pet_breeds(rs.getString("GEN_MEB_PET_BREEDS"));
				gmp.setGen_meb_pet_gender(rs.getString("GEN_MEB_PET_GENDER"));
				gmp.setGen_meb_pet_chip(rs.getString("GEN_MEB_PET_CHIP"));
				gmp.setGen_meb_pet_sterilization(rs.getString("GEN_MEB_PET_STERILIZATION"));
				gmp.setGen_pet_color(rs.getString("GEN_MEB_PET_COLOR"));
				gmp.setGen_pet_comment(rs.getString("GEN_PET_COMMENT"));
				gmp.setGen_pet_state(rs.getString("GEN_PET_STATE"));
				
			}		
		} catch(SQLException e) {
				throw new RuntimeException("A database mehtod error occured :" +e.getMessage());
		}
			
			return gmp;
		}
	
	private List<GeneralMemberPetVO> selectGeneralMemberPetByMebNo(List<GeneralMemberPetVO> gmpList,ResultSet rs) {
		
		try {
			while(rs.next()) {
				GeneralMemberPetVO gmp = new GeneralMemberPetVO();
				gmp.setGen_meb_pet_no(rs.getInt("GEN_MEB_PET_NO"));
				gmp.setAdopt_pet_no(rs.getInt("ADOPT_PET_NO"));
				gmp.setGen_meb_no(rs.getInt("GEN_MEB_NO"));
				gmp.setGen_meb_pet_breeds(rs.getString("GEN_MEB_PET_BREEDS"));
				gmp.setGen_meb_pet_gender(rs.getString("GEN_MEB_PET_GENDER"));
				gmp.setGen_meb_pet_chip(rs.getString("GEN_MEB_PET_CHIP"));
				gmp.setGen_meb_pet_sterilization(rs.getString("GEN_MEB_PET_STERILIZATION"));
				gmp.setGen_pet_color(rs.getString("GEN_MEB_PET_COLOR"));
				gmp.setGen_pet_comment(rs.getString("GEN_PET_COMMENT"));
				gmp.setGen_pet_state(rs.getString("GEN_PET_STATE"));
				gmpList.add(gmp);
			}
			
		} catch(SQLException e) {
			throw new RuntimeException("A database method error occured :" +e.getMessage());
		}
		return gmpList;
	}

	

	
}
