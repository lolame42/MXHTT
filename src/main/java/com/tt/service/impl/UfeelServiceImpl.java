/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;
import com.tt.pojos.Ufeel;
import com.tt.service.NotiService;
import com.tt.service.UfeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.reponsitory.UfeelRepository;
import com.tt.reponsitory.UserRepository;

/**
 *
 * @author DAVADO
 */
@Service
public class UfeelServiceImpl implements UfeelService{
    @Autowired
    UfeelRepository ufeelReponsitory;
    @Autowired
    UserRepository userReponsitory;
    @Autowired
    NotiService  notiService;

    @Override
    public boolean add(Status status, int id) {
        Ufeel ufeel=new Ufeel();
        ufeel.setStatuslike(status);
        ufeel.setIdUser(id);
        
        Noti addnoti = new Noti();
        Login login =userReponsitory.getUserById(id);
        addnoti.setAvatar(login.getImage());
        addnoti.setName(login.getFull_name());
        notiService.add(addnoti, status.getLogin(), status, 2);
        
        return ufeelReponsitory.add(ufeel);
        
        
        
    }

    @Override
    public boolean check(Status status, int i) {
        return this.ufeelReponsitory.check(status, i);
    }
    
    
}
