/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.service.AdminService;
import com.tt.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DAVADO
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/stthashtag")
    public String thongketheohashtag(Model model) {
        model.addAttribute("stthashtag", this.adminService.stthashtag());
        return "stthashtag";
    }

    @GetMapping("/auctionstt")
    public String thongketrangthainhungphiendaugia(Model model) {
        model.addAttribute("auctionstt", this.adminService.auctionstt());
        return "auctionstt";
    }

    @GetMapping("/userreport")
    public String thongketaikhoanbireport(Model model) {
        model.addAttribute("userreport", this.adminService.userreport());
        return "userreport";
    }

    @GetMapping("/lock/{iduser}")
    public String locktaikhoan(Model model, @PathVariable(value = "iduser") String iduser) {
        List<Login> list = userService.getListUserbyId(Integer.parseInt(iduser));
        if (!list.isEmpty()) {
            userService.xoa(list.get(0).getId());
            model.addAttribute("userreport", this.adminService.userreport());
            return "userreport";

        }
        return "error";

    }
}
