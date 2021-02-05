package com.mongodb_;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/4 16:15
 */
@Data
@Document("mongodb_data")
public class MongodbEntity {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 时间
     */
    private Date createTime;

}
