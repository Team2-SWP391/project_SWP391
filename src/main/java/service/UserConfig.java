package service;

import Model.Human;
import com.laptrinh.ConfigSecurity;
import db.dao.DbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserConfig implements UserDetailsService {
@Autowired
    DbDao dao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Human h=dao.GetUser(username);
        if(h==null){
            ConfigSecurity.notify="invalid username and password";
            return null;
        }
        else if(h.isStatus()==false){
            ConfigSecurity.notify="your account inactive";
            return null;
        }
        else{
            List<GrantedAuthority> l = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(h.getRole().trim());
            l.add(grantedAuthority);
            UserDetails userDetails = new User(username, h.getPassword(), l);
            return userDetails;
        }
    }
}
