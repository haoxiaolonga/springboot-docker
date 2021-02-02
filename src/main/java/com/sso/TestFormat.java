package com.sso;

import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.concurrent.CompletableFuture;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/10 16:47
 */
public class TestFormat {

    public static void main(String[] args) throws IOException {
        System.out.println(String.format("%s","131231231奥术大师多"));
        System.out.println(Date.from(LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN)
                .atZone(ZoneId.systemDefault()).toInstant()));
        File file = new File("../TestFormat.java");
        if(file.exists()){

        } else{
            System.out.println("!!!");
        }
        System.out.println(file.getCanonicalPath());

        CompletableFuture.runAsync(()->{}).whenComplete((unused, throwable)->{

        });
        CompletableFuture.runAsync(()->{}).complete((Void) new Object());
        
    }

}
