package com.tt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import com.tt.service.LoginService;
import com.tt.service.UserService;
import com.tt.service.impl.UserServiceImpl;
import com.tt.validator.LoginNameValidator;
import com.tt.validator.WebAppValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.RequestEntity.options;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            } else
            {
                if(userServiceImpl.getUserByUserName(login.getUser_name()).isEmpty())
                    model.addAttribute("errMsg","Tên tài khoản đã tồn tại");
                else
                     model.addAttribute("errMsg", "Đăng ký không thành công");
               
            }
        }
        return "register";
    }
}
