package com.ioc_;

import lombok.AllArgsConstructor;

/**
 * @author haoxl
 * @Title: 深入理解ioc
 * @Package
 * @Description:
 * @date 2021/3/18 17:05
 */
public class Rectangle {
    private int width;
    private int length;

    public Rectangle() {
        System.out.println("hello constructor method");
    }

    public void setWidth(int width){
        this.width = width;
    }

    /* SpringIoC 就是通过set()方法注入的 */
    public void setlength(int length){
        this.length = length;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}
