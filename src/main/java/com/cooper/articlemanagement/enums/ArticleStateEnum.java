package com.cooper.articlemanagement.enums;

public enum  ArticleStateEnum {
    WRITE_ARTICLE(1001,"Submit","提交"),
    SEARCH_MY_ARTICLE(1003, "SearchMyArticle", "搜索文章名称："),
    MODIFY_ARTICLE(1002, "Modify", "修改"),
    ADD_SUCCES(2001, "Home", "添加成功"),
    DELETE_SUCCES(3001, "Home", "删除成功"),
    UNKONE_ERROR(0000, "未知错误，请联系管理员", "");
    private Integer state;
    private String msgOrUrl;
    private String prompt;

    ArticleStateEnum(Integer state, String msgOrUrl, String prompt) {
        this.state = state;
        this.msgOrUrl = msgOrUrl;
        this.prompt = prompt;
    }

    public Integer getState() {
        return state;
    }

    public String getMsgOrUrl() {
        return msgOrUrl;
    }

    public String getPrompt() {
        return prompt;
    }
}
