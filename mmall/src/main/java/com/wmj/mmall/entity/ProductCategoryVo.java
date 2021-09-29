package com.wmj.mmall.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVo {
    private Integer id;
    private String name;
    private String banner;
    private Integer type;
    private String top;
    List<ProductCategoryVo> children;
    List<ProductVo> productVoList;


    public ProductCategoryVo(Integer id, String name, String banner, Integer type, String top) {
        this.id = id;
        this.name = name;
        this.banner = banner;
        this.type = type;
        this.top = top;
    }
}
