package com.cooper.articlemanagement.entity;

import java.util.Date;

public class Article {
    private Integer articleId;// 文章编号
    private Integer authorId;// 作者编号
    private String authorNickname;// 作者昵称
    private String articleTopic;// 文章题目
    private String articleContent;// 文章
    private Date articleDate;// 创建时间
    private Date articleModifyDate;// 最后一次修改时间时间
    private Integer articleStatus;// 文章状态 0 正常 1 违规
    private Category category;// 分类类型

    public Article() {}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" + "articleId=" + articleId + ", authorId=" + authorId + ", authorNickname='" + authorNickname
            + '\'' + ", articleTopic='" + articleTopic + '\'' + ", articleContent='" + articleContent + '\''
            + ", articleDate=" + articleDate + ", articleModifyDate=" + articleModifyDate + ", articleStatus="
            + articleStatus + ", category=" + category + '}';
    }
}
