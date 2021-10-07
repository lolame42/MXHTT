/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Status;
import com.tt.pojos.Ufeel;
import com.tt.reponsitory.UfeelReponsitory;
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
@Transactional
@Repository
public class UfeelReponsitoryImpl implements UfeelReponsitory {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean add(Ufeel ufeel) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ufeel.setDate(new Date());
        try {
            session.save(ufeel);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;

    }

    @Override
    public boolean check(Status status, int i) {
        List<Ufeel> list= status.getUfeel();
        for(int j=0;j<list.size();j++)
        {
            if(list.get(j).getIdUser()==i)
                return true;
        }
        return false;
    }

}
