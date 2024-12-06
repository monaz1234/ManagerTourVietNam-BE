package com.ManagerTourVietNam.model;
import jakarta.persistence.*;
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @Column(name = "promotion_code")  // Đảm bảo rằng tên cột là đúng
    private String promotion_code;
    @Column(name = "code")
    private String code;
    @Column(name = "name")  // Đảm bảo rằng tên cột là đúng
    private String name;
    @Column(name = "description")  // Đảm bảo rằng tên cột là đúng
    private String description;
    @Column(name = "status")
    private boolean status;
    @Column(name = "discount")
    private double discount;
    public Promotion(){}
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
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

    public Promotion(String promotion_code, String code, String name, String description, boolean status, double discount) {
        this.promotion_code = promotion_code;
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
        this.discount = discount;
    }
    @Override
    public String toString() {
        return "Promotion{" +
                "promotion_code='" + promotion_code + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", discount='" + discount + '\'' +
                ", status=" + status +
                '}';
    }
}
