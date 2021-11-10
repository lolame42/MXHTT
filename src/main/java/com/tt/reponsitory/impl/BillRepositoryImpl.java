/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Bill;
import com.tt.pojos.Login;
import com.tt.pojos.Sell;
import com.tt.pojos.Status;
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
import com.tt.reponsitory.BillRepository;

/**
 *
 * @author DAVADO
 */
@Transactional
@Repository
public class BillRepositoryImpl implements BillRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addbill(Bill bill) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(bill);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Bill> getbillsell(Login login) {
        List<Bill> list = login.getBillsell();
        Collections.sort(list, new Comparator<Bill>() {
            @Override
            public int compare(Bill o1, Bill o2) {
                return o2.getId() - o1.getId();
            }
        });
        return list;
    }

    @Override
    public List<Bill> getbillpay(Login login) {
        List<Bill> list = login.getBillpay();
        Collections.sort(list, new Comparator<Bill>() {
            @Override
            public int compare(Bill o1, Bill o2) {
                return o2.getId() - o1.getId();
            }
        });
        return list;
    }

    @Override
    public List<Bill> getBillbyidBill(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root root = query.from(Bill.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("id"), i);
        query = query.where(p);

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean update(Bill bill, String code) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            
            Bill addcode = getBillbyidBill(bill.getId()).get(0);
            addcode.setCodemomo(code);
            addcode.setType(1);
            addcode.setDateend(new Date());
            session.update(addcode);
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

    @Override
    public boolean access(Bill bill, int i) {
       Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            
            Bill addcode = getBillbyidBill(bill.getId()).get(0);
            addcode.setType(i);
            session.update(addcode);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
