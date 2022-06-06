package com.laptrinh;

import Model.User;
import db.dao.DbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Handler;
import service.JwtService;
import service.Sendmail;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
@ComponentScan(basePackages = {"db.dao","service"})
@RequestMapping("/shopfuniture/")
public class UserController {
    public static String message="";
    @Autowired
    JwtService jwtService;
    @Autowired
    Handler handler;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    Sendmail sendmail;
    @Autowired
    public DbDao dao;
    @GetMapping("reset-password")
    public String Resetpass(){
        return "";
    }
    @GetMapping("confirm")
    public String confirmmail(Model m){
        if(!message.equals("")){
            m.addAttribute("message",message);
            message="";
        }
        return "confirm";
    }
    @PostMapping("confirm")
    public String confirmed(@RequestParam String email){
        if(email==null||email.trim().equals("")){
            message="email not valid";
            return "redirect:/shopfuniture/confirm";
        }
        UUID u= UUID.randomUUID();
        String uid =u.toString();
           boolean send= sendmail.sendmail(request,response,email,uid);
        if(!send){
            message="connect interrupted ,please try again";
            return "redirect:/shopfuniture/confirm";
        }
        else{
           HttpSession h= request.getSession();
           h.setMaxInactiveInterval(60);
           h.setAttribute("uid",uid);
           h.setAttribute("mail",email);
           message="the code sent to your mail, check out and active your account";
            return "redirect:/shopfuniture/login";
        }
    }
    @GetMapping("admin")
    @ResponseBody
    public String admin() {
        return "hoang anh admin";
    }
    @GetMapping("saler")
    @ResponseBody
    public String saler() {
        return "hoang anh saler";
    }
    @GetMapping("user1")
    @ResponseBody
    public String user(){
     if(!message.equals("")){
         message="";
         return "welcome user "+SecurityContextHolder.getContext().getAuthentication().getName(); }
        return "user "+SecurityContextHolder.getContext().getAuthentication().getName();
    }
//    @GetMapping("register")
//    public String register(Model m){
//        if(!message.equals("")){
//            m.addAttribute("message",message);
//           message="";}
//        return "register";
//    }
    @GetMapping("register")
    public String register(@RequestParam("uid") String uid){
         if(request.getSession().getAttribute("uid")==null||uid==null||
                 !uid.equals(request.getSession().getAttribute("uid"))){
             message="the code expired, confirm the code by account email";
             return "redirect:/shopfuniture/confirm?error";
         }
         if(uid.equals(request.getSession().getAttribute("uid"))){
          String id = (String) request.getSession().getAttribute("uid");
          String email=(String) request.getSession().getAttribute("mail");
             HttpSession h= request.getSession();
             h.setMaxInactiveInterval(60*30);
             h.setAttribute("uid",uid);
             h.setAttribute("mail",email);
         }
        return "register";
    }
    @PostMapping("register")
    public String Postregister(@RequestParam("password") String pass,@RequestParam("confirmpassword") String pass2,@RequestParam("register") String name){
     if(!pass.trim().equals(pass2.trim()))
         message="password doesn't match";
     else if(pass.trim().equals("")||pass2.trim().equals("")||name.trim().equals(""))
         message="account and password can't empty";
     else if(dao.callmail(name))
         message="account name exist";
     else if(pass.length()<8)
         message="password length must >=8";
     else if(request.getSession().getAttribute("mail")==null){
         message="session for register expired,please try again";
         return "redirect:/shopfuniture/confirm?error";
     }
     else{
         try {
             dao.register(name, pass);
            String authen= dao.registercustomer(name, (String)request.getSession().getAttribute("mail"), false, null, null);
            if(authen==null)
                throw new Exception("");
            request.getSession().removeAttribute("mail");
             request.getSession().removeAttribute("uid");
             Cookie c=new Cookie("remember_user",jwtService.generateTokenLogin(name));
             c.setMaxAge(60*60*24);
             response.addCookie(c);
             message="register successful\n welcome to furniture shop";
         }catch (Exception e){
             message="application can't feedback in moment";
             return "redirect:/shopfuniture/register";
         }
         return "redirect:/shopfuniture/user1";
     }
        return "redirect:/shopfuniture/register";
    }
    @GetMapping("login")
    public String hello(Model m) {
      //  Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(!message.equals("")){
            m.addAttribute("message",message);
            message="";
        }
//        if(!Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equals("remember_user"))&&authentication!=null&&
//                authentication.isAuthenticated()){
//            return "redirect:/shopfuniture/user1";
//        }
//     String token=jwtService.generateTokenLogin("anh123");
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        if(authentication!=null)
//       new SecurityContextLogoutHandler().logout(request,response,authentication);
     //   UUID u=UUID.randomUUID();
//        if(authentication!=null&&authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
//        return "redirect:/spring-mvc/admin";
//       else if(authentication!=null&&authentication.getAuthorities().contains(new SimpleGrantedAuthority("USER")))
//            return "redirect:/spring-mvc/user1";
        //else
            return "login";
    }
    @GetMapping(value = "email")
    public String loginemail(){
        return "redirect:https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/shopfuniture/user-mail&response_type=code&client_id=868310752307-cumrlt756s3ahtpkhd67f0514c6cm2sm.apps.googleusercontent.com&approval_prompt=force";
    }
    @GetMapping(value = "user-mail")
    @ResponseBody
    public String usermail() throws IOException {
        response.setContentType("text/html");
        String refreshtoken=request.getParameter("code");
        if(request.getParameter("code")!=null){
            try{
        String authentoken=handler.getToken(refreshtoken);
       User u= handler.getUserInfo(authentoken);
       if(dao.callmail(u.getEmail())==false){
       dao.insertmailaccount(u,authentoken);
       if(dao.getidaccount(u.getEmail())!=0)
           dao.insertmailcustomer(u,dao.getidaccount(u.getEmail()));}
       Cookie c= new Cookie("remember_user",jwtService.generateTokenLogin(u.getEmail()));
       c.setMaxAge(60*60*24);
       response.addCookie(c);
         return "user :"+u.getEmail()+"<img src="+u.getPicture()+">";}
            catch(Exception e){
                return "can't connect to google api";
            }
        }

        return "can't connect to google api";
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
    @GetMapping("exception")
    public String exception(){
return "403";
    }

    @GetMapping("reset-pass")
    public String reset_pass(){
        return "reset_password";
    }
}
