package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin_Saler {
   private int sid;
   private int aid;
   private int stt;
   private String fname;
   private String content;
   private boolean status;
   private Timestamp datetime;

   public Admin_Saler(String content,String fname,boolean status,Timestamp timestamp,int stt) {
      this.status=status;
      this.datetime=timestamp;
      this.fname=fname;
      this.content = content;
      this.stt=stt;
   }
}
