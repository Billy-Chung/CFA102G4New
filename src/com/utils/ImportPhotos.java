package com.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.product.model.ProductVO;
import com.product.model.productService;
import com.productPhotos.model.productPhotosDAO;
import com.productPhotos.model.productPhotosDAO_interface;
import com.productPhotos.model.productPhotosService;
import com.productPhotos.model.productPhotosVO;

public class ImportPhotos {

	public static void main(String[] args) throws IOException {

		productService prodSvc = new productService();
		List<ProductVO> prods = prodSvc.getAll();

		productPhotosDAO_interface prodPhotoDAO = new productPhotosDAO();
		List<productPhotosVO> prodPhotos = prodPhotoDAO.getAll();

		for (productPhotosVO photo : prodPhotos) {
			prodPhotoDAO.delete(photo.getProduct_photo_no());
		}

		String pathTemplate = "./WebContent/front_end/product_images/%s.jpg";

		for (ProductVO prod : prods) {
			Integer prodNo = prod.getProduct_no();
			String photoPath = String.format(pathTemplate, prodNo);

			File photo = new File(photoPath);
			byte[] photoTank;

			FileInputStream fis = new FileInputStream(photo);
			int size = fis.available();
			photoTank = new byte[size];
			fis.read(photoTank);

			productPhotosVO prodPhotoVO = new productPhotosVO();
			prodPhotoVO.setProduct_no(prodNo);
			prodPhotoVO.setProduct_photo_no(prodNo);
			prodPhotoVO.setProduct_photo(photoTank);
			
			prodPhotoDAO.insert(prodPhotoVO);
		}

	}
}
