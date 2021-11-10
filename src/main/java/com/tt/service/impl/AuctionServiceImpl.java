/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.reponsitory.AuctionRepository;
import com.tt.service.AuctionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    private AuctionRepository auctionRepository;
    
    @Override
    public boolean addauc(Auction auction, Login login) {
        return auctionRepository.addauc(auction, login);
    }

    @Override
    public List<Auction> getAuction() {
        return auctionRepository.getAuction();
    }

    @Override
    public List<Auction> getAuctionByIduser(int i) {
        return auctionRepository.getAuctionByIduser(i);
    }

    @Override
    public List<Auction> getAuctionByIdAuction(int id) {
        return auctionRepository.getAuctionByIdAuction(id);
    }

    @Override
    public List<Auction> getAuction(int page) {
        return auctionRepository.getAuction(page);
    }

  

    @Override
    public boolean deleteauc(int id) {
        return auctionRepository.deleteauc(id);
    }
    
    
}
