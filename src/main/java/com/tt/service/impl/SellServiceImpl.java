/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Sell;
import com.tt.reponsitory.SellReponsitory;
import com.tt.service.NotiService;
import com.tt.service.SellService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class SellServiceImpl implements SellService {

    @Autowired
    SellReponsitory sellReponsitory;
    @Autowired
    NotiService notiService;

    @Override
    public boolean addsell(Login login, Auction auction, double value) {
        Sell sell = new Sell();
        sell.setLoginsell(login);
        sell.setValue(value);
        sell.setAuctionsell(auction);
        Noti noti = new Noti();
        noti.setAvatar(login.getImage());
        noti.setName(login.getFull_name());
        notiService.addnotiauc(noti, auction.getLogin(), auction, 3);
        return sellReponsitory.addsell(sell);
    }

    @Override
    public List<Sell> getSellByIdAuction(int i) {

        return sellReponsitory.getSellByIdAuction(i);
    }

    @Override
    public boolean Laso(String string, int a) {
        if (a == 0) {
            try {
                Integer.parseInt(string);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else
        {
            try {
                Long.parseLong(string);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

}
