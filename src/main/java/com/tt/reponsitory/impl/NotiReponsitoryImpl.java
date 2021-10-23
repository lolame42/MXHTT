/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Sell;
import com.tt.pojos.Status;
import com.tt.reponsitory.NotiReponsitory;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DAVADO
 */
@Repository
@Transactional
public class NotiReponsitoryImpl implements NotiReponsitory {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean add(Noti noti, Login login, Status status, int type) {
        Session session = sessionFactory.getObject().getCurrentSession();
        noti.setLoginnoti(login);
        noti.setStatusnoti(status);
        noti.setType(type);
        Date date = new Date();
        noti.setDate(date);
        try {
            session.save(noti);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
        

    }

    @Override
    public boolean addnotiauc(Noti noti, Login login, Auction auction, int type) {
        Session session = sessionFactory.getObject().getCurrentSession();
        noti.setLoginnoti(login);
        noti.setAuctionnoti(auction);
        noti.setType(type);
        Date date = new Date();
        noti.setDate(date);
        try {
            session.save(noti);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
        
    }

    @Override
    public List<Noti> getNotibyLogin(Login i) {
       
       List<Noti> list = i.getNotiuser();
       Collections.sort(list, new Comparator<Noti>() {
           @Override
           public int compare(Noti o1, Noti o2) {
               return o2.getId() - o1.getId();
           }
       });
       return list;
    }

}
