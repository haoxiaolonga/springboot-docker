package com.sso.springioc;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/2 14:15
 */
@RestController
@RequestMapping("/testIoc")
//@RequiredArgsConstructor  构造方法注入
public class Test {

//    @Autowired // 属性注入
    Map<String, TestIocInterface> testIocInterfaceMap;

    @Autowired //set 方法注入
    private void setTestIocInterfaceMap(Map<String, TestIocInterface> map){
        this.testIocInterfaceMap=map;
    }

    @Resource(name = "test1") //由J2EE提供- 默认按照name查找，byName装配
    private TestIocInterface testIocInterface;

    @Resource(type = TestIocInterfaceImplTwo.class)
    private TestIocInterface testIocInterfaceTwo;

    @Resource(type = TestIocInterface.class,name = "test1")
    private TestIocInterface testIocInterfaceThr;

    @Resource(type = TestIocInterface.class)
    @Qualifier("Test2")
    private TestIocInterface testIocInterfaceFour;

    @Autowired
    @Qualifier("Test2") //Spring 注解autowird-只能按照byType查找，结合@qualifier(也是spring的)指定名称
    private TestIocInterface testIocInterface2;

    @GetMapping("/{address}")
    public void doSomething(@PathVariable String address) {
        TestIocInterface target = testIocInterfaceMap.get(address);
        target.printAddress();
        testIocInterface.printAddress();
        testIocInterface2.printAddress();
        System.out.print("resource 通过type找:");
        testIocInterfaceTwo.printAddress();
        System.out.print("resource 通过type + name找:");
        testIocInterfaceThr.printAddress();

        System.out.print("resource 通过type + qualifier 找:");
        testIocInterfaceFour.printAddress();
    }

}
