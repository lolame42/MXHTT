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
public interface StatusRepository {
    boolean add(Status status, int id);
    
    List<Status> getStatus();
    
    List<Status> getStatusByIduser(int id);
    
    List<Status> getStatusByIdStatus(int id);
    
    List<Status> getStatus(int page);
    
    boolean deletestt(int id);
    
    boolean update(int id,String string,String string1);
    
    
    
   
     
}
