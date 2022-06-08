package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homepage {
    @GetMapping(value = {"/", "/home-page" , "/trang-chu"})
    public String HomePage(){
        return "home/index";
    }
    @GetMapping(value = {"/product"})
    public String Product(){
        return "home/product";
    }
}

