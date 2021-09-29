package com.wmj.mmall.service;

import com.wmj.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wmj.mmall.entity.CartVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
public interface CartService extends IService<Cart> {
    public List<CartVo> findAllCartByUserId(Integer id);
}
