/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service;

import com.tt.pojos.Status;
import com.tt.pojos.Ufeel;

/**
 *
 * @author DAVADO
 */
public interface UfeelService {
    boolean add(Status status, int id);
    
    boolean check(Status status,int id);
    
}
