/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Comment;
import com.tt.pojos.Sell;
import com.tt.pojos.Status;
import com.tt.service.StatusService;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tt.reponsitory.CommentRepository;

/**
 *
 * @author DAVADO
 */
@Transactional
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private StatusService statusService;

    @Override
    public boolean addcmt(Comment cmt) {
        Session s = sessionFactory.getObject().getCurrentSession();
        cmt.setDate(new Date());
        try {

            s.save(cmt);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List<Comment> getCmtByIdStatus(int i) {
        Status a = statusService.getStatusByIdStatus(i).get(0);
        List<Comment> list = a.getComment();
        Collections.sort(list, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
               return o2.getId()-o1.getId();
            }          
        });
        return list;
    }
}
