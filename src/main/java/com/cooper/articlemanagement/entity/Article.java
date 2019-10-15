package com.cooper.articlemanagement.entity;

import java.util.Date;

public class Article {
    private Integer articleId;//文章编号
    private Integer authorId;//作者编号
    private String authorNickname;//作者昵称
    private String articleTopic;//文章题目
    private String articleSummary;//摘要
    private String articleContent;//文章
    private Date articleDate;//创建时间
    private Date articleModifyDate;//最后一次修改时间时间
    private Integer articleStatus;//文章状态 0 正常 1 违规

    public Article() {
    }

    public Article(Integer articleId, Integer authorId, String authorNickname, String articleTopic, String articleSummary, String articleContent, Date articleDate, Integer articleStatus) {
        this.articleId = articleId;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.articleTopic = articleTopic;
        this.articleSummary = articleSummary;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
        this.articleStatus = articleStatus;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public String getArticleTopic() {
        return articleTopic;
    }

    public void setArticleTopic(String articleTopic) {
        this.articleTopic = articleTopic;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public Date getArticleModifyDate() {
        return articleModifyDate;
    }

    public void setArticleModifyDate(Date articleModifyDate) {
        this.articleModifyDate = articleModifyDate;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    @Override
    public String toString() {
        return "ArticleTest{" +
                "articleId=" + articleId +
                ", authorId=" + authorId +
                ", authorNickname='" + authorNickname + '\'' +
                ", articleTopic='" + articleTopic + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleDate=" + articleDate +
                ", articleStatus=" + articleStatus +
                '}';
    }
}
