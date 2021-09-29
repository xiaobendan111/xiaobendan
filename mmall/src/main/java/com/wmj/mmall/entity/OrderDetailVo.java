package com.wmj.mmall.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailVo {
    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 消费
     */
    private Float cost;

    private Product product;

}
