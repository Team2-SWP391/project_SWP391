package Model;

public class Inventory {
    int In_id;
    String I_name;
    String location;

    public Inventory() {
    }

    public Inventory(int in_id, String i_name, String location) {
        In_id = in_id;
        I_name = i_name;
        this.location = location;
    }

    public int getIn_id() {
        return In_id;
    }

    public void setIn_id(int in_id) {
        In_id = in_id;
    }

    public String getI_name() {
        return I_name;
    }

    public void setI_name(String i_name) {
        I_name = i_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}