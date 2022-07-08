package filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import service.JwtService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtService jwtService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            response.sendRedirect("/shopfuniture/login");
        }
      else  if(roles.contains("USER")) {
            Cookie c1=new Cookie("remember_user",jwtService.generateTokenLogin(authentication.getName()));
            c1.setMaxAge(60*60*24);
            c1.setPath("/");
            response.addCookie(c1);
            response.sendRedirect("/shopfuniture/home-page");
        }
       else if(roles.contains("SALE")) {
            response.sendRedirect("/shopfuniture/manager/home");
        }
      else if(roles.contains("ADMIN")){
          response.sendRedirect("/shopfuniture/admin/list-employee");}
    }
}
