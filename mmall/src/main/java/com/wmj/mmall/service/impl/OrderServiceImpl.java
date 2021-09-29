package com.wmj.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.entity.*;
import com.wmj.mmall.mapper.*;
import com.wmj.mmall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wmj.mmall.service.UserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderMapper mapper;
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public boolean save(User user, Orders orders,String address,String remark) {
        //判断是不是新地址
        if (orders.getUserAddress().equals("newAddress")){
            UserAddress userAddress = new UserAddress();
            userAddress.setUserId(user.getId());
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsdefault(1);
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("isdefault",1);
            wrapper.eq("user_id",user.getId());
            UserAddress userAddress1 = userAddressMapper.selectOne(wrapper);
            if (userAddress1 != null){
                userAddress1.setIsdefault(0);
                //修改之前的默认地址为0 为新加的地址让位
                userAddressMapper.updateById(userAddress1);
            }
            userAddressMapper.insert(userAddress);
            orders.setUserAddress(userAddress.getAddress());
        }
        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);
        int i = mapper.insert(orders);

        //根据userid 查到cart 然后把需要的数据甜到 order_detail
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        List<Cart> carts = cartMapper.selectList(wrapper);
        for (Cart cart:carts){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCost(cart.getCost());
            orderDetail.setProductId(cart.getProductId());
            orderDetail.setQuantity(cart.getQuantity());
            //添加完拿就行了 据说是会回填进去
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insert(orderDetail);
        }

        //添加完成之后把当前用户的购物车清空
        wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        cartMapper.delete(wrapper);

        if (i==1)
            return true;
        else
            return false;
    }

    @Override
    public List<OrdersVo> findByUser(Integer id) {
        List<OrdersVo> ordersVos = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",id);
        List<Orders> ordersList = mapper.selectList(wrapper);
        for (Orders orders:ordersList){
            OrdersVo ordersVo = new OrdersVo();
            List<OrderDetailVo> orderDetailVos = new ArrayList<>();
            BeanUtils.copyProperties(orders,ordersVo);
            wrapper = new QueryWrapper();
            wrapper.eq("order_id",orders.getId());
            List<OrderDetail> orderDetails  = orderDetailMapper.selectList(wrapper);
            for (OrderDetail orderDetail:orderDetails){
                OrderDetailVo orderDetailVo = new OrderDetailVo();
                BeanUtils.copyProperties(orderDetail,orderDetailVo);
                orderDetailVo.setProduct(productMapper.selectById(orderDetail.getProductId()));
                orderDetailVos.add(orderDetailVo);
                ordersVo.setOrderDetailVos(orderDetailVos);
            }
            ordersVos.add(ordersVo);
        }
        return ordersVos;
    }
}
