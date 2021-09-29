package com.wmj.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.CartService;
import com.wmj.mmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/userAddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private CartService cartService;
    @GetMapping("findAddress")
    public ModelAndView fing(HttpSession session){
     User user = (User) session.getAttribute("user");
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("userAddressList");
     modelAndView.addObject("carts",cartService.findAllCartByUserId(user.getId()));
     QueryWrapper wrapper = new QueryWrapper();
     wrapper.eq("user_id",user.getId());
     modelAndView.addObject("addressList",userAddressService.list(wrapper));
     return modelAndView;
    }

    @GetMapping("del/{id}")
    public String del(@PathVariable("id") Integer id){
        userAddressService.removeById(id);
        return "redirect:/userAddress/findAddress";
    }
}

