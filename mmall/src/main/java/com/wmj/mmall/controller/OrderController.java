package com.wmj.mmall.controller;


import com.wmj.mmall.entity.Orders;
import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.CartService;
import com.wmj.mmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService  service;
    @Autowired
    private CartService cartService;

    @PostMapping("settlement3")
    public ModelAndView settlement3(Orders orders, HttpSession session, String address, String remark){
        User user = (User) session.getAttribute("user");
        service.save(user,orders,address,remark);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("carts",cartService.findAllCartByUserId(user.getId()));
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }

}

