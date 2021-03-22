package com.basic.jdk;

/**
 * @author haoxl
 * @Title: 测试抽象类
 * @Package
 * @Description:
 * @date 2021/3/21 14:30
 */
public abstract class TestAbstract {

    abstract void method1();

    static void method2() {
        System.out.println("打印静态方法");
    }

    public static void main(String[] args) {
        TestAbstract.method2();
    }

}
