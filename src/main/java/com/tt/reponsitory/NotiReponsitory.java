/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;

/**
 *
 * @author DAVADO
 */
public interface NotiReponsitory {
   boolean add(Noti noti,Login login, Status status,int type);
}