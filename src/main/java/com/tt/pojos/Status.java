/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.pojos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DAVADO
 */
@Entity
@Table(name = "status")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatus")
    private int idStatus;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "iduser")
    private Login login;
    @Column(name = "content")
    private String content;
    @Column(name = "image")
    private String image;
    @Column(name = "date")
    private Date date;
    @Column(name = "hashtag")
    private String hashtag;
    @Transient
    private int check;
    @Transient
    private int countlike;
    @Transient
    private MultipartFile file;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "statuslike")
    private List<Ufeel> ufeel;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "statuscomment")
    private List<Comment> comment;

    /**
     * @return the idStatus
     */
    public int getIdStatus() {
        return idStatus;
    }

    /**
     * @param idStatus the idStatus to set
     */
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
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
     * @return the image
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

    /**
     * @return the hashtag
     */
    public String getHashtag() {
        return hashtag;
    }

    /**
     * @param hashtag the hashtag to set
     */
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    /**
     * @return the avatar
     */
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
     * @return the comment
     */
    /**
     * @return the ufeel
     */
    public List<Ufeel> getUfeel() {
        return ufeel;
    }

    /**
     * @param ufeel the ufeel to set
     */
    public void setUfeel(List<Ufeel> ufeel) {
        this.ufeel = ufeel;
    }

    /**
     * @return the statuscmt
     */
    public List<Comment> getComment() {
        return comment;
    }

    /**
     * @param statuscmt the statuscmt to set
     */
    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    /**
     * @return the check
     */
    public int getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(int check) {
        this.check = check;
    }

    /**
     * @return the countlike
     */
    public int getCountlike() {
        return countlike;
    }

    /**
     * @param countlike the countlike to set
     */
    public void setCountlike(int countlike) {
        this.countlike = countlike;
    }

    /**
     * @return the notistt
     */
}
