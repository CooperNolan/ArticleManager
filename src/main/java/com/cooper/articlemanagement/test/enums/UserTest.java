package com.cooper.articlemanagement.test.enums;

import com.cooper.articlemanagement.enums.UserStateEnum;
import org.junit.Test;

public class UserTest {
    @Test
    public static void main(String[] args) {
        System.out.println(UserStateEnum.CODE_ERROR.getMsgOrUrl());
        System.out.println(UserStateEnum.LOGIN_ERROR.getMsgOrUrl());
        System.out.println(UserStateEnum.STATUS_ERROR.getMsgOrUrl());
    }
}
