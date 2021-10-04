/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.reponsitory.StatusReponsitory;
import com.tt.service.StatusService;
import com.tt.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class StatusServiceImpl implements StatusService{
    @Autowired
    private Cloudinary Cloudinary;
    @Autowired
    private StatusReponsitory statusReponsitory ;
    @Autowired
    private UserService userService;
    


    @Override
    public List<Status> getStatus() {
        return statusReponsitory.getStatus();
    }

    @Override
    public boolean add(Status status, int i) {
       return statusReponsitory.add(status, i);
    }

    @Override
    public List<Status> getStatusByIduser(int i) {
        return statusReponsitory.getStatusByIduser(i);
    }

    @Override
    public List<Status> getStatusByIdStatus(int id) {
        return statusReponsitory.getStatusByIdStatus(id);
    }

    @Override
    public List<Status> getStatus(String string, int page) {
        return statusReponsitory.getStatus(string, page);
    }

    @Override
    public List<Status> getStatusByor(int i,String kw, int page) {
        return statusReponsitory.getStatusByor(i,kw,page);
    }

    @Override
    public List<Status> getStatusByor(int i) {
        return statusReponsitory.getStatusByor(i);
    }

    
}
