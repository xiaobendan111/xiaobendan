package com.wmj.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.entity.Product;
import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.CartService;
import com.wmj.mmall.service.ProductCategoryService;
import com.wmj.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    @GetMapping("level/{type}/{id}")
    public String levelTwo(Model model, HttpSession session, @PathVariable("type") Integer type, @PathVariable("id") Integer id){
        model.addAttribute("level",service.findByLevelId(type, id));
        model.addAttribute("list",productCategoryService.getAllProductCategoryVo());
        User user = (User) session.getAttribute("user");
        if (user!=null)
        model.addAttribute("carts",cartService.findAllCartByUserId(user.getId()));
        return "productList";
    }
    @GetMapping("shangpin/{id}")
    public String shangpin(Model model,@PathVariable("id") Integer id,HttpSession session){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        model.addAttribute("shangpin",service.getOne(wrapper));
        model.addAttribute("list",productCategoryService.getAllProductCategoryVo());
        User user = (User) session.getAttribute("user");
        if (user!=null)
            model.addAttribute("carts",cartService.findAllCartByUserId(user.getId()));
        return "productDetail";
    }

}

