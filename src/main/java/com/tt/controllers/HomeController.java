/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.controllers;

import com.tt.pojos.User;
import com.tt.reponsitory.UserReponsitory;
import com.tt.service.UserService;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("logins", this.userService.getUsers());      
        return "login";
    }
     @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("logins", this.userService.getUsers());      
        return "home";
    }
    
    @RequestMapping("/find")
    public String find(Model model,@RequestParam(value="kw",required=false,defaultValue = "")String kw){
         model.addAttribute("user", this.userService.getUsers(kw));
        return "finduser";
    }
    
    
    @RequestMapping("/hello/{name}")
    public String hello(Model model, @PathVariable(value = "name")String name){
        model.addAttribute("message", "Anh " + name + "!!!");
        return "hello";
    }
    @RequestMapping(path = "/hello-post", method = RequestMethod.POST)
    public String show(Model model, @ModelAttribute(value = "user")User user){
        model.addAttribute("fullName", user.getFristName()+" "+user.getLastName());
        return "index";
    }
    @RequestMapping(path = "/test")
    public String testRedirect(Model model){
        model.addAttribute("testMg", "Anh Iu Em"); 
        
        return "forward:/hello/Tu";
    }
}
