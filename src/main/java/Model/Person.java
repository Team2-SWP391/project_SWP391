package Model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Configuration;
public class Person {
   private String username;
    private String pass;
    private String email;
    private String fullname;
    private String phone ;
    private  String image ;
    private String address;
    public Person(){
    }

    public Person(String username, String pass, String email, String fullname, String phone, String image,String address) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.image = image;
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
