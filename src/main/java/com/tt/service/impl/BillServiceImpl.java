/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Bill;
import com.tt.pojos.Check;
import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.reponsitory.BillReponsitory;
import com.tt.reponsitory.NotiReponsitory;
import com.tt.service.BillService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class BillServiceImpl implements BillService{
    @Autowired
    BillReponsitory billReponsitory;
    @Autowired
    NotiReponsitory notiReponsitory;

    @Override
    public boolean addbill(Login loginsell, Login loginpay, int tien) {
        Bill bill = new Bill();
        bill.setDate(new Date());
        bill.setLoginpay(loginpay);
        bill.setLoginsell(loginsell);
        bill.setValue(tien);
        bill.setType(0);
        Noti noti = new Noti();
        notiReponsitory.addnotiauc(noti, loginpay, null, 4);
        
        return billReponsitory.addbill(bill);
    }

    @Override
    public List<Bill> getbillsell(Login login) {
        return billReponsitory.getbillsell(login);
    }
    @Override
    public List<Bill> getbillpay(Login login) {
        return billReponsitory.getbillpay(login);
    }

   

    @Override
    public List<Bill> getBillbyidBill(int i) {
       return billReponsitory.getBillbyidBill(i);
    }

    

    
    
    
}