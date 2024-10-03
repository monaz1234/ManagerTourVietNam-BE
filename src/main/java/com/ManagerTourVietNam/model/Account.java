package com.ManagerTourVietNam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account" )
public class Account {
    @Id
    // hàm tự tăng
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idaccount;
    private String username;
    private String password;
    private String idtypeuser;
    private Boolean status;
    private String iduser;
    private String image;


    public String getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(String idaccount) {
        this.idaccount = idaccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdtypeuser() {
        return idtypeuser;
    }

    public void setIdtypeuser(String idtypeuser) {
        this.idtypeuser = idtypeuser;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
