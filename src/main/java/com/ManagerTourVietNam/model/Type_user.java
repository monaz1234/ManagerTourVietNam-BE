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
    private boolean status;
    private int power;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
