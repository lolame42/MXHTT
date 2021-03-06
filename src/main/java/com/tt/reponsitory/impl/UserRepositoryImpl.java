/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Status;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tt.reponsitory.UserRepository;

/**
 *
 * @author Tu
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    @Transactional
    public List<Login> getUsers() {
        Session s = sessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From Login");
        return q.getResultList();
    }

    @Override
    public List<Login> getUsers(String kw) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Login> query = builder.createQuery(Login.class);
        Root root = query.from(Login.class);
        query = query.select(root);
        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.like(root.get("full_name").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);

        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Login> getUserByUserName(String string) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Login> query = builder.createQuery(Login.class);
        Root root = query.from(Login.class);
        query = query.select(root);
        if (!string.isEmpty() && string != null) {
            Predicate p = builder.like(root.get("user_name").as(String.class),
                    String.format("%s", string));
            query = query.where(p);

        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Login> getUserByPhone(String string) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Login> query = builder.createQuery(Login.class);
        Root root = query.from(Login.class);
        query = query.select(root);
        if (!string.isEmpty() && string != null) {
            Predicate p = builder.like(root.get("phone").as(String.class),
                    String.format("%s", string));
            query = query.where(p);

        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Login> getUserByEmail(String string) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Login> query = builder.createQuery(Login.class);
        Root root = query.from(Login.class);
        query = query.select(root);
        if (!string.isEmpty() && string != null) {
            Predicate p = builder.like(root.get("email").as(String.class),
                    String.format("%s", string));
            query = query.where(p);

        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Login getUserById(int id) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        Login login = session.get(Login.class, id);
        return login;

    }

    @Override
    public List<Login> getListUserByid(int id) {

        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Login> query = builder.createQuery(Login.class);
        Root root = query.from(Login.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("Id"), id);
        query = query.where(p);

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean xoa(int i) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        Login list = getUserById(i);
        if (list.getUserrole() == "ROLE_USER") {
            try {
                session.delete(list);
                return true;
            } catch (Exception e) {
                
            }
        }
        return false;
    }
}
