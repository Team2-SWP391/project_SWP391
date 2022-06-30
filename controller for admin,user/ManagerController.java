package com.laptrinh;

import db.dao.DbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Order_detail;

import java.io.IOException;

@Controller
@RequestMapping("/shopfuniture/manager/")
public class ManagerController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    DbDao dao;
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
    @GetMapping("home")
    public  String homeC(){
        request.setAttribute("order", dao.getListOrder());

        return "dashboard_sale";
    }
    @GetMapping("upload")
    public String Upload(){
        String id = request.getParameter("Oid");
        SecurityContextHolder.getContext().getAuthentication().getName();
        request.setAttribute("orderE", dao.getOrderByOid(id));
        request.setAttribute("od", dao.getListOrderDetailByOid(id));
        return "edit";
    }

    @GetMapping("deleteO")
    public String DeleteOrder() throws IOException, IOException {
        String Oid = request.getParameter("Oid");
        if(dao.getListOrderDetailByOid(Oid) != null ){
            dao.DeleteOrderByOid(Oid);
        } else {
            request.setAttribute("mess", "you must delete order information first");
        }


        return  "redirect: /shopfuniture/manager/home";
    }

    @GetMapping("set-total")
    @ResponseBody
    public String SetTotal(@RequestParam(required = false) String quantity, HttpServletResponse response, HttpServletRequest request){
        String k = request.getParameter("id");
        Order_detail od = dao.getOrderDetailByODid(k);
        Double h = Double.parseDouble(quantity)*od.getPrice();
        return ""+Double.toString(h);
    }
    @GetMapping("list_product")
    public String List_P(){
        request.setAttribute("listP",dao.getDataProduct());
        request.setAttribute("listC", dao.getDataCate());
        request.setAttribute("listI", dao.getListInventory());
        return "product_manager";
    }
    @GetMapping("deleteP")
    public String Delete_P(){
        String Pid = request.getParameter("pid");

        dao.DeleteProductByPid(Pid);
        return "redirect: /shopfuniture/manager/list_product";
    }
    @GetMapping("editP")
    public String Edit_P(){

        return "";
    }
    @GetMapping("list_feedback")
    public String list_F(){
        request.setAttribute("listF", dao.getListFeedback());
        return "Feedback_manager";
    }
    @GetMapping("answer_fb")
    public String answer_F(){
        String Fid = request.getParameter("fid");
        String ans = request.getParameter(Fid);
        dao.answer_Feedback(ans, Fid);
        return "redirect: /shopfuniture/manager/list_feedback";
    }
    @GetMapping("delete_fb")
    public String Delete_feedback(){
        String Fid = request.getParameter("fid");

        return "redirect: /shopfuniture/manager/list_feedback";
    }


}