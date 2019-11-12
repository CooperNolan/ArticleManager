package com.cooper.articlemanagement.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static List<String> stringToList(String str, String separator) {
        List<String> list = new ArrayList<String>();
        int index = 0;
        while (true) {
            index = str.indexOf(separator);
            if (index > -1) {
                list.add(str.substring(0, index));
            } else {
                list.add(str);
                break;
            }
            str = str.substring(index + 1);
        }
        return list;
    }

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
