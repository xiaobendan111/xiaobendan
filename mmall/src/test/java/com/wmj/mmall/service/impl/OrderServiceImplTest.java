package com.wmj.mmall.service.impl;

import com.wmj.mmall.entity.Orders;
import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService service;
    @Test
    void test(){
        User user = new User();
        user.setId(2);
        user.setLoginName("dwa");
        Orders orders = new Orders();
        orders.setCost(153.2f);
        orders.setUserAddress("dwada");
    }


    @Test
    void test1(){
        service.findByUser(10);
    }
}