package com.generalMember.model;

import java.sql.Date;
import java.util.List;

public class GeneralMemberService {
	private GeneralMemberDAO_Interface dao;
	
	public GeneralMemberService() {
		dao = new GeneralMemberDAO();
	}

	public GeneralMemberVO addGeneralMember(String meb_name,String phone,Date birthday,byte[] photo,String comment,String address,String email,String account,String password,String gender,Integer meb_money,String post_permission) 
	{
		GeneralMemberVO gmVO = new GeneralMemberVO();
		
		gmVO.setMeb_name(meb_name);
		gmVO.setPhone(phone);
		gmVO.setBirthday(birthday);
		gmVO.setPhoto(photo);
		gmVO.setComment(comment);
		gmVO.setAddress(address);
		gmVO.setEmail(email);
		gmVO.setAccount(account);
		gmVO.setPassword(password);
		gmVO.setGender(gender);
		gmVO.setMeb_money(meb_money);
		gmVO.setPost_permission(post_permission);
		
		dao.insert(gmVO);
		
		return gmVO;
		
	}
	
	//預留給 Struts 2 用的
	public void addGeneralMember(GeneralMemberVO gmVO) {
		dao.insert(gmVO);
	}
	
	public GeneralMemberVO updateGeneralMember(String meb_name,String phone,Date birthday,byte[] photo,String comment,String address,String email,String account,String password,String gender,Integer meb_money,String post_permission,Integer ger_meb_no) {
		GeneralMemberVO gmVO = new GeneralMemberVO();
		
		gmVO.setGer_meb_no(ger_meb_no);
		gmVO.setMeb_name(meb_name);
		gmVO.setPhone(phone);
		gmVO.setBirthday(birthday);
		gmVO.setPhoto(photo);
		gmVO.setComment(comment);
		gmVO.setAddress(address);
		gmVO.setEmail(email);
		gmVO.setAccount(account);
		gmVO.setPassword(password);
		gmVO.setGender(gender);
		gmVO.setMeb_money(meb_money);
		gmVO.setPost_permission(post_permission);
		
		dao.update(gmVO);
		
		return dao.findByPrimaryKey(ger_meb_no); 
	}
	
		public GeneralMemberVO forgotPassword(String password,Integer gmno) {
		
		GeneralMemberVO gmVO = new GeneralMemberVO();
		gmVO.setPassword(password);
		gmVO.setGer_meb_no(gmno);
		
		dao.forgotPassword(gmVO);
		return gmVO;
	}
	
	//預留給 Struts 2 用的
	public void updateGeneralMember(GeneralMemberVO gmVO) {
		dao.update(gmVO);
	}
	
	public void deleteGeneralMember(Integer ger_meb_no) {
		dao.delete(ger_meb_no);
	}
	
	public GeneralMemberVO getOneGeneralMember(Integer ger_meb_no) {
		return dao.findByPrimaryKey(ger_meb_no);
	}
	
	public GeneralMemberVO getOneGeneralMember(String account) {
		return dao.findByAccount(account);
	}
	
	public GeneralMemberVO getOneGeneralMemberEmail(String email) {
		return dao.findByEmail(email);
	}
	
	public List<GeneralMemberVO> getAll(){
		return dao.getAll();
	}
	

}
