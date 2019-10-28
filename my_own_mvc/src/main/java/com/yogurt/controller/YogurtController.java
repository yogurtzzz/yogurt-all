package com.yogurt.controller;

import com.yogurt.annotations.Controller;
import com.yogurt.annotations.RequestMapping;

@Controller
public class YogurtController {
    @RequestMapping("/yogurtMvc")
    public String hello(){
        return "hello,yogurt mvc";
    }
}
