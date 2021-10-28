/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import java.util.List;

/**
 *
 * @author DAVADO
 */
public interface StatusReponsitory {
    boolean add(Status status, int id);
    List<Status> getStatus();
    List<Status> getStatusByIduser(int id);
    List<Status> getStatusByIdStatus(int id);
    List<Status> getStatus(int page);
    boolean deletestt(int id);
    boolean update(int id,String string);
    
    
    
    
    boolean addauc(Auction auction,Login login);
    List<Auction> getAuction();
    List<Auction> getAuctionByIduser(int id);
    List<Auction> getAuctionByIdAuction(int id);
    List<Auction> getAuction(int page);
    boolean deleteauc(int id);
    
    
    
            
    
    
 
}
