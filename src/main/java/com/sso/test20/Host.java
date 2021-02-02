package com.sso.test20;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/19 11:10
 */
public class Host {

    private static class StrLenCmp implements Comparator<String>, Serializable{
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }
    /*  比较字符长度策略  */
    public static final Comparator<String> STRING_COMPARATOR = new StrLenCmp();


    public enum obj {
        SPRING("1", "春"),
        SUMMER("2", "夏"),
        AUTUMN("3", "秋"),
        WINTER("4", "冬");
        private String obj;
        private String desc;

        obj(String s, String s1) {
        }
    }

    public static void main(String[] args) {
    }

}
