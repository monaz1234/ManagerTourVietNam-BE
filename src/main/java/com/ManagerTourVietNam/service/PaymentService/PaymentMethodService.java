package com.ManagerTourVietNam.service.PaymentService;

import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
import com.ManagerTourVietNam.repository.PaymentRepository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentRepository paymentRepository;

    // lấy danh sách payment
    public List<PaymentMethod> getAllPaymentMethod(){
        return paymentRepository.findAll();
    }
    // lấy payment theo id
    public Optional<PaymentMethod> getPaymentMethod(String id){
        return paymentRepository.findById(id);
    }
    // update
    public ResponseEntity<PaymentMethod> updatePaymentMethod (String id, PaymentMethod PaymentDetail) {
        Optional<PaymentMethod> optionalPaymentMethod = paymentRepository.findById(id);

        if(optionalPaymentMethod.isPresent()){
            PaymentMethod paymentMethod = optionalPaymentMethod.get();
            paymentMethod.setId_method(PaymentDetail.getId_method());
            paymentMethod.setPayment_code(PaymentDetail.getPayment_code());
            paymentMethod.setPayment_name(PaymentDetail.getPayment_name());
            paymentMethod.setDescription(PaymentDetail.getDescription());
            paymentMethod.setFee_percentage(PaymentDetail.getFee_percentage());
            paymentMethod.setSupported_currencies(PaymentDetail.getSupported_currencies());
            paymentMethod.setTransaction_limit(PaymentDetail.getTransaction_limit());
            paymentMethod.setIs_active(PaymentDetail.isIs_active());
            paymentMethod.setCreated_at(PaymentDetail.getCreated_at());
            paymentMethod.setUpdated_at(PaymentDetail.getUpdated_at());

            PaymentMethod upDatedPaymentMethod = paymentRepository.save(paymentMethod);
            return new ResponseEntity<>(upDatedPaymentMethod, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // xóa payment theo id
    public void deletePaymentMethod(String id){
        paymentRepository.deleteById(id);
    }
    // thêm phương thức thanh toán
    public ResponseEntity<PaymentMethod> addPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod.getId_method() == null ||
                paymentMethod.getPayment_code() == null ||
                paymentMethod.getPayment_name() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            // Lưu phương thức thanh toán vào cơ sở dữ liệu
            PaymentMethod createdPaymentMethod = paymentRepository.save(paymentMethod);
            return new ResponseEntity<>(createdPaymentMethod, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // Xử lý lỗi vi phạm ràng buộc dữ liệu (ví dụ: trùng lặp khóa chính)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Xử lý lỗi chung
            e.printStackTrace(); // In lỗi ra console để kiểm tra
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // kích hoạt / vô hiệu hóa phương thức thanh toán theo id payement method
    public ResponseEntity<PaymentMethod> togglePaymentMethodStatus(String id)
    {
        Optional<PaymentMethod> optionalPaymentMethod = paymentRepository.findById(id);
        if(optionalPaymentMethod.isPresent())
        {
            PaymentMethod paymentMethod = optionalPaymentMethod.get();
            paymentMethod.setIs_active(!paymentMethod.isIs_active());
            PaymentMethod updatedPaymentMethod = paymentRepository.save(paymentMethod);
            return new ResponseEntity<>(updatedPaymentMethod,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
