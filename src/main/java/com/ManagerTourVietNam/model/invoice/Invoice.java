package com.ManagerTourVietNam.model.invoice;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.Book.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Invoice {
    @Id
    @Column(name = "ID_Invoice", length = 10)
    private String idinvoice;

    @Column(name = "Idaccount")
    private String idaccount;

    @Column(name = "Payment_Time")
    private LocalDate payment_time;

    @Column(name = "Total_amount")
    private int total_amount;

    @Column(name = "Status")
    private int status;

    @Column(name = "Payment_Name")
    private String payment_name;
    @Column(name = "Idbook")
    private String idbook;

    public String getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(String idinvoice) {
        this.idinvoice = idinvoice;
    }

    public String getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(String idaccount) {
        this.idaccount = idaccount;
    }

    public LocalDate getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(LocalDate payment_time) {
        this.payment_time = payment_time;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdbook() {
        return idbook;
    }

    public void setIdbook(String idbook) {
        this.idbook = idbook;
    }

    @JsonProperty("payment_name")  // Đảm bảo ánh xạ đúng từ JSON
    public String getPayment_name() {
        return payment_name;
    }

    @JsonProperty("payment_name")  // Đảm bảo ánh xạ đúng từ JSON
    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }
    @Override
    public String toString() {
        return "invoice{" +
                "idinvoice='" + idinvoice + '\'' +
                ", idaccount=" + idaccount +
                ", payment_time=" + payment_time +
                ", total_amount=" + total_amount +
                ", status=" + status +
                ", payment_name=" + payment_name +
                ", idbook =" + idbook +
                '}';
    }
}
