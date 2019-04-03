package com.ccnu.test.fundtest.utils;

public class StringUtil {

    /*
     * c 要填充的字符 length 填充后字符串的总长度 content 要格式化的字符串 格式化字符串，左对齐
     */
    public static String flushLeft(char c, long length, String content) {
        String str = "";
        String cs = "";
        if (content.length() > length) {
            str = content;
        } else {
            for (int i = 0; i < length - content.length(); i++) {
                cs = cs + c;
            }
        }
        str = cs + content;
        return str;
    }
}
