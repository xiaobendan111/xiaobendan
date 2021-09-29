package com.wmj.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.service.UserAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAddressServiceImplTest {
    @Autowired
    private UserAddressService service;
    @Test
    void a (){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",10);
        service.list(wrapper);
        System.out.println(service.list(wrapper));
    }
}