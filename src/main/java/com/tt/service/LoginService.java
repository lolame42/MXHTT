/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service;

import com.tt.pojos.Login;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author DAVADO
 */
public interface LoginService extends UserDetailsService {

    List<Login> getLogins(String user_name);

    boolean addOrUpdate(Login login);
    
    boolean Update(Login login);

}
