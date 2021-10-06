package com.generalMember.model;

import java.util.List;

public interface GeneralMemberDAO_Interface {
	public void insert(GeneralMemberVO gmVO);
	public void update(GeneralMemberVO gmVO);
	public void updatePassword(GeneralMemberVO gmVO);
	public void forgotPassword(GeneralMemberVO gmVO);
	public void delete(Integer gmno);
	public GeneralMemberVO findByPrimaryKey(Integer gmno);
	public GeneralMemberVO findByAccount(String account);
	public GeneralMemberVO findByEmail(String email);
	public List<GeneralMemberVO> getAll();

}
