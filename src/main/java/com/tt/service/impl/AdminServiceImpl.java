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


@Service
public class AdminServiceImpl implements AdminService{
    
    @Autowired
    private AdminRepository adminReponsitory;
    
    @Override
    public List<Object[]> stthashtag() {
        return this.adminReponsitory.stthashtag();
        
    }
    @Override
    public List<Object[]> auctionstt() {
        return this.adminReponsitory.auctionstt();
        
    }

    @Override
    public List<Object[]> userreport() {
       return this.adminReponsitory.userreport();
    }
    
}
