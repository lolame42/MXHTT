/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Login;
import com.tt.reponsitory.LoginReponsitory;
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
public class LoginReponsitoryImpl implements LoginReponsitory{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Login> getLogins(String user_name) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Login> query = builder.createQuery(Login.class);
        Root root = query.from(Login.class);
        query = query.select(root);
        
        if(!user_name.isEmpty()){
            Predicate p = builder.equal(root.get("user_name")
                    .as(String.class), user_name.trim());
            query=query.where(p);
        }
        
        Query q= session.createQuery(query);
        return q.getResultList();
    }
    @Override
    public boolean addOrUpdate(Login login) {
        Session session=sessionFactory.getObject().getCurrentSession();
        try {
            session.save(login);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
        
    }
}
