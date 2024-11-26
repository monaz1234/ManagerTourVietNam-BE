package com.ManagerTourVietNam.model.BookDetail;

import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.model.Promotion;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "BOOKDETAIL")
public class BookDetail {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBookDetail", length = 4)
    private String idbookdetail;

    @ManyToOne
    @JoinColumn(name = "IDBook", referencedColumnName = "IDBook")
    private Book idbook;

    @ManyToOne
    @JoinColumn(name = "Promotion_code", referencedColumnName = "promotion_code")
    private Promotion promotion_code;

    @Column(name = "Time_Book")
    private java.sql.Date time_book;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Participant")
    private String participant;

    public String getIdbookdetail() {
        return idbookdetail;
    }

    public void setIdbookdetail(String idbookdetail) {
        this.idbookdetail = idbookdetail;
    }

    public Book getIdbook() {
        return idbook;
    }

    public void setIdbook(Book idbook) {
        this.idbook = idbook;
    }

    public Promotion getPromotion_code() {
        return promotion_code;
    }

    public void setPromotion_code(Promotion promotion_code) {
        this.promotion_code = promotion_code;
    }

    public Date getTime_book() {
        return time_book;
    }

    public void setTime_book(Date time_book) {
        this.time_book = time_book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public BookDetail(){}
    public BookDetail(String idbookdetail, Book idbook, Promotion promotion_code, Date time_book, int quantity, String participant) {
        this.idbookdetail = idbookdetail;
        this.idbook = idbook;
        this.promotion_code = promotion_code;
        this.time_book = time_book;
        this.quantity = quantity;
        this.participant = participant;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "idbookdetail='" + idbookdetail + '\'' +
                ", idbook=" + idbook +
                ", promotion_code=" + promotion_code +
                ", time_book=" + time_book +
                ", quantity=" + quantity +
                ", participant='" + participant + '\'' +
                '}';
    }
}
