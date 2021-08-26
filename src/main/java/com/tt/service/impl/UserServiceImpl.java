/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Login;
import com.tt.pojos.User;
import com.tt.reponsitory.UserReponsitory;
import com.tt.service.UserService;
import java.util.List;
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
    
    @Override
    public List<Login> getUsers() {
        return this.userReponsitory.getUsers();
    }  

    @Override
    public List<Login> getUsers(String kw) {
        return this.userReponsitory.getUsers(kw);
    }
}
