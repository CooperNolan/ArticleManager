package com.cooper.articlemanagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cooper.articlemanagement.entity.Article;

@Repository("articleDao")
public interface ArticleDao {

    Article select(HashMap<String, Object> map);

    List<Article> select(Map<String, Object> map);

    List<Article> select();

    int insert(Article article);

    int update(Article article);

    int updateCategoryByDelete(@Param("categoryIdBefore") Integer categoryIdBefore,
        @Param("categoryIdAfter") Integer categoryIdAfter);

    int delete(@Param("articleId") Integer articleId);

    Integer selectHomeTotalNum(Map<String, Object> map);

    List<Article> selectHomeList(Map<String, Object> map);

}
