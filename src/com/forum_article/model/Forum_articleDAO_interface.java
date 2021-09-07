package com.forum_article.model;

import java.util.List;

public interface Forum_articleDAO_interface {

	public Forum_articleVO insert(Forum_articleVO FORUM_ARTICLE_NO);

	public void update(Forum_articleVO FORUM_ARTICLE_NO);

	public void delete(Integer FORUM_ARTICLE_NO);

	public Forum_articleVO findByforum_article_noPK(Integer forum_article_no);

	public List<Forum_articleVO> getforum_article();

}
