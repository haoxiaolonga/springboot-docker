package com;

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
public class DockerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerApplication.class, args);
    }
}
