package com.ManagerTourVietNam.repository.PaymentRepository;

import com.ManagerTourVietNam.model.PaymentModel.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentMethod, String> {

}
