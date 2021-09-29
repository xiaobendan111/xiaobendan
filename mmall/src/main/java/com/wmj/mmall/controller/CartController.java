package com.wmj.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.entity.Cart;
import com.wmj.mmall.entity.CartVo;
import com.wmj.mmall.entity.Product;
import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.CartService;
import com.wmj.mmall.service.ProductService;
import com.wmj.mmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("add/{productid}/{productPrice}/{quantity}")
    public String add(Model model,
                      @PathVariable("productid") Integer productid,
                      @PathVariable("productPrice") float price,
                      @PathVariable("quantity") Integer quantity,
                      HttpSession session){
        Cart cart = new Cart();
        cart.setProductId(productid);
        cart.setCost(price*quantity);
        cart.getCost();
        cart.setQuantity(quantity);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        try {
            if (service.save(cart)){
                return "redirect:/cart/jinru";
            };
        } catch (Exception exception) {
            return "redirect:/productCategory/list";
        }

        return null;
    }

    @PostMapping("update/{id}/{quantity}/{cost}")
    @ResponseBody
    public String update(@PathVariable("id") Integer id,
                         @PathVariable("quantity") Integer quantity,
                         @PathVariable("cost") Integer cost){
        Cart cart = service.getById(id);
        Integer a = cart.getQuantity();
        Product product = productService.getById(cart.getProductId());
        cart.setQuantity(quantity);
        cart.setCost(cost);
        if (service.updateById(cart)){
            product.setStock(product.getStock()-quantity+a);
            productService.updateById(product);
            return "ok";
        }else {
            return "no";
        }
    }

    @RequestMapping("jinru")
    public String jinru(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("carts",service.findAllCartByUserId(user.getId()));
        return "settlement1";
    }

    @RequestMapping("settlement2")
    public String settlement2(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("carts",service.findAllCartByUserId(user.getId()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        model.addAttribute("addressList",userAddressService.list(wrapper));
        return "settlement2";
    }

    @GetMapping("removeCart/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        if (service.removeById(id)){
            return "redirect:/cart/jinru";
        }
        return null;
    }

}

