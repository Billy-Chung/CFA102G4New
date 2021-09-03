package com.shpping_information.model;

import java.util.List;


public interface Shpping_informationDAO_interface {


	public void insert(Shpping_informationVO shopping_information_no);
	
	public void update(Shpping_informationVO shopping_information_no);
	
	public void delete(Integer shopping_information_no);
	
	public Shpping_informationVO findByShopping_informationPK(Integer SHOPPING_INFORMATION_NO);
	
	public List<Shpping_informationVO> getshopping_information_no();
	
	
}
