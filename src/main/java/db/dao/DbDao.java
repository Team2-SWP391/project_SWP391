package db.dao;

import Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
@Repository
@Scope(value = "singleton")
@Transactional(rollbackFor = Exception.class)
public class DbDao {
    @Bean
    @Scope(value = "singleton")
    public BCryptPasswordEncoder b(){
        return new BCryptPasswordEncoder();
    }
    protected Connection connection;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
   public ResourceBundle r = ResourceBundle.getBundle("dao");

    public DbDao() {
        try {
            Class.forName(r.getString("forname"));
            String user = r.getString("user");
            String pass = r.getString("password");
            String url = r.getString("url");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException  | SQLException ex) {
            Logger.getLogger(DbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /// hoàng
    public List<Order> getListOrder(){
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement p = connection.prepareStatement("SELECT [Oid]\n" +
                    "      ,[C_id]\n" +
                    "      ,[ammount]\n" +
                    "      ,[Shipping_address]\n" +
                    "      ,[Order_address]\n" +
                    "      ,[Order_date]\n" +
                    "      ,[Order_status]\n" +
                    "  FROM [orders]");
            ResultSet r=p.executeQuery();
            while(r.next())
                list.add(new Order(r.getInt(1),
                        r.getInt(2),
                        r.getDouble(3),
                        r.getString(4),
                        r.getString(5),
                        r.getDate(6),
                        r.getInt(7)));
        }catch (Exception e){
            System.out.println("happened error!");
        }
        return list;
    }
    public Order getOrderByOid(String Oid){
        Order o = new Order();
        try {
            PreparedStatement p = connection.prepareStatement("SELECT [Oid]\n" +
                    "      ,[C_id]\n" +
                    "      ,[ammount]\n" +
                    "      ,[Shipping_address]\n" +
                    "      ,[Order_address]\n" +
                    "      ,[Order_date]\n" +
                    "      ,[Order_status]\n" +
                    "  FROM [orders] where Oid = ?");
            p.setString(1, Oid);
            ResultSet r=p.executeQuery();
            if(r.next()){
                return new Order(r.getInt(1),
                        r.getInt(2),
                        r.getDouble(3),
                        r.getString(4),
                        r.getString(5),
                        r.getDate(6),
                        r.getInt(7));
            }

        }catch (Exception e){
            System.out.println("happened error!");
        }
        return null;
    }
    public List<Order_detail> getListOrderDetailByOid(String Oid){
        List<Order_detail> list = new ArrayList<>();
        try {
            PreparedStatement p = connection.prepareStatement("SELECT TOP 1000 [ODid]\n" +
                    "      ,[O_id]\n" +
                    "      ,[P_id]\n" +
                    "      ,[price]\n" +
                    "      ,[quanity]\n" +
                    "  FROM [order_detail] where O_id = ?");
            p.setString(1, Oid);
            ResultSet r=p.executeQuery();
            while (r.next()){
                list.add(new Order_detail(r.getInt(1),
                        r.getInt(2),
                        r.getInt(3),
                        r.getDouble(4),
                        r.getInt(5)));
            }

        }catch (Exception e){
            System.out.println("happened error!");
        }
        return list;
    }
    public Order_detail getOrderDetailByODid(String ODid){

        try {
            PreparedStatement p = connection.prepareStatement("SELECT * FROM [Project_SWP].[dbo].[order_detail] Where ODid = ?");
            p.setString(1, ODid);
            ResultSet r=p.executeQuery();
            if (r.next()){
                return new Order_detail(r.getInt(1),
                        r.getInt(2),
                        r.getInt(3),
                        r.getDouble(4),
                        r.getInt(5));
            }

        }catch (Exception e){
            System.out.println("happened error!");
        }
        return null;
    }
    public void DeleteOrderDetailByODid(String ODid){
        try {
            PreparedStatement p = connection.prepareStatement("DELETE FROM [Project_SWP].[dbo].[order_detail]\n" +
                    "      WHERE ODid = ? ");
            p.setString(1, ODid);
            p.executeUpdate();
            connection.commit();

        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){
                System.out.println("rollback error!");
            }
            System.out.println("happened error!");
        }
    }
    public void DeleteOrderByOid(String Oid){
        try {
            PreparedStatement p = connection.prepareStatement("DELETE FROM [test].[dbo].[order]\n" +
                    "      WHERE O_id = ?");
            p.setString(1, Oid);
            p.executeUpdate();
            connection.commit();

        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){
                System.out.println("rollback error!");
            }
            System.out.println("happened error!");
        }
    }
    public void DeleteOrderDetailByOid(String Oid){
        try {
            PreparedStatement p = connection.prepareStatement("DELETE FROM [test].[dbo].[order_detail]\n" +
                    "      WHERE O_id = ?");
            p.setString(1, Oid);
            p.executeUpdate();
            connection.commit();

        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){
                System.out.println("rollback error!");
            }
            System.out.println("happened error!");
        }
    }
    public void DeleteProductByPid(String Pid){
        try {
            PreparedStatement p = connection.prepareStatement("DELETE FROM [Project_SWP].[dbo].[product]\n" +
                    "      WHERE Pid = ?");
            p.setString(1, Pid);
            p.executeUpdate();
            connection.commit();

        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){
                System.out.println("rollback error!");
            }
            System.out.println("happened error!");
        }
    }
    public List<Inventory> getListInventory(){
        List<Inventory> list= new ArrayList<>();
        String sql="SELECT [Inid]\n" +
                "      ,[I_name]\n" +
                "      ,[I_location]\n" +
                "  FROM [Project_SWP].[dbo].[inventory]";
        try{
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Inventory(rs.getInt("Inid"),rs.getString("I_name"), rs.getString("I_location")));
            }return list;
        }catch (Exception e){

        }
        return null;
    }
    public List<Feedback> getListFeedback(){
        List<Feedback> list= new ArrayList<>();
        String sql="SELECT TOP 1000 [Fid]\n" +
                "      ,[C_id]\n" +
                "      ,[detail]\n" +
                "      ,[F_date]\n" +
                "      ,[F_status]\n" +
                "      ,[rely]\n" +
                "      ,[P_id]\n" +
                "  FROM [Project_SWP].[dbo].[feedback]";
        try{
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Feedback(rs.getInt("Fid"),rs.getInt("C_id"),rs.getString("detail"),rs.getDate("F_date"),rs.getInt("F_status"),rs.getString("rely"),rs.getInt("P_id")));
            }return list;
        }catch (Exception e){

        }
        return null;
    }
    public void answer_Feedback(String ans, String Fid){
        try {
            PreparedStatement p = connection.prepareStatement("UPDATE [Project_SWP].[dbo].[feedback]\n" +
                    "   SET \n" +
                    "      [F_status] = 1\n" +
                    "      ,[rely] = ?\n" +
                    "      \n" +
                    " WHERE Fid = ?");
            p.setString(1, ans);
            p.setString(2,Fid);
            p.executeUpdate();
            connection.commit();

        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){
                System.out.println("rollback error!");
            }
            System.out.println("happened error!");
        }
    }
    public void delete_Feedback(String Fid){
        try {
            PreparedStatement p = connection.prepareStatement("DELETE FROM [Project_SWP].[dbo].[feedback]\n" +
                    "      WHERE Fid =?");

            p.setString(1,Fid);
            p.executeUpdate();
            connection.commit();

        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){
                System.out.println("rollback error!");
            }
            System.out.println("happened error!");
        }
    }
    /// hoàng kết thúc

    /// quang
    public JdbcTemplate _jdbcTemplate;
    PreparedStatement ps;
    ResultSet rs;
    // get list lide banner
    public List<Slides> getDataSlide(){
        List<Slides>lst= new ArrayList<>();
        String sql ="select * from slide";
        try{
            PreparedStatement p=connection.prepareStatement(sql);
            ResultSet r=p.executeQuery();
            while(r.next()){
                lst.add(new Slides(r.getInt(1),r.getString(2)
                        ,r.getString(3), r.getString(4)));
            }
            return lst;
        }catch(Exception e){

        }
        return null;
    }
    //get list product
    public List<Product> getDataProduct(){
        List<Product>lstp= new ArrayList<>();
        String sql ="select * from product";
        try{
            PreparedStatement p=connection.prepareStatement(sql);
            ResultSet r=p.executeQuery();
            while(r.next()){
                lstp.add(new Product(r.getInt("Pid"),r.getString("P_name"),
                        r.getString("P_image"),r.getFloat("price"),
                        r.getString("decription"),r.getInt("C_id")
                        // r.getString("P_new")
                        ,r.getInt("In_id")));
            }
            return lstp;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public List<Product> getDataNewProduct(){
        List<Product>lstpn= new ArrayList<>();
        String sql ="select * from Product where P_status=0 order by RAND()";
        try{
            PreparedStatement p=connection.prepareStatement(sql);
            ResultSet r=p.executeQuery();
            while(r.next()){
                lstpn.add(new Product(r.getInt(1),r.getString(2),
                        r.getString(3),r.getFloat(4),
                        r.getString(5),r.getInt(6),
                        r.getInt(7),r.getDate(8),
                        r.getInt(9)));
            }
            return lstpn;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    // get data product_categories
    public List<Category> getDataCate(){
        List<Category> lstc= new ArrayList<>();
        String sql="select * from product_categories";
        try{
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                lstc.add(new Category(rs.getInt("Cid"),rs.getString("C_name")));
            }return lstc;
        }catch (Exception e){

        }
        return null;
    }
    // get data product by category id
    public List<Product> getDataProductByCategoryId(String categoryId){
        try {
            List<Product> lst=new ArrayList<>();
            ps=connection.prepareStatement("SELECT * FROM product inner join product_categories on product.C_id=product_categories.Cid where product.C_id= ? ");
            ps.setString(1, categoryId);
            rs=ps.executeQuery();
            while (rs.next()){
                lst.add(new Product(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getFloat(4),
                        rs.getString(5),rs.getInt(6),
                        rs.getInt(7),rs.getDate(8),
                        rs.getInt(9)));
            }return lst;
        }catch (Exception ex){
        }
        return null;
    }

    public Product getDataProductById(String id){
        try {
            PreparedStatement p=connection.prepareStatement("select * from Product where Pid=?");
            p.setString(1,id);
            ResultSet r=p.executeQuery();
            if(r.next()){
                return new Product(r.getInt(1),r.getString(2),
                        r.getString(3),r.getFloat(4),
                        r.getString(5),r.getInt(6),
                        r.getInt(7),r.getDate(8),
                        r.getInt(9));
            }
        }catch (Exception ex){

        }


        return null;
    }
    public int getTotalProduct(){
        try {
            ps=connection.prepareStatement("select count (*) from product");

            rs=ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception ex){

        }
        return 0;
    }
    public List<Product> pagingProduct(int index){
        List<Product> list= new ArrayList<>();
        try {
            ps=connection.prepareStatement("select * from product order by Pid Offset ? rows fetch next 3 rows only");
            ps.setInt(1,(index-1)*3);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getFloat(4),
                        rs.getString(5),rs.getInt(6),
                        rs.getInt(7),rs.getDate(8),
                        rs.getInt(9)));
            }return list;
        }catch (Exception ex){

        }
        return null;
    }
    /// quang ket thuc
    public Human GetUser(String user){
        try {
            PreparedStatement p = connection.prepareStatement("select * from account where username=?");
            p.setString(1,user);
            ResultSet r=p.executeQuery();
        if(r.next()){
    return new Human(r.getString("username"),r.getString("password").trim(),r.getString("role"),r.getBoolean("status"));
        }
        }catch (Exception e){

        }
        return null;
    }
