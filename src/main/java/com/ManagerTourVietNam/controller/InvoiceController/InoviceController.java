package com.ManagerTourVietNam.controller.InvoiceController;


import com.ManagerTourVietNam.repository.InvoiceRepository.InvoiceRepository;
import com.ManagerTourVietNam.service.BookService.SequenceGeneratorService;
import com.ManagerTourVietNam.service.InvoiceService.InvoiceService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ManagerTourVietNam.model.invoice.Invoice;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InoviceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private APIContext apiContext;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private InvoiceRepository invoiceRepository;
//    @PostMapping("/create")
//    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
//        Invoice savedInvoice = invoiceService.createInvoice(invoice);
//        return ResponseEntity.ok(savedInvoice);
//    }
    @PostMapping("/invoice/create")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        // Tạo mã ID tự động
        System.out.println("Invoice data received: " + invoice);
        System.out.println("Payment Name received: " + invoice.getPayment_name());

        if (invoice.getIdinvoice() == null || invoice.getIdinvoice().isEmpty()) {
            String generatedId = sequenceGeneratorService.generateInvoiceId();
            invoice.setIdinvoice(generatedId);
        }

        // Lưu vào cơ sở dữ liệu
        Invoice savedInvoice = invoiceRepository.save(invoice);

        return ResponseEntity.ok(savedInvoice);

    }

    @PostMapping("/paypal/pay")
    public String payWithPayPal(@RequestBody Invoice invoice) {
        // Tạo mã hóa đơn trước
        String idinvoice = sequenceGeneratorService.generateInvoiceId();
        invoice.setIdinvoice(idinvoice);

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(invoice.getTotal_amount() / 23000)); // Quy đổi từ VND sang USD

        Transaction transaction = new Transaction();
        transaction.setDescription("Thanh toán hóa đơn: " + idinvoice);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:9000/payment/cancel");
        redirectUrls.setReturnUrl("http://localhost:9000/payment/success");
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);
        // Lưu các thông tin như idaccount và idbook từ frontend vào hóa đơn
        invoice.setIdaccount(invoice.getIdaccount()); // Thêm idaccount
        invoice.setIdbook(invoice.getIdbook()); // Thêm idbook
        invoice.setPayment_name("paypal");

        // Lưu hóa đơn vào cơ sở dữ liệu (nếu cần thiết)
        invoiceRepository.save(invoice);
        try {
            Payment createdPayment = payment.create(apiContext);
            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return link.getHref(); // Trả về URL để redirect người dùng
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "Thanh toán thất bại!";
    }

    @GetMapping("/payment/success")
    public ResponseEntity<String> successPay(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {

        try {
            // Xác nhận giao dịch
            Payment payment = Payment.get(apiContext, paymentId);
            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);

            Payment executedPayment = payment.execute(apiContext, paymentExecution);
            System.out.println("dữ liệu thanh toán: " + executedPayment);
            String description = executedPayment.getTransactions().get(0).getDescription();
            String idinvoice = description.split(": ")[1];
            if (executedPayment.getState().equals("approved")) {
                // Lấy thông tin giao dịch
                Transaction transaction = executedPayment.getTransactions().get(0);
                Amount amount = transaction.getAmount();

                // Cập nhật hóa đơn với thông tin thanh toán
                Invoice invoice = invoiceRepository.findById(idinvoice).orElseThrow(() -> new RuntimeException("Invoice not found"));
                invoice.setTotal_amount((int) (Double.parseDouble(amount.getTotal()) * 23000)); // USD -> VND
                invoice.setPayment_time(LocalDate.now());
                invoice.setStatus(1); // Đã thanh toán
                invoiceRepository.save(invoice);

                String redirectUrl = "http://localhost:4200/customer";
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(redirectUrl));
                return new ResponseEntity<>(headers, HttpStatus.FOUND); // Trả về HTTP 302
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thanh toán thất bại.");
    }

    @GetMapping("/payment/cancel")
    public String cancelPay() {
        return "Thanh toán đã bị hủy!";
    }
//    @PostMapping("/momo-payment")
//    public ResponseEntity<String> processMomoPayment(@RequestBody Invoice invoice) {
//        // Giả lập xử lý thanh toán Momo (cần tích hợp SDK hoặc API Momo thực tế)
//        boolean paymentSuccess = momoService.processPayment(invoice);
//        if (paymentSuccess) {
//            invoice.setStatus(1); // Đặt trạng thái đã thanh toán thành công
//            invoiceService.createInvoice(invoice);
//            return ResponseEntity.ok("Thanh toán qua Momo thành công!");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thanh toán qua Momo thất bại.");
//        }
//    }

//    @GetMapping("/api/invoices")
//    public List<Invoice> GetAllInvoice() {
//        return invoiceService.getAllInvoice();
//    }
//    @PostMapping("/api/invoice")
//    public Invoice addInvoice(@RequestBody Invoice invoiceSub) {
//        return invoiceService.addInvoice(invoiceSub);
//    }
//    @PutMapping("/api/invoice/{id}")
//    public ResponseEntity<Invoice> updateInvoice(@PathVariable String id, @RequestBody Invoice invoiceDetail) {
//        try {
//            Invoice updatedInoviceSub = invoiceService.updateInvoice(id, invoiceDetail);
//            return ResponseEntity.ok(updatedInoviceSub);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(null); // You can customize the error message as needed
//        }
//    }
//    // Xóa người dùng
//    @DeleteMapping("/api/invoice/{id}")
//    public void deleteInovice(@PathVariable String id){
//        invoiceService.deleteInvoice(id);
//    }
//    @GetMapping("/api/invoice/{id}")
//    public ResponseEntity<Invoice> findInvoiceById(@PathVariable String id) {
//        Optional<Invoice> invoiceSub = invoiceService.findInvoiceById(id);
//        return invoiceSub.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
//    }
//    @GetMapping("/api/invoice/phantrang")
//    public ResponseEntity<Page<Invoice>> getInvoices(@RequestParam int page, @RequestParam int pageSize) {
//        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
//        Page<Invoice> pageInvoice = invoiceRepository.findAll(pageable);
//        return ResponseEntity.ok(pageInvoice);
//    }
//    @GetMapping("/api/invoice-ids")
//    public List<String> getAllInvoiceIds() {
//        return invoiceRepository.findAll().stream()
//                .map(Invoice::getIdinvoice)
//                .collect(Collectors.toList());
//    }
//    @GetMapping("/api/invoices/search")
//    public ResponseEntity<List<Invoice>> searchInvoices(@RequestParam String query) {
//        List<Invoice> invoices = invoiceService.findInvoiceByAllFields(query);
//        if (invoices.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Không tìm thấy
//        }
//        return new ResponseEntity<>(invoices, HttpStatus.OK);
//    }
//    // Lấy tổng tiền theo ngày
//    @GetMapping("/api/invoices/total-by-date")
//    public ResponseEntity<BigDecimal> getTotalByDate(
//            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
//        System.out.println("Received date: " + date);  // Log ra giá trị ngày nhận được
//        BigDecimal totalAmount = invoiceRepository.findTotalAmountByDate(date);
//        return ResponseEntity.ok(totalAmount);
//    }
//    // Lấy tổng tiền theo tháng
//    @GetMapping("/api/invoices/total-by-month")
//    public ResponseEntity<BigDecimal> getTotalByMonth(
//            @RequestParam("month") int month, @RequestParam("year") int year) {
//        BigDecimal totalAmount = invoiceRepository.findTotalAmountByMonth(month, year);
//        return ResponseEntity.ok(totalAmount);
//    }
//    // Lấy tổng tiền theo năm
//    @GetMapping("/api/invoices/total-by-year")
//    public ResponseEntity<BigDecimal> getTotalByYear(@RequestParam("year") int year) {
//        BigDecimal totalAmount = invoiceRepository.findTotalAmountByYear(year);
//        return ResponseEntity.ok(totalAmount);
//    }
}
