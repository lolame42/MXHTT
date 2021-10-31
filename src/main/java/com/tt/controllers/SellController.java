/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Auction;
import com.tt.pojos.Bill;
import com.tt.pojos.Login;
import com.tt.pojos.Sell;
import com.tt.service.BillService;
import com.tt.service.NotiService;
import com.tt.service.SellService;
import com.tt.service.StatusService;
import com.tt.service.UfeelService;
import com.tt.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tt.reponsitory.BillRepository;

/**
 *
 * @author DAVADO
 */
@Controller
public class SellController {

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
    BillRepository billReponsitory;

    @ModelAttribute
    public void ahihi(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
    }

    @RequestMapping("/billsell/{kind}")
    public String billban(Model model, @PathVariable(value = "kind") String kind, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        //
        if (kind != null) {
            List<Auction> listauction = statusService.getAuctionByIdAuction(Integer.parseInt(kind));
            if (!listauction.isEmpty()) {
                Auction auction = listauction.get(0);
                if (auction.getLogin().getId() == a.getId()) {
                    List<Sell> sell = sellService.getSellByIdAuction(auction.getId());
                    if (!sell.isEmpty()) {
                        billService.addbill(auction.getLogin(), sell.get(0).getLoginsell(), (int) sell.get(0).getValue());
                    }
                    statusService.deleteauc(auction.getId());

                }
            }
            model.addAttribute("allbillsell", billService.getbillsell(a));
            model.addAttribute("allbillpay", billService.getbillpay(a));

            return "billsell";
        }

        return "error";

    }

    @GetMapping("/access/{idBill}")
    public String access(Model model, Principal principal, @PathVariable(value = "idBill") String idBill) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        List<Bill> bill = billService.getBillbyidBill(Integer.parseInt(idBill));
        if (bill.get(0).getLoginsell().getId() == a.getId()) {
            if (bill.get(0).getType() == 1) {
                billService.access(bill.get(0), 3);
                a = userService.getUserByUserName(principal.getName()).get(0);
                model.addAttribute("allbillsell", billService.getbillsell(a));
                model.addAttribute("allbillpay", billService.getbillpay(a));
                return "billsell";

            }

        }

        return "error";

    }

    @GetMapping("/request/{idBill}")
    public String request(Model model, Principal principal, @PathVariable(value = "idBill") String idBill) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        List<Bill> bill = billService.getBillbyidBill(Integer.parseInt(idBill));
        if (bill.get(0).getLoginsell().getId() == a.getId()) {
            if (bill.get(0).getType() == 1) {
                billService.access(bill.get(0), 2);
                a = userService.getUserByUserName(principal.getName()).get(0);
                model.addAttribute("allbillsell", billService.getbillsell(a));
                model.addAttribute("allbillpay", billService.getbillpay(a));
                return "billsell";

            }

        }

        return "error";

    }

    @GetMapping("/check/{idBill}")
    public String check(Model model, Principal principal, @PathVariable(value = "idBill") String idBill) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        List<Bill> bill = billService.getBillbyidBill(Integer.parseInt(idBill));
        if (bill.get(0).getLoginpay().getId() == a.getId()) {
            if (bill.get(0).getType() == 0 || bill.get(0).getType() == 2) {
                model.addAttribute("bill", bill.get(0));
                model.addAttribute("newbill", new Bill());
                return "check";
            }
            if (bill.get(0).getType() == 3) {
                model.addAttribute("bill", bill.get(0));
                return "access";
            }

        }
        if (bill.get(0).getLoginsell().getId() == a.getId()) {
            if (bill.get(0).getType() == 1 || bill.get(0).getType() == 3) {
                model.addAttribute("bill", bill.get(0));
                return "access";
            }
        }
        return "error";

    }

    @PostMapping("/check/{idBill}")
    public String addcheck(Model model, Principal principal, @PathVariable(value = "idBill") String idBill, @ModelAttribute(value = "newbill") Bill newbill) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        List<Bill> bill = billService.getBillbyidBill(Integer.parseInt(idBill));
        model.addAttribute("bill", bill.get(0));
        model.addAttribute("newbill", new Bill());
        if (!newbill.getCodemomo().isEmpty()) {
            if (billService.update(bill.get(0), newbill.getCodemomo())) {
                a = userService.getUserByUserName(principal.getName()).get(0);
                model.addAttribute("err", "xác nhận thành công");
                model.addAttribute("allbillsell", billService.getbillsell(a));
                model.addAttribute("allbillpay", billService.getbillpay(a));

                return "billsell";
            }
            model.addAttribute("err", "xác nhận không thành công");

        }
        model.addAttribute("err", "mã giao dịch định dạng không xác định");
        return "check";
    }

}
