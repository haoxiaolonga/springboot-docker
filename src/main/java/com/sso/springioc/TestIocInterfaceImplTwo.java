package com.sso.springioc;

import org.springframework.stereotype.Component;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/2 14:17
 */
@Component("Test2")
public class TestIocInterfaceImplTwo implements TestIocInterface {
    @Override
    public void printAddress() {
        System.out.println("hello: " + this.getClass().getSimpleName());
    }
}
