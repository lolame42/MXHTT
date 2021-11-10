/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tt.reponsitory.StatusRepository;

/**
 *
 * @author DAVADO
 */
@Transactional
@Repository
public class StatusRepositoryImpl implements StatusRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Cloudinary Cloudinary;

    @Override
    public boolean add(Status status, int id) {
        try {
            Session session = sessionFactory.getObject().getCurrentSession();
            Login login = session.get(Login.class, id);
            if (status.getFile() != null) {
                Map r = this.Cloudinary.uploader().upload(status.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                status.setImage((String) r.get("secure_url"));
            }
            status.setDate(new Date());
            status.setLogin(login);

            session.save(status);
            return true;

        } catch (IOException ex) {
            Logger.getLogger(StatusRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

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

    @Override
    public List<Status> getStatus(int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Status> query = builder.createQuery(Status.class);
        Root root = query.from(Status.class);
        query = query.select(root);

        query = query.orderBy(builder.desc(root.get("idStatus")));

        Query q = session.createQuery(query);

        int max = 30;
        q.setMaxResults(max);
        q.setFirstResult((page - 1) * max);
        return q.getResultList();
    }



    @Override
    public boolean deletestt(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Status status = getStatusByIdStatus(id).get(0);
            session.delete(status);
            return true;
        } catch (Exception e) {}
        return false;
    }
    @Override
    public boolean update(int id, String string, String string1) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Status status = getStatusByIdStatus(id).get(0);
            if (!string.equals("trong")) {
                status.setContent(string);
            }
            if(!string1.equals("trong"))
                status.setHashtag(string1);
            session.update(status);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
