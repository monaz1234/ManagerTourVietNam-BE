package com.ManagerTourVietNam.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TYPE_USER")
public class Type_user {
    @Id
    // hàm tự tăng
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Type_User_ID", length = 4)
    private String idtypeuser;
    @Column(name = "Name_Type", length = 60)
    private String name_type;
    @Column(name = "Status")
    private int status;
    @Column(name = "Salary")
    private int Salary;

    @OneToMany(mappedBy = "typeUser")
    private List<User> users;

    @OneToMany(mappedBy = "typeUser")
    private List<Account> accounts;


    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getIdtypeuser() {
        return idtypeuser;
    }

    public void setIdtypeuser(String idtypeuser) {
        this.idtypeuser = idtypeuser;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }
}
