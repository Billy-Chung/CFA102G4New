package com.forum_article_report.model;

import java.util.List;

public interface Forum_article_reportDAO_interface {

	public Forum_article_reportVO insert(Forum_article_reportVO forum_article_report);

	public void update(Forum_article_reportVO forum_article_report);

	public void delete(Integer ARTICLE_REPORT_NO);

	public Forum_article_reportVO findByArticle_report_noPK(Integer ARTICLE_REPORT_NO);

	public List<Forum_article_reportVO> getforum_article_report();

}
