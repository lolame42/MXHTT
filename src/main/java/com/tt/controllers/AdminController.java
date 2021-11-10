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
import java.text.ParseException;
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
    public String thongketrangthaitheothoigian(Model model, @RequestParam(required = false) Map<String, String> params) throws ParseException {
        String from = params.getOrDefault("fromDate", "0001-01-01");
        String to = params.getOrDefault("toDate", "3000-01-01");
        List<Object[]> list = adminService.stttime();
        List<Object[]> list1 = null;
        int frommonth, fromyear, tomonth, toyear;
        frommonth = fromyear = tomonth = toyear = -1;
        if (!from.isEmpty()) {
            String[] parts = from.split("-");
            frommonth = Integer.parseInt(parts[1]);
            fromyear = Integer.parseInt(parts[0]);
        }
        if (!to.isEmpty()) {
            String[] parts1 = to.split("-");
            tomonth = Integer.parseInt(parts1[1]);
            toyear = Integer.parseInt(parts1[0]);
        }
        for (int i = 0; i < list.size(); i++) {
            int month = (int) list.get(i)[0];
            int year = (int) list.get(i)[1];
            boolean check = true;
            if (frommonth != -1) {
                if (fromyear > year || (fromyear == year && frommonth > month)) {
                    check = false;
                }
            }
            if (tomonth != -1) {
                if (toyear < year || (toyear == year && tomonth < month)) {
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
    public String thongkephiendaugiatheothang(Model model, @RequestParam(required = false) Map<String, String> params) {
        String from = params.getOrDefault("fromDate", "0001-01-01");
        String to = params.getOrDefault("toDate", "3000-01-01");
        List<Object[]> list = this.adminService.auctionMonth();
        List<Object[]> list1 = null;
        int frommonth, fromyear, tomonth, toyear;
        frommonth = fromyear = tomonth = toyear = -1;
        if (!from.isEmpty()) {
            String[] parts = from.split("-");
            frommonth = Integer.parseInt(parts[1]);
            fromyear = Integer.parseInt(parts[0]);
        }
        if (!to.isEmpty()) {
            String[] parts1 = to.split("-");
            tomonth = Integer.parseInt(parts1[1]);
            toyear = Integer.parseInt(parts1[0]);
        }
        for (int i = 0; i < list.size(); i++) {
            int month = (int) list.get(i)[0];
            int year = (int) list.get(i)[1];
            boolean check = true;
            if (frommonth != -1) {
                if (fromyear > year || (fromyear == year && frommonth > month)) {
                    check = false;
                }
            }
            if (tomonth != -1) {
                if (toyear < year || (toyear == year && tomonth < month)) {
                    check = false;
                }
            }
            if (check == false) {
                list.remove(i);
                i--;
            }
        }

        model.addAttribute("auctiontime", list);
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
