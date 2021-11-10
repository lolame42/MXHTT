/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Comment;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.service.BillService;
import com.tt.service.CommentService;
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
import com.tt.service.ReportService;

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
    @Autowired
    UfeelService ufeelService;
    @Autowired
    BillService billService;
    @Autowired
    BillRepository billReponsitory;
    @Autowired
    CommentService commentService;
    @Autowired
    ReportService reportService;

    @ModelAttribute
    public void ahihi(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
    }

    @GetMapping("/status/{idstt}")
    public String status(Model model, @PathVariable(value = "idstt") String idstt, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);

        List<Status> status = statusService.getStatusByIdStatus(Integer.parseInt(idstt));
        if (!status.isEmpty()) {
            if (a.getId() == status.get(0).getLogin().getId() || a.getUserrole().trim().equals("ROLE_ADMIN")) {
                if (status.get(0).getComment().size() > 0) {
                    model.addAttribute("countcmt", status.get(0).getComment().size());
                }
                if (status.get(0).getUfeel().size() > 0) {
                    model.addAttribute("countlike", status.get(0).getUfeel().size());
                }
                status.get(0).setCheck(1);
            } else {
                if (ufeelService.check(status.get(0), a.getId()) == true) {
                    status.get(0).setCheck(1);
                } else {
                    status.get(0).setCheck(2);
                }
            }
            if(reportService.check(status.get(0).getLogin(), a, 1))
                model.addAttribute("report","Bạn bị hạn chế bình luận từ chủ bài đăng");            
            model.addAttribute("status", status.get(0));
            model.addAttribute("newcmt", new Comment());

            model.addAttribute("allcomment", commentService.getCmtByIdStatus(Integer.parseInt(idstt)));
            return "status";
        }
        return "error";
    }

    @PostMapping("/status/{idstt}")
    public String addcmt(Model model,
            @ModelAttribute(value = "newcmt") Comment newcmt,
            @PathVariable(value = "idstt") String idstt, Principal principal
    ) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);

        List<Status> status = statusService.getStatusByIdStatus(Integer.parseInt(idstt));
        if (!status.isEmpty()) {
            model.addAttribute("status", status.get(0));
            model.addAttribute("allcomment", commentService.getCmtByIdStatus(Integer.parseInt(idstt)));
            model.addAttribute("newcmt", new Comment());
            if (newcmt.getContent().length() > 4999) {
                model.addAttribute("errMsg", "Bạn bình luận quá ư là dài !");
            } else {
                if (newcmt.getContent().length() == 0) {
                    model.addAttribute("errMsg", "Bạn chưa bình luận !");
                } else {
                    if (commentService.addcmt(a, status.get(0), newcmt)) {

                        model.addAttribute("errMsg", "Bình Luận thành công");
                        model.addAttribute("allcomment", commentService.getCmtByIdStatus(Integer.parseInt(idstt)));
                        status = statusService.getStatusByIdStatus(Integer.parseInt(idstt));
                        if (a.getId() == status.get(0).getLogin().getId() || a.getUserrole().equals("ROLE_ADMIN")) {
                            model.addAttribute("countcmt", status.get(0).getComment().size());
                            if (status.get(0).getUfeel().size() > 0) {
                                model.addAttribute("countlike", status.get(0).getUfeel().size());
                            }
                            status.get(0).setCheck(1);
                        } else {
                            if (ufeelService.check(status.get(0), a.getId()) == true) {
                                status.get(0).setCheck(1);
                            } else {
                                status.get(0).setCheck(0);
                            }
                            status = statusService.getStatusByIdStatus(Integer.parseInt(idstt));
                            model.addAttribute("status", status.get(0));
                        }

                    } else {
                        model.addAttribute("errMsg", "Bình Luận thất bại");
                    }
                }
            }
        }
        return "status";
    }

    @GetMapping("/setting/status/{idstatus}")
    public String viewsettingstatus(Model model, Principal principal,
            @PathVariable(value = "idstatus") String idstatus
    ) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);

        List<Status> liststatus = statusService.getStatusByIdStatus(Integer.parseInt(idstatus));
        if (!liststatus.isEmpty()) {
            Status status = liststatus.get(0);
            if (status.getLogin().getId() == a.getId()) {
                model.addAttribute("status", status);
                model.addAttribute("newstatus", new Status());
                return "settingstatus";
            }
        }
        return "error";
    }

    @PostMapping("/setting/status/{idstatus}")
    public String settingstatus(Model model,
            @ModelAttribute(value = "newstatus") Status newstatus, Principal principal,
            @PathVariable(value = "idstatus") String idstatus
    ) {

        Status status = statusService.getStatusByIdStatus(Integer.parseInt(idstatus)).get(0);
        int dem = 0;
        if (newstatus.getContent().length() > 4999) {
            dem++;
            model.addAttribute("errcontent", "nội dung không hợp lệ");
        }
        else
        {
            if(newstatus.getContent().length() == 0)
                newstatus.setContent("trong");
        }
        if (newstatus.getHashtag().length() > 20) {
            dem++;
            model.addAttribute("errhashtag", "hashtag độ dài tối đa 20 kí tự");
        }
        else
        {
            if(newstatus.getHashtag().length() == 0)
                newstatus.setHashtag("trong");
        }
        if (dem == 0) {
            if (!statusService.update(status.getIdStatus(), newstatus.getContent(),newstatus.getHashtag())||( newstatus.getContent().equals("trong")&& newstatus.getHashtag().equals("trong"))) {
                model.addAttribute("err", "Sửa không thành công");
            } else {
                model.addAttribute("err", "Sửa thành công");
            }
        } else {
            model.addAttribute("err", "Sửa không thành công");
        }
        Status status1 = statusService.getStatusByIdStatus(Integer.parseInt(idstatus)).get(0);
        model.addAttribute("status", status1);
        model.addAttribute("newstatus", new Status());
        return "settingstatus";
    }

    @RequestMapping("/delete/status/{idstatus}")
    public String deletestatus(Model model, Principal principal,
            @PathVariable(value = "idstatus") String idstatus
    ) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);

        List<Status> liststatus = statusService.getStatusByIdStatus(Integer.parseInt(idstatus));
        if (!liststatus.isEmpty()) {
            Status status = liststatus.get(0);
            if (status.getLogin().getId() == a.getId() || a.getUserrole().equals("ROLE_ADMIN")) {
                statusService.deletestt(status.getIdStatus());
                model.addAttribute("status", status);
                model.addAttribute("newstatus", new Status());

                List<Status> allstatus = this.statusService.getStatus(1);
                for (int i = 0; i < allstatus.size(); i++) {
                    if (ufeelService.check(allstatus.get(i), a.getId()) == true) {
                        allstatus.get(i).setCheck(1);
                    } else {
                        allstatus.get(i).setCheck(0);
                    }
                }

                model.addAttribute("allstatus", allstatus);
                model.addAttribute("countstt", this.statusService.getStatus().size());
                model.addAttribute("status", new Status());

                return "home";
            }
        }
        return "error";
    }
}
