package com.generalMemberPetPhotos.model;

import java.util.List;

import com.adoptMemberReport.model.AdoptMemberReportVO;

public interface GeneralMemberPetPhotosDAO_Interface {
	public GeneralMemberPetPhotosVO insert(GeneralMemberPetPhotosVO gmppVO);
	public void update(GeneralMemberPetPhotosVO gmppVO);
	public void delete(Integer gmppno);
	public GeneralMemberPetPhotosVO findByPrimaryKey(Integer gmppno);
	public GeneralMemberPetPhotosVO findByPhotoCover(Integer gmppno);
	public List<GeneralMemberPetPhotosVO> findByGeneralMemberPetNo(Integer gen_meb_no);
	public List<GeneralMemberPetPhotosVO> getAll();
	
}
