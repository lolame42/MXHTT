/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Bill;
import com.tt.pojos.Report;
import com.tt.pojos.Status;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tt.reponsitory.AdminRepository;
import java.util.Date;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author DAVADO
 */
@Transactional
@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> stthashtag(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root root = q.from(Status.class);
        Predicate p;
        if (kw != null && !kw.isEmpty()) {
            p = b.like(root.get("hashtag").as(String.class),
                    String.format("%%%s%%", kw));
            q = q.where(p);
        }

        q.multiselect(root.get("hashtag"), b.count(root.get("hashtag")));
        q.groupBy(root.get("hashtag"));
        Query query = session.createQuery(q);
        return query.getResultList();

    }

    @Override
    public List<Object[]> auctionstt() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root root = q.from(Bill.class);

        q.multiselect(root.get("type"), b.count(root.get("type")));
        q.groupBy(root.get("type"));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> userreport() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root root = q.from(Report.class);

        q.multiselect(root.get("loginbidong"), b.count(root.get("loginbidong")));
        q.groupBy(root.get("loginbidong"));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> auctionMonth() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root root = q.from(Auction.class);
        Predicate p;

        q.multiselect(b.function("MONTH", Integer.class, root.get("date")), b.function("YEAR", Integer.class, root.get("date")), b.count(b.function("MONTH", Integer.class, root.get("date"))));
        q.groupBy(b.function("MONTH", Integer.class, root.get("date")), b.function("YEAR", Integer.class, root.get("date")));
        Query query = session.createQuery(q);
        return query.getResultList();

    }

    @Override
    public List<Object[]> stttime() {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root root = q.from(Status.class);
        Predicate p;

    

        q.multiselect(b.function("MONTH", Integer.class, root.get("date")), b.function("YEAR", Integer.class, root.get("date")), b.count(b.function("MONTH", Integer.class, root.get("date"))));
        q.groupBy(b.function("MONTH", Integer.class, root.get("date")), b.function("YEAR", Integer.class, root.get("date")));
        Query query = session.createQuery(q);
        
        return query.getResultList();

    }

}
