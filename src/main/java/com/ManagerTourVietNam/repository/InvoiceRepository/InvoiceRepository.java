package com.ManagerTourVietNam.repository.InvoiceRepository;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
import com.ManagerTourVietNam.model.invoice.invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<invoice, String> {





        @Query("SELECT i FROM invoice i " +
                "WHERE (:idinvoice IS NULL OR i.idinvoice LIKE %:idinvoice%) " +
                "AND (:id_method IS NULL OR i.id_method.id LIKE %:id_method%) " +
                "AND (:idaccount IS NULL OR i.idaccount.id LIKE %:idaccount%)")
        List<invoice> searchInvoices(@Param("idinvoice") String idinvoice,
                                     @Param("id_method") String id_method,
                                     @Param("idaccount") String idaccount);

}
