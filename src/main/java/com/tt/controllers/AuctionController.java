/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.Auction;
import com.tt.pojos.Login;
import com.tt.pojos.Sell;
import com.tt.service.AuctionService;
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
    AuctionService auctionService;
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

        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));

        model.addAttribute("allauction", this.auctionService.getAuction(page));
        model.addAttribute("countauc", this.auctionService.getAuction().size());
        model.addAttribute("auction", new Auction());

        return "auction";
    }

    @PostMapping("/auction")
    public String addauc(Model model, @ModelAttribute(value = "auction") Auction auction, Principal principal, @RequestParam(required = false) Map<String, String> params) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);
        //
        int dem = 0;
        if (auction.getContent() == null || auction.getContent().isEmpty() || auction.getContent().length() > 4999) {
            model.addAttribute("errcontent", "Gi???i thi???u s???n ph???m kh??ng ???????c tr???ng v?? t???i ??a 5000 k?? t???");
            dem++;
        }
        if (auction.getFile().isEmpty() || auction.getFile() == null) {
            model.addAttribute("errimage", "H??nh ???nh s???n ph???m tr???ng");
            dem++;
        }
        if (auction.getStepstr().isEmpty() || auction.getStepstr() == null) {
            model.addAttribute("errstep", "B?????c tr???ng");
            dem++;
        } else {
            if (sellService.Laso(auction.getStepstr(), 0)) {
                if (Double.parseDouble(auction.getStepstr()) % 10 != 0 || Double.parseDouble(auction.getStepstr()) > 100 || Double.parseDouble(auction.getStepstr()) < 0) {
                    model.addAttribute("errstep", "B?????c c???n l?? s??? nguy??n, t???i ??a 1000 (????n v??? ng??n VND)");
                    dem++;
                }
            } else {
                dem++;
                model.addAttribute("errstep", "B?????c kh??ng ?????nh d???ng");
            }
        }

        if (dem == 0) {
            auction.setStep(Double.parseDouble(auction.getStepstr()));
            if (auctionService.addauc(auction, a) == true) {
                model.addAttribute("err", "Th??m th??nh c??ng");

            } else {
                model.addAttribute("err", "Th??m th???t b???i");
            }
        } else {
            model.addAttribute("err", "Th??m th???t b???i");
        }
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));

        model.addAttribute("allauction", this.auctionService.getAuction(page));
        model.addAttribute("countauc", this.auctionService.getAuction().size());
        model.addAttribute("auction", new Auction());

        return "auction";
    }

    @GetMapping("/auctionpart/{idauc}")
    public String auctionpart(Model model, @PathVariable(value = "idauc") String idauc, Principal principal) {
        Login a = userService.getUserByUserName(principal.getName()).get(0);

        model.addAttribute("newsell", new Sell());

        List<Auction> listauction = auctionService.getAuctionByIdAuction(Integer.parseInt(idauc));
        if (!listauction.isEmpty()) {
            Auction auction = listauction.get(0);
            model.addAttribute("auction", auction);
            List<Sell> allsell = sellService.getSellByIdAuction(Integer.parseInt(idauc));
            double top = 0;
            if (auction.getLogin().getId() == a.getId()||a.getUserrole().equals("ROLE_ADMIN")) {
                model.addAttribute("my", "a");
            } else {
                if ( reportService.check(auction.getLogin(), a, 2)) {
                    model.addAttribute("check", "B???n ???? b??? h???n ch??? t??? ch??? phi??n ?????u gi?? n??y");
                }
            }
            if (!allsell.isEmpty()) {
                Sell topSell = allsell.get(0);
                if (topSell.getValue() == auction.getStep() * 20) {

                    model.addAttribute("an", "S???n ph???m n??y ???? ???????c mua v???i gi?? cao nh???t");

                } else {
                    model.addAttribute("allsell", allsell);
                    top = topSell.getValue();
                    model.addAttribute("top", top);
                    if (topSell.getLoginsell().getId() == a.getId()) {
                        model.addAttribute("check", "B???n ??ang l?? ng?????i ra gi?? cao nh???t");
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

        model.addAttribute("newsell", new Sell());
        Auction auction = auctionService.getAuctionByIdAuction(Integer.parseInt(idauc)).get(0);
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
                model.addAttribute("an", "S???n ph???m n??y ???? ???????c mua v???i gi?? cao nh???t");
            } else {
                model.addAttribute("allsell", allsell);
                top = topSell.getValue();
                model.addAttribute("top", top);
                if (topSell.getLoginsell().getId() == a.getId()) {
                    model.addAttribute("check", "B???n ??ang l?? ng?????i ra gi?? cao nh???t");
                }
            }
        }
        
        if (sellService.Laso(newsell.getStep(), 0)) {
            if ((Integer.parseInt(newsell.getStep()) % auction.getStep() != 0)) {
                model.addAttribute("errValue", "Gi?? ti???n kh??ng h???p l???, ph???i chia h???t cho b?????c nh???y");
            } else {
                if (Integer.parseInt(newsell.getStep()) < top) {
                    model.addAttribute("errValue", "Gi?? ti???n nh??? h??n s??? ti???n ?????nh hi???n t???i");
                } else {
                    if (Integer.parseInt(newsell.getStep()) > auction.getStep() * 20) {
                        model.addAttribute("errValue", "Gi?? ti???n l???n h??n gi?? mua ngay");
                    } else {
                        double value = Integer.parseInt(newsell.getStep());
                        sellService.addsell(a, auction, value);
                        allsell = sellService.getSellByIdAuction(Integer.parseInt(idauc));
                        topSell = allsell.get(0);
                        model.addAttribute("allsell", allsell);
                        top = topSell.getValue();
                        model.addAttribute("top", top);
                        model.addAttribute("errValue", "?????u gi?? th??nh c??ng");
                    }
                }
            }
        } else {
            model.addAttribute("errValue", "Gi?? ti???n kh??ng ?????nh d???ng");
        }
        
        if (!allsell.isEmpty()) {
            if (topSell.getLoginsell().getId() == a.getId()) {
                model.addAttribute("check", "B???n ??ang l?? ng?????i ra gi?? cao nh???t");

            }
            if (topSell.getValue() == auction.getStep() * 20) {
                model.addAttribute("an", "S???n ph???m n??y ???? ???????c mua v???i gi?? cao nh???t");
            }
        }
        return "auctionpart";
    }
}
