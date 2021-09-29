package com.wmj.mmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void a(){
        QueryWrapper wrapper = new QueryWrapper();
        Map<String,Object> map = new HashMap<>();
        map.put("login_name","cgn");
        map.put("password","123");
        wrapper.allEq(map);
        System.out.println(userMapper.selectList(wrapper));
    }

}