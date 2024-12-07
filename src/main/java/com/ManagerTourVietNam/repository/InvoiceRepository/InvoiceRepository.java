package com.ManagerTourVietNam.repository.InvoiceRepository;

import com.ManagerTourVietNam.model.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    @Query("SELECT i.idinvoice FROM Invoice i")
    List<String> getAllInvoiceIds();
//        @Query("SELECT i FROM invoice i " +
//                "WHERE (:idinvoice IS NULL OR i.idinvoice LIKE %:idinvoice%) " +
//                "AND (:id_method IS NULL OR i.id_method.id LIKE %:id_method%) " +
//                "AND (:idaccount IS NULL OR i.idaccount.id LIKE %:idaccount%)")
//        List<invoice> searchInvoices(@Param("idinvoice") String idinvoice,
//                                     @Param("id_method") String id_method,
//                                     @Param("idaccount") String idaccount);
//        // Lấy tổng tiền theo ngày
//        @Query("SELECT SUM(i.total_amount) FROM invoice i WHERE i.payment_time = :date")
//        BigDecimal findTotalAmountByDate(@Param("date") LocalDate date);
//        // Lấy tổng tiền theo tháng (chỉ cần so sánh tháng và năm)
//        @Query("SELECT SUM(i.total_amount) FROM invoice i WHERE MONTH(i.payment_time) = :month AND YEAR(i.payment_time) = :year")
//        BigDecimal findTotalAmountByMonth(@Param("month") int month, @Param("year") int year);
//        // Lấy tổng tiền theo năm
//        @Query("SELECT SUM(i.total_amount) FROM invoice i WHERE YEAR(i.payment_time) = :year")
//        BigDecimal findTotalAmountByYear(@Param("year") int year);

}
