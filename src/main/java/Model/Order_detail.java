package Model;

import java.util.function.DoubleUnaryOperator;

public class Order_detail {
    int OD_id;
    int Oid;
    int P_id;
    double price;
    int quantity;
    public Order_detail() {
    }
    public Order_detail(int OD_id, int oid, int p_id, double price, int quantity) {
        this.OD_id = OD_id;
        Oid = oid;
        P_id = p_id;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOD_id() {
        return OD_id;
    }

    public void setOD_id(int OD_id) {
        this.OD_id = OD_id;
    }

    public int getOid() {
        return Oid;
    }

    public void setOid(int oid) {
        Oid = oid;
    }

    public int getP_id() {
        return P_id;
    }

    public void setP_id(int p_id) {
        P_id = p_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double amount(String number){
        return price* Double.parseDouble(number);
    }
}
