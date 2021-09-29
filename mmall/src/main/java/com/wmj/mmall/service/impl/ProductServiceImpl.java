package com.wmj.mmall.service.impl;

import com.wmj.mmall.entity.Product;
import com.wmj.mmall.mapper.ProductMapper;
import com.wmj.mmall.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductService service;

    @Override
    public List<Product> findByLevelId(Integer type,Integer id) {
        Map<String,Object> map = new HashMap<>();
        if (type == 1) map.put("categorylevelone_id",id);
        if (type == 2) map.put("categoryleveltwo_id",id);
        if (type == 3) map.put("categorylevelthree_id",id);
        List<Product> products = service.listByMap(map);
        return products;
    }
}
