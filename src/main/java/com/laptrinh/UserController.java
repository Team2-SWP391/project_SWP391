package com.laptrinh;

import Model.Person;
import Model.User;
import db.dao.DbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.Handler;
import service.JwtService;
import service.Sendmail;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Controller
@ComponentScan(basePackages = {"db.dao","service", "exception"})
@RequestMapping("/shopfuniture/user")
@MultipartConfig
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
    @GetMapping("user1")
    @ResponseBody
    public String user(){
        System.out.println("login user");
        if(SecurityContextHolder.getContext().getAuthentication()!=null)
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "user logined";
    }
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
            return "redirect:/shopfuniture/user/confirm";
        }
        else if(dao.confirm_mail(email)){
            message="email was used, please try another";
            return "redirect:/shopfuniture/user/confirm";
        }
        UUID u= UUID.randomUUID();
        String uid =u.toString();
           boolean send= sendmail.sendmail(request,email,"uid="+uid,"create new account furnitureshop?", "Hello "+email+
            "We got a request to create new account furnitureshop from you.","/shopfuniture/user/register");
        if(!send){
            message="connect interrupted or not get response,please try again";
            return "redirect:/shopfuniture/user/confirm";
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
//    @GetMapping("user1")
//    @ResponseBody
//    public String user(){
//     if(!message.equals("")){
//         message="";
//         return "welcome user "+SecurityContextHolder.getContext().getAuthentication().getName(); }
//        return "user "+SecurityContextHolder.getContext().getAuthentication().getName();
//    }

    @GetMapping("register")
    public String register(@RequestParam(value = "uid",required = false) String uid,Model m){
        if(!message.trim().equals("")){
            m.addAttribute("message",message);
            message="";
            return "register";
        }
        if(request.getSession().getAttribute("mail")!=null&&uid==null){
            message="try to confirm your account email again";
            return "redirect:/shopfuniture/user/confirm?error";
        }
         if(request.getSession().getAttribute("uid")==null||uid==null){
             return "redirect:/shopfuniture/user/confirm";
         }
         if(!uid.equals(request.getSession().getAttribute("uid"))){
            message="the code expired or incorrect, confirm the code by account email";
            return "redirect:/shopfuniture/user/confirm?error";
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
    public String Postregister(@RequestParam(value = "password",required = false) String pass,
                               @RequestParam(value = "confirmpassword",required = false) String pass2,
                               @RequestParam(value = "register",required = false) String name){

        if(!pass.trim().equals(pass2.trim())){
         message="password doesn't match";}
     else if(pass.trim().equals("")||pass2.trim().equals("")||name.trim().equals(""))
         message="account and password can't empty";
     else if(dao.callmail(name))
         message="account name exist";
     else if(pass.length()<8)
         message="password length must >=8";
     else if(request.getSession().getAttribute("mail")==null){
         message="session for register expired,please try again";
         return "redirect:/shopfuniture/user/confirm?error";
     }
     else{
         try {
             User u=new User(null,name,false,null,null,null,null,null);
             dao.insertmailaccount(u,pass); ;
            String authen= dao.registercustomer(name, (String)request.getSession().getAttribute("mail"), false, null, null);
            if(authen==null)
                throw new Exception("");
             request.getSession().removeAttribute("mail");
             request.getSession().removeAttribute("uid");
             Cookie c=new Cookie("remember_user",jwtService.generateTokenLogin(name));
             c.setPath("/");
             c.setMaxAge(60*60*24);
             response.addCookie(c);
             message="register successful\n welcome to furniture shop";
         }catch (Exception e){
             message="application can't feedback in moment";
             return "redirect:/shopfuniture/user/register";
         }
         return "redirect:/shopfuniture/user/home-page";
     }
     if(request.getSession().getAttribute("uid")!=null){
        return "redirect:/shopfuniture/user/register?uid="+request.getSession().getAttribute("uid");}
     else{
         return "redirect:/shopfuniture/user/register";}
    }

    @GetMapping(value = "email")
    public String loginemail(){
        return "redirect:https://accounts.google.com/o/oauth2/auth?scope=email&" +
                "redirect_uri=http://localhost:8080/shopfuniture/user/google-mail&response_type=code&" +
                "client_id=868310752307-cumrlt756s3ahtpkhd67f0514c6cm2sm.apps.googleusercontent.com&approval_prompt=force";
    }
    @GetMapping(value = "google-mail")
    public String usermail() throws Exception {
        response.setContentType("text/html");
        String refreshtoken=request.getParameter("code");
        if(request.getParameter("code")!=null){
            try{
        String authentoken=handler.getToken(refreshtoken);
       User u= handler.getUserInfo(authentoken);
       if(dao.callmail(u.getEmail())==false){
       dao.insertmailaccount(u,authentoken);
       if(dao.getidaccount(u.getEmail())!=0)
           dao.insertmailcustomer(u,dao.getidaccount(u.getEmail()));
       }
       Cookie c= new Cookie("remember_user",jwtService.generateTokenLogin(u.getEmail()));
       c.setPath("/");
       c.setMaxAge(60*60*24);
       response.addCookie(c);
         return "redirect:/shopfuniture/home-page";}
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        throw new Exception("");
    }
    @GetMapping("exception")
    public String exception(){
return "403";
    }

    @GetMapping("reset-pass")
    public String reset_pass(Model m){
        if(!message.trim().equals("")){
            m.addAttribute("error",message);
            message="";
        }
        return "reset_password";
    }

    @PostMapping("reset-pass")
    public String reset_pass2(@RequestParam(value = "email" ,required = false)String mail){
         if(mail==null||!dao.confirm_mail(mail)){
             message="your email invalid , you must use your mail";
             return "redirect:/shopfuniture/user/reset-pass?error";
         }
         else{
             UUID u= UUID.randomUUID();
             String n=u.toString();
             boolean b=sendmail.sendmail(request,mail,"code="+n+"&email="+mail,"reset password furnitureshop?","We got a request to" +
                     " reset password furnitureshop for you.","/shopfuniture/user/forgot_password");
             if(b){
                 HttpSession h= request.getSession();
                 h.setMaxInactiveInterval(61*2);
                 h.setAttribute("code",n);
                 h.setAttribute("mail",mail);
                 message="Verification code sent to your mail\n for reset password just in 120s";
             }
             else{
                 message="application refused your request in a moment";
                 return "redirect:/shopfuniture/user/reset-pass?error";
             }
         }
        return "redirect:/shopfuniture/login";
    }
    @GetMapping("forgot_password")
    public String forgotpass(){
        return "newpassword";
    }

    @PostMapping("forgot_password")
    @ResponseBody
    public String forgotpass2(@RequestParam(value = "password",required = false)String pass,
                              @RequestParam(value = "code",required = false) String code,
                              @RequestParam(value = "email",required = false) String email){
        if(request.getSession().getAttribute("code")==null||
                request.getSession().getAttribute("mail")==null||code==null||email==null
                ||!code.equals(request.getSession().getAttribute("code"))||
                !email.equals(request.getSession().getAttribute("mail"))){
            return "<span style='color:red;background-color:rgba(255,109,44,0.55);'>something wrong happening!</span>";
        }
        else if(pass==null||pass.length()<8){
            return "<span style='color:red;background-color:rgba(255,109,44,0.55);'>password invalid!</span>";
        }
        else{
            boolean b=dao.changepassword(email,pass);
            if(b==true){
                request.getSession().removeAttribute("mail");
                request.getSession().removeAttribute("code");
            }
            if(!b){
            return "<span style='color:red;background-color:rgba(255,109,44,0.55);'>something wrong happening!</span>";}
            return "<span style='color:rgba(0,255,110,0.55);background-color:rgba(135,255,93,0.55);'>reset successful</span>";
        }
    }
    @GetMapping("profile")
    public String getprofile(Model m){
        if(!message.equals("")){
            m.addAttribute("message",message);
            message="";
        }
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       if(authentication==null||!authentication.isAuthenticated()||authentication.getName().trim().equals("")||
               !authentication.getAuthorities().stream().anyMatch(au -> au.getAuthority().equals("USER")))
           return "redirect:/shopfuniture/logout";
       else{
           m.addAttribute("user",authentication.getName());
           Person p=dao.getProfile(authentication.getName());
           m.addAttribute("profile",p);
        return "user_profile";}
    }

   @PostMapping(value = "profile")//
    public String profile(@RequestParam(required = false) MultipartFile file, @RequestParam(required = false) String name,
                          @RequestParam(required = false) String email, @RequestParam(required = false) String address, @RequestParam(required = false) String phone) throws Exception {
        request.setCharacterEncoding("UTF-8");
       response.setCharacterEncoding("UTF-8");
        String filename="";
        if(file!=null&&!file.getOriginalFilename().trim().equals("")) {
            try {
                String path = request.getServletContext().getRealPath("resources");
                File f = new File(path + "\\" + file.getOriginalFilename());
                int i = 1;
                filename=file.getOriginalFilename();
                while (f.exists()){
                    f = new File(path + "\\" +
                            file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."))
                            + i + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
                    filename=file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."))
                            + i + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                     i++;
                }
                f.createNewFile();
                file.transferTo(f);
            } catch (Exception e) {
             System.out.println("exception "+e.getMessage());
            }
        }
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null||authentication.getName()==null||authentication.getName().trim().equals(""))
            throw new Exception("");
            String authen = SecurityContextHolder.getContext().getAuthentication().getName();
            if (dao.checkmail(email, phone, authen)){
                message="update failed,your phone and your email must be unique";
                }
            else{
                Person person=new Person(null,null,email,name,phone,filename,address);
               String n= dao.registercustomer(authen,null,true,person,authen);
               if(n==null){
                   message="update failed,please try again!";
               }
               else
                   message="update successful!";
            }
        return "redirect:/shopfuniture/user/profile";
   }
    @PostMapping("change_password")
    @ResponseBody
    public String change_password(@RequestParam String old,@RequestParam String newpass,@RequestParam String newpass1) throws Exception {
        if(!newpass.trim().equals(newpass1.trim()))
            return "equal";
        String authen = SecurityContextHolder.getContext().getAuthentication().getName();
      int i= dao.change_pass(newpass,old,authen);
        return i>0?"success":i==-1?"pass":"fail";
   }
   @GetMapping("change_password")
    public String changepass(Model m){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       if(authentication==null||!authentication.isAuthenticated()||authentication.getName().trim().equals("")||
               !authentication.getAuthorities().stream().anyMatch(au -> au.getAuthority().equals("USER")))
           return "redirect:/shopfuniture/logout";
       else{
           m.addAttribute("user",authentication.getName());
           Person p=dao.getProfile(authentication.getName());
           m.addAttribute("profile",p);
           return "change_password"; }
   }

}
