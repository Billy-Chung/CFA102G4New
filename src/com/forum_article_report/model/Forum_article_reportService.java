package com.forum_article_report.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class Forum_article_reportService {
	public Forum_article_reportDAO_interface dao;

	public Forum_article_reportService() {
		dao = new Forum_article_reportDAO();
	}

	public Forum_article_reportVO insertForum_article_report(Integer forum_article_no, Integer gen_meb_no,
			String forum_comm_report_com, String forum_comm_report_state, Date forum_comm_report_date) {
		Forum_article_reportVO forum_article_report = new Forum_article_reportVO();
		forum_article_report.setForum_article_no(forum_article_no);
		forum_article_report.setGen_meb_no(gen_meb_no);
		forum_article_report.setForum_comm_report_com(forum_comm_report_com);
		forum_article_report.setForum_comm_report_state(forum_comm_report_state);
		forum_article_report.setForum_comm_report_date(forum_comm_report_date);
		forum_article_report = dao.insert(forum_article_report);

		return forum_article_report;

	}

	public void updateForum_article_report(Integer forum_article_no, Integer gen_meb_no, String forum_comm_report_com,
			String forum_comm_report_state, Date forum_comm_report_date, Integer article_report_no) {
		Forum_article_reportVO forum_article_report = new Forum_article_reportVO();
		forum_article_report.setForum_article_no(forum_article_no);
		forum_article_report.setGen_meb_no(gen_meb_no);
		forum_article_report.setForum_comm_report_com(forum_comm_report_com);
		forum_article_report.setForum_comm_report_state(forum_comm_report_state);
		forum_article_report.setForum_comm_report_date(forum_comm_report_date);
		forum_article_report.setArticle_report_no(article_report_no);
		dao.update(forum_article_report);
	}

	public Forum_article_reportVO findByArticle_report_noPK(Integer ARTICLE_REPORT_NO) {
		Forum_article_reportVO forum_article_report = new Forum_article_reportVO();
		forum_article_report = dao.findByArticle_report_noPK(ARTICLE_REPORT_NO);

		return forum_article_report;
	}
	

	public List<Forum_article_reportVO> getforum_article_report(){
		List<Forum_article_reportVO> forum_article_report = dao.getforum_article_report();

		return forum_article_report;
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
