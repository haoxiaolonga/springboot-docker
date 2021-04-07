package com.sso;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/26 10:27
 */
@RestController
@RequestMapping("/json")
@Slf4j
public class JsonController {

    @GetMapping("/getData")
    public Map jsonArray(){

        /*System.out.println(new HashMap<>(0).size()); //threshold = 1
        System.out.println(new HashMap<>(1).entrySet().size()); //threshold = 1
        System.out.println(new HashMap<>(8).entrySet().size());//threshold = 8
        System.out.println(new HashMap<>(12).entrySet().size());//threshold = 16
        System.out.println(new HashMap<>(16).entrySet().size());//threshold = 16
        System.out.println(new HashMap<>(17).entrySet().size());//threshold = 32*/
        log.info("测试ELK:{}",System.currentTimeMillis());

        Gson gson = new GsonBuilder().create();
        String a = gson.fromJson("",String.class);

        return new HashMap(2) { //threshold = 2
            {
                put("springboot", "local");
                put("springboot", "local");
            }
        };
    }
}
