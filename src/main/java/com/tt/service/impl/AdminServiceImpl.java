/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.reponsitory.AdminRepository;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminReponsitory;

    @Override
    public List<Object[]> stthashtag(String kw) {
        return this.adminReponsitory.stthashtag(kw);

    }

    @Override
    public List<Object[]> auctionstt() {
        return this.adminReponsitory.auctionstt();

    }

    @Override
    public List<Object[]> userreport() {
        return this.adminReponsitory.userreport();
    }

    @Override
    public List<Object[]> auctionMonth() {
        return this.adminReponsitory.auctionMonth();
    }

    @Override
    public List<Object[]> stttime() {
        return this.adminReponsitory.stttime();
    }

}
