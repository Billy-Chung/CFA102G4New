package com.forum_article_cls.model;

import java.util.List;


public interface Forum_article_clsDAO_interface {

		public Forum_article_clsVO insert(Forum_article_clsVO FORUM_ARTICLE_CLS);
		
		public void update(Forum_article_clsVO FORUM_ARTICLE_CLS);
		
		public void delete(Integer FORUM_ARTICLE_CLS_NO);
		
		public Forum_article_clsVO findByForum_article_cls_NoPK(Integer FORUM_ARTICLE_CLS_NO);
		
		public List<Forum_article_clsVO> getforum_article_cls();
	
}
