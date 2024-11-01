package com.ManagerTourVietNam.controller.PaymentMethodController;

import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
import com.ManagerTourVietNam.service.PaymentService.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

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

}
