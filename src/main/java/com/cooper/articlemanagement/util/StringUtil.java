package com.cooper.articlemanagement.util;

public class StringUtil {

    public static Integer getPageToInteger(String pageStr) {
        Integer pageInt = 1;
        try {
            pageInt = Integer.parseInt(pageStr);
        } catch (Exception e) {
            pageInt = 1;
        }
        if (pageInt <= 0) {
            pageInt = 1;
        }
        return pageInt;
    }

    public static Integer getCategoryToInteger(String categoryStr) {
        Integer categoryInt = 0;
        try {
            categoryInt = Integer.parseInt(categoryStr);
        } catch (Exception e) {
            categoryInt = 0;
        }
        return categoryInt;
    }
}
