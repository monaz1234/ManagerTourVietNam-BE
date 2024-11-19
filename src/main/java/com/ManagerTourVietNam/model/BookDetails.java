package com.ManagerTourVietNam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book_detail")
public class BookDetails {
    @Id
    private String idbook;
    private String promotion_code;
    private String time_book;
    private String quantity;
    private String participant;

    public String getIdBook() {
        return idbook;
    }
    public void setIdBook(String idbook) {
        this.idbook = idbook;
    }

    public String getPromotion_code() {
        return promotion_code;
    }
    public void setPromotion_code(String promotion_code) {
        this.promotion_code = promotion_code;
    }
    public String getTime_book() {
        return time_book;
    }
    public void setTime_book(String time_book) {
        this.time_book = time_book;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getParticipant() {
        return participant;
    }
    public void setParticipant(String participant) {
        this.participant = participant;
    }

}
