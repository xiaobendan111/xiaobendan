package com.wmj.mmall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;
    @Test
    void findAll(){
        cartMapper.selectList(null).forEach(System.out::println);
    }
}