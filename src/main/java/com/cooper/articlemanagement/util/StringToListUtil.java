package com.cooper.articlemanagement.util;

import java.util.ArrayList;
import java.util.List;

public class StringToListUtil {
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
            str = str.substring(index+1);
        }
        return list;
    }
}
