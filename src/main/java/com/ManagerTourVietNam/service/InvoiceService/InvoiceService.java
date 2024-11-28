package com.ManagerTourVietNam.service.InvoiceService;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.InvoiceRepository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ManagerTourVietNam.model.invoice.invoice;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<invoice> getAllInvoice(){
        return invoiceRepository.findAll();
    }

    public invoice addInvoice(invoice invoiceSub) {
        return invoiceRepository.save(invoiceSub);
    }

    public invoice updateInvoice(String id, invoice invoiceDetails) throws UserPrincipalNotFoundException {
        return invoiceRepository.findById(id).map(invoiceSub -> {
            invoiceSub.setIdinvoice(invoiceDetails.getIdinvoice());

            // Cập nhật Idaccount
            invoiceSub.setIdaccount(invoiceDetails.getIdaccount() != null ? invoiceDetails.getIdaccount() : null);

            // Cập nhật IdPaymentMethod
            invoiceSub.setIdPaymentMethod(invoiceDetails.getIdPaymentMethod() != null ? invoiceDetails.getIdPaymentMethod() : null);

            // Cập nhật các thông tin khác
            invoiceSub.setStatus(invoiceDetails.isStatus());
            invoiceSub.setPayment_time(invoiceDetails.getPayment_time());
            invoiceSub.setTotal_amount(invoiceDetails.getTotal_amount());

            return invoiceRepository.save(invoiceSub);
        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
    }

    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }

    public Optional<invoice> findInvoiceById(String id) {
        return invoiceRepository.findById(id);
    }

    public Map<String, Object> getInovicesWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<invoice> pageInovices = invoiceRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("invoices", pageInovices.getContent());
        response.put("currentPage", pageInovices.getNumber());
        response.put("totalItems", pageInovices.getTotalElements());
        response.put("totalPages", pageInovices.getTotalPages());

        return response;
    }


    public List<invoice> findInvoiceByAllFields(String query) {
        return invoiceRepository.searchInvoices(query, query, query);
    }








}
