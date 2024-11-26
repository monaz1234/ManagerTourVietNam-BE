package com.ManagerTourVietNam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    private String promotion_code;
    private String code;
    private String name;
    private String description;
    private boolean status;


    public Promotion(){}


    public String getPromotion_code() {
        return promotion_code;
    }
    public void setPromotion_code(String promotion_code) {
        this.promotion_code = promotion_code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public Promotion(String promotion_code, String code, String name, String description, boolean status) {
        this.promotion_code = promotion_code;
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Promotion{" +
                "promotion_code='" + promotion_code + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
