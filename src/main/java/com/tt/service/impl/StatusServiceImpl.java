/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.reponsitory.StatusReponsitory;
import com.tt.service.StatusService;
import com.tt.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private Cloudinary Cloudinary;
    @Autowired
    private StatusReponsitory statusReponsitory;
    @Autowired
    private UserService userService;

    @Override
    public boolean add(Status status, int i) {
        return statusReponsitory.add(status, i);
    }

    @Override
    public List<Status> getStatus() {
        return statusReponsitory.getStatus();
    }

    @Override
    public List<Status> getStatusByIduser(int i) {
        return statusReponsitory.getStatusByIduser(i);
    }

    @Override
    public List<Status> getStatusByIdStatus(int id) {
        return statusReponsitory.getStatusByIdStatus(id);
    }

    @Override
    public List<Status> getStatus(int page) {
        return statusReponsitory.getStatus(page);
    }

    @Override
    public boolean addauc(Auction auction, Login login) {
        return statusReponsitory.addauc(auction, login);
    }

    @Override
    public List<Auction> getAuction() {
        return statusReponsitory.getAuction();
    }

    @Override
    public List<Auction> getAuctionByIduser(int i) {
        return statusReponsitory.getAuctionByIduser(i);
    }

    @Override
    public List<Auction> getAuctionByIdAuction(int id) {
        return statusReponsitory.getAuctionByIdAuction(id);
    }

    @Override
    public List<Auction> getAuction(int page) {
        return statusReponsitory.getAuction(page);
    }

}
