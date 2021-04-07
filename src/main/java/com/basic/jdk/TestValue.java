package com.basic.jdk;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/3/28 15:38
 */
public class TestValue {

    public static void main(String[] args) {

        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> m1 = new HashMap<>();
        m1.put("m1","m1");

        list.add(m1);
        System.out.println(list.get(0).get("m1"));
        m1.put("m1","m1_update");
        System.out.println(list.get(0).get("m1"));

        Map<String,String> m2 = new HashMap<>();
        m2.put("m2","m2");
        m1=m2;
        System.out.println(list.get(0).get("m1"));

        list.add(m2);
        System.out.println(list.get(1).get("m2"));


    }

    @Test
    public void revert(){
        List<String> list = new ArrayList<>();
        LinkedList<String> list2 = new LinkedList<>();
        list2.element();
    }

    @Test
    public void revertSubmit(){
        int[] nums = {1,2,2};
        subCollection(nums);
    }


    /**
     *  1<=length <=10
     * @param num
     * @return
     */
    public List<List<Integer>> subCollection(int[] num){

        List<List<Integer>> res = new ArrayList<>(2);
        res.add(Collections.EMPTY_LIST);
        res.add(CollectionUtils.arrayToList(num));

        int length = num.length;
        for (int i = 0; i < length; i++) {

        }

        return null;
    }

    @Test
    public void x(){

        String a = new String("2");
        String b = String.valueOf("2");

        System.out.println(a==b);
        System.out.println(a.equals(b));

    }

}
