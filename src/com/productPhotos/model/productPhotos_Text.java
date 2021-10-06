package com.productPhotos.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class productPhotos_Text {
	
public static void main(String[] args) {
		
		productPhotosDAO ppdao = new productPhotosDAO();
		
		//新增
		productPhotosVO pp1 = new productPhotosVO();
		pp1.setProduct_photo_no(11);
		pp1.setProduct_no(3);
		try {
			byte[] pic = getPictureByteArray("images/adoptMember1.png");
			pp1.setProduct_photo(pic);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		ppdao.insert(pp1);
		System.out.println("新增成功");
		System.out.println("---------------------");
		
		//更新
		productPhotosVO pp2 = new productPhotosVO();
		pp2.setProduct_photo_no(11);
		pp2.setProduct_no(1);
		try {
			byte[] pic = getPictureByteArray("images/adoptMember2.png");
			pp2.setProduct_photo(pic);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		ppdao.update(pp2);
		System.out.println("更新成功");
		System.out.println("---------------------");
		
		//刪除單一
		ppdao.delete(11);
		System.out.println("刪除成功");
		System.out.println("---------------------");
		
		//查詢單一
		productPhotosVO pp3 = ppdao.findByPrimaryKey(1);
		System.out.print(pp3.getProduct_photo_no() + ",");
		System.out.print(pp3.getProduct_no() + ",");
		System.out.print(pp3.getProduct_photo());
		System.out.println();
		System.out.println("---------------------");
		
		//查詢全部
		List<productPhotosVO> list = ppdao.getAll();
		for (productPhotosVO pp4 : list) {
			System.out.print(pp4.getProduct_photo_no() + ",");
			System.out.print(pp4.getProduct_no() + ",");
			System.out.print(pp4.getProduct_photo());
			System.out.println();
			System.out.println("---------------------");
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}

