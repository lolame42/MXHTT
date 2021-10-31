/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Report;
import com.tt.pojos.Sell;
import com.tt.pojos.Status;
import com.tt.service.StatusService;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
import com.tt.reponsitory.SellRepository;

/**
 *
 * @author DAVADO
 */
@Transactional
@Repository
public class SellRepositoryImpl implements SellRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private StatusService auctionService;

    @Override
    public boolean addsell(Sell sell) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try {
            sell.setDate(new Date());
            s.save(sell);
            return true;
        } catch (Exception e) {
        }
        return false;

    }

    @Override
    public List<Sell> getSellByIdAuction(int i) {
        Auction a = auctionService.getAuctionByIdAuction(i).get(0);
        List<Sell> list = a.getListsell();
        Collections.sort(list, new Comparator<Sell>() {
            @Override
            public int compare(Sell o1, Sell o2) {
                return o2.getId() - o1.getId();
            }
        });
        return list;
    }

   
}
