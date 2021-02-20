package com.properties_;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/20 10:53
 */
@Component
@ConfigurationProperties(prefix = "test.properties")
@Data
public class DataProperties {

    private List<String> list = new ArrayList();
    private List<String> arrayTest = new ArrayList();
    private Integer number;
    private String bootstrapStr;
}