//    public String register(String name ,String pass){
//        try {
//            connection.setAutoCommit(false);
//            PreparedStatement p = connection.prepareStatement("insert into account (username,password,role,status) values(?,?,?,?)");
//            p.setString(1,name);
//            p.setString(2,bCryptPasswordEncoder.encode(pass));
//            p.setString(3,"USER");
//            p.setBoolean(4,true);
//            p.executeUpdate();
//        }catch (Exception e){
//        }
//        return null;
//    }
    public String registercustomer(String name ,String email, boolean exist, Person person,String authen){
        try {
            connection.setAutoCommit(false);
            if(!exist){//insert
            int id =getidaccount(name);
            PreparedStatement p = connection.prepareStatement("insert into customer (A_id,email) values(?,?)");
            p.setInt(1,id);
            p.setString(2,email);
            p.executeUpdate();
            }
            else{//update
                int id =getidaccount(authen);PreparedStatement p;
                if(person.getImage()==null||person.getImage().trim().equals("")){
                    p = connection.prepareStatement("update customer " +
                     "set email=?,Address=?,Full_name=?,Phone_number=? where A_id=?");
                    p.setInt(5,id);
                }
                else {
                    p = connection.prepareStatement("update customer " +
                            "set email=?,Address=?,Full_name=?,Phone_number=?,image=? where A_id=?");
                    p.setString(5,person.getImage());
                    p.setInt(6,id);
                }
                   p.setString(1,person.getEmail());
                    p.setNString(2,person.getAddress());
                    p.setNString(3,person.getFullname());
                    p.setString(4,person.getPhone());

                    p.executeUpdate();

            }
            connection.commit();
            return name;
        }catch (Exception e){
            System.out.println("error "+e.getMessage());
            try {
                connection.rollback();
            }catch (Exception ex){

            }
        }
        return null;
    }
    public String getRole(String user){
        try {
            PreparedStatement p = connection.prepareStatement("select role from account where username=?");
            p.setString(1,user);
            ResultSet r=p.executeQuery();
            if(r.next()){
             return r.getString("role");
            }
        }catch (Exception e){

        }
        return null;
    }
    public boolean callmail(String name) {
        try {
            PreparedStatement p = connection.prepareStatement("select * from account where username=?");
            p.setString(1,name);
           ResultSet r= p.executeQuery();
           if(r.next())
               return true;
        }catch (Exception e){
            System.out.println("happened error!"+e.getMessage());
        }
        return false;
    }
    public boolean checkmail(String name,String phone,String authen) {
        try {
            int id=getidaccount(authen);
            PreparedStatement p = connection.prepareStatement("select c.email from (select email,Phone_number from customer where A_id!=?) as c" +
                    " where c.email=? or c.Phone_number=?");
            p.setInt(1,id);
            p.setString(2,name);
            p.setString(3,phone);
            ResultSet r= p.executeQuery();
            if(r.next())
                return true;
        }catch (Exception e){
            System.out.println("happened error!"+e.getMessage());
        }
        return false;
    }
    public void insertmailaccount(User user,String token)  {
        try {
            connection.setAutoCommit(false);
            PreparedStatement p = connection.prepareStatement("insert into account(username,[password],[role],[status]) " +
                    " values(?,?,?,?)");
            p.setNString(1,user.getEmail());
            p.setNString(2,token);
            p.setNString(3,"USER");
            p.setBoolean(4,true);
           p.executeUpdate();
           connection.commit();
        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){

            }
            System.out.println("happened error!"+e.getMessage());
        }
    }
    public int getidaccount(String name){
        try {
            PreparedStatement p = connection.prepareStatement("select Aid from account where username=?");
            p.setString(1,name);
            ResultSet r=p.executeQuery();
            if(r.next())
                return r.getInt("Aid");
        }catch (Exception e){

        }
        return 0;
    }
    public void insertmailcustomer(User user,int aid)  {
        try {
            connection.setAutoCommit(false);
            PreparedStatement p = connection.prepareStatement("insert into customer(A_id,email,image) " +
                    " values(?,?,?)");
            p.setInt(1,aid);
            p.setString(2,user.getEmail());
            p.setNString(3,user.getPicture());
            p.executeUpdate();
            connection.commit();
        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){

            }
            System.out.println("happened error!"+e.getMessage());
        }
    }
