package com.laptrinh;

import db.dao.DbDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.JwtService;
import service.UserConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Optional;

import static com.laptrinh.UserController.message;

@Controller
@RequestMapping("/shopfuniture/")
public class Common {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserConfig userConfig;
    @Autowired
    public DbDao dao;
    private void checkuser(ModelMap m){
        if(request.getCookies()!=null&&Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equals("remember_user"))){
            String token="";
            for (Cookie c: request.getCookies()) {
                if(c.getName().equals("remember_user")){
                    token=c.getValue();
                    break;
                }
            }
            String name = jwtService.getUsernameFromToken(token);
            UserDetails userDetails = userConfig.loadUserByUsername(name);
            if(userDetails!=null&&userDetails.getAuthorities().stream().anyMatch(au ->au.getAuthority().equals("USER") )
                    &&userDetails.getUsername()!=null){
                m.addAttribute("user",userDetails.getUsername());
            }
        }

    }
    @GetMapping("login")
    public String hello(Model m) {
        if(!message.equals("")){
            m.addAttribute("message",message);
            message="";
        }
        if(!ConfigSecurity.notify.equals("")){
            m.addAttribute("notify",ConfigSecurity.notify);
            ConfigSecurity.notify="";
        }
        return "login";
    }
    @GetMapping(value = "logout" )
    public String logout() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        Cookie c = new Cookie("remember_user", "");
        c.setMaxAge(0);
        c.setPath("/");
        response.addCookie(c);
        return "redirect:/shopfuniture/login";
    }
    //first homepage
    @GetMapping(value = {"","home-page" , "trang-chu"})
    public String HomePage(ModelMap m){
        if(!message.equals("")){
            m.addAttribute("message",message);
            message="";
        }
        response.setContentType("text/html;charset=UTF-8");
        checkuser(m);

        m.addAttribute("list", dao.getDataSlide());
        m.addAttribute("product",dao.getDataProduct());
        m.addAttribute("category",dao.getDataCate());
        m.addAttribute("productNew",dao.getDataNewProduct());
        int count=dao.getTotalProduct();
        int endPage=count/3;
        if(count%3!=0){
            endPage++;
        }

        m.addAttribute("endP",endPage);
        m.addAttribute("listPaging",dao.pagingProduct(1));
        return "index";
    }
    //paging hompage
    @GetMapping("home-page/{index}")
    public String HomePagePaging(@PathVariable String index,ModelMap m){
        response.setContentType("text/html;charset=UTF-8");
        if(index==null){
            index= String.valueOf(1);
        }
        checkuser(m);
        m.addAttribute("list", dao.getDataSlide());
        m.addAttribute("product",dao.getDataProduct());
        m.addAttribute("category",dao.getDataCate());
        m.addAttribute("productNew",dao.getDataNewProduct());
        int count=dao.getTotalProduct();
        int endPage=count/3;
        if(count%3!=0){
            endPage++;
        }
        m.addAttribute("endP",endPage);
        m.addAttribute("listPaging",dao.pagingProduct(Integer.parseInt(index)));
        m.addAttribute("tag",index);

        return "index";
    }
    //first category
    @RequestMapping("category")
    public String Category(ModelMap m){
        checkuser(m);
        response.setContentType("text/html;charset=UTF-8");
        m.addAttribute("list", dao.getDataSlide());
        m.addAttribute("product",dao.getDataProduct());
        m.addAttribute("category",dao.getDataCate());
        m.addAttribute("productNew",dao.getDataNewProduct());
        int count=dao.getTotalProduct();
        int endPage=count/3;
        if(count%3!=0){
            endPage++;
        }
        m.addAttribute("endP",endPage);
        m.addAttribute("listPaging",dao.pagingProduct(1));
        m.addAttribute("tag",1);
        return "category";
    }
    //paging category
    @GetMapping("categoryPaging/{index}")
    public String CategoryPaging(@PathVariable String index,ModelMap m){
        checkuser(m);
        response.setContentType("text/html;charset=UTF-8");
        m.addAttribute("list", dao.getDataSlide());
        m.addAttribute("product",dao.getDataProduct());
        m.addAttribute("category",dao.getDataCate());
        m.addAttribute("productNew",dao.getDataNewProduct());
        int count=dao.getTotalProduct();
        int endPage=count/3;
        if(count%3!=0){
            endPage++;
        }
        m.addAttribute("endP",endPage);
        m.addAttribute("listPaging",dao.pagingProduct(Integer.parseInt(index)));
        m.addAttribute("tag",index);
        return "category";
    }
    // Phan loai product theo category
    @GetMapping(value={"category/{id}","{id}"})
    public String Category_product(@PathVariable String id, ModelMap m){
        checkuser(m);
        response.setContentType("text/html;charset=UTF-8");
        m.addAttribute("idCate",id);
        m.addAttribute("CateIdProduct",dao.getDataProductByCategoryId(id));
        m.addAttribute("list", dao.getDataSlide());
        m.addAttribute("category",dao.getDataCate());
        m.addAttribute("productNew",dao.getDataNewProduct());
        return "category_product";
    }

    @GetMapping("single_product_item/{id}")
    public String single_product_item(@PathVariable String id,ModelMap m){
        checkuser(m);
        response.setContentType("text/html;charset=UTF-8");
        //m.addAttribute("idCate",id);
        //m.addAttribute("CateIdProduct",dao.getDataProductByCategoryId(id));
        m.addAttribute("list", dao.getDataSlide());
        m.addAttribute("category",dao.getDataCate());
        m.addAttribute("productId",dao.getDataProductById(id));
        m.addAttribute("productNew",dao.getDataNewProduct());
        return "single-product";
    }


}
