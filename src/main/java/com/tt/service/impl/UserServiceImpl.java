/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Login;
import com.tt.pojos.User;
import com.tt.reponsitory.UserReponsitory;
import com.tt.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tu
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserReponsitory userReponsitory;
     @Autowired
    private Cloudinary Cloudinary;
    @Override
    public List<Login> getUsers() {
        return this.userReponsitory.getUsers();
    }  

    @Override
    public List<Login> getUsers(String kw) {
        return this.userReponsitory.getUsers(kw);
    }

    @Override
    public boolean addOrUpdate(Login login) {
         try {
            Map r  = this.Cloudinary.uploader().upload(login.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            login.setImage((String) r.get("secure_url"));
            return userReponsitory.addOrUpdate(login);
           
        } catch (IOException ex) {
            System.err.println("==ADD ANH==" + ex.getMessage());
        }
         return false;
    }
}
