/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.service.NotiService;
import com.tt.service.SellService;
import com.tt.service.StatusService;
import com.tt.service.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DAVADO
 */
@Controller
public class StatusController {

    @Autowired
    UserService userService;
    @Autowired
    StatusService statusService;
    @Autowired
    NotiService notiService;
    @Autowired
    SellService sellService;

    @ModelAttribute
    public void ahihi(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal, @RequestParam(required = false) Map<String, String> params) {

        //
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));

        model.addAttribute("allstatus", this.statusService.getStatus(page));
        model.addAttribute("countstt", this.statusService.getStatus().size());
        //
        model.addAttribute("status", new Status());

        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String name = auth.getName(); //get logged in username
        //model.addAttribute("username", name);
        //model.addAttribute("name",principal.getName()) ;
        return "home";
    }

    @PostMapping("/home")
    public String addstt(Model model, @ModelAttribute(value = "status") Status status, Principal principal, @RequestParam(required = false) Map<String, String> params) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        //

        //
        if (status.getContent() == null || status.getContent().isEmpty()) {
            model.addAttribute("errcontent", "Nội dung trạng thái không được trống");
        } else {
            if (status.getHashtag().length() > 20) {
                model.addAttribute("errhashtag", "Hashtag tối đa 20 ký tự ");
            } else {
                if (status.getHashtag().isEmpty()) {
                    status.setHashtag(null);
                }
                if (this.statusService.add(status, a.getId()) == true) {
                    model.addAttribute("errMsg", "Đăng bài thành công");

                } else {
                    model.addAttribute("errMsg", "Đăng bài không thành công");

                }
            }
        }

        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        model.addAttribute("allstatus", this.statusService.getStatus(1));
        model.addAttribute("countstt", this.statusService.getStatus().size());
        model.addAttribute("status", new Status());

        return "home";

    }

    @GetMapping("/auction")
    public String auction(Model model, Principal principal, @RequestParam(required = false) Map<String, String> params) {

        //
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));

        model.addAttribute("allauction", this.statusService.getAuction(page));
        model.addAttribute("countauc", this.statusService.getAuction().size());
        model.addAttribute("auction", new Auction());

        return "auction";
    }

    @PostMapping("/auction")
    public String addauc(Model model, @ModelAttribute(value = "auction") Auction auction, Principal principal, @RequestParam(required = false) Map<String, String> params) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        //
        int dem = 0;
        if (auction.getContent() == null || auction.getContent().isEmpty() || auction.getContent().length() > 4999) {
            model.addAttribute("errcontent", "Giới thiệu sản phẩm không được trống và tối đa 5000 ký tự");
            dem++;
        }
        if (auction.getFile().isEmpty() || auction.getFile() == null) {
            model.addAttribute("errimage", "Hình ảnh sản phẩm trống");
            dem++;
        }
        if (auction.getStepstr().isEmpty() || auction.getStepstr() == null) {
            model.addAttribute("errstep", "Bước trống");
            dem++;
        } else {
            if (sellService.Laso(auction.getStepstr())) {
                if (Double.parseDouble(auction.getStepstr()) % 10 != 0 || Double.parseDouble(auction.getStepstr()) > 100) {
                    model.addAttribute("errstep", "Bước cần là số nguyên, tối đa 1000 (Đơn vị ngàn VND)");
                    dem++;
                }
            } else {
                dem++;
                model.addAttribute("errstep", "Bước không định dạng");
            }

        }

        if (dem == 0) {
            auction.setStep(Double.parseDouble(auction.getStepstr()));
            if (statusService.addauc(auction, a) == true) {
                model.addAttribute("err", "Thêm thành công");

            } else {
                model.addAttribute("err", "Thêm thất bại");
            }
        } else {
            model.addAttribute("err", "Thêm thất bại");
        }
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));

        model.addAttribute("allauction", this.statusService.getAuction(page));
        model.addAttribute("countauc", this.statusService.getAuction().size());
        model.addAttribute("auction", new Auction());

        return "auction";

    }

}
