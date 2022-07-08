package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Saler {
    private int id;
    private String user;
    private String password;
    private String full_name;
    private String address;
    private String phone;
    private int aid;
    private boolean active;
}
