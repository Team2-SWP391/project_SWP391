package filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import service.JwtService;
import service.UserConfig;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class ControlsFilter extends OncePerRequestFilter {
    public static Set s;
//    @Bean
//    SimpleGrantedAuthority simpleGrantedAuthority(){
//        return new SimpleGrantedAuthority();
//    }
public static int t=0;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserConfig userConfig;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization")!=null)
        System.out.println(request.getHeader("Authorization"));
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(request.getRequestURI().contains("spring-mvc/login")){
        filterChain.doFilter(request,response);
    }
        else if(request.getRequestURI().contains("spring-mvc/logout")&&request.getMethod().equalsIgnoreCase("get")){
            for (Cookie c:request.getCookies()) {
                if(c.getName().equals("remember_user")){
                    System.out.println("response cookie :"+c.getValue());}
            }
            Cookie c1=new Cookie("remember_user","");
            c1.setMaxAge(0);System.out.println("response filter :"+response);
            response.addCookie(c1); }
        else if (authentication != null) {
            String token = jwtService.generateTokenLogin(authentication.getName());
            UserDetails userDetails = userConfig.loadUserByUsername(authentication.getName());
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
     else if(Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equals("remember_user"))){
         String token="";
            for (Cookie c: request.getCookies()) {
                if(c.getName().equals("remember_user")){
                    token=c.getValue();break;}
            }
             String name = jwtService.getUsernameFromToken(token);
            UserDetails userDetails = userConfig.loadUserByUsername(name);
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
     }
        filterChain.doFilter(request, response);
    }
}
