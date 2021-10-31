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
import com.tt.service.CommentService;
import com.tt.service.NotiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.reponsitory.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentReponsitory;
    @Autowired
    NotiService notiService;

    @Override
    public boolean addcmt(Login login, Status status, Comment comment) {

        comment.setLogincmt(login);
        comment.setStatuscomment(status);
        if (login.getId() != status.getLogin().getId()) {
            Noti noti = new Noti();
            noti.setAvatar(login.getImage());
            noti.setName(login.getFull_name());
            notiService.add(noti, status.getLogin(), status, 1);
        }
        return commentReponsitory.addcmt(comment);
    }

    @Override
    public List<Comment> getCmtByIdStatus(int i) {
        return commentReponsitory.getCmtByIdStatus(i);
    }

}
