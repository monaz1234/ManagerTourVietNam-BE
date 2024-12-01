package com.ManagerTourVietNam.model.Book;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.TourModel.Tour;
import jakarta.persistence.*;

@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "IDBook", length = 4)
    private String idbook;
    @Column(name = "Status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "IDAccount", referencedColumnName = "IDAccount")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "IDTour", referencedColumnName = "IDTour")
    private Tour tour;

    public String getIdbook() {
        return idbook;
    }

    public void setIdbook(String idbook) {
        this.idbook = idbook;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Book(){}
    public Book(String idbook, boolean status, Account account, Tour tour) {
        this.idbook = idbook;
        this.status = status;
        this.account = account;
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idbook='" + idbook + '\'' +
                ", status=" + status +
                ", account=" + account +
                ", tour=" + tour +
                '}';
    }
}
