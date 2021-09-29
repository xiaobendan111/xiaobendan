package com.wmj.mmall.service;

import com.wmj.mmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
public interface ProductService extends IService<Product> {
    public List<Product> findByLevelId(Integer type,Integer id);
}
