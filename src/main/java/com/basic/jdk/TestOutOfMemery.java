package com.basic.jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/3/21 15:22
 */
public class TestOutOfMemory {
    private static final Integer K = 1024;

    public static void main(String[] args) {
        int size = K * K * 8;
        List<byte[]> list = new ArrayList<byte[]>();
        for (int i = 1; i <= size; i++) {
            System.out.println("JVM写入数据" + i + "M");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new byte[size]);
        }
    }

}
