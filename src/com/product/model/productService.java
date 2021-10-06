package com.product.model;

import java.util.List;

public class productService {

	private productDAO_interface dao;

	public productService() {
		dao = new productDAO();
	}

	public ProductVO addproduct(Integer product_type_no, String product_name, Integer product_price, String product_comment, String product_status, Integer product_all_stars, Integer product_all_comments) {

		ProductVO p = new ProductVO();

		p.setProduct_type_no(product_type_no);
		p.setProduct_name(product_name);
		p.setProduct_price(product_price);
		p.setProduct_comment(product_comment);
		p.setProduct_status(product_status);
		p.setProduct_all_stars(product_all_stars);
		p.setProduct_all_comments(product_all_comments);
		dao.insert(p);
		return p;
	}

	public ProductVO updateproduct(Integer product_type_no, String product_name, Integer product_price, String product_comment, String product_status, Integer product_all_stars, Integer product_all_comments) {

		ProductVO p = new ProductVO();

		p.setProduct_type_no(product_type_no);
		p.setProduct_name(product_name);
		p.setProduct_price(product_price);
		p.setProduct_comment(product_comment);
		p.setProduct_status(product_status);
		p.setProduct_all_stars(product_all_stars);
		p.setProduct_all_comments(product_all_comments);
		dao.update(p);
		return p;
	}

	public void deleteproduct(Integer product_no) {
		dao.delete(product_no);
	}

	public ProductVO getOneproduct(Integer product_no) {
		return dao.findByPrimaryKey(product_no);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}