public boolean confirm_mail(String mail){
    try {
        PreparedStatement p = connection.prepareStatement("select * from customer where email=?");
        p.setString(1,mail);
        ResultSet r=p.executeQuery();
        if(r.next())
            return true;
    }catch (Exception e){

    }
        return false;
}
    public boolean changepassword(String mail,String pass){
        try {
            PreparedStatement p = connection.prepareStatement("update account set password=? " +
                    "where Aid=(select c.A_id from customer c join account a on a.Aid=c.A_id where c.email=?)");
            p.setString(1,bCryptPasswordEncoder.encode(pass));
            p.setString(2,mail);
            p.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean account_saler(String username,String pass)  {
        try {
            connection.setAutoCommit(false);
            PreparedStatement p = connection.prepareStatement("insert into account(username,[password],[role],[status]) " +
                    " values(?,?,?,?)");
            p.setNString(1,username);
            p.setNString(2,bCryptPasswordEncoder.encode(pass));
            p.setNString(3,"SALE");
            p.setBoolean(4,false);
            p.executeUpdate();
            connection.commit();
            return true;
        }catch (Exception e){
            System.out.println("happened error!"+e.getMessage());
            try {
                if(connection!=null)
                connection.rollback();
            }catch (Exception d){

            }

        }
        return false;
    }
    public void insert_saler(String address,String name,String phone,String username){
        try {
           int id= getidaccount(username);
            connection.setAutoCommit(false);
            PreparedStatement p = connection.prepareStatement("insert into saler(A_id,[address],Full_name,Phone_number)\n" +
                                  " values(?,?,?,?)");
            p.setInt(1,id);
            p.setNString(2,address);
            p.setNString(3,name);
            p.setString(4,phone);
            p.executeUpdate();
            connection.commit();
            if(!connection.getAutoCommit()){
                connection.setAutoCommit(true);
            }
        }catch (Exception e){
            try {
                connection.rollback();
                if(!connection.getAutoCommit()){
                    connection.setAutoCommit(true);
                }
            }catch (Exception d){

            }
            System.out.println("happened error!"+e.getMessage());
        }
    }
    public Person getProfile(String authen){
        try {
            int id=getidaccount(authen);
            ps=connection.prepareStatement("select email,Full_name,Phone_number,[image],[Address] from customer where A_id=?");
            ps.setInt(1,id);
            rs=null;
           rs= ps.executeQuery();
           if(rs.next()){
               return new Person( null,null,rs.getString(1),rs.getString(2),rs.getString(3)
               ,rs.getString(4), rs.getString(5));
           }

        }catch (Exception e){
System.out.println("error "+e.getMessage());
        }
        return null;
    }
    public int change_pass(String news,String old,String authen){
        try{
            ps=null;
            rs=null;
            String pass_encode="";
            ps=connection.prepareStatement("select password from account where username=?");
            ps.setString(1,authen);
            rs=ps.executeQuery();
            if(rs.next())
             pass_encode=rs.getString(1);
            ps=null;
            rs=null;
            if(pass_encode.equals("")){
                return 0;
            }
            if(!bCryptPasswordEncoder.matches(old,pass_encode)){
               return -1;
            }
            ps=connection.prepareStatement("update account set password=? where username=?");
            ps.setString(1,bCryptPasswordEncoder.encode(news));
            ps.setString(2,authen);
           int i= ps.executeUpdate();
           return i;
        }catch (Exception e){

        }
        return 0;
    }
    public int deleteSaler(int id)  {
        try{
            ps=null;
            ps=connection.prepareStatement("select [Sid] from saler where A_id=?");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            int d=0;
            if(rs.next())
              d=rs.getInt(1);
            if(d==0)
                return 0;
            ps=null;
            connection.setAutoCommit(false);
            ps=connection.prepareStatement("delete manage_product where S_id=? " +
                    " delete manage_order where S_id=? delete manage_feedback where S_id=?" +
                    " delete saler where A_id=? delete account where Aid=?");
            ps.setInt(1,d);
            ps.setInt(2,d);
            ps.setInt(3,d);
            ps.setInt(4,id);
            ps.setInt(5,id);

           int i= ps.executeUpdate();
           connection.commit();
           if(connection.getAutoCommit()==false)
               connection.setAutoCommit(true);
            return i;
        }catch (Exception e){
            try {
                connection.rollback();
                if(connection.getAutoCommit()==false)
                    connection.setAutoCommit(true);
            } catch (Exception ex) {

            }
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public boolean changeActive(int aid,int status){
        try {
          ps=null;
          if(status==1){
          ps=connection.prepareStatement("update account set status=0 where Aid=?");
          ps.setInt(1,aid);
          ps.executeUpdate();
          return true;
          }
          else if(status==0){
              ps=connection.prepareStatement("update account set status=1 where Aid=?");
              ps.setInt(1,aid);
              ps.executeUpdate();
              return true;
            }
        }catch (Exception e){
System.out.println(e.getMessage());
        }
        return false;
    }
    public ArrayList<Saler> getAllSaler(){
        ArrayList<Saler> a=new ArrayList<>();
        try{
            ps=null;
            ps=connection.prepareStatement("select a.username,a.password,s.Full_name,s.address,s.Phone_number,s.Sid,a.Aid,a.[status]" +
                    " from account a join saler s on s.A_id=a.Aid and a.[role]='SALE'");
            rs=null;
            rs=ps.executeQuery();
            while(rs.next())
                a.add(new Saler(rs.getInt(6), rs.getString(1),rs.getString(2) ,rs.getNString(3)
                ,rs.getNString(4),rs.getString(5), rs.getInt(7),rs.getBoolean(8)));
            if(a.size()<1)
            return null;
            else
                return a;
        }catch (Exception e){
System.out.println(e.getMessage());
        }
        return null;
    }
    public int getAdminId(String user){
        try{
          ps=null;rs=null;
          ps=connection.prepareStatement("select Aid from account  where username=?");
          ps.setString(1,user);
          rs=ps.executeQuery();
          if(rs.next())
              return rs.getInt(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public void updateFeedBack(){
        try{
          ps=null;
          ps=connection.prepareStatement("update mess_saler_admin set seen_status=1 where [from]='sale' and seen_status=0");
          ps.executeUpdate();
        }catch (Exception e){

        }
    }
public boolean sendFeedback(String content,boolean status,int sid,String authen){
        int id=getAdminId(authen);
        if(id==-1)
        return false;
        try {
            ps = null;
            rs = null;
            connection.setAutoCommit(false);
            ps=connection.prepareStatement("insert into mess_saler_admin(saler_id,admin_id,dtime,[content],seen_status,[from])" +
                    " values(?,?,?,?,?,'admin')");
            ps.setInt(1,sid);
            ps.setInt(2,id);
            ps.setTimestamp(3,new Timestamp(new Date().getTime()));
            ps.setNString(4,content);
            ps.setBoolean(5,status);

            ps.executeUpdate();
            connection.commit();
            if(connection.getAutoCommit()==false)
                connection.setAutoCommit(true);
            return true;
        }catch (Exception e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            System.out.println(e.getMessage());
        }
        return false;
}
public ArrayList<Admin_Saler> getAllFeedBack(){
    ArrayList<Admin_Saler> a=null;
    try{
       a=new ArrayList<>();
       ps=null;rs=null;
       ps=connection.prepareStatement("select m.content,m.dtime,s.Full_name as fname,m.seen_status,m.stt from mess_saler_admin m" +
               " join saler s on s.Sid=m.saler_id where m.from='sale'");
       rs=ps.executeQuery();
       if(rs.next())
           a.add(new Admin_Saler(rs.getString(1),rs.getString(3),rs.getBoolean(4),rs.getTimestamp(2),rs.getInt(5)));
       if(a.size()>0)
           return a;
    }catch (Exception e){

    }
        return null;
}
///////////for testing//////////
public String GetUsertest(String user,String pass){
    try {
        PreparedStatement p = connection.prepareStatement("select * from account where username=?");
        p.setString(1,user);
        ResultSet r=p.executeQuery();
        boolean b=false;
        if(r.next()){
           b= new BCryptPasswordEncoder().matches(pass,r.getString("password"));
        }
       return b?pass:null;
    }catch (Exception e){
System.out.println(e.getMessage());
    }
    return null;
}
    public String validate(String user,String pass){
        try {
            if(user==null||user.equals(""))
                return "";
            if(pass==null||pass.equals(""))
                return "";
            PreparedStatement p = connection.prepareStatement("select * from account where username =?");
            p.setString(1,user);
            ResultSet r=p.executeQuery();
            boolean b=false;
            if(r.next()){
                b= new BCryptPasswordEncoder().matches(pass,r.getString("password"));
            }
            return b?pass:"";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
public int getUserByid(int id){
    try {
        PreparedStatement p = connection.prepareStatement("select * from customer where id=?");
        p.setInt(1,id);
        ResultSet r=p.executeQuery();
        if(r.next())
            return 1;
    }catch (Exception e){

    }
    return 0;
}
    public int getSalerByid(int id){
        try {
            PreparedStatement p = connection.prepareStatement("select * from saler where Sid=?");
            p.setInt(1,id);
            ResultSet r=p.executeQuery();
            if(r.next())
                return 1;
        }catch (Exception e){

        }
        return 0;
    }
    public boolean validateUserInfor(String email,String phone,String address,String name){
if(email==null||phone==null||address==null||name==null){
    return false;
}
if(email.equals("")||address.equals("")||phone.equals("")||phone.length()<10||name.equals("")||address.split(",").length!=3)
    return false;
return true;
 }
    public boolean validateSaler(String phone,String address,String name){
        if(phone==null||address==null||name==null){
            return false;
        }
        if(address.trim().equals("")||phone.trim().equals("")||phone.length()<10||name.trim().equals(""))
            return false;
        return true;
    }
    public boolean validateSalerPassword(String pass){
        if(pass==null){
            return false;
        }
        if(pass.equals(""))
            return false;
        if(pass.length()<8)
            return false;
        return true;
    }
}
