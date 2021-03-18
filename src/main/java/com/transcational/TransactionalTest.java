package com.transcational;

import com.transcational.entity.LsUser;
import com.transcational.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String testMapper(){
        LsUser user = userMapper.selectById(1L);
        if(Objects.isNull(user)){
            return "裂开了";
        }
        return user.getUserName();
    }

}
