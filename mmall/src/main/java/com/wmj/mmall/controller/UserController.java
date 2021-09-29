package com.wmj.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.entity.User;
import com.wmj.mmall.service.CartService;
import com.wmj.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private CartService cartService;


    @PostMapping("register")
    public String register(User user,Model model){
        boolean b = false;
        try {
            b = service.save(user);
        } catch (Exception exception) {
            model.addAttribute("msg",user.getLoginName()+"已被注册,请换一个!");
            model.addAttribute("user",user);
            return "register";
        }
        if (b) return "login";
        model.addAttribute("msg","添加失败");
        return "register";

    }

    @PostMapping("/login")
    public String login(Model model, String loginName, String passWord, HttpSession session){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name",loginName);
        wrapper.eq("password",passWord);
        User user = service.getOne(wrapper);
        if (user==null){
            model.addAttribute("msg","账号密码错误");
            return "login";
        }else {
            session.setAttribute("user",user);
            return "redirect:/productCategory/list";
        }

    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("userInfo")
    public ModelAndView userInfo(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("carts",cartService.findAllCartByUserId(user.getId()));
        return modelAndView;
    }

}

