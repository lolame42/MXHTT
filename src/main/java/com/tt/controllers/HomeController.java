/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.tt.pojos.Comment;
import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;
import com.tt.reponsitory.UserReponsitory;
import com.tt.service.CommentService;
import com.tt.service.LoginService;
import com.tt.service.NotiService;
import com.tt.service.StatusService;
import com.tt.service.UserService;
import java.security.Principal;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private NotiService notiService;

    @GetMapping("/")
    public String login(Model model, Principal principal) {

        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //

        model.addAttribute("status", new Status());

        List<Status> allstatus = statusService.getStatus();
        Collections.reverse(allstatus);
        model.addAttribute("allstatus", allstatus);

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
        model.addAttribute("noti", a.getNotiuser());
        //
        model.addAttribute("status", new Status());

        model.addAttribute("allstatus", statusService.getStatus());

        if (!status.getContent().isEmpty()) {
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

    @GetMapping("/setting")
    public String viewsetting(Model model, Principal principal) {

        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //
        return "setting";
    }

    @PostMapping("/setting")
    public String changesetting(Model model, @ModelAttribute(value = "user") Login user, Principal principal) {

        if (this.loginService.Update(user)) {
            model.addAttribute("thongbao", "thay đổi thành công");
            return "setting";
        } else {
            model.addAttribute("thongbao", "thay đổi thất bại");
            return "redirect:/setting";
        }

    }

    @RequestMapping("/wall/{user_name}")
    public String wall(Model model, @PathVariable(value = "user_name") String user_name, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //

        Login loginwall;
        loginwall = userService.getUserById(Integer.parseInt(user_name)).get(0);

        if (!loginwall.getUser_name().isEmpty()) {
            model.addAttribute("userwall", loginwall);
            model.addAttribute("statuswall", loginwall.getStatus());
            return "wall";
        } else {
            return "home";
        }

    }

    @GetMapping("/status/{idstt}")
    public String status(Model model, @PathVariable(value = "idstt") String idstt, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //

        model.addAttribute("newcmt", new Comment());
        Status status = statusService.getStatusByIdStatus(Integer.parseInt(idstt)).get(0);
        if (!status.getDate().toString().isEmpty()) {
            model.addAttribute("status", status);
            List<Comment> allcomment =status.getComment();
            Collections.reverse(allcomment);
            model.addAttribute("allcomment",allcomment );
        } else {
            model.addAttribute("zore", "aaaa");
        }

        return "status";

    }

    @PostMapping("/status/{idstt}")
    public String addcmt(Model model, @ModelAttribute(value = "newcmt") Comment newcmt, @PathVariable(value = "idstt") String idstt, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //

        model.addAttribute("user", userService.getUserByUserName(principal.getName()).get(0));
        Status status = statusService.getStatusByIdStatus(Integer.parseInt(idstt)).get(0);
        if (!status.getDate().toString().isEmpty()) {
            model.addAttribute("status", status);
             List<Comment> allcomment =status.getComment();
            Collections.reverse(allcomment);
            model.addAttribute("allcomment",allcomment );
            model.addAttribute("idstt", idstt);
        } else {

            return "home";
        }
        if (!newcmt.getContent().isEmpty()) {
            newcmt.setLogin(a);
            newcmt.setStatus(status);

            if (this.commentService.add(newcmt) == false) {
                model.addAttribute("errMsg", "Bình luận không thành công");
            } else {
                Noti addnoti = new Noti();
                addnoti.setAvatar(a.getImage());
                addnoti.setName(a.getFull_name());
                notiService.add(addnoti, a, status, 1);
                model.addAttribute("errMsg", "Bình luận thành công");
            }

        } else {
            model.addAttribute("errMsg", "Bình luận không được trống");
        }

        return "status";

    }

    @RequestMapping("/find")
    public String find(Model model, @RequestParam(value = "kw", required = false, defaultValue = "") String kw, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        List<Noti> noti = a.getNotiuser();
        Collections.reverse(noti);
        model.addAttribute("noti", noti);
        //

        model.addAttribute("userfind", this.userService.getUsers(kw));
        return "finduser";
    }

    @RequestMapping("/hello/{name}")
    public String hello(Model model, @PathVariable(value = "name") String name) {
        model.addAttribute("message", "Anh " + name + "!!!");
        return "hello";
    }

    @RequestMapping(path = "/test")
    public String testRedirect(Model model) {
        model.addAttribute("testMg", "Anh Iu Em");

        return "forward:/hello/Tu";
    }
}
