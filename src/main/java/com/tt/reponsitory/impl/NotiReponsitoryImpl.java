/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;
import com.tt.reponsitory.NotiReponsitory;
import java.util.Date;
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

}
