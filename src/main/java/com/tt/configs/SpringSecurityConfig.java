/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author DAVADO
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tt.reponsitory",
    "com.tt.service"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ldb-company",
                "api_key", "954583823992543",
                "api_secret", "VRMHBCZgPJEk4M_WFGnbz0_MR90",
                "secure", true
        ));
        return c;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/")
                .usernameParameter("user_name")
                .passwordParameter("user_password");

        http.formLogin().defaultSuccessUrl("/home").failureUrl("/?error");

        http.logout().logoutSuccessUrl("/");
        http.exceptionHandling()
                .accessDeniedPage("/?accessDenied");
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/home")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/status/**")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/find")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/auction")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/auctionpart/**")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/billsell/**")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/check/**")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')");
               
                

        http.csrf().disable();
    }

}
