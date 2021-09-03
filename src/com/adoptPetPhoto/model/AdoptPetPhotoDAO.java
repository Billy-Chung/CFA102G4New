package com.adoptPetPhoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class AdoptPetPhotoDAO implements AdoptPetPhoto_interface {

	private static final String INSERT_SQL = "insert into ADOPT_PET_PHOTO (ADOPT_PET_NO,ADOPT_PET_PHOTO,ADOPT_PET_COVER_STATE,ADOPT_PET_COVER_CHANGE_TIME) values(?,?,?,?)";
	private static final String UPDATE_SQL = "update ADOPT_PET_PHOTO set ADOPT_PET_COVER_STATE = ?, adopt_pet_cover_change_time = ? where ADOPT_PET_PHOTO_NO = ?";
	private static final String DELETE_SQL = "delete from ADOPT_PET_PHOTO where ADOPT_PET_PHOTO_NO = ?";
	private static final String FIND_BY_PET_NO = "SELECT * FROM ADOPT_PET_PHOTO WHERE ADOPT_PET_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ADOPT_PET_PHOTO WHERE ADOPT_PET_PHOTO_NO = ?";
	private static final String FIND_ADOPT_PET_COVER_STATE = "SELECT * FROM ADOPT_PET_PHOTO WHERE ADOPT_PET_NO = ? and ADOPT_PET_COVER_STATE = 1 order by adopt_pet_cover_change_time desc limit 1";

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
	public AdoptPetPhotoVO insert(AdoptPetPhotoVO adoptPetPhoto) {
		Connection con = null;
		try {
			con = ds.getConnection();
			String[] cols = { "ADOPT_PET_PHOTO_NO" };
			PreparedStatement pstmt = createInsertPreparedStatement(con, adoptPetPhoto, INSERT_SQL, cols);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int key = rs.getInt(1);
				adoptPetPhoto.setAdopt_pet_photo_no(key);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return adoptPetPhoto;
	}

	@Override
	public void update(AdoptPetPhotoVO adoptPetPhoto) {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = createUpdatePreparedStatement(con, adoptPetPhoto, UPDATE_SQL);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer adopt_pet_photo_no) {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = createDeletePreparedStatement(con, adopt_pet_photo_no, DELETE_SQL);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public AdoptPetPhotoVO findByPhotoCover(Integer adopt_pet_photo_no) {
		Connection con = null;
		AdoptPetPhotoVO photoCover = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_ADOPT_PET_COVER_STATE);
			pstmt.setInt(1, adopt_pet_photo_no);
			ResultSet rs = pstmt.executeQuery();
			photoCover = selectPhotoCover(rs);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return photoCover;
	}

	@Override
	public AdoptPetPhotoVO findByPK(Integer adopt_pet_photo_no) {
		Connection con = null;
		AdoptPetPhotoVO photo = null;
		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, adopt_pet_photo_no);
			ResultSet rs = pstmt.executeQuery();
			photo = selectOneByPK(rs);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return photo;
	}

	@Override
	public List<AdoptPetPhotoVO> findByadoptPetNo(Integer adopt_pet_no) {
		Connection con = null;
		List<AdoptPetPhotoVO> adoptMemberPhotoList = new ArrayList<>();

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_PET_NO);
			pstmt.setInt(1, adopt_pet_no);
			ResultSet rs = pstmt.executeQuery();
			adoptMemberPhotoList = selectAdoptMebPhotos(adoptMemberPhotoList, rs);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return adoptMemberPhotoList;
	}

	private PreparedStatement createInsertPreparedStatement(Connection con, AdoptPetPhotoVO adoptPetPhoto, String SQL,
			String[] cols) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL, cols);
		pstmt.setInt(1, adoptPetPhoto.getAdopt_pet_no());
		pstmt.setBytes(2, adoptPetPhoto.getAdopt_pet_photo());
		pstmt.setString(3, adoptPetPhoto.getAdopt_pet_cover_state());
		pstmt.setTimestamp(4, adoptPetPhoto.getAdopt_pet_cover_change_time());
		return pstmt;
	}

	private PreparedStatement createUpdatePreparedStatement(Connection con, AdoptPetPhotoVO adoptPetPhoto, String SQL)
			throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, adoptPetPhoto.getAdopt_pet_cover_state());
		pstmt.setTimestamp(2, adoptPetPhoto.getAdopt_pet_cover_change_time());
		pstmt.setInt(3, adoptPetPhoto.getAdopt_pet_photo_no());
		return pstmt;
	}

	private PreparedStatement createDeletePreparedStatement(Connection con, Integer adopt_pet_photo_no, String SQL)
			throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		pstmt.setInt(1, adopt_pet_photo_no);
		return pstmt;
	}

	private List<AdoptPetPhotoVO> selectAdoptMebPhotos(List<AdoptPetPhotoVO> adoptMemberPhotoList, ResultSet rs) {

		try {
			while (rs.next()) {
				AdoptPetPhotoVO adoptPetPhoto = new AdoptPetPhotoVO();
				adoptPetPhoto.setAdopt_pet_photo_no(rs.getInt("ADOPT_PET_PHOTO_NO"));
				adoptPetPhoto.setAdopt_pet_no(rs.getInt("ADOPT_PET_NO"));
				adoptPetPhoto.setAdopt_pet_photo(rs.getBytes("ADOPT_PET_PHOTO"));
				adoptPetPhoto.setAdopt_pet_cover_state(rs.getString("ADOPT_PET_COVER_STATE"));
				adoptPetPhoto.setAdopt_pet_cover_change_time(rs.getTimestamp("ADOPT_PET_COVER_CHANGE_TIME"));
				adoptMemberPhotoList.add(adoptPetPhoto);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return adoptMemberPhotoList;
	}

	private AdoptPetPhotoVO selectPhotoCover(ResultSet rs) {
		AdoptPetPhotoVO photoCover = new AdoptPetPhotoVO();
		try {
			while (rs.next()) {
				photoCover.setAdopt_pet_photo_no(rs.getInt("ADOPT_PET_PHOTO_NO"));
				photoCover.setAdopt_pet_no(rs.getInt("ADOPT_PET_NO"));
				photoCover.setAdopt_pet_photo(rs.getBytes("ADOPT_PET_PHOTO"));
				photoCover.setAdopt_pet_cover_state(rs.getString("ADOPT_PET_COVER_STATE"));
				photoCover.setAdopt_pet_cover_change_time(rs.getTimestamp("ADOPT_PET_COVER_CHANGE_TIME"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return photoCover;
	}

	private AdoptPetPhotoVO selectOneByPK(ResultSet rs) {
		AdoptPetPhotoVO photo = new AdoptPetPhotoVO();
		try {
			while (rs.next()) {
				photo.setAdopt_pet_photo_no(rs.getInt("ADOPT_PET_PHOTO_NO"));
				photo.setAdopt_pet_no(rs.getInt("ADOPT_PET_NO"));
				photo.setAdopt_pet_photo(rs.getBytes("ADOPT_PET_PHOTO"));
				photo.setAdopt_pet_cover_state(rs.getString("ADOPT_PET_COVER_STATE"));
				photo.setAdopt_pet_cover_change_time(rs.getTimestamp("ADOPT_PET_COVER_CHANGE_TIME"));

			}
		} catch (SQLException e) {
			throw new RuntimeException("A database method error occured. " + e.getMessage());
		}
		return photo;
	}

}
