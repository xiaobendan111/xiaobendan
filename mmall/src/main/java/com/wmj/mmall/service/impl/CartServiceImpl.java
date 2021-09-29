package com.wmj.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.entity.Cart;
import com.wmj.mmall.entity.CartVo;
import com.wmj.mmall.entity.Product;
import com.wmj.mmall.enums.ResultEnum;
import com.wmj.mmall.exception.MmallException;
import com.wmj.mmall.mapper.CartMapper;
import com.wmj.mmall.mapper.ProductMapper;
import com.wmj.mmall.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper mapper;

    @Autowired
    private ProductMapper productMapper;
    @Override
    public boolean save(Cart entity) {
        int i;
        Product product = productMapper.selectById(entity.getProductId());

        if (product.getStock() <= 0){
            log.error("【添加购物车异常】!库存不足 stock={}",product.getStock());
            throw new MmallException(ResultEnum.STOCK_ERROR);
        }

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("product_id",entity.getProductId());
        Cart cart = mapper.selectOne(wrapper);
        if (cart == null){
            i = mapper.insert(entity);
            product.setStock(product.getStock()-entity.getQuantity());
        }else {
            product.setStock(product.getStock()-entity.getQuantity());
            entity.setCost(cart.getCost()+entity.getCost());
            entity.setQuantity(cart.getQuantity()+entity.getQuantity());
            entity.setId(cart.getId());
            i = mapper.updateById(entity);
        }



        productMapper.updateById(product);
        if (i==1) return true;
        return false;
    }

    @Override
    public List<CartVo> findAllCartByUserId(Integer id) {
        List<CartVo> cartVos = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",id);
        List<Cart> carts = mapper.selectList(wrapper);
        for (Cart cart:carts){
            CartVo cartVo = new CartVo();
            Product product = productMapper.selectById(cart.getProductId());
            BeanUtils.copyProperties(product,cartVo);
            BeanUtils.copyProperties(cart,cartVo);
            cartVos.add(cartVo);
        }
        return cartVos;
    }

    @Override
    public boolean removeById(Serializable id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        Cart cart = mapper.selectOne(wrapper);
        if (mapper.deleteById(id) == 1){
            Product product = productMapper.selectById(cart.getProductId());
            Product product1 = new Product();
            product1.setId(product.getId());
            product1.setStock(cart.getQuantity()+product.getStock());
            productMapper.updateById(product1);
            return true;
        }
        return false;
    }
}
