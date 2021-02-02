package com.sso.springioc;

import org.springframework.stereotype.Component;

/**
 * @author haoxl
 * @Title: ImplOne
 * @Package
 * @Description:
 * @date 2021/2/2 14:16
 */
@Component("test1")
public class TestIocInterfaceImpl implements TestIocInterface {

    @Override
    public void printAddress() {
        System.out.println("hello: " + this.getClass().getSimpleName());
    }
}
