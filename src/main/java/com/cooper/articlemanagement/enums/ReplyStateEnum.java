package com.cooper.articlemanagement.enums;

public enum  ReplyStateEnum {
    ADD_REPLY_SUCCESS(1001, "提交成功"),
    UNKONE_ERROR(0000,"未知错误，请联系管理员！");
    private Integer state;
    private String msgOrUrl;

    ReplyStateEnum(Integer state, String msgOrUrl) {
        this.state = state;
        this.msgOrUrl = msgOrUrl;
    }

    public Integer getState() {
        return state;
    }

    public String getMsgOrUrl() {
        return msgOrUrl;
    }
}
