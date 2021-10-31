/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import com.tt.pojos.Login;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DAVADO
 */

public interface LoginRepository {
    List<Login> getLogins(String user_name);
    boolean addOrUpdate(Login login);
    
}
