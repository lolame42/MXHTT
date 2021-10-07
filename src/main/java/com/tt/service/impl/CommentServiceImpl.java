/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Comment;
import com.tt.pojos.Login;
import com.tt.pojos.Noti;
import com.tt.pojos.Status;
import com.tt.reponsitory.CommentReponsitory;
import com.tt.service.CommentService;
import com.tt.service.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentReponsitory commentReponsitory;
    @Autowired
    NotiService notiService;

    @Override
    public boolean add(Login login, Status status,Comment comment) {
        
        comment.setLogin(login);
        comment.setStatus(status);
        Noti addnoti = new Noti();
        addnoti.setAvatar(login.getImage());
        addnoti.setName(login.getFull_name());
        notiService.add(addnoti, status.getLogin(), status, 1);

        return commentReponsitory.add(comment);
    }

}
