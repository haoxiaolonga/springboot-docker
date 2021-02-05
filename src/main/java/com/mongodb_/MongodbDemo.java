package com.mongodb_;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/4 16:10
 */
@Slf4j
@RestController
@RequestMapping("/mongo")
public class MongodbDemo {

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/addData")
    public String addDocument() {

        MongodbEntity mongodbEntity = new MongodbEntity();
        mongodbEntity.setAddress("交子大道");
        mongodbEntity.setName("地铁站");
        mongodbEntity.setPhone("028");
        mongodbEntity.setCreateTime(Date.from(Instant.now()));
        mongoTemplate.save(mongodbEntity);
        return "操作成功";
    }

    @GetMapping("/getDocumentByName")
    public Object getDocument(@RequestParam String name) {

        Query query = new Query();
        Criteria criteria = new Criteria("address");
        // 模糊查询
        criteria.regex(name);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, MongodbEntity.class);
    }

}
