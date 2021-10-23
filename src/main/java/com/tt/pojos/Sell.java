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
import javax.persistence.Transient;

/**
 *
 * @author DAVADO
 */
@Entity
@Table(name = "sell")
public class Sell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "iduser")
    private Login loginsell;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAuction")
    private Auction auctionsell;
    @Column(name = "value")
    private double value;
    @Column(name = "date")
    private Date date;
    @Transient
    private String step;

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
     * @return the auctionsell
     */
    public Auction getAuctionsell() {
        return auctionsell;
    }

    /**
     * @param auctionsell the auctionsell to set
     */
    public void setAuctionsell(Auction auctionsell) {
        this.auctionsell = auctionsell;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
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
     * @return the step
     */
    public String getStep() {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(String step) {
        this.step = step;
    }

}
