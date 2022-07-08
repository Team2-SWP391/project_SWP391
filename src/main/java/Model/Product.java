package Model;

import java.util.Date;

public class Product {
    private int Pid;

    public Product() {
    }



    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String p_name) {
        P_name = p_name;
    }

    public String getP_image() {
        return P_image;
    }

    public void setP_image(String p_image) {
        P_image = p_image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    public int getP_new() {
        return P_status;
    }

    public void setP_new(int p_status) {
        P_status = p_status;
    }

    public int getIn_id() {
        return In_id;
    }

    public void setIn_id(int in_id) {
        In_id = in_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }



    private String P_name;
    private String P_image;
    private float price;
    private String description;
    private int C_id;
    private int In_id;
    private Date create_at;
    private  int P_status;

    public Product(int pid, String p_name, String p_image, float price, String description, int c_id, int in_id) {
        Pid = pid;
        P_name = p_name;
        P_image = p_image;
        this.price = price;
        this.description = description;
        C_id = c_id;
        In_id = in_id;
    }
    public Product(int pid, String p_name, String p_image, float price, String description, int c_id, int in_id, java.sql.Date date, int p_status) {
        Pid = pid;
        P_name = p_name;
        P_image = p_image;
        this.price = price;
        this.description = description;
        C_id = c_id;
        P_status = p_status;
        In_id = in_id;
        this.create_at = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Pid=" + Pid +
                ", P_name='" + P_name + '\'' +
                ", P_image='" + P_image + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", C_id=" + C_id +
                ", In_id=" + In_id +
                ", create_at=" + create_at +
                ", P_status=" + P_status +
                '}';
    }
}
