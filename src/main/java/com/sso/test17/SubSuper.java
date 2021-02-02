package com.sso.test17;

import java.util.Date;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/18 16:12
 */
public class SubSuper extends Super {
    private final Date date;

    SubSuper() {
        date = new Date();
    }

    @Override
    public void overrideMe() {
        System.out.println(date);
    }

    public static void main(String[] args) {
        /* 构造时，会调用父类super, 父类super内调用了外部方法，由于子类重写，调用子类方法，但是子类还未成功初始化，打印为null                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           */
        SubSuper subSuper = new SubSuper();
        subSuper.overrideMe();
    }

}
