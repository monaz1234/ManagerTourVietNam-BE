package com.ManagerTourVietNam.controller.PaymentMethodController;

import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
import com.ManagerTourVietNam.model.ServiceModel.Service;
import com.ManagerTourVietNam.repository.PaymentRepository.PaymentRepository;
import com.ManagerTourVietNam.service.PaymentService.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentRepository paymentRepository;

    // lấy danh sách
    @GetMapping("/payment")
    public List<PaymentMethod> getAllPaymentMethod(){
        return paymentMethodService.getAllPaymentMethod();
    }

    // lấy thông tin payment theo id
    @GetMapping("/payment/{id}")
    public Optional<PaymentMethod> getPaymentMethod(@PathVariable String id){
        return paymentMethodService.getPaymentMethod(id);
    }

    // update payment
    @PutMapping("/payment/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable String id, @RequestBody PaymentMethod paymentDetail){
        return paymentMethodService.updatePaymentMethod(id, paymentDetail);
    }

    //delete payment
    @DeleteMapping("/payment/{id}")
    public void deletePaymentMethod(@PathVariable String id){
        paymentMethodService.deletePaymentMethod(id);
    }

    // add payment
    @PostMapping("/payment/add_payment")
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestBody PaymentMethod paymentMethod)
    {
        return  paymentMethodService.addPaymentMethod(paymentMethod);
    }
    //toggle status
    @PostMapping("/payment/toggle_status/{id}")
    public ResponseEntity<PaymentMethod> togglePaymentMethodStatus(@PathVariable String id)
    {
        return paymentMethodService.togglePaymentMethodStatus(id);
    }



    // Lấy danh sách người dùng với phân trang
    @GetMapping("/payment_method/phantrang")
    public ResponseEntity<Page<PaymentMethod>> getPaymentMethods(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<PaymentMethod> pagePaymentMethods = paymentRepository.findAll(pageable);
        return ResponseEntity.ok(pagePaymentMethods);
    }

    @GetMapping("/payment_method-ids")
    public List<String> getAllServiceIds() {
        return paymentRepository.findAll().stream()
                .map(PaymentMethod::getId_method)
                .collect(Collectors.toList());
    }

}
