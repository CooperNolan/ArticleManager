package com.cooper.articlemanagement.dao;

import com.cooper.articlemanagement.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("articleDao")
public interface ArticleDao {
    Article select(HashMap<String,Object> map);
    List<Article> select(Map<String, Object> map);
    List<Article> select();
    int insert(Article article);
    int update(Article article);
    int delete(@Param("articleId") Integer articleId);
    Integer selectHomeTotalNum(Map<String,Object> map);
    List<Article> selectHomeList(Map<String,Object> map);
}
