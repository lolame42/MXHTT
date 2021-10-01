/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;
import com.tt.service.StatusService;
import com.tt.service.UserService;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    private Cloudinary Cloudinary;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //
        List<Status> allstatus = statusService.getStatus();
        Collections.reverse(allstatus);
        model.addAttribute("allstatus", allstatus);
        //
        model.addAttribute("status", new Status());

        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String name = auth.getName(); //get logged in username
        //model.addAttribute("username", name);
        //model.addAttribute("name",principal.getName()) ;
        return "home";
    }

    @PostMapping("/home")
    public String addstt(Model model, @ModelAttribute(value = "status") Status status, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //
        List<Status> allstatus = statusService.getStatus();
        Collections.reverse(allstatus);
        model.addAttribute("allstatus", allstatus);
        //
        model.addAttribute("status", new Status());

        if (!status.getContent().isEmpty()) {
            if (status.getHashtag().isEmpty()) {
                status.setHashtag(null);
            }

            if (this.statusService.add(status, a.getId()) == false) {
                model.addAttribute("errMsg", "Đăng bài không thành công");
                return "home";
            } else {
                return "redirect:/home";
            }

        } else {
            model.addAttribute("errMsg", "Status không được trống");
        }

        return "home";

    }

    @GetMapping("/auction")
    public String auctionhome(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //
        List<Status> allstatus = statusService.getStatus();
        Collections.reverse(allstatus);
        model.addAttribute("allstatus", allstatus);
        //
        model.addAttribute("status", new Status());

        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String name = auth.getName(); //get logged in username
        //model.addAttribute("username", name);
        //model.addAttribute("name",principal.getName()) ;
        return "auction";
    }

    @PostMapping("/auction")
    public String addauc(Model model, @ModelAttribute(value = "status") Status status, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //
        List<Status> allstatus = statusService.getStatus();
        Collections.reverse(allstatus);
        model.addAttribute("allstatus", allstatus);
        //
        model.addAttribute("status", new Status());
        int dem = 0;
        if (status.getContent().isEmpty()) {
            model.addAttribute("ErrContent", "Giới thiệu sản phẩm trống");
            dem++;
        }
        if (status.getFile().isEmpty()) {
            model.addAttribute("ErrFile", "Ảnh sản phẩm trống");
            dem++;
        }
        if (status.getStep().isEmpty()) {
            model.addAttribute("ErrStep", "Bước nhảy Trống");
            dem++;
        } else {
            double bien = Integer.parseInt(status.getStep());
            if (bien < 10000 || bien % 10000 != 0 || bien > 100000) {
                model.addAttribute("ErrStep", "Bước nhảy phải là bội số của 10.000 và nhỏ hơn 101.000");
                dem++;
            }
        }
        if (status.getHour().isEmpty()) {
            model.addAttribute("ErrHour", "Số giờ tồn tại trống");
            dem++;
        } else {
            double bien = Integer.parseInt(status.getHour());
            if (bien < 0 || bien % 2 != 0 || bien > 50) {
                model.addAttribute("ErrStep", "Số giờ tồn tại là số nguyên, lớn hơn 0 và nhỏ hơn 50");
                dem++;
            }
        }
        

        if (dem == 0) {
            try {
                Map r = this.Cloudinary.uploader().upload(status.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                status.setImage((String) r.get("secure_url"));

            } catch (IOException ex) {
                System.err.println("==ADD ANH==" + ex.getMessage());
            }
            if (this.statusService.add(status, a.getId()) == false) {
                model.addAttribute("errMsg", "Đăng bài không thành công");
                return "auction";

            } else {
                return "redirect:/auction";
            }
        }

        return "auction";
    }

}
