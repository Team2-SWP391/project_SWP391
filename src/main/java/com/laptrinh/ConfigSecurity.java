package com.laptrinh;

import Model.User;
import filter.ControlsFilter;
import filter.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.annotation.SessionScope;
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
//        return http.build();
//    }
@Bean
public CustomSuccessHandler successHandler() {
    return new CustomSuccessHandler();
}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/shopfuniture/user/**").hasAnyAuthority("USER")
                .antMatchers("/shopfuniture/admin/**").hasAnyAuthority("ADMIN").
                antMatchers("/shopfuniture/manager/**").hasAnyAuthority("SALE").
                anyRequest().authenticated().
                and().formLogin().loginPage("/shopfuniture/login").
                permitAll().loginProcessingUrl("/shopfuniture/login")
                .successHandler(successHandler()).and().exceptionHandling().accessDeniedPage("/shopfuniture/user/exception");
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
    public void configure(WebSecurity web) {
                web.ignoring().antMatchers("/shopfuniture/source/**").
                and().ignoring().antMatchers("/shopfuniture/user/email")
                .and().ignoring().antMatchers("/shopfuniture/user/register").
                and().ignoring().antMatchers("/shopfuniture/user/google-mail")
                .and().ignoring().antMatchers("/shopfuniture/user/confirm").
                and().ignoring().antMatchers("/shopfuniture/user/reset-pass").
                and().ignoring().antMatchers("/shopfuniture/home-page").//https://fonts.googleapis.com
                and().ignoring().antMatchers("/shopfuniture/manager/register").
                and().ignoring().antMatchers("/assets/**").///shopfuniture/category
                and().ignoring().antMatchers("/shopfuniture/category").
                and().ignoring().antMatchers("/sale/**").
                and().ignoring().antMatchers("/view/**").
//                        and().ignoring().antMatchers("/shopfuniture/admin/list-employee").
//                        and().ignoring().antMatchers(HttpMethod.POST,"/shopfuniture/admin/delete/**").
//                        and().ignoring().antMatchers(HttpMethod.POST,"/shopfuniture/admin/change_active/**").//
                and().ignoring().antMatchers("/shopfuniture/user/forgot_password");
    }
}
