package com.wmj.mmall.controller;


import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.CartService;
import com.wmj.mmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderService service;
    @Autowired
    private CartService cartService;
    @GetMapping("orders")
    public ModelAndView orders(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("orderList",service.findByUser(user.getId()));
        modelAndView.addObject("carts",cartService.findAllCartByUserId(user.getId()));
        return modelAndView;
    }

}

