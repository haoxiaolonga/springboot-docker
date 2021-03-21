package com.transcational;

import com.transcational.entity.LsUser;
import com.transcational.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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

}
