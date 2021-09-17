/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.reponsitory.StatusReponsitory;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StatusReponsitoryImpl implements StatusReponsitory {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addOrUpdate(Status status) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.save(status);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Status> getStatus() {

        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Status");
        return q.getResultList();

    }

   
    

   

}
