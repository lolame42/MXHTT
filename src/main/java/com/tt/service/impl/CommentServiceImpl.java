/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.tt.pojos.Comment;
import com.tt.reponsitory.CommentReponsitory;
import com.tt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVADO
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentReponsitory commentReponsitory;

    @Override
    public boolean add(Comment comment) {
        return commentReponsitory.add(comment);
    }
    
}
