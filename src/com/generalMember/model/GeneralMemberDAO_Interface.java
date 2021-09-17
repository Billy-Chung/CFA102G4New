package com.generalMember.model;

import java.util.List;

public interface GeneralMemberDAO_Interface {
	public void insert(GeneralMemberVO gmVO);
	public void update(GeneralMemberVO gmVO);
	public void delete(Integer gmno);
	public GeneralMemberVO findByPrimaryKey(Integer gmno);
	public GeneralMemberVO findByAccount(String account);
	public List<GeneralMemberVO> getAll();

}
