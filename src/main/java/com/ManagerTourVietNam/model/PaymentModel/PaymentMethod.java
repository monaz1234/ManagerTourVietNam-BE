package com.ManagerTourVietNam.model.PaymentModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class PaymentMethod {
    @Id
    private String id_method;
    private String payment_code;
    private String payment_name;
    private String description;
    private double fee_percentage;
    private String supported_currencies;
    private Long transaction_limit;
    private boolean is_active;
    private LocalDate created_at;
    private LocalDate updated_at;

    public String getId_method() {
        return id_method;
    }

    public void setId_method(String id_method) {
        this.id_method = id_method;
    }

    public String getPayment_code() {
        return payment_code;
    }

    public void setPayment_code(String payment_code) {
        this.payment_code = payment_code;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee_percentage() {
        return fee_percentage;
    }

    public void setFee_percentage(double fee_percentage) {
        this.fee_percentage = fee_percentage;
    }

    public String getSupported_currencies() {
        return supported_currencies;
    }

    public void setSupported_currencies(String supported_currencies) {
        this.supported_currencies = supported_currencies;
    }

    public Long getTransaction_limit() {
        return transaction_limit;
    }

    public void setTransaction_limit(Long transaction_limit) {
        this.transaction_limit = transaction_limit;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public PaymentMethod(){

    }

    public PaymentMethod(String id_method, String payment_code, String payment_name, String description, double fee_percentage, String supported_currencies, Long transaction_limit, boolean is_active, LocalDate created_at, LocalDate updated_at) {
        this.id_method = id_method;
        this.payment_code = payment_code;
        this.payment_name = payment_name;
        this.description = description;
        this.fee_percentage = fee_percentage;
        this.supported_currencies = supported_currencies;
        this.transaction_limit = transaction_limit;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id_method='" + id_method + '\'' +
                ", payment_code='" + payment_code + '\'' +
                ", payment_name='" + payment_name + '\'' +
                ", description='" + description + '\'' +
                ", fee_percentage=" + fee_percentage +
                ", supported_currencies='" + supported_currencies + '\'' +
                ", transaction_limit=" + transaction_limit +
                ", is_active=" + is_active +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
