package com.cooper.articlemanagement.entity;

import java.util.Date;
import java.util.List;

public class Reply {

    private Integer replyId;// 回复编号
    private Integer replyGrade;// 回复等级 0 留言 1 回复留言
    private Integer articleId;// 文章编号
    private Integer usersId;// 回复人编号
    private String userNickname;// 回复人昵称
    private Integer forUsersId;// 被回复人编号
    private String forUsersIdNickname;// 被回复人昵称
    private String replyContent;// 回复内容
    private Date replyDate;// 回复时间
    private List<Reply> replyList;// 子回复内容
    private Integer replyStatus;// 回复状态 0 正常 1 违规

    public Reply() {}

    public Reply(Integer replyId, Integer replyGrade, Integer articleId, Integer usersId, String userNickname,
        Integer forUsersId, String forUsersIdNickname, String replyContent, Date replyDate, List<Reply> replyList,
        Integer replyStatus) {
        this.replyId = replyId;
        this.replyGrade = replyGrade;
        this.articleId = articleId;
        this.usersId = usersId;
        this.userNickname = userNickname;
        this.forUsersId = forUsersId;
        this.forUsersIdNickname = forUsersIdNickname;
        this.replyContent = replyContent;
        this.replyDate = replyDate;
        this.replyList = replyList;
        this.replyStatus = replyStatus;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyGrade() {
        return replyGrade;
    }

    public void setReplyGrade(Integer replyGrade) {
        this.replyGrade = replyGrade;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getForUsersId() {
        return forUsersId;
    }

    public void setForUsersId(Integer forUsersId) {
        this.forUsersId = forUsersId;
    }

    public String getForUsersIdNickname() {
        return forUsersIdNickname;
    }

    public void setForUsersIdNickname(String forUsersIdNickname) {
        this.forUsersIdNickname = forUsersIdNickname;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    @Override
    public String toString() {
        return "Reply{" + "replyId=" + replyId + ", replyGrade=" + replyGrade + ", articleId=" + articleId
            + ", usersId=" + usersId + ", userNickname='" + userNickname + '\'' + ", forUsersId=" + forUsersId
            + ", forUsersIdNickname='" + forUsersIdNickname + '\'' + ", replyContent='" + replyContent + '\''
            + ", replyDate=" + replyDate + ", replyList=" + replyList + ", replyStatus=" + replyStatus + '}';
    }
}
