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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DAVADO
 */
@Entity
@Table(name = "auction")
public class Auction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauction")
    private int idauction;
    @Column(name = "image")
    private String image;
    @Column(name = "date")
    private Date date;
    @Column(name = "dateend")
    private Date dateend;
    @Column(name = "step")
    private double step;
    @Transient
    private MultipartFile file;
    @Column(name = "content")
    private String content;
    @Transient
    private String stepstr;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idlogin")
    private Login login;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "auctionsell")
    private List<Sell> listsell;

    /**
     * @return the id
     */
  
    public int getId() {
        return getIdaution();
    }
    

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.setIdaution(id);
    }

    /**
     * @return the loginAuction
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
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
    public double getStep() {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(double step) {
        this.step = step;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

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
     * @return the stepstr
     */
    public String getStepstr() {
        return stepstr;
    }

    /**
     * @param stepstr the stepstr to set
     */
    public void setStepstr(String stepstr) {
        this.stepstr = stepstr;
    }

    /**
     * @return the idaution
     */
    public int getIdaution() {
        return idauction;
    }

    /**
     * @param idaution the idaution to set
     */
    public void setIdaution(int idaution) {
        this.idauction = idaution;
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * @return the listsell
     */
    public List<Sell> getListsell() {
        return listsell;
    }

    /**
     * @param listsell the listsell to set
     */
    public void setListsell(List<Sell> listsell) {
        this.listsell = listsell;
    }

    /**
     * @return the loginAuction
     */
}
