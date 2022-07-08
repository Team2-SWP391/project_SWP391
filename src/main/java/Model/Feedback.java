package Model;

import java.util.Date;

public class Feedback {
    int Fid;
    int C_id;
    String detail;
    Date Create_data;
    int status;
    String rely;
    int P_id;

    public Feedback() {
    }

    public Feedback(int fid, int c_id, String detail, Date create_data, int status, String rely, int p_id) {
        Fid = fid;
        C_id = c_id;
        this.detail = detail;
        Create_data = create_data;
        this.status = status;
        this.rely = rely;
        P_id = p_id;
    }

    public int getFid() {
        return Fid;
    }

    public void setFid(int fid) {
        Fid = fid;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreate_data() {
        return Create_data;
    }

    public void setCreate_data(Date create_data) {
        Create_data = create_data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRely() {
        return rely;
    }

    public void setRely(String rely) {
        this.rely = rely;
    }

    public int getP_id() {
        return P_id;
    }

    public void setP_id(int p_id) {
        P_id = p_id;
    }
}