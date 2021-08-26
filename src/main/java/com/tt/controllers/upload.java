package com.tt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tt.pojos.Login;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.RequestEntity.options;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tu
 */
@Controller
public class upload {

    @Autowired
    private Cloudinary Cloudinary;
    
    @GetMapping("/admin/uploads")
    public String list(Model model){
    model.addAttribute("upload", new Login());
    
    return "upload";
    }
    @PostMapping("/admin/uploads")
    public String add(@ModelAttribute(value = "upload")Login login){
        try {
            this.Cloudinary.uploader().upload(login.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            
            return "redirect:/";
        } catch (IOException ex) {
            System.err.println("==ADD ANH==" + ex.getMessage());
        }
        
        return "upload";
    }
}
