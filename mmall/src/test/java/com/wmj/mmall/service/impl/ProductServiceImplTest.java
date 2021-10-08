package com.wmj.mmall.service.impl;

import com.wmj.mmall.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductService service;

    @Test
    void test1(){
        System.out.println(service.sousuo("香"));
    }
}