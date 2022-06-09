package filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.util.UrlPathHelper;
import service.JwtService;
import sun.plugin2.message.helper.URLHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtService jwtService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("USER")) {
            Cookie c=new Cookie("remember_user",jwtService.generateTokenLogin(authentication.getName()));
            c.setMaxAge(60*60*24);
            response.addCookie(c);System.out.println("response success :"+response);
            response.sendRedirect("/spring-mvc/user1");
        }
      else if(roles.contains("ADMIN")){
          response.sendRedirect("/spring-mvc/admin");}
    }
}
