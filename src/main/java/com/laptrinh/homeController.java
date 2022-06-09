package com.laptrinh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @RequestMapping(value = "home")
    public String Home(){
        return "index";
    }
}
