package com.ManagerTourVietNam.model;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "USER")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUser", length = 4)
    private String iduser;
    @Column(name = "Name", length = 100)
    private String name;
    @Column(name = "Birth")
    private java.sql.Date birth;
    @Column(name = "Email", length = 200)
    private String email;
    @Column(name = "Phone", length = 14)
    private String phone;
    @Column(name = "Points")
    private int points;
    @Column(name = "Salary")
    private int salary;
    @Column(name = "Reward")
    private int reward;
    @Column(name = "Status")
    private int status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Type_User", referencedColumnName = "Type_User_ID")
    private Type_user typeUser;










    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
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
}
