package com.wmj.mmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {
    @Autowired
    private CartService service;

    @Test
    void test(){
        System.out.println(service.findAllCartByUserId(10));
    }
    @Test
    void test1(){
        service.removeById(224);
    }
}