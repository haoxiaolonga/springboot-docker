package com.sso.test18;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/18 16:57
 */
public interface Dancer<T> {

    public int compare(T t1,T t2);

    default void dance(){

    }

    void dance1();

}
