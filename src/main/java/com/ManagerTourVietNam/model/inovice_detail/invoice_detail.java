package com.ManagerTourVietNam.model.inovice_detail;

import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.model.TourModel.Tour;
import com.ManagerTourVietNam.model.invoice.invoice;

import jakarta.persistence.*;

@Entity
@Table(name = "INVOICE_DETAIL")
public class invoice_detail {
    @Id
    @Column(name = "Id_InvoceDetail", length = 4)
    private String idvoicedetail;

    @ManyToOne
    @JoinColumn(name = "ID_Invoice" ,referencedColumnName = "ID_Invoice")
    private invoice idinvoice;

    @ManyToOne
    @JoinColumn(name = "IDBook", referencedColumnName = "IDBook")
    private Book idbook;

    @ManyToOne
    @JoinColumn(name = "idtour", referencedColumnName = "idtour")
    private Tour idtour;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Unit_price")
    private int unit_price;

    @Column(name = "Total_amount")
    private int total_amount;

    public String getIdvoicedetail() {
        return idvoicedetail;
    }

    public void setIdvoicedetail(String idvoicedetail) {
        this.idvoicedetail = idvoicedetail;
    }

    public invoice getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(invoice idinvoice) {
        this.idinvoice = idinvoice;
    }

    public Book getIdbook() {
        return idbook;
    }

    public void setIdbook(Book idbook) {
        this.idbook = idbook;
    }

    public Tour getIdtour() {
        return idtour;
    }

    public void setIdtour(Tour idtour) {
        this.idtour = idtour;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "invoice_detail{" +
                "idvoicedetail='" + idvoicedetail + '\'' +
                ", idinvoice=" + idinvoice +
                ", idbook=" + idbook +
                ", idtour=" + idtour +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                ", total_amount=" + total_amount +
                '}';
    }
}
