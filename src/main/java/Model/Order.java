package Model;
import java.util.Date;
public class Order {
    private int Oid;
    private int Cid;
    private double amount;
    private String shipping_address;
    private String order_address;
    private Date order_date;
    private int status;
    public Order(){

    }
    public Order(int oid, int cid, double amount, String shipping_address, String order_address, Date order_date, int status) {
        Oid = oid;
        Cid = cid;
        this.amount = amount;
        this.shipping_address = shipping_address;
        this.order_address = order_address;
        this.order_date = order_date;
        this.status = status;
    }

    public int getOid() {
        return Oid;
    }

    public void setOid(int oid) {
        Oid = oid;
    }

    public int getCid() {
        return Cid;
    }

    public void setCid(int cid) {
        Cid = cid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}