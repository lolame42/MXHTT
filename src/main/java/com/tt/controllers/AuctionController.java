/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Sell;
import com.tt.service.BillService;
import com.tt.service.NotiService;
import com.tt.service.ReportService;
import com.tt.service.SellService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DAVADO
 */
@Controller
public class AuctionController {

    @Autowired
    UserService userService;
    @Autowired
    StatusService statusService;
    @Autowired
    NotiService notiService;
    @Autowired
    SellService sellService;
    @Autowired
    ReportService reportService;

    @ModelAttribute
    public void ahihi(Model model, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        model.addAttribute("user", a);
        model.addAttribute("noti", notiService.getNotibyLogin(a));
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
            if (sellService.Laso(auction.getStepstr(), 0)) {
                if (Double.parseDouble(auction.getStepstr()) % 10 != 0 || Double.parseDouble(auction.getStepstr()) > 100 || Double.parseDouble(auction.getStepstr()) < 0) {
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

    @GetMapping("/auctionpart/{idauc}")
    public String auctionpart(Model model, @PathVariable(value = "idauc") String idauc, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);

        //
        model.addAttribute("newsell", new Sell());

        List<Auction> listauction = statusService.getAuctionByIdAuction(Integer.parseInt(idauc));
        if (!listauction.isEmpty()) {
            Auction auction = listauction.get(0);
            model.addAttribute("auction", auction);
            List<Sell> allsell = sellService.getSellByIdAuction(Integer.parseInt(idauc));
            double top = 0;
            if (auction.getLogin().getId() == a.getId()||a.getUserrole().equals("ROLE_ADMIN")) {
                model.addAttribute("my", "a");
            } else {
                if ( reportService.check(auction.getLogin(), a, 2)) {
                    model.addAttribute("check", "Bạn đã bị hạn chế từ chủ phiên đấu giá này");
                }
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
        if (sellService.Laso(newsell.getStep(), 0)) {
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

}
