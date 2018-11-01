package com.hellospringdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Le on 1/1/2016.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
