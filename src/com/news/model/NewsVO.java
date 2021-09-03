package com.news.model;

import java.io.Serializable;
import java.sql.Date;

public class NewsVO implements Serializable {
	
	private Integer News_no;
	private Date News_date;
	private String News_state;
	private String News_data;
	private byte[] News_img;
	
	public Integer getNews_no() {
		return News_no;
	}
	public void setNews_no(Integer news_no) {
		News_no = news_no;
	}
	public Date getNews_date() {
		return News_date;
	}
	public void setNews_date(Date news_date) {
		News_date = news_date;
	}
	public String getNews_state() {
		return News_state;
	}
	public void setNews_state(String news_state) {
		News_state = news_state;
	}
	public String getNews_data() {
		return News_data;
	}
	public void setNews_data(String news_data) {
		News_data = news_data;
	}
	public byte[] getNews_img() {
		return News_img;
	}
	public void setNews_img(byte[] news_img) {
		News_img = news_img;
	}
	
}
