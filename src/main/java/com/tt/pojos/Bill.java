/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author DAVADO
 */
@Entity
@Table(name = "bill")
public class Bill implements Serializable {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbill")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idsell")
    private Login loginsell;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idpay")
    private Login loginpay;
    @Column(name = "value")
    private int value;
    //1 chua thanh toan, 2 da thanh toan , 3 hoan thanh
    @Column(name = "type")
    private int type;
    @Column(name = "date")
    private Date date;
    @Column(name = "dateend")
    private Date dateend;
    @Column(name = "codemomo")
    private String codemomo;
   

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
     * @return the loginsell
     */
    public Login getLoginsell() {
        return loginsell;
    }

    /**
     * @param loginsell the loginsell to set
     */
    public void setLoginsell(Login loginsell) {
        this.loginsell = loginsell;
    }

    /**
     * @return the loginpay
     */
    public Login getLoginpay() {
        return loginpay;
    }

    /**
     * @param loginpay the loginpay to set
     */
    public void setLoginpay(Login loginpay) {
        this.loginpay = loginpay;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
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
     * @return the dateend
     */
    public Date getDateend() {
        return dateend;
    }

    /**
     * @param dateend the dateend to set
     */
    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    /**
     * @return the codemomo
     */
    public String getCodemomo() {
        return codemomo;
    }

    /**
     * @param codemomo the codemomo to set
     */
    public void setCodemomo(String codemomo) {
        this.codemomo = codemomo;
    }

    /**
     * @return the codemomo
     */
   


}
