/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author DAVADO
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomment")
    private int id;
    @Column(name = "datecomment")
    private Date date;
    @Column(name = "contencomment")
    private String content;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idlogin")
    private Login logincmt;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idstatuscomment")
    private Status statuscomment;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the logincmt
     */
    public Login getLogincmt() {
        return logincmt;
    }

    /**
     * @param logincmt the logincmt to set
     */
    public void setLogincmt(Login logincmt) {
        this.logincmt = logincmt;
    }

    /**
     * @return the statuscomment
     */
    public Status getStatuscomment() {
        return statuscomment;
    }

    /**
     * @param statuscomment the statuscomment to set
     */
    public void setStatuscomment(Status statuscomment) {
        this.statuscomment = statuscomment;
    }

   
}
