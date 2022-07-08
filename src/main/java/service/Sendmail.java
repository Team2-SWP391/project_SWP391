package service;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class Sendmail {
    ResourceBundle r = ResourceBundle.getBundle("dao");
    public boolean sendmail(HttpServletRequest request,String mail,String code,String message,String message2,String link) {
        try {
            request.setCharacterEncoding("UTF-8");
            if(mail==null||mail.trim().equals(""))
                throw new Exception("");
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            pr.put("mail.smtp.port", "587"); //TLS Port
            pr.put("mail.smtp.auth", "true"); //enable authentication
            pr.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            Session s = Session.getInstance(pr);
            MimeMessage m = new MimeMessage(s);
            m.setFrom(new InternetAddress(r.getString("email"), "FurnitureShop"));
            m.setRecipients(Message.RecipientType.TO, mail);//or m.addRecipients(Message.RecipientType.TO, a);
            m.setSubject(message, "UTF-8");
            m.setContent("<strong>"+message2+",let click on link attached:</strong>" +
                    "<a href=http://localhost:8080"+link+"?"+code+" style='font-size:20px;'>click here</a>","text/html");
            Transport.send(m, r.getString("email"), r.getString("password"));
            return true;
        } catch (Exception e) {
            Logger.getLogger(Sendmail.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
