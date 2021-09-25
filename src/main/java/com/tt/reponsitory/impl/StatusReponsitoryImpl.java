/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.reponsitory.StatusReponsitory;
import java.util.Date;
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
    public boolean add(Status status, int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Login login = session.get(Login.class, id);
        Date date = new Date();
        status.setDate(date);
        status.setLogin(login);
        if(status.getHashtag().isEmpty())
            status.setHashtag(null);

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

    @Override
    public List<Status> getStatusByIduser(int i) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Login login = session.get(Login.class, i);
        List<Status> test = login.getStatus();
        return test;
    }

    @Override
    public List<Status> getStatusByIdStatus(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Status> query = builder.createQuery(Status.class);
        Root root = query.from(Status.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("idStatus"), id);
        query = query.where(p);

        Query q = session.createQuery(query);
        return q.getResultList();
    }

   
}
