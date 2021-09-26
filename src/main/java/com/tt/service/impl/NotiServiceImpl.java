/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;
import com.tt.reponsitory.NotiReponsitory;
import com.tt.service.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class NotiServiceImpl implements NotiService{
    @Autowired
    NotiReponsitory notiReponsitory;

    @Override
    public boolean add(Noti noti, Login login, Status status, int type) {
        return notiReponsitory.add(noti, login, status, type);
    }
    
}
