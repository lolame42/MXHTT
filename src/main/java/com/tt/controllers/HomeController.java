/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Auction;
import com.tt.pojos.Comment;
import com.tt.pojos.Login;
import com.tt.pojos.Sell;
import com.tt.pojos.Status;
import com.tt.service.BillService;
import com.tt.service.CommentService;
import com.tt.service.LoginService;
import com.tt.service.NotiService;
import com.tt.service.SellService;
import com.tt.service.StatusService;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private BillService billService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private NotiService notiService;
    @Autowired
    private SellService sellService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String login(Model model, Principal principal) {

        return "login";
    }

    @GetMapping("/setting")
    public String viewsetting(Model model, Principal principal) {

        return "setting";
    }

    @PostMapping("/setting")
    public String changesetting(Model model, @ModelAttribute(value = "user") Login user, Principal principal) {

        return "";

    }

    @RequestMapping("/wall/{user_name}")
    public String wall(Model model, @PathVariable(value = "user_name") String user_name, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        //
        Login loginwall;
        loginwall = userService.getUserById(Integer.parseInt(user_name));

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
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        //

        List<Status> status = statusService.getStatusByIdStatus(Integer.parseInt(idstt));
        if (!status.isEmpty()) {
            model.addAttribute("status", status.get(0));
            model.addAttribute("newcmt", new Comment());
            model.addAttribute("allcomment", commentService.getCmtByIdStatus(Integer.parseInt(idstt)));
            return "status";
        }
        return "error";

    }

    @PostMapping("/status/{idstt}")
    public String addcmt(Model model, @ModelAttribute(value = "newcmt") Comment newcmt, @PathVariable(value = "idstt") String idstt, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        //
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

                    } else {
                        model.addAttribute("errMsg", "Bình Luận thất bại");
                    }
                }
            }
        }

        return "status";

    }

    @GetMapping("/auctionpart/{idauc}")
    public String auctionpart(Model model, @PathVariable(value = "idauc") String idauc, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        //
        model.addAttribute("newsell", new Sell());

        List<Auction> listauction = statusService.getAuctionByIdAuction(Integer.parseInt(idauc));
        if (!listauction.isEmpty()) {
            Auction auction = listauction.get(0);
            model.addAttribute("auction", auction);
            List<Sell> allsell = sellService.getSellByIdAuction(Integer.parseInt(idauc));
            double top = 0;
            if (auction.getLogin().getId() == a.getId()) {
                model.addAttribute("my", "a");
            }
            if (!allsell.isEmpty()) {
                Sell topSell = allsell.get(0);
                if (topSell.getValue() == auction.getStep() * 20) {

                    model.addAttribute("an", "Sản phẩm này đã được mua với giá cao nhất");

                } else {
                    model.addAttribute("allsell", allsell);
                    top = topSell.getValue();
                    model.addAttribute("top", top);
                    if (topSell.getLoginsell().getId() == a.getId()) {
                        model.addAttribute("check", "Bạn đang là người ra giá cao nhất");
                    }
                }

            }

            return "auctionpart";
        } else {
            return "error";
        }

    }

    @PostMapping("/auctionpart/{idauc}")
    public String addsell(Model model, @ModelAttribute(value = "newsell") Sell newsell, @PathVariable(value = "idauc") String idauc, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
        //
        model.addAttribute("newsell", new Sell());
        Auction auction = statusService.getAuctionByIdAuction(Integer.parseInt(idauc)).get(0);
        model.addAttribute("auction", auction);
        List<Sell> allsell = sellService.getSellByIdAuction(Integer.parseInt(idauc));
        double top = 0;
        if (auction.getLogin().getId() == a.getId()) {
            model.addAttribute("my", "a");
        }
        Sell topSell = new Sell();
        if (!allsell.isEmpty()) {
            topSell = allsell.get(0);
            if (topSell.getValue() == auction.getStep() * 20) {

                model.addAttribute("an", "Sản phẩm này đã được mua với giá cao nhất");

            } else {
                model.addAttribute("allsell", allsell);
                top = topSell.getValue();
                model.addAttribute("top", top);
                if (topSell.getLoginsell().getId() == a.getId()) {
                    model.addAttribute("check", "Bạn đang là người ra giá cao nhất");
                }
            }

        }
        if (sellService.Laso(newsell.getStep())) {
            if ((Integer.parseInt(newsell.getStep()) % auction.getStep() != 0)) {
                model.addAttribute("errValue", "Giá tiền không hợp lệ, phải chia hết cho bước nhảy");
            } else {
                if (Integer.parseInt(newsell.getStep()) < top) {
                    model.addAttribute("errValue", "Giá tiền nhỏ hơn số tiền đỉnh hiện tại");
                } else {
                    if (Integer.parseInt(newsell.getStep()) > auction.getStep() * 20) {
                        model.addAttribute("errValue", "Giá tiền lớn hơn giá mua ngay");
                    } else {
                        double value = Integer.parseInt(newsell.getStep());
                        sellService.addsell(a, auction, value);
                        allsell = sellService.getSellByIdAuction(Integer.parseInt(idauc));
                        topSell = allsell.get(0);
                        model.addAttribute("allsell", allsell);
                        top = topSell.getValue();
                        model.addAttribute("top", top);
                        model.addAttribute("errValue", "Đấu giá thành công");

                    }

                }

            }
        } else {
            model.addAttribute("errValue", "Giá tiền không định dạng");
        }
        if (!allsell.isEmpty()) {
            if (topSell.getLoginsell().getId() == a.getId()) {
                model.addAttribute("check", "Bạn đang là người ra giá cao nhất");

            }
            if (topSell.getValue() == auction.getStep() * 20) {
                model.addAttribute("an", "Sản phẩm này đã được mua với giá cao nhất");

            }
        }

        return "auctionpart";

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

    @RequestMapping("/hello/{name}")
    public String hello(Model model,
            @PathVariable(value = "name") String name
    ) {
        model.addAttribute("message", "Anh " + name + "!!!");
        return "hello";
    }

    @RequestMapping(path = "/test")
    public String testRedirect(Model model
    ) {
        model.addAttribute("testMg", "Anh Iu Em");

        return "forward:/hello/Tu";
    }
}
