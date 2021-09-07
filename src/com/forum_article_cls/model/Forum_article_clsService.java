package com.forum_article_cls.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Forum_article_clsService {
	public Forum_article_clsDAO_interface dao;

	public Forum_article_clsService() {
		dao = new Forum_article_clsDAO();
	}

	public Forum_article_clsVO insertForum_article_cls(String forum_article_cla_name) {
		Forum_article_clsVO forum_article_cls = new Forum_article_clsVO();

		forum_article_cls.setForum_article_cla_name(forum_article_cla_name);
		forum_article_cls = dao.insert(forum_article_cls);

		return forum_article_cls;

	}

	public void updateForum_article_cls(String forum_article_cla_name, Integer forum_article_cla_no) {
		Forum_article_clsVO forum_article_cls = new Forum_article_clsVO();

		forum_article_cls.setForum_article_cla_name(forum_article_cla_name);
		forum_article_cls.setForum_article_cla_no(forum_article_cla_no);
		dao.update(forum_article_cls);
	}

	public Forum_article_clsVO findByForum_article_cls_NoPK(Integer FORUM_ARTICLE_CLS_NO) {
		Forum_article_clsVO forum_article_cls = new Forum_article_clsVO();
		forum_article_cls = dao.findByForum_article_cls_NoPK(FORUM_ARTICLE_CLS_NO);

		return forum_article_cls;
	}

	public List<Forum_article_clsVO> getforum_article_cls() {
		List<Forum_article_clsVO> forum_article_cls = dao.getforum_article_cls();

		return forum_article_cls;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
