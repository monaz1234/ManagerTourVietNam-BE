package com.ManagerTourVietNam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_user")
public class Type_user {
    @Id
    // hàm tự tăng
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idtypeuser;
    private String name_type;
    private int status;
    private int Salary;
    private int power;

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





    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
