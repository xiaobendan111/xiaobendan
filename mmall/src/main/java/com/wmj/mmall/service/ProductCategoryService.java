package com.wmj.mmall.service;

import com.wmj.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wmj.mmall.entity.ProductCategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVo> getAllProductCategoryVo();
}
