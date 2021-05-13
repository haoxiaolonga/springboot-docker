package com.transcational;

import com.transcational.entity.LsUser;
import com.transcational.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author haoxl
 * @Title: 测试事务
 * @Package
 * @Description:
 * @date 2021/3/4 10:52
 */
@RestController("/trans")
public class TransactionalTest {

    @Autowired
    private UserMapper userMapper;

    /**
     *  Propagation.REQUIRED 加入当前事务，若没有，创建一个事务
     *  Propagation.REQUIRES_NEW 始终创建一个新的事务
     *  Propagation.NESTED 嵌套在当前事务（一个子事务），若没有，从新开启一个required
     *  Propagation.SUPPORTS 支持事务，当前有就用，没有就在无事务下执行
     *  Propagation.NOT_SUPPORTED 挂起当前事务，无事务执行
     *  Propagation.NEVER 不能在有事务情况下使用，有则抛出异常
     *  Propagation.MANDATORY 必须在事务下执行，否则抛出异常
     *
     * @return
     */
    @GetMapping
    public String testMapper(){
        LsUser user = userMapper.selectById(1L);
        if(Objects.isNull(user)){
            return "裂开了";
        }
        return user.getUserName();
    }

    @PostMapping
    @Transactional(rollbackFor = Throwable.class)
    public String postMapper() throws IOException{
        LsUser user = new LsUser();
        user.setUserId(1000L).setUserName("京东")
            .setCreateBy(1L).setUpdateTime(new Date()).setCreateTime(new Date());

        Throwable x = new Throwable();
        if(x instanceof Throwable){

        }


        userMapper.insert(user);
        if(1==1){
            // 默认异常回滚是runtime,IOException 不是runtimeException子类 所以不会回滚。
            throw new IOException();
        }
        return user.getUserName();
    }

    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(10);
//        semaphore.acquire();
//        semaphore.acquire(2);
//
        Lock lock =new ReentrantLock();
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(2,2);

//        lock.tryLock(2, TimeUnit.SECONDS);
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.incrementAndGet();
//
//        HashSet<String> set  = new HashSet<>();
//        set.add("1");
//        set.add("1");
//
//
    /*    HashMap hashMap = new HashMap(2);
        hashMap.put(new Object(),new Object());
//
        ConcurrentHashMap hash2Map = new ConcurrentHashMap(2);
        hash2Map.put(new Object(),new Object());
        Long a = null;
        System.out.println("123" + a);


        ReentrantReadWriteLock lock22 = new ReentrantReadWriteLock();

        CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();
        arrayList.add(1);

        List list = Collections.synchronizedList(new ArrayList<>());
        list.add(1);

        new Object().wait();
        Thread.sleep(2);*/
        CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();
        arrayList.add(1);

        ReentrantReadWriteLock loc22 = new ReentrantReadWriteLock();
        Lock l2ock = loc22.readLock();

        short t = 2;
        HashMap hashtable = null;
        hashtable.put(null,null);

        HashMap map = new HashMap(2);
        map.put(null,null);

        ConcurrentHashMap map2 = new ConcurrentHashMap();
        map2.put(null,null);


        // 工厂模式 -- 返回的是抽象类或者接口的实现
        Calendar.getInstance();
        // 抽象工厂模式 -- 返回的是可以用来创建抽象类或者接口的工厂类。
        XPathFactory.newInstance().newXPath();
        // 生成器模式 创建方法返回的是实例本身，例如Stringbuffer 的 append 或者链式调用。
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("2");

        //单例模式  枚举，懒汉，恶汉，
        Runtime.getRuntime();

        //适配器模式 接收一个与当前类不同抽象类或者接口的实例作为参数，返回一个经过修饰或者重写给定参数实例的抽象类或接口的实现。
         List a = Arrays.asList();

         //装饰器模式  通过构造方法返回一个增加了额外方法的参数实例。
        List b = Collections.synchronizedList(new ArrayList<>());

        //代理模式 proxy。newProxyInstance.
    }


}
