/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service;

import com.tt.pojos.Login;
import java.util.List;

/**
 *
 * @author Tu
 */
public interface UserService {

    List<Login> getUsers();

    List<Login> getUsers(String kw);

    List<Login> getUserByUserName(String string);

    Login getUserById(int id);

    List<Login> getListUserbyId(int id);

    List<Login> getUserByPhone(String string);

    List<Login> getUserByEmail(String string);
    
    boolean xoa(int id );
    

}
