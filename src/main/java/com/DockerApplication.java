package com;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/26 10:22
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com.transcational.mapper")
public class DockerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerApplication.class, args);
    }
}
