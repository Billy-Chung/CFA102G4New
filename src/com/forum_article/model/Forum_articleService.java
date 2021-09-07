package com.forum_article.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class Forum_articleService {

	public Forum_articleDAO_interface dao;

	public Forum_articleService() {
		dao = new Forum_articleDAO();
	}

	public Forum_articleVO insertForum_article(Integer forum_article_cls_no, Integer gen_meb_no,
			String forum_comm_com_name, String forum_comm_com, Date forum_comm_date, String forum_comm_state) {
		Forum_articleVO forum_article = new Forum_articleVO();

		forum_article.setForum_article_cls_no(forum_article_cls_no);
		forum_article.setGen_meb_no(gen_meb_no);
		forum_article.setForum_comm_com_name(forum_comm_com_name);
		forum_article.setForum_comm_com(forum_comm_com);
		forum_article.setForum_comm_date(forum_comm_date);
		forum_article.setForum_comm_state(forum_comm_state);
		forum_article = dao.insert(forum_article);

		return forum_article;

	}

	public void updateForum_article(Integer forum_article_cls_no, Integer gen_meb_no, String forum_comm_com_name,
			String forum_comm_com, Date forum_comm_date, String forum_comm_state, Integer forum_article_no) {
		Forum_articleVO forum_article = new Forum_articleVO();

		forum_article.setForum_article_cls_no(forum_article_cls_no);
		forum_article.setGen_meb_no(gen_meb_no);
		forum_article.setForum_comm_com_name(forum_comm_com_name);
		forum_article.setForum_comm_com(forum_comm_com);
		forum_article.setForum_comm_date(forum_comm_date);
		forum_article.setForum_comm_state(forum_comm_state);
		forum_article.setForum_article_no(forum_article_no);
		dao.update(forum_article);
	}

	public Forum_articleVO findByforum_article_noPK(Integer forum_article_no) {

		Forum_articleVO forum_article = new Forum_articleVO();

		forum_article = dao.findByforum_article_noPK(forum_article_no);

		return forum_article;
	}

	public List<Forum_articleVO> getforum_article() {

		List<Forum_articleVO> forum_article = dao.getforum_article();

		return forum_article;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
