/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import java.util.List;

/**
 *
 * @author DAVADO
 */
public interface AuctionService {

    boolean addauc(Auction auction, Login login);

    List<Auction> getAuction();

    List<Auction> getAuctionByIduser(int id);

    List<Auction> getAuctionByIdAuction(int id);

    List<Auction> getAuction(int page);

    boolean deleteauc(int id);

}
