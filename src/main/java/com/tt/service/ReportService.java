/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service;

import com.tt.pojos.Login;

/**
 *
 * @author DAVADO
 */
public interface ReportService {
    boolean addreport(Login login, Login login1,String type);
    boolean check(Login login, Login login1,int type);
    int countrp(Login login);
}
