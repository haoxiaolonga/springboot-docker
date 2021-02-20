package com.properties_;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 10:55
 */
@RestController
@RequestMapping("/test/properties")
@Slf4j
public class TestPropertiesData {

    @Value("${test.properties.bootstrapStr:testValue}")
    private String bootstrapStr;

    @Value("${test.properties.number:100}")
    private Integer number;

    @Value("${test.properties.list:new ArrayList()}")
    private List<String> list;

    @Value("${test.properties.arrayTest:new ArrayList()}")
    private List<String> arrayTest;

    @Autowired
    private DataProperties dataProperties;

    @GetMapping("/annotation")
    public void testValueByAnnotation() {
        try {
            System.out.println(" ---------------- 注解读取 ----------------- ");

            System.out.println(bootstrapStr);
            System.out.println("逗号格式:" + list.size());
            System.out.println("换行格式:" + arrayTest.size());
            System.out.println(number);
        } catch (Exception e) {
            System.out.println("通过@value 读取配置异常：" + e.getMessage());
        }
    }

    @GetMapping("/congratulation")
    public void testValueByCongratulation() {
        if (Objects.nonNull(dataProperties)) {
            System.out.println(" ---------------- 配置对象读取 ----------------- ");

            System.out.println(dataProperties.getBootstrapStr());
            System.out.println("逗号格式:" + dataProperties.getList().size());
            System.out.println("换行格式:" + dataProperties.getArrayTest().size());
            System.out.println(dataProperties.getNumber());
        } else {
            System.out.println("没有从congratulation读到配置");
        }
    }


}
