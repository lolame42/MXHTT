/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.pojos;

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
@Table(name = "check")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcheck")
    private int idcheck;
    @Column(name = "code")
    private long code;
    @Transient
    private String codestr;
    @Column(name = "value")
    private int value;
    @Column(name = "date")
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idbillcheck")
    private Bill billcheck;

    /**
     * @return the id
     */
    public int getIdcheck() {
        return idcheck;
    }

    /**
     * @param id the id to set
     */
    public void setIdcheck(int id) {
        this.idcheck = id;
    }

    /**
     * @return the code
     */
    public long getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(long code) {
        this.code = code;
    }

    /**
     * @return the codestr
     */
    public String getCodestr() {
        return codestr;
    }

    /**
     * @param codestr the codestr to set
     */
    public void setCodestr(String codestr) {
        this.codestr = codestr;
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
     * @return the billcheck
     */
    public Bill getBillcheck() {
        return billcheck;
    }

    /**
     * @param billcheck the billcheck to set
     */
    public void setBillcheck(Bill billcheck) {
        this.billcheck = billcheck;
    }

    /**
     * @return the billcheck
     */
    
}
