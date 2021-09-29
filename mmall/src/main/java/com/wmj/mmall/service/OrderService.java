package com.wmj.mmall.service;

import com.wmj.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wmj.mmall.entity.OrdersVo;
import com.wmj.mmall.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
public interface OrderService extends IService<Orders> {
    public boolean save(User user,Orders orders,String address,String remark);
    public List<OrdersVo> findByUser(Integer id);
}
