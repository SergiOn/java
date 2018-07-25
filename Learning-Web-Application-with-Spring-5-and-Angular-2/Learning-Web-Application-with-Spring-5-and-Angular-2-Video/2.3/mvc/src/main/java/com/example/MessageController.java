package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @RequestMapping("/form")
    public String form(){
        return "form";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name") String name,
                        Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/morning")
    public String morning(@RequestParam(value = "name") String name,
                        Model model){
        model.addAttribute("name", name);
        return "morning";
    }
}
