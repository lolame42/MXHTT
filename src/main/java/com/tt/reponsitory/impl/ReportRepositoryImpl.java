/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Report;
import com.tt.pojos.Status;
import com.tt.reponsitory.ReportRepository;
import java.util.Date;
import java.util.List;
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
@Repository
@Transactional
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addreport(Report report) {

        Session session = sessionFactory.getObject().getCurrentSession();

        try {
            session.save(report);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;

    }

    @Override
    public boolean check(Login login, Login login1, int type) {
        List<Report> list = login1.getReportbidong();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLoginChudong().getId() == login.getId()) {
                if (type == 1) {
                    if (list.get(i).getType().equals("Lời lẽ không đúng chuẩn mực.")) {
                        return true;
                    }
                } else {
                    if (type == 2) {
                        if (list.get(i).getType().equals("Không thanh toán đấu giá")) {
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }

    @Override
    public int countrp(Login login) {
        return login.getReportbidong().size();
    }

}
