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
import com.tt.reponsitory.AuctionRepository;
import java.io.IOException;
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
public class AuctionRepositoryImpl implements AuctionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Cloudinary Cloudinary;


    @Override
    public boolean addauc(Auction auction, Login login) {

        try {
            Session session = sessionFactory.getObject().getCurrentSession();

            if (auction.getFile() != null) {
                Map r = this.Cloudinary.uploader().upload(auction.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                auction.setImage((String) r.get("secure_url"));
            }
            auction.setDate(new Date());
            auction.setLogin(login);

            session.save(auction);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(StatusRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Auction> getAuction() {

        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Auction");
        return q.getResultList();
    }

    @Override
    public List<Auction> getAuctionByIduser(int i) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Login login = session.get(Login.class, i);
        List<Auction> test = login.getAuction();
        return test;
    }

    @Override
    public List<Auction> getAuctionByIdAuction(int id) {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Auction> query = builder.createQuery(Auction.class);
        Root root = query.from(Auction.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("idauction"), id);
        query = query.where(p);

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Auction> getAuction(int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Auction> query = builder.createQuery(Auction.class);
        Root root = query.from(Auction.class);
        query = query.select(root);
        query = query.orderBy(builder.desc(root.get("idauction")));

        Query q = session.createQuery(query);

        int max = 30;
        q.setMaxResults(max);
        q.setFirstResult((page - 1) * max);
        return q.getResultList();
    }
    
    @Override
    public boolean deleteauc(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Auction auction = getAuctionByIdAuction(id).get(0);
            session.delete(auction);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

}
