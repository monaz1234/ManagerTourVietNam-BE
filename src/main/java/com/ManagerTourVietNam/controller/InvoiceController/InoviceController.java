package com.ManagerTourVietNam.controller.InvoiceController;


import com.ManagerTourVietNam.repository.InvoiceRepository.InvoiceRepository;
import com.ManagerTourVietNam.service.InvoiceService.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ManagerTourVietNam.model.invoice.invoice;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InoviceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/api/invoices")
    public List<invoice> GetAllInvoice() {
        return invoiceService.getAllInvoice();
    }

    @PostMapping("/api/invoice")
    public invoice addInvoice(@RequestBody invoice invoiceSub) {
        return invoiceService.addInvoice(invoiceSub);
    }

    @PutMapping("/api/invoice/{id}")
    public ResponseEntity<invoice> updateInvoice(@PathVariable String id, @RequestBody invoice invoiceDetail) {
        try {
            invoice updatedInoviceSub = invoiceService.updateInvoice(id, invoiceDetail);
            return ResponseEntity.ok(updatedInoviceSub);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // You can customize the error message as needed
        }
    }

    // Xóa người dùng
    @DeleteMapping("/api/invoice/{id}")
    public void deleteInovice(@PathVariable String id){
        invoiceService.deleteInvoice(id);
    }


    @GetMapping("/api/invoice/{id}")
    public ResponseEntity<invoice> findInvoiceById(@PathVariable String id) {
        Optional<invoice> invoiceSub = invoiceService.findInvoiceById(id);
        return invoiceSub.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @GetMapping("/api/invoice/phantrang")
    public ResponseEntity<Page<invoice>> getInvoices(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<invoice> pageInvoice = invoiceRepository.findAll(pageable);
        return ResponseEntity.ok(pageInvoice);
    }


    @GetMapping("/api/invoice-ids")
    public List<String> getAllInvoiceIds() {
        return invoiceRepository.findAll().stream()
                .map(invoice::getIdinvoice)
                .collect(Collectors.toList());
    }

    @GetMapping("api/invoices/search")
    public ResponseEntity<List<invoice>> searchInvoices(@RequestParam String query) {
        List<invoice> invoices = invoiceService.findInvoiceByAllFields(query);
        if (invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Không tìm thấy
        }
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @GetMapping("api/invoicepdf")
    public String generateInvoice(
            @RequestParam String name,
            @RequestParam String idHoaDon,
            @RequestParam String idTour,
            @RequestParam int soLuongChoNgoi,
            @RequestParam double tongGiaTien,
            @RequestParam String phuongThucThanhToan) {

        InvoicePDFGenerator pdfGenerator = new InvoicePDFGenerator();
        pdfGenerator.generateInvoice(name, idHoaDon, idTour, soLuongChoNgoi, tongGiaTien, phuongThucThanhToan);

        String dirIUploadImageVehicle = System.getProperty("user.dir") + "/public/pdf";
        return "Hóa đơn đã được tạo tại: " + dirIUploadImageVehicle + "/hoadon_" + idHoaDon + ".pdf";
    }

}
