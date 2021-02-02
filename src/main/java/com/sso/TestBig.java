package com.sso;

import java.math.BigDecimal;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/17 17:35
 */
public class TestBig {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("1.05");
        bigDecimal.setScale(1,BigDecimal.ROUND_HALF_EVEN);
        System.out.println(bigDecimal.toPlainString());

    }

}
