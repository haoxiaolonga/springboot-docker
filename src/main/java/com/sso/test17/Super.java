package com.sso.test17;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/18 16:12
 */
public class Super {

    public Super(){
//        overrideMe();
        notOver(); //用私有方法--包括需要执行的可覆盖方法代码。。防止调用子类的方法？
    }

    private void notOver(){
        System.out.println("1222");
    }

    public void overrideMe(){

    }

}
