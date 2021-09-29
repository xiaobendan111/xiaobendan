package com.wmj.mmall.controller;


import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.CartService;
import com.wmj.mmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductCategoryService service;

    @GetMapping("list")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("list",service.getAllProductCategoryVo());
        User user = (User) session.getAttribute("user");
        if (user!=null)
        modelAndView.addObject("carts",cartService.findAllCartByUserId(user.getId()));
        return modelAndView;
    }

}

