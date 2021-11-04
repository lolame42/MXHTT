/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import java.util.Date;
import java.util.List;

/**
 *
 * @author DAVADO
 */
public interface AdminRepository {

    List<Object[]> stthashtag(String kw);

    List<Object[]> auctionstt();

    List<Object[]> userreport();

    List<Object[]> auctionMonth();

    List<Object[]> stttime();

}
