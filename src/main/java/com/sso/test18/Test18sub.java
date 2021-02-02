package com.sso.test18;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/18 16:34
 */
public class Test18sub extends Test18 {

    @Override
    public void method1(String ob1) {

    }

    @Override
    public void method2(String ob3) {

    }

    @Override
    public void method3(String ob3) {

    }

    @Override
    public void method4(String ob4) {

        new Dancer<String>(){
            @Override
            public int compare(String t1, String t2) {
                return t1.length() - t2.length();
            }

            @Override
            public void dance1() {

            }

        };
        new Test18(){

            @Override
            public void afterPropertiesSet() throws Exception {

            }

            @Override
            public void method1(String ob1) {

            }

            @Override
            public void method3(String ob3) {

            }

            @Override
            public void method4(String ob4) {

            }
        };
    }

    public static void main(String[] args) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
