package com.laptrinh;

import db.dao.DbDao;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import Model.Saler;
import Model.Admin_Saler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/shopfuniture/admin/")
public class AdminController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    DbDao dao;
    @GetMapping("/")
    @ResponseBody
    public String saler(){
        return "this is page of admin";
    }
    @GetMapping("register")
    @ResponseBody
    public String register(@RequestParam(required = false) String employee,@RequestParam(required = false) String pass1,
                           @RequestParam(required = false) String pass2, @RequestParam(required = false) String name,
                           @RequestParam(required = false) String phone,@RequestParam(required = false) String address) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if (dao.callmail(employee))
                return "username existed";
            else if (!pass1.equals(pass2))
                return "password confirm must incorrect";
            else if (!(address.split(",").length == 3))
                return "format address incorrect";
            else if (pass1.length() < 8) {
                return "password must > 7 chars";
            } else if (dao.account_saler(employee, pass1)) {
                dao.insert_saler(address, name, phone, employee);
                return "successful";
            }
        }catch (Exception e){
        }
        return "some thing wrong happened";
    }
    @GetMapping("list-employee")
    public String getAllSaler(Model m){
        ArrayList<Saler> a=dao.getAllSaler();
        ArrayList<Admin_Saler> a2=dao.getAllFeedBack();
        if(a2!=null){
           List<Admin_Saler> a3= a2.stream().filter(ad -> ad.isStatus()==false).collect(Collectors.toList());
           m.addAttribute("news",a3);
            m.addAttribute("feed",a2);
        }
        if(a!=null&&a.size()>0)
            m.addAttribute("list",a);
        return "view_saler";
    }
    @PostMapping("delete/{id}")
    @ResponseBody
    public String deletebyid(@PathVariable String id){
        try {
            int i = dao.deleteSaler(Integer.parseInt(id));
            return "done";
        }catch (Exception e){
System.out.println(e.getMessage());
        }
        return "fail";
    }
    @PostMapping("change_active/{id}-{status}")
    @ResponseBody
    public String changeActive(@PathVariable(value = "id") String id,@PathVariable(value = "status") String status1){

        try{
            int i=Integer.parseInt(id);
            int status=Integer.parseInt(status1);
            if(dao.changeActive(i,status)){
                return "done";}
        }catch (Exception e){

        }
        return "fail";
    }
    @PostMapping("feed-back")
    @ResponseBody
    public String feedback(@RequestParam String content,@RequestParam String salerid) throws Exception{
     String authen= SecurityContextHolder.getContext().getAuthentication().getName();
       try {
           request.setCharacterEncoding("UTF-8");
           response.setCharacterEncoding("UTF-8");
     boolean b= dao.sendFeedback(content, false, Integer.parseInt(salerid.trim()), authen);
        return  b?"true":"false";
       }catch (Exception e){
          return "false";
      }
    }
@GetMapping("update-feedback")
@ResponseBody
    public String updatefeedback(){
        dao.updateFeedBack();
        return "true";
}
}
