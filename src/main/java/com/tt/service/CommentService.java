/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service;

import com.tt.pojos.Comment;
import com.tt.pojos.Login;
import com.tt.pojos.Status;
import java.util.List;

/**
 *
 * @author DAVADO
 */
public interface CommentService {
    boolean addcmt(Login login,Status status, Comment comment);
    
    List<Comment> getCmtByIdStatus(int i) ;
    
}
