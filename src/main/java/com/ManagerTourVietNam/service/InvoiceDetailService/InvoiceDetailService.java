package com.ManagerTourVietNam.service.InvoiceDetailService;
import com.ManagerTourVietNam.model.invoice.invoice;
import com.ManagerTourVietNam.repository.InvoiceDetail.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ManagerTourVietNam.model.inovice_detail.invoice_detail;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class InvoiceDetailService {
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public List<invoice_detail> getAllInvoiceDetail(){
        return invoiceDetailRepository.findAll();
    }

    public invoice_detail addInvoiceDetail(invoice_detail invoice_detailSub) {
        return invoiceDetailRepository.save(invoice_detailSub);
    }

    public invoice_detail updateInvoiceDetail(String id, invoice_detail invoiceDetails) throws UserPrincipalNotFoundException {
        return invoiceDetailRepository.findById(id).map(invoiceDetailSub -> {
            invoiceDetailSub.setIdvoicedetail(invoiceDetails.getIdvoicedetail());
            invoiceDetailSub.setQuantity(invoiceDetails.getQuantity());
            invoiceDetailSub.setTotal_amount(invoiceDetails.getTotal_amount());
            invoiceDetailSub.setUnit_price(invoiceDetails.getUnit_price());
            // Cập nhật Idaccount
            invoiceDetailSub.setIdinvoice(invoiceDetails.getIdinvoice() != null ? invoiceDetails.getIdinvoice() : null);

            invoiceDetailSub.setIdtour(invoiceDetails.getIdinvoice() != null ? invoiceDetails.getIdtour() : null);
            // Cập nhật IdPaymentMethod
            invoiceDetailSub.setIdbook(invoiceDetails.getIdinvoice() != null ? invoiceDetails.getIdbook() : null);

            return invoiceDetailRepository.save(invoiceDetailSub);
        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
    }

    public void deleteInvoiceDetail(String id) {
        invoiceDetailRepository.deleteById(id);
    }

    public Optional<invoice_detail> findInvoiceDetailById(String id) {
        return invoiceDetailRepository.findById(id);
    }

    public Map<String, Object> getInovicesDetailWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<invoice_detail> pageInovicesDetail = invoiceDetailRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("invoiceDetails", pageInovicesDetail.getContent());
        response.put("currentPage", pageInovicesDetail.getNumber());
        response.put("totalItems", pageInovicesDetail.getTotalElements());
        response.put("totalPages", pageInovicesDetail.getTotalPages());

        return response;
    }

    public List<invoice_detail> findInvoiceDetailByAllFields(String query) {
        return invoiceDetailRepository.searchInvoiceDetails(query, query,query, query);
    }

    public List<invoice_detail> getInvoiceDetailsByIdInvoice(String idinvoice) {
        return invoiceDetailRepository.findByIdinvoice_IdInvoice(idinvoice);
    }

}
