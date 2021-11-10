/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Login;
import com.tt.pojos.Report;
import com.tt.reponsitory.ReportRepository;
import com.tt.service.ReportService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public boolean addreport(Login login, Login login1, String type) {
        if (login.getId() == login1.getId()||check(login, login1, Integer.parseInt(type))) {
            return false;
        }
        Report report = new Report();
        report.setLoginBidong(login1);
        report.setLoginChudong(login);
        report.setDate(new Date());
        if (Integer.parseInt(type) == 1) {
            report.setType("Lời lẽ không đúng chuẩn mực.");
        }
        if (Integer.parseInt(type) == 2) {
            report.setType("Không thanh toán đấu giá");
        }

        return reportRepository.addreport(report);
    }

    @Override
    public boolean check(Login login, Login login1, int i) {
        return reportRepository.check(login, login1, i);
    }

    @Override
    public int countrp(Login login) {
        return reportRepository.countrp(login);
    }
}
