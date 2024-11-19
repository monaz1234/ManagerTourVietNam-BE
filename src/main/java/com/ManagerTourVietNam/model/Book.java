package com.ManagerTourVietNam.model;
import jakarta.persistence.*;
@Entity
@Table(name = "book")
public class Book {
    @Id
    private String idbook;
    private String idaccount;
    private String idtour;
    private boolean status;


    public String getIdbook() {
        return idbook;
    }
    public void setIdbook(String idbook) {
        this.idbook = idbook;
    }

    public String getIdaccount() {
        return idaccount;
    }
    public void setIdaccount(String idaccount) {
        this.idaccount = idaccount;
    }
    public String getIdtour() {
        return idtour;
    }
    public void setIdtour(String idtour) {
        this.idtour = idtour;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

}
