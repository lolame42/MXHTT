/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Bill;
import com.tt.pojos.Login;
import com.tt.pojos.Sell;
import com.tt.reponsitory.BillReponsitory;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DAVADO
 */
@Transactional
@Repository
public class BillReponsitoryImpl implements BillReponsitory {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addbill(Bill bill) {
        Session session =  this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(bill);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Bill> getbillsell(Login login) {
        List<Bill> list = login.getBillsell();
        Collections.sort(list, new Comparator<Bill>() {
           @Override
           public int compare(Bill o1, Bill o2) {
               return o2.getId() - o1.getId();
           }
       });
       return list;
        
        
    }

    @Override
    public List<Bill> getbillpay(Login login) {
        List<Bill> list = login.getBillpay();
        Collections.sort(list, new Comparator<Bill>() {
           @Override
           public int compare(Bill o1, Bill o2) {
               return o2.getId() - o1.getId();
           }
       });
       return list;
        
        
    }

}
