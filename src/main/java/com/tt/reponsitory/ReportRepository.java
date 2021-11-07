/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import com.tt.pojos.Login;
import com.tt.pojos.Report;

/**
 *
 * @author DAVADO
 */
public interface ReportRepository {
    boolean addreport(Report report);
    boolean check(Login login, Login login1,int type);
    int countrp(Login login);
    
}
