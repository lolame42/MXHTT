/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import com.tt.pojos.Bill;
import com.tt.pojos.Check;
import com.tt.pojos.Login;
import java.util.List;

/**
 *
 * @author DAVADO
 */
public interface BillReponsitory {

    boolean addbill(Bill bill);

    List<Bill> getbillsell(Login login);

    List<Bill> getbillpay(Login login);

    List<Bill> getBillbyidBill(int i);

    List<Check> getCheckbyidBill(int i);
    
    boolean addcheck(Check check);

}
