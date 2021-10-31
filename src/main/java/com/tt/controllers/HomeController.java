/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.service.NotiService;
import com.tt.service.StatusService;
import com.tt.service.UfeelService;
import com.tt.service.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private NotiService notiService;
    @Autowired
    private UfeelService ufeelService;

    @GetMapping("/")
    public String login(Model model, Principal principal) {

        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal, @RequestParam(required = false) Map<String, String> params) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        System.out.println( a.getUserrole());
        //
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        List<Status> allstatus = this.statusService.getStatus(page);
        for (int i = 0; i < allstatus.size(); i++) {
            if (ufeelService.check(allstatus.get(i), a.getId()) == true || a.getUserrole().trim().equals("ROLE_ADMIN")) {
                allstatus.get(i).setCheck(1);
            } else {
                allstatus.get(i).setCheck(0);
            }
        }
        for (int i = 0; i < allstatus.size(); i++) {
            if (allstatus.get(i).getLogin().getId() == a.getId()||a.getUserrole().trim().equals("ROLE_ADMIN")) {
                allstatus.get(i).setCountlike(allstatus.get(i).getUfeel().size());
                allstatus.get(i).setCountcmt(allstatus.get(i).getComment().size());
            }

        }
        model.addAttribute("allstatus", allstatus);

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
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
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
        List<Status> liststatus = this.statusService.getStatus(1);
        for (int i = 0; i < liststatus.size(); i++) {
            if (liststatus.get(i).getLogin().getId() == a.getId()) {
                liststatus.get(i).setCountlike(liststatus.get(i).getUfeel().size());
            }
        }
        model.addAttribute("allstatus", liststatus);
        model.addAttribute("countstt", this.statusService.getStatus().size());
        model.addAttribute("status", new Status());

        return "home";

    }

    @RequestMapping("/find")
    public String find(Model model,
            @RequestParam(value = "kw", required = false, defaultValue = "") String kw, Principal principal
    ) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));        //

        model.addAttribute("userfind", this.userService.getUsers(kw));
        return "finduser";
    }

}
