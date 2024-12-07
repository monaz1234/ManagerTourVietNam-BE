package com.ManagerTourVietNam.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idaccount")
@Table(name = "ACCOUNT")
public class Account {
    @Id
    // hàm tự tăng
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAccount", length = 4)
    private String idaccount;
    @Column(name = "Username", length = 100)
    private String username;
    @Column(name = "Password", length = 100)
    private String password;

    // private String idtypeuser;
    @Column(name = "Status")
    private int status;

    // private String iduser;

    @Column(name = "Image", length = 300)
    private String image;

    @ManyToOne
    @JoinColumn(name = "idTypeUser", referencedColumnName = "Type_User_ID")
    private Type_user typeUser;

    @ManyToOne
    @JoinColumn(name = "IDUser", referencedColumnName = "IDUser")
    private User user;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Type_user getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(Type_user typeUser) {
        this.typeUser = typeUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account() {
    }

    public Account(String idaccount, String username, String password, int status, String image, Type_user typeUser,
            User user) {
        this.idaccount = idaccount;
        this.username = username;
        this.password = password;
        this.status = status;
        this.image = image;
        this.typeUser = typeUser;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idaccount='" + idaccount + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", typeUser=" + typeUser +
                ", user=" + user +
                '}';
    }

}
