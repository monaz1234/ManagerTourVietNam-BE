//package com.ManagerTourVietNam.model.inovice_detail;
//
//import com.ManagerTourVietNam.model.Book.Book;
//import com.ManagerTourVietNam.model.invoice.invoice;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "INVOICE_DETAIL")
//public class invoice_detail {
//    @Id
//    @Column(name = "Id_InvoceDetail")
//    private String idvoicedetail;
//
//    @ManyToOne
//    @JoinColumn(name = "ID_Invoice" ,referencedColumnName = "ID_Invoice")
//    private invoice idinvoice;
//
//    @ManyToOne
//    @JoinColumn(name = "ID_Book", referencedColumnName = "ID_Book")
//    private Book idbook;
//
////    @ManyToOne
////    @JoinColumn(name = " ")
//
//
//
//}
