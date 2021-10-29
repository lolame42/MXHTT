/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Sell;
import com.tt.pojos.Status;
import com.tt.service.SellService;
import com.tt.service.StatusService;
import com.tt.service.UfeelService;
import com.tt.service.UserService;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DAVADO
 */
@RestController
public class APIcontroller {

    @Autowired
    StatusService statusService;
    @Autowired
    UserService userService;
    @Autowired
    UfeelService ufeelService;
    @Autowired
    SellService sellService;

    @PostMapping("api/add-like/{idStatus}/{idlogin}")
    public void addlike(@PathVariable(value = "idStatus") String idStatus, @PathVariable(value = "idlogin") String idlogin) {
        Login a = userService.getUserById(Integer.parseInt(idlogin));

        Status status = new Status();
        status = statusService.getStatusByIdStatus(Integer.parseInt(idStatus)).get(0);
        if (ufeelService.check(status, a.getId()) == false) {
            ufeelService.add(status, a.getId());
        }
    }

    
}
