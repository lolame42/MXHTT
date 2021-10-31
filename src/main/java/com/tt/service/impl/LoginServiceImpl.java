/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Login;
import com.tt.service.LoginService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tt.reponsitory.LoginRepository;

/**
 *
 * @author DAVADO
 */
@Service("userDetailsService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginReponsitory;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary Cloudinary;

    @Override
    public boolean addOrUpdate(Login login) {
        if (userServiceImpl.getUserByUserName(login.getUser_name()).isEmpty()) {
            try {
                Map r = this.Cloudinary.uploader().upload(login.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                login.setImage((String) r.get("secure_url"));
                String pass = login.getUser_password();
                login.setUser_password(this.passwordEncoder.encode(pass));
                login.setUserrole("ROLE_USER");
                return loginReponsitory.addOrUpdate(login);

            } catch (IOException ex) {
                System.err.println("==ADD ANH==" + ex.getMessage());
            }
            return false;
        }
        return false;

    }

    @Override
    public List<Login> getLogins(String user_name) {
        return this.loginReponsitory.getLogins(user_name);
    }

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        List<Login> logins = this.getLogins(user_name);
        if (logins.isEmpty()) {
            throw new UsernameNotFoundException("tài khoản không tồn tại");
        }
        Login login = logins.get(0);
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(login.getUserrole()));

        return new org.springframework.security.core.userdetails.User(login.getUser_name(), login.getUser_password(), auth);
    }

   

}
