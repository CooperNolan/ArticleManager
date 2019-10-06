package com.cooper.articlemanagement.test.dao;

import com.cooper.articlemanagement.dao.ArticleDao;
import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleTest extends BaseTest {

    @Autowired
    ArticleDao articleDao;

    @Test
    public void selectTest1(){
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("articleStatus", 0);
        hashMap.put("articleId", 2);
        Article article = articleDao.select(hashMap);
        System.out.println(article);
    }
    @Test
    public void selectTest2(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleStatus", 0);
        map.put("authorId", 1);
        List<Article> articles = articleDao.select(map);
        for (Article article : articles) {
            System.out.println(article);
        }
    }
    @Test
    public void selectTest3(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleStatus", 0);
        map.put("search", "Test");
        List<Article> articles = articleDao.select(map);
        for (Article article : articles) {
            System.out.println(article);
        }
    }
    @Test
    public void insert(){
        Article article = new Article();
        article.setAuthorId(1);
        article.setAuthorNickname("admin");
        article.setArticleTopic("54Test");
        article.setArticleDate(new Date());
        article.setArticleStatus(1);
        article.setArticleSummary("1");
        article.setArticleContent("2");
        articleDao.insert(article);
        System.out.println(article);
    }
    @Test
    public void update(){
        Article article = new Article();
        article.setArticleId(4);
        article.setArticleTopic("updateTest");
        article.setArticleStatus(0);
        article.setArticleSummary("5");
        article.setArticleContent("4");
        articleDao.update(article);
        System.out.println(article);
    }
    @Test
    public void delete(){
        articleDao.delete(4);
    }

    @Test
    public void selectTotal(){
        /*System.out.println(articleDao.selectHomeTotalNum());*/
    }

    @Test
    public void selectPageArticle(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", 0);
        map.put("pageNum", 10);
        map.put("search","test");
        List<Article> articleList = articleDao.selectHomeList(map);
        for (Article article : articleList) {
            System.out.println(article);
        }
    }
}
