package com.cooper.articlemanagement.enums;

public enum UserStateEnum {

    CODE_ERROR(-1001, "验证码错误!"), LOGIN_ERROR(-2002, "用户名或密码错误！"), STATUS_ERROR(-1002, "账号已被锁定,请联系管理员!"),
    LOGIN_SUCCESS(2001, "Home"), LOGOUT_ERROR(-3002, "session不存在"), REGISTERED_ERROR(-4002, "用户名已存在！"),
    LOGOUT_SUCCESS(3001, "Login"), REGISTERED_SUCCESS(4001, "Home"), UPDATE_SUCCESS(5001, "Home"),
    UNKONE_ERROR(0000, "未知错误，请联系管理员！");

    private Integer state;
    private String msgOrUrl;

    UserStateEnum(Integer state, String msgOrUrl) {
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
