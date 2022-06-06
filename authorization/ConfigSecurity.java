package com.laptrinh;

import filter.ControlsFilter;
import filter.CustomSuccessHandler;
import filter.FailedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import service.UserConfig;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
public static String notify="";
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserConfig userConfig;
    @Bean
    public ControlsFilter controlsFilter(){
        return new ControlsFilter();
    }
    @Autowired
    public ConfigSecurity(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                /*with the whitelist -> will be permitted*/
//                .antMatchers("/spring-mvc/user1").hasAnyRole("STUDENT")
//                .antMatchers("/spring-mvc/admin").hasAnyRole("ADMIN")
//                /*anu request must be authenticated*/
//                .anyRequest()
//                .authenticated()
//                .and().httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
@Bean
public CustomSuccessHandler successHandler() {
    return new CustomSuccessHandler();
}
    @Bean
    public FailedLogin failedLogin() {
        return new FailedLogin();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/shopfuniture/user1").hasAnyAuthority("USER")
                .antMatchers("/shopfuniture/admin").hasAnyAuthority("ADMIN").
                antMatchers("/shopfuniture/saler").hasAnyAuthority("SALE").
                anyRequest().authenticated().
                and().formLogin().loginPage("/shopfuniture/login").
                permitAll().loginProcessingUrl("/shopfuniture/login")
                .successHandler(successHandler()).and().exceptionHandling().accessDeniedPage("/shopfuniture/exception");
        http.addFilterBefore(controlsFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public  PasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userConfig).passwordEncoder(bCryptPasswordEncoder());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/a/**").and().ignoring().antMatchers("/shopfuniture/email")
                .and().ignoring().antMatchers("/shopfuniture/register").and().ignoring().antMatchers("/shopfuniture/user-mail")
                .and().ignoring().antMatchers("/shopfuniture/confirm");
    }
}
