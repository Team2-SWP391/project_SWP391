package com.laptrinh;


import db.dao.DbDao;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@ComponentScan(basePackages = {"db.dao", "service"})
@RequestMapping("/admin/")
public class homeController{
    @Autowired
    DbDao db;
    @GetMapping("home")
    public  String homeC(HttpServletRequest request, HttpServletResponse response, Model m){

        request.setAttribute("order", db.getList());

        return "index";
    }
    @GetMapping("upload")
    public String Upload(HttpServletRequest request, HttpServletResponse response, Model m){
        String id = request.getParameter("Oid");
        request.setAttribute("orderE", db.getOrderByOid(id));
        return "edit";
    }
    @GetMapping("edit")
    public void EditC(HttpServletRequest request, HttpServletResponse response, Model m){
        String id = request.getParameter("Oid");
        request.setAttribute("orderE", db.getOrderByOid(id));
        try {
            response.sendRedirect("/admin/upload");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
