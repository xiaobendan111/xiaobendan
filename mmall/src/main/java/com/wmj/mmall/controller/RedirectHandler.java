package com.wmj.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectHandler {

    @GetMapping("/{url}")
    public String redirec(@PathVariable("url") String url){
        return url;
    }


//    redirect重定向 url地址栏的路径会改变  forward转发 url地址栏路径不会改变
    @GetMapping("")
    public String main(){
        return "redirect:/productCategory/list";
    }

}
