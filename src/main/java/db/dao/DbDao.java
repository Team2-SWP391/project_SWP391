package db.dao;

import Model.Human;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
@Repository
@Scope(value = "singleton")
public class DbDao {
    @Bean
    @Scope(value = "singleton")
    public BCryptPasswordEncoder b(){
        return new BCryptPasswordEncoder();
    }
    protected Connection connection;
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
    public ArrayList<String> getList(){
        ArrayList<String> a=new ArrayList<>();;
        try {
            PreparedStatement p = connection.prepareStatement("select * from login");
            ResultSet r=p.executeQuery();
            while(r.next())
               a.add(r.getString("name").trim());
        }catch (Exception e){
            System.out.println("happened error!");
        }
        return a;
    }
    public Human GetUser(String user){
        try {
            PreparedStatement p = connection.prepareStatement("select * from DBlogin where username=?");
            p.setString(1,user);
            ResultSet r=p.executeQuery();
        if(r.next()){
    return new Human(r.getString("username"),r.getString("password").trim(),r.getString("role"));
        }
        }catch (Exception e){
            System.out.println("happened error "+e.getMessage());
        }
        return null;
    }
    public void delte(String name) {
        try {
            connection.setAutoCommit(false);
           PreparedStatement p = connection.prepareStatement("delete [login] where name=?");
           p.setString(1,name);
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
    public void create(String name) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement p = connection.prepareStatement("insert into login(name) values(?)");
            p.setNString(1,name);
            p.executeUpdate();
            connection.commit();
        }catch (Exception e){
            try {
                connection.rollback();
            }catch (Exception d){
                System.out.println("rollback error!"+d.getMessage());
            }
            System.out.println("happened error!"+e.getMessage());
        }
    }
    public void edit(String name,String name1) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement p = connection.prepareStatement("update login set name =? where name =?");
            p.setString(1,name);
            p.setString(2,name1);
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
}
