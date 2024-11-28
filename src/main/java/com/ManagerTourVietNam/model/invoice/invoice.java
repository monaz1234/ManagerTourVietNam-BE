//package com.ManagerTourVietNam.model.invoice;
//
//import com.ManagerTourVietNam.model.Account;
//import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
//import jakarta.persistence.Column;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//import java.sql.Date;
//
//public class invoice {
//    @Id
//    @Column(name = "ID_Invoice", length = 4)
//    private String idinvoice;
//
//
//    @ManyToOne
////    @Column(name = "Account", length = 4)
//    @JoinColumn(name = "Account", referencedColumnName = "Account")
//    private Account idaccount;
//
//    @Column(name = "Payment_Time")
//    private java.sql.Date payment_time;
//
//    @Column(name = "Total_amount")
//    private int total_amount;
//
//    @Column(name = "Status")
//    private boolean status;
//
//    @ManyToOne
//    @JoinColumn(name = "ID_Method", referencedColumnName = "ID_Method")
//    private PaymentMethod idPaymentMethod;
//
//    public String getIdinvoice() {
//        return idinvoice;
//    }
//
//    public void setIdinvoice(String idinvoice) {
//        this.idinvoice = idinvoice;
//    }
//
//    public Account getIdaccount() {
//        return idaccount;
//    }
//
//    public void setIdaccount(Account idaccount) {
//        this.idaccount = idaccount;
//    }
//
//    public Date getPayment_time() {
//        return payment_time;
//    }
//
//    public void setPayment_time(Date payment_time) {
//        this.payment_time = payment_time;
//    }
//
//    public int getTotal_amount() {
//        return total_amount;
//    }
//
//    public void setTotal_amount(int total_amount) {
//        this.total_amount = total_amount;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public PaymentMethod getIdPaymentMethod() {
//        return idPaymentMethod;
//    }
//
//    public void setIdPaymentMethod(PaymentMethod idPaymentMethod) {
//        this.idPaymentMethod = idPaymentMethod;
//    }
//
//    @Override
//    public String toString() {
//        return "invoice{" +
//                "idinvoice='" + idinvoice + '\'' +
//                ", idaccount=" + idaccount +
//                ", payment_time=" + payment_time +
//                ", total_amount=" + total_amount +
//                ", status=" + status +
//                ", idPaymentMethod=" + idPaymentMethod +
//                '}';
//    }
//}
