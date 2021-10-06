package com.adoptMemberReport.model;

import java.sql.Date;
import java.util.List;

import com.generalMember.model.GeneralMemberVO;

public class AdoptMemberReportService {
	private AdoptMemberReportDAO_Interface dao;
	
	public AdoptMemberReportService() {
		dao = new AdoptMemberReportDAO();
	}
	
	public AdoptMemberReportVO addAdoptMemberReport(Integer adopt_meb_report_no,Integer adopt_meb_no,Integer gen_meb_no, String eport_comment,String adopt_meb_report_state,Date adopt_meb_report_date) {
		
		AdoptMemberReportVO amrVO = new AdoptMemberReportVO();
		
		amrVO.setAdopt_meb_report_no(adopt_meb_report_no);
		amrVO.setAdopt_meb_no(adopt_meb_no);
		amrVO.setGen_meb_no(gen_meb_no);
		amrVO.setEport_comment(eport_comment);
		amrVO.setAdopt_meb_report_state(adopt_meb_report_state);
		amrVO.setAdopt_meb_report_date(adopt_meb_report_date);
		
		dao.insert(amrVO);
		
		return amrVO;
	}
	
	//預留給 Struts 2 用的
		public void addAdoptMemberReport(AdoptMemberReportVO amrVO) {
			dao.insert(amrVO);
		}
		
		public AdoptMemberReportVO updateAdoptMemberReport(Integer adopt_meb_report_no,Integer adopt_meb_no,Integer gen_meb_no, String eport_comment,String adopt_meb_report_state,Date adopt_meb_report_date) {
			AdoptMemberReportVO amrVO = new AdoptMemberReportVO();
			
			amrVO.setAdopt_meb_report_no(adopt_meb_report_no);
			amrVO.setAdopt_meb_no(adopt_meb_no);
			amrVO.setGen_meb_no(gen_meb_no);
			amrVO.setEport_comment(eport_comment);
			amrVO.setAdopt_meb_report_state(adopt_meb_report_state);
			amrVO.setAdopt_meb_report_date(adopt_meb_report_date);
			
			dao.update(amrVO);
			
			return dao.findByPrimaryKey(adopt_meb_report_no); 
		}
		
		
		public void updateAdoptMemberReport(AdoptMemberReportVO amrVO) {
			dao.update(amrVO);
		}
		
		public void deleteAdoptMemberReport(Integer adopt_meb_report_no) {
			dao.delete(adopt_meb_report_no);
		}
		
		public AdoptMemberReportVO getOneAdoptMemberReport(Integer adopt_meb_report_no) {
			return dao.findByPrimaryKey(adopt_meb_report_no);
		}
		
		public List<AdoptMemberReportVO> getAll(){
			return dao.getAll();
		}
}
