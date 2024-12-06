package com.ManagerTourVietNam.repository.InvoiceDetail;
import com.ManagerTourVietNam.model.inovice_detail.invoice_detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceDetailRepository extends JpaRepository<invoice_detail, String> {

    @Query("SELECT d FROM invoice_detail d " +
            "WHERE (:idvoicedetail IS NULL OR d.idvoicedetail LIKE %:idvoicedetail%) " +
            "AND (:idinvoice IS NULL OR d.idinvoice.idinvoice LIKE %:idinvoice%) " +
            "AND (:idbook IS NULL OR d.idbook.idbook LIKE %:idbook%) " +
            "AND (:idtour IS NULL OR d.idtour.idtour LIKE %:idtour%)")
    List<invoice_detail> searchInvoiceDetails(
            @Param("idvoicedetail") String idvoicedetail,
            @Param("idinvoice") String idinvoice,
            @Param("idbook") String idbook,
            @Param("idtour") String idtour
    );

    @Query("SELECT i FROM invoice_detail i WHERE i.idinvoice.idinvoice = :idinvoice")
    List<invoice_detail> findByIdinvoice_IdInvoice(@Param("idinvoice") String idinvoice);




}
