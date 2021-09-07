package com.news.model;

import java.util.List;

public interface NewsDAO_interface {

public NewsVO insert(NewsVO news);
	
	public void update(NewsVO news);
	
	public void delete(Integer news);
	
	public NewsVO findByNewsPK(Integer NEWS_NO);
	
	public List<NewsVO> getNews();
	
}
