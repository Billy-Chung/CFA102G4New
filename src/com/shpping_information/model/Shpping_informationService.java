package com.shpping_information.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Shpping_informationService {
	
		private Shpping_informationDAO_interface dao;

		public Shpping_informationService() {
			dao = new Shpping_informationDAO();
		}
	
		public Shpping_informationVO insertShpping(
				 byte[] shopping_img,
				 String shopping_date,
				 String shopping_tel,
				 String shopping_fax,
				 String shopping_add) {
			Shpping_informationVO shpping_information = new Shpping_informationVO();
	
			shpping_information.setShopping_img(shopping_img);
			shpping_information.setShopping_date(shopping_date);
			shpping_information.setShopping_tel(shopping_tel);
			shpping_information.setShopping_fax(shopping_fax);
			shpping_information.setShopping_add(shopping_add);
			shpping_information=dao.insert(shpping_information);
			return shpping_information;
		}
	
		public void updateShpping(
				 byte[] shopping_img,
				 String shopping_date,
				 String shopping_tel,
				 String shopping_fax,
				 String shopping_add,
				 Integer shopping_information_no) {
			Shpping_informationVO shpping_information = new Shpping_informationVO();
		
			shpping_information.setShopping_img(shopping_img);
			shpping_information.setShopping_date(shopping_date);
			shpping_information.setShopping_tel(shopping_tel);
			shpping_information.setShopping_fax(shopping_fax);
			shpping_information.setShopping_add(shopping_add);
			shpping_information.setShopping_information_no(shopping_information_no);
			dao.update(shpping_information);
		}
		public Shpping_informationVO findByShopping_informationPK(Integer shopping_information_no) {
			Shpping_informationVO shpping_information = new Shpping_informationVO();

			shpping_information = dao.findByShopping_informationPK(shopping_information_no);

			return shpping_information;
		}
			
		public List<Shpping_informationVO> getshopping_information_no() {

			List<Shpping_informationVO> shpping_information = dao.getshopping_information_no();

			return shpping_information;
		}
			
		public static byte[] getPictureByteArray(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			return buffer;
		}	
}


