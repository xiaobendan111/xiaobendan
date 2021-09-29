package com.wmj.mmall.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrdersVo {
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 总金额
     */
    private Float cost;


    /**
     * 订单号
     */
    private String serialnumber;

    private List<OrderDetailVo> orderDetailVos;
}
