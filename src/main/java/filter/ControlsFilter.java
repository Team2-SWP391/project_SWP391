package filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import service.JwtService;
import service.UserConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ControlsFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserConfig userConfig;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (authentication != null) {
            UserDetails userDetails = userConfig.loadUserByUsername(authentication.getName());
            if(userDetails!=null) {
                UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
     else if(request.getCookies()!=null&&Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equals("remember_user"))){
         String token="";
            for (Cookie c: request.getCookies()) {
                if(c.getName().equals("remember_user")){
                    token=c.getValue();
                    break;
                }
            }
               String name = jwtService.getUsernameFromToken(token);

            UserDetails userDetails = userConfig.loadUserByUsername(name);
               if(userDetails!=null){
                   UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);}
     }
        if(request.getRequestURI().equals("/shopfuniture/"))
            response.sendRedirect("/shopfuniture/home-page");
        else
        filterChain.doFilter(request, response);
    }
}
