/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.reponsitory;

import com.tt.pojos.Comment;
import java.util.List;

/**
 *
 * @author DAVADO
 */
public interface CommentReponsitory {
    boolean addcmt(Comment cmt);
    List<Comment> getCmtByIdStatus(int i) ;
}
