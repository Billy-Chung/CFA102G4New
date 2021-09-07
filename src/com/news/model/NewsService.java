package com.news.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class NewsService {

	public NewsDAO_interface dao;

	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO insertNews(Date news_date, String news_state, String news_data, byte[] news_img) {
		NewsVO news = new NewsVO();

		news.setNews_date(news_date);
		news.setNews_state(news_state);
		news.setNews_data(news_data);
		news.setNews_img(news_img);
		news = dao.insert(news);

		return news;

	}

	public void update(Date news_date, String news_state, String news_data, byte[] news_img, Integer news_no) {
		NewsVO news = new NewsVO();

		news.setNews_date(news_date);
		news.setNews_state(news_state);
		news.setNews_data(news_data);
		news.setNews_img(news_img);
		news.setNews_no(news_no);
		dao.update(news);

	}

	public NewsVO findByNewsPK(Integer NEWS_NO) {
		NewsVO news = new NewsVO();

		news = dao.findByNewsPK(NEWS_NO);
		return news;
	}

	public List<NewsVO> getNews() {

		List<NewsVO> news = dao.getNews();

		return news;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;

	}
}
