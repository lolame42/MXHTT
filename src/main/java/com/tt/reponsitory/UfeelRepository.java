/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.pojos.Ufeel;

/**
 *
 * @author DAVADO
 */
public interface UfeelRepository {
    boolean add(Ufeel ufeel);
    
    boolean check(Status status,int id);
    
}
