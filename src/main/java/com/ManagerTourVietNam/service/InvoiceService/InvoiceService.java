package com.ManagerTourVietNam.service.InvoiceService;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.repository.AccountRepository;
import com.ManagerTourVietNam.repository.BookRepository.BookRepository;
import com.ManagerTourVietNam.repository.InvoiceRepository.InvoiceRepository;
import com.ManagerTourVietNam.service.BookService.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ManagerTourVietNam.model.invoice.Invoice;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<String> findMissingInvoiceIds(List<String> existingIds) {
        List<Integer> allIds = new ArrayList<>();

        // Lấy các phần số từ idbook (ví dụ từ "I001" lấy ra 1)
        for (String id : existingIds) {
            allIds.add(Integer.parseInt(id.substring(1)));
        }

        // Tìm khoảng trống trong dãy số (bắt đầu từ 1)
        List<String> missingIds = new ArrayList<>();
        int lastId = Collections.max(allIds); // Lấy id lớn nhất hiện có
        for (int i = 1; i <= lastId; i++) {
            if (!allIds.contains(i)) {
                missingIds.add("I" + String.format("%03d", i)); // Tạo ID thiếu (ví dụ I003, I004...)
            }
        }

        return missingIds; // Trả về danh sách các ID bị thiếu
    }
    @Transactional
    public String generateNextInvoiceId() {
        // Log the current existing IDs for debugging
        List<String> existingIds = invoiceRepository.findAll().stream()
                .map(Invoice::getIdinvoice)
                .collect(Collectors.toList());
        System.out.println("Existing Invoice IDs: " + existingIds);

        // Continue with finding missing IDs or generating the next one
        List<String> missingIds = findMissingInvoiceIds(existingIds);

        // If there are any missing IDs, use the first missing one
        if (!missingIds.isEmpty()) {
            return missingIds.get(0); // Use the first missing ID
        }

        // If no missing IDs, generate the next one
        int maxId = existingIds.stream()
                .filter(id -> id.startsWith("I")) // Chỉ lấy ID có dạng hợp lệ
                .map(id -> Integer.parseInt(id.substring(1))) // Parse out the number from "B001"
                .max(Integer::compare)
                .orElse(0); // Start at 0 if no existing IDs
        return "I" + String.format("%03d", maxId + 1); // Return the next ID in the format B001, B002...
    }

    public Invoice createInvoice(Invoice invoice) {
        // Tạo ID mới cho book
        System.out.println("Received Book before saving: " + invoice); // Log dữ liệu nhận được
        if (invoice.getIdinvoice() == null || invoice.getIdinvoice().isEmpty()) {
            invoice.setIdinvoice(generateNextInvoiceId());  // Gọi method tạo idbook
        }

        // Gán thời gian hiện tại nếu chưa có
        if (invoice.getPayment_time() == null) {
            invoice.setPayment_time(LocalDate.now());
        }

        Invoice savedInvoice = invoiceRepository.save(invoice);
        System.out.println("Saved invoice with ID: " + savedInvoice.getIdinvoice());
        return savedInvoice;

    }

//    public List<invoice> getAllInvoice(){
//        return invoiceRepository.findAll();
//    }
//    public invoice addInvoice(invoice invoiceSub) {
//        return invoiceRepository.save(invoiceSub);
//    }
//    public invoice updateInvoice(String id, invoice invoiceDetails) throws UserPrincipalNotFoundException {
//        return invoiceRepository.findById(id).map(invoiceSub -> {
//            invoiceSub.setIdinvoice(invoiceDetails.getIdinvoice());
//            // Cập nhật Idaccount
//            invoiceSub.setIdaccount(invoiceDetails.getIdaccount() != null ? invoiceDetails.getIdaccount() : null);
//            // Cập nhật IdPaymentMethod
//            invoiceSub.setIdPaymentMethod(invoiceDetails.getIdPaymentMethod() != null ? invoiceDetails.getIdPaymentMethod() : null);
//            // Cập nhật các thông tin khác
//            invoiceSub.setStatus(invoiceDetails.isStatus());
//            invoiceSub.setPayment_time(invoiceDetails.getPayment_time());
//            invoiceSub.setTotal_amount(invoiceDetails.getTotal_amount());
//            return invoiceRepository.save(invoiceSub);
//        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
//    }
//    public void deleteInvoice(String id) {
//        invoiceRepository.deleteById(id);
//    }
//    public Optional<invoice> findInvoiceById(String id) {
//        return invoiceRepository.findById(id);
//    }
//    public Map<String, Object> getInovicesWithPagination(int page, int size, String sortBy) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Page<invoice> pageInovices = invoiceRepository.findAll(pageable);
//        Map<String, Object> response = new HashMap<>();
//        response.put("invoices", pageInovices.getContent());
//        response.put("currentPage", pageInovices.getNumber());
//        response.put("totalItems", pageInovices.getTotalElements());
//        response.put("totalPages", pageInovices.getTotalPages());
//        return response;
//    }
//    public List<invoice> findInvoiceByAllFields(String query) {
//        return invoiceRepository.searchInvoices(query, query, query);
//    }








}
