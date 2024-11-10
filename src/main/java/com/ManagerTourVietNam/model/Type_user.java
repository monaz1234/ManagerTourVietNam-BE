package com.ManagerTourVietNam.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TYPE_USER")
public class Type_user {
    public Type_user() {}

    // Constructor với đúng tên thuộc tính
    @JsonCreator
    public Type_user(@JsonProperty("idtypeuser") String idtypeuser,
                     @JsonProperty("name_type") String name_type,
                     @JsonProperty("status") int status,
                     @JsonProperty("salary") int Salary) {
        this.idtypeuser = idtypeuser;
        this.name_type = name_type;
        this.status = status;
        this.Salary = Salary;  // Dùng 'Salary' thay vì 'salary'
    }

    @Id
    @Column(name = "Type_User_ID", length = 4)
    private String idtypeuser;

    @Column(name = "Name_Type", length = 60)
    private String name_type;

    @Column(name = "Status")
    private int status;

    @Column(name = "Salary")
    private int Salary;  // Sửa lại tên trường là 'Salary' với chữ 'S' in hoa



    // Getter và Setter cho Salary
    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
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
