/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service;

import com.tt.pojos.Bill;
import com.tt.pojos.Login;
import java.util.List;

/**
 *
 * @author DAVADO
 */
public interface BillService {
     boolean addbill(Login loginsell,Login loginpay, int tien);
     List<Bill> getbillsell(Login login);
     List<Bill> getbillpay(Login login);
}
