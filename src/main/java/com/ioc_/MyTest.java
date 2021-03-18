package com.ioc_;

import org.junit.Test;

/**
 * @author haoxl
 * @Title: 测试
 * @Package
 * @Description:
 * @date 2021/3/18 17:12
 */
public class MyTest {

    /**
     * ioc :inversions of control 控制反转 DI dependency injection 依赖注入
     * ioc 容器使用applicationcontext -- 一个接口 是beanfactory的子类，实现了更多的功能。
     *  其中主要依赖子类classpathxmlapplicationcontext 从class path 加载配置文件。
     *  ioc 管控bean 创建，管理的权利， 控制整个bean的生命周期。
     *  反转：把这个权利交给spring 容器管控。区别：由之前的自己创建对象，变成现在被动接收别人给我们的对象的过程，这就是反转。
     *
     *  依赖： 程序运行需要依赖外部的资源，提供程序内对象所需要的数据，资源。
     *  注入: 配置文件吧资源从外部注入到内部，容器加载了外部的文件、对象、资源，
     *      然后吧这些资源赋值给程序内的对象，维护了程序内外对象之间的依赖关系
     *  ioc 是思想，Di是Spring 的实现过程
     *  IOC 的好处： 解耦。
     *
     */
    @Test
    public void myTest(){

        /* Ioc给属性赋值的实现方式，把【创建对象的过程】转移给set()方法
        * 而不是由自己去new ，即不是自己创建的了（直接在对象的内部 new ,是程序主动创建对象的正向过程;）。
        * 使用set()方法 ，是别人给我的；而IOC就是用它的容器来创建、管理这些对象。
        * 原理就是使用set()方法。
        * */
        Rectangle rectangle = new Rectangle();
        rectangle.setlength(10);
        rectangle.setWidth(10);
        System.out.println(rectangle);
    }

}
