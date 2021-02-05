package com.exception_;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/5 16:55
 */
@RestController
@RequestMapping("/exception")
public class ExceptionTest {

    @PostMapping("/createNull")
    public String createNullException() {
        if (1 == 1) {
            throw new NullPointerException("YES! NULL");
        }
        return "测试NULL";
    }

}
