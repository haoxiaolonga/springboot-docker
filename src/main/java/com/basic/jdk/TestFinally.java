package com.basic.jdk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description: 测试return和finally返回值
 * @date 2021/2/22 14:27
 */

/**
 * 总结到底返回值变不变可以简单的这么记忆：
 * 当finally调用的任何可变API，会修改返回值；
 * 当finally调用任何的不可变API，对返回值没有影响。
 * 总结一下：其实return与finally并没有明显的谁强谁弱。在执行时，
 * 是return语句先把返回值写入但内存中，然后停下来等待finally语句块执行完，return再执行后面的一段。
 */

@RestController
@RequestMapping("/finally")
public class TestFinally {

    @GetMapping("/basic")
    public int testReturn(int x, int y) {
        return demo1(x, y);
    }

    @GetMapping("/quoto")
    public String testReturn2(BigDecimal x, BigDecimal y) {
        return demo2(x, y).toPlainString();
    }

    public static void main(String[] args) {
        System.out.println(new TestFinally().demo3("test", "normal").get("test"));
        System.out.println(new TestFinally().demo3("test", null).get("test"));
    }

    /**
     * @param x > 0
     * @param y >= 0
     * @return
     */
    public int demo1(int x, int y) {
        int z;
        try {
            z = x / y;
            return z;
        } catch (Exception e) {
            z = -2;
            return z;
        } finally {
            z = -3;
            System.out.println("执行finally int 数据为 " + z);
            // 测试结果 如果finally 总没有return 即使修改了基本类型的值，实际返回方法外部的也是try或者catch中的值。
//            return z;
        }
    }

    /**
     * @param x > 0
     * @param y >= 0
     * @return
     */
    public BigDecimal demo2(BigDecimal x, BigDecimal y) {
        BigDecimal z;
        try {
            z = x.divide(y, 0, BigDecimal.ROUND_HALF_UP);
            return z;
        } catch (Exception e) {
            z = new BigDecimal("-1");
            return z;
        } finally {
            z = new BigDecimal("-3");
            System.out.println("执行finally bigDecimal 数据为 " + z.toPlainString());
            // 测试结果 如果finally 总没有return 即使修改了基本类型的值，实际返回方法外部的也是try或者catch中的值。
//            return z;
        }
    }

    public Map<String, String> demo3(String key, String value) {
        Map<String, String> target = new HashMap<>(1);
        try {
            if (Objects.isNull(value)) {
                throw new RuntimeException("hello");
            }
            target.put(key, value);
            return target;
        } catch (Exception e) {
            target.put(key, "exception value");
            return target;
        } finally {
            target.put(key, "finally value");
            System.out.println("执行finally map 数据为 " + target.get(key));
            // 测试结果 如果finally 修改引用对象的值，即使没有return,目标对象再之前的return 值也是被修改的。
//            return target;
        }
    }
}
