package com.wmj.mmall.entity;

import lombok.Data;

@Data
public class ProductVo {
    private Integer id;
    private String name;
    private Float price;
    private String fileName;

    public ProductVo(Integer id, String name, Float price, String fileName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fileName = fileName;
    }
}
