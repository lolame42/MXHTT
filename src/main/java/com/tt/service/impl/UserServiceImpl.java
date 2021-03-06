/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Login;
import com.tt.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import com.tt.reponsitory.UserRepository;

/**
 *
 * @author Tu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userReponsitory;

    @Override
    public List<Login> getUsers() {
        return this.userReponsitory.getUsers();
    }

    @Override
    public List<Login> getUsers(String kw) {
        return this.userReponsitory.getUsers(kw);
    }

    @Override
    public List<Login> getUserByUserName(String string) {
        return this.userReponsitory.getUserByUserName(string);
    }

    @Override
    public Login getUserById(int id) {
        return this.userReponsitory.getUserById(id);
    }


    @Override
    public List<Login> getListUserbyId(int i) {
        return userReponsitory.getListUserByid(i);
    }

    @Override
    public List<Login> getUserByPhone(String string) {
        return userReponsitory.getUserByPhone(string);
    }

    @Override
    public List<Login> getUserByEmail(String string) {
        return userReponsitory.getUserByEmail(string);
    }

    @Override
    public boolean xoa(int i) {
        return userReponsitory.xoa(i);
    }

   
}
