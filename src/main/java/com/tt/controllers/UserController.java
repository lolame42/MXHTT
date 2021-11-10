/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.service.BillService;
import com.tt.service.NotiService;
import com.tt.service.ReportService;
import com.tt.service.SellService;
import com.tt.service.StatusService;
import com.tt.service.UfeelService;
import com.tt.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tt.reponsitory.BillRepository;

/**
 *
 * @author DAVADO
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StatusService statusService;
    @Autowired
    NotiService notiService;
    @Autowired
    SellService sellService;
    @Autowired
    UfeelService ufeelService;
    @Autowired
    BillService billService;
    @Autowired
    ReportService reportService;

    @ModelAttribute
    public void ahihi(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
    }
     @RequestMapping("/wall/{user_name}")
    public String wall(Model model, @PathVariable(value = "user_name") String user_name, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
       
        List<Login> list = userService.getListUserbyId(Integer.parseInt(user_name));

        if (!list.isEmpty()) {
            List<Status> allstatus = list.get(0).getStatus();
             if(reportService.countrp(list.get(0))>0)
             {
                 model.addAttribute("countrp",reportService.countrp(list.get(0)));
             }
            if (a.getId() == list.get(0).getId()) {
                for (int i = 0; i < allstatus.size(); i++) {
                    if (allstatus.get(i).getLogin().getId() == a.getId()||a.getUserrole().trim().equals("ROLE_ADMIN")) {
                        allstatus.get(i).setCountlike(allstatus.get(i).getUfeel().size());
                        allstatus.get(i).setCountcmt(allstatus.get(i).getComment().size());
                    }
                }
            }
            if (reportService.check(a, list.get(0), 1)) {
                model.addAttribute("check1", "mot");
            }
            if (reportService.check(a, list.get(0), 2)) {
                model.addAttribute("check2", "hai");
            }

            model.addAttribute("userwall", list.get(0));
            model.addAttribute("statuswall", allstatus);
            model.addAttribute("auctionwall", list.get(0).getAuction());
            return "wall";
        } else {
            return "error";
        }
    }

    @RequestMapping("/report/{iduser}/{type}")
    public String report(Model model, Principal principal, @PathVariable(value = "iduser") String iduser, @PathVariable(value = "type") String type) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        //
        List<Login> list = userService.getListUserbyId(Integer.parseInt(iduser));
        if (!list.isEmpty()) {
            if (sellService.Laso(type, 0)) {
                int typeint = Integer.parseInt(type);
                if ((typeint == 1 || typeint == 2) && !reportService.check(a, list.get(0), typeint)) {
                    List<Status> allstatus = list.get(0).getStatus();
                    if (a.getId() == list.get(0).getId()) {
                        for (int i = 0; i < allstatus.size(); i++) {
                            if (allstatus.get(i).getLogin().getId() == a.getId()) {
                                allstatus.get(i).setCountlike(allstatus.get(i).getUfeel().size());
                            }
                        }
                    }

                    if (reportService.addreport(a, list.get(0), type)) {
                        list = userService.getListUserbyId(Integer.parseInt(iduser));
                        if (reportService.check(a, list.get(0), 1)) {
                            model.addAttribute("check1", "mot");
                        }
                        if (reportService.check(a, list.get(0), 2)) {
                            model.addAttribute("check2", "hai");
                        }

                        model.addAttribute("userwall", list.get(0));
                        model.addAttribute("statuswall", allstatus);
                        model.addAttribute("auctionwall", list.get(0).getAuction());

                        return "wall";
                    }
                }
            }
        }
        return "error";
    }
}
