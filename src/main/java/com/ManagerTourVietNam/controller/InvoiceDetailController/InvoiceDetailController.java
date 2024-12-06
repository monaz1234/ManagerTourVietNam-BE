package com.ManagerTourVietNam.controller.InvoiceDetailController;


import com.ManagerTourVietNam.model.inovice_detail.invoice_detail;

import com.ManagerTourVietNam.model.invoice.invoice;
import com.ManagerTourVietNam.repository.InvoiceDetail.InvoiceDetailRepository;
import com.ManagerTourVietNam.service.InvoiceDetailService.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceDetailController {
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @GetMapping("/api/invoiceDetails")
    public List<invoice_detail> GetAllInvoiceDetails() {
        return invoiceDetailService.getAllInvoiceDetail();
    }

    @PostMapping("/api/invoiceDetail")
    public invoice_detail addInvoiceDetail(@RequestBody invoice_detail invoiceSubDetail) {
        return invoiceDetailService.addInvoiceDetail(invoiceSubDetail);
    }

    @PutMapping("/api/invoiceDetail/{id}")
    public ResponseEntity<invoice_detail> updateInvoiceDetail(@PathVariable String id, @RequestBody invoice_detail invoiceDetail) {
        try {
            invoice_detail updatedInoviceDetailSub = invoiceDetailService.updateInvoiceDetail(id, invoiceDetail);
            return ResponseEntity.ok(updatedInoviceDetailSub);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // You can customize the error message as needed
        }
    }


    // Xóa người dùng
    @DeleteMapping("/api/invoiceDetail/{id}")
    public void deleteInoviceDetail(@PathVariable String id){
        invoiceDetailService.deleteInvoiceDetail(id);
    }

    @GetMapping("/api/invoiceDetail/{id}")
    public ResponseEntity<invoice_detail> findInvoiceDetailById(@PathVariable String id) {
        Optional<invoice_detail> invoiceDetailSub = invoiceDetailService.findInvoiceDetailById(id);
        return invoiceDetailSub.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @GetMapping("/api/invoiceDetail/phantrang")
    public ResponseEntity<Page<invoice_detail>> getInvoiceDetails(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<invoice_detail> pageInvoiceDetail = invoiceDetailRepository.findAll(pageable);
        return ResponseEntity.ok(pageInvoiceDetail);
    }


    @GetMapping("/api/invoiceDetail-ids")
    public List<String> getAllInvoiceDetailIds() {
        return invoiceDetailRepository.findAll().stream()
                .map(invoice_detail::getIdvoicedetail)
                .collect(Collectors.toList());
    }

    @GetMapping("api/invoiceDetails/search")
    public ResponseEntity<List<invoice_detail>> searchInvoiceDetails(@RequestParam String query) {
        List<invoice_detail> invoice_details = invoiceDetailService.findInvoiceDetailByAllFields(query);
        if (invoice_details.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Không tìm thấy
        }
        return new ResponseEntity<>(invoice_details, HttpStatus.OK);
    }

    @GetMapping("/api/invoice-details/{idinvoice}")
    public List<invoice_detail> getInvoiceDetails(@PathVariable String idinvoice) {
        return invoiceDetailService.getInvoiceDetailsByIdInvoice(idinvoice);
    }

}
