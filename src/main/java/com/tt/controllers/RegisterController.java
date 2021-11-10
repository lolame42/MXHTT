package com.tt.controllers;

import com.tt.pojos.Login;
import com.tt.service.LoginService;
import com.tt.service.impl.UserServiceImpl;
import com.tt.validator.WebAppValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tu
 */
@Controller
public class RegisterController {

    @Autowired
    private WebAppValidator loginValidator;
    @Autowired
    private LoginService userDetailsService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @GetMapping("register")
    public String list(Model model) {
        model.addAttribute("login", new Login());
        return "register";
    }

    @PostMapping("register")
    public String add(Model model, @ModelAttribute(value = "login") @Valid Login login, BindingResult result) {
        if (result.hasErrors()) {
        } else {
            if (this.userDetailsService.addOrUpdate(login) == true) {
                return "redirect:/";
            } else {
                if (!userServiceImpl.getUserByUserName(login.getUser_name()).isEmpty()) {
                    model.addAttribute("erruser", "Tên tài khoản đã tồn tại");
                }
                if (!userServiceImpl.getUserByPhone(login.getPhone()).isEmpty()) {
                    model.addAttribute("errphone", "Số điện thoại đã được sử dụng");
                }
                if (!userServiceImpl.getUserByEmail(login.getEmail()).isEmpty()) {
                    model.addAttribute("erremail", "Địa chỉ email đã được sử dụng");
                }
            }
        }
        return "register";
    }
}
