package com.basic.jdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/4/6 11:06
 */
public class TestList {
    public static void main(String[] args) {


        ArrayList<String> list = new ArrayList<>();
        list.add("");

        //通过在方法上加synchronized;
        Vector<String> vector = new Vector<>();
        vector.add("");

        //通过外层加ReentrantLock 可重入锁。
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("");

        //所有方法内部价synchronized;
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        synchronizedList.add("");



    }

}
