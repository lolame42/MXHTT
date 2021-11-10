/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;
import com.tt.service.NotiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.reponsitory.NotiRepository;

/**
 *
 * @author DAVADO
 */
@Service
public class NotiServiceImpl implements NotiService{
    @Autowired
    NotiRepository notiReponsitory;

    @Override
    public boolean add(Noti noti, Login login, Status status, int type) {
        return notiReponsitory.add(noti, login, status, type);
    }

    @Override
    public boolean addnotiauc(Noti noti, Login login, Auction actn, int type) {
        return notiReponsitory.addnotiauc(noti, login, actn, type);
    }

    @Override
    public List<Noti> getNotibyLogin(Login login) {
        return notiReponsitory.getNotibyLogin(login);
    }   
}
