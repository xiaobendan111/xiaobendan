package com.wmj.mmall.entity;

import lombok.Data;

@Data
public class CartVo {
    private Integer id;
    private Integer quantity;
    private Integer productId;
    private Integer stock;
    private float cost;
    private Float price;
    private String name;
    private String fileName;
}
