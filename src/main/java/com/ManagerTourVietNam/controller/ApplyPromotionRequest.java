package com.ManagerTourVietNam.controller;

public class ApplyPromotionRequest {
    private String idtour;
    private String promotion_code;
    private int quantity;
    private String code;

    public String getCode() { return code;}
    public void setCode(String code) {this.code=code;}
    public String getIdtour() { return idtour; }
    public void setIdtour(String idtour) {this.idtour = idtour;}
    public String getPromotion_code() {return promotion_code;}
    public void setPromotion_code(String promotion_code) {this.promotion_code = promotion_code;}
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {this.quantity = quantity;}

}
