package com.product.model;

import java.util.List;

public interface productDAO_interface {
	
	public void insert(ProductVO product);
	
	public void update(ProductVO product);
	
	public void delete(Integer product_no);
	
	public ProductVO findByPrimaryKey(Integer product_no);
	
	public List<ProductVO> getAll();
}
