/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tu
 */
@Entity
@Table(name = "login")
public class Login implements Serializable {

    private static String ADMIN = "ROLE_ADMIN";
    private static String USER = "ROLE_USER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @Size(min = 1, max = 50, message = "{login.full_name.lenErr}")
    private String full_name;
    @Size(min = 6, max = 15, message = "{login.use_name.lenErr}")
    private String user_name;
    @Size(min = 6, message = "{login.use_password.lenErr}")
    private String user_password;
    @Size(min = 12, max = 50, message = "{login.email.lenErr}")
    private String email;
    @Size(min = 10, max = 11, message = "{login.phone.lenErr}")
    private String phone;
    @NotNull(message = "{login.file.lenErr}")
    @Transient
    private MultipartFile file;
    private String image;
    private String description;
    private String userrole;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "login")
    private List<Status> status;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "loginnoti")
    private List<Noti> notiuser;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "login")
    private List<Auction> auction;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "loginsell")
    private List<Bill> billsell;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "loginpay")
    private List<Bill> billpay;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "loginbidong")
    private List<Report> reportbidong;

    /**
     * @return the Id
     */
    public void setLogin(Login a) {
        this.setId(a.getId());
        this.setDescription(a.getDescription());
        this.setEmail(a.getEmail());
        this.setFull_name(a.getFull_name());
        this.setImage(a.getImage());
        this.setPhone(a.getPhone());
        this.setUser_name(a.getUser_name());
        this.setUser_password(a.getUser_password());
        this.setUserrole(a.getUserrole());

    }

    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the full_name
     */
    public String getFull_name() {
        return full_name;
    }

    /**
     * @param full_name the full_name to set
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return the user_password
     */
    public String getUser_password() {
        return user_password;
    }

    /**
     * @param user_password the user_password to set
     */
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the userrole
     */
    public String getUserrole() {
        return userrole;
    }

    /**
     * @param userrole the userrole to set
     */
    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public void setLogin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the status
     */
    public List<Status> getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(List<Status> status) {
        this.status = status;
    }

    /**
     * @return the notiuser
     */
    public List<Noti> getNotiuser() {
        return notiuser;
    }

    /**
     * @param notiuser the notiuser to set
     */
    public void setNotiuser(List<Noti> notiuser) {
        this.notiuser = notiuser;
    }

    /**
     * @return the auction
     */
    public List<Auction> getAuction() {
        return auction;
    }

    /**
     * @param auction the auction to set
     */
    public void setAuction(List<Auction> auction) {
        this.auction = auction;
    }

    /**
     * @return the billsell
     */
    public List<Bill> getBillsell() {
        return billsell;
    }

    /**
     * @param billsell the billsell to set
     */
    public void setBillsell(List<Bill> billsell) {
        this.billsell = billsell;
    }

    /**
     * @return the billpay
     */
    public List<Bill> getBillpay() {
        return billpay;
    }

    /**
     * @param billpay the billpay to set
     */
    public void setBillpay(List<Bill> billpay) {
        this.billpay = billpay;
    }

    /**
     * @return the report
     */
    public List<Report> getReportbidong() {
        return reportbidong;
    }

    /**
     * @param report the report to set
     */
    public void setReportbidong(List<Report> reportbidong) {
        this.reportbidong = reportbidong;
    }

    /**
     * @return the ADMIN
     */
    public static String getADMIN() {
        return ADMIN;
    }

    /**
     * @param aADMIN the ADMIN to set
     */
    public static void setADMIN(String aADMIN) {
        ADMIN = aADMIN;
    }

    /**
     * @return the USER
     */
    public static String getUSER() {
        return USER;
    }

    /**
     * @param aUSER the USER to set
     */
    public static void setUSER(String aUSER) {
        USER = aUSER;
    }

    /**
     * @return the report1
     */
 
  
}
