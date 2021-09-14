/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.tt.pojos.Login;
/**
 *
 * @author DAVADO
 */
@Component
public class SpringValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Login.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Login login= (Login) target;
        if (login.getEmail().contains("@gmail.com"))
            errors.rejectValue("email","login.email.Err" );
        if(login.getFile()==null)
            errors.rejectValue("file","login.file.Err" );
            
            
    }
    
    
}
