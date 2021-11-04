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
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String thongketheohashtag(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw", null);

        model.addAttribute("stthashtag", this.adminService.stthashtag(kw));
        return "stthashtag";
    }

    @GetMapping("/auctionstt")
    public String thongketrangthainhungphiendaugia(Model model) {
        model.addAttribute("auctionstt", this.adminService.auctionstt());
        return "auctionstt";
    }

    @GetMapping("/statustime")
    public String thongketrangthaitheothoigian(Model model, @RequestParam(required = false) Map<String, String> params) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null;
        String from = params.getOrDefault("fromDate", null);
        Date toDate = null;
        String to = params.getOrDefault("toDate", null);
        List<Object[]> list = adminService.stttime();
        try {
            if (from != null) {
                fromDate = f.parse(from);
            }
        } catch (Exception e) {
        }
        try {
            if (to != null) {
                toDate = f.parse(to);
            }
        } catch (Exception e) {
        }
        boolean check;

        for (int i = 0; i < 1; i++) {
            check = true;

            if (fromDate != null) {
                System.out.println("from");
                System.out.println(list.get(i)[0]);
                System.out.println(fromDate.getMonth());
                if ((int) list.get(i)[0] < fromDate.getMonth()) {
                    check = false;

                }
            }
            if (toDate != null) {
                System.out.println("to");
                System.out.println(list.get(i)[0]);
                System.out.println(toDate.getMonth());
                if ((int) list.get(i)[0] > toDate.getMonth()) {

                    check = false;
                }
            }
            if (check == false) {
                list.remove(i);
                i--;

            }

        }

        model.addAttribute("statustime", list);
        return "statustime";
    }

    @GetMapping("/auctiontime")
    public String thongkephiendaugiatheothang(Model model) {
        model.addAttribute("auctiontime", this.adminService.auctionMonth());
        return "auctiontime";
    }

    @GetMapping("/userreport")
    public String thongketaikhoanbireport(Model model) {
        model.addAttribute("userreport", this.adminService.userreport());
        return "userreport";
    }

    @GetMapping("/lock/{iduser}")
    public String locktaikhoan(Model model, @PathVariable(value = "iduser") String iduser, Principal principal) {

        Login a = userService.getUserByUserName(principal.getName()).get(0);
        List<Login> list = userService.getListUserbyId(Integer.parseInt(iduser));
        if (!list.isEmpty()) {
            if (list.get(0).getId() != a.getId() && a.getUserrole().trim().equals("ROLE_ADMIN")) {
                userService.xoa(list.get(0).getId());
                model.addAttribute("userreport", this.adminService.userreport());
                return "userreport";
            }
        }
        return "error";

    }
}
