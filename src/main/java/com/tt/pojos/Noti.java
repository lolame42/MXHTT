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
@Table(name = "noti")
public class Noti implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnoti")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUser")
    private Login loginnoti;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idStt")
    private Status statusnoti;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAuction")
    private Auction auctionnoti;
    @Column(name = "Fullname")
    private String name;
    @Column(name = "Avatar")
    private String avatar;
    @Column(name = "Type")
    private int type;
    @Column(name = "date")
    private Date date;

    /**
     * @return the loginnoti
     */
    public Login getLoginnoti() {
        return loginnoti;
    }

    /**
     * @param loginnoti the loginnoti to set
     */
    public void setLoginnoti(Login loginnoti) {
        this.loginnoti = loginnoti;
    }

    /**
     * @return the statusnoti
     */
    public Status getStatusnoti() {
        return statusnoti;
    }

    /**
     * @param statusnoti the statusnoti to set
     */
    public void setStatusnoti(Status statusnoti) {
        this.statusnoti = statusnoti;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
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
     * @return the auction
     */
    public Auction getAuctionnoti() {
        return auctionnoti;
    }

    /**
     * @param auction the auction to set
     */
    public void setAuctionnoti(Auction auctionnoti) {
        this.auctionnoti = auctionnoti;
    }

}
