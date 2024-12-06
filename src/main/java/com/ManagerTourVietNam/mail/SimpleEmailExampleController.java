package com.ManagerTourVietNam.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SimpleEmailExampleController {

    @Autowired
    public JavaMailSender emailSender;

    // Nhận email của người nhận thông qua tham số truy vấn
    @GetMapping("/api/sendemails1")
    public String sendHtmlEmail(@RequestParam String recipientEmail) {

        String htmlMsg = "<html>" +
                "<body style='font-family: Arial, sans-serif; color: #333; text-align: center;'>" +
                "<h1>Welcome to Vietnam Tours!</h1>" +
                "<p>We are pleased to inform you that your tour booking was successful!</p>" +
                "<p>Please proceed with the payment at ABC Travel Company, address: <strong>ABC XYZ</strong></p>" +
                "<p>Thank you for choosing our services.</p>" +
                "</body>" +
                "</html>";

        String subject = "Tour Booking Confirmation";

        try {
            sendEmail(recipientEmail, subject, htmlMsg);
            return "Email Sent to " + recipientEmail;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email";
        }
    }
    @GetMapping("/api/sendemails2")
    public String sendPaymentConfirmationEmail(@RequestParam String recipientEmail) {
        String htmlMsg = "<html>" +
                "<body style='font-family: Arial, sans-serif; color: #333; text-align: center;'>" +
                "<h1>Payment Confirmation</h1>" +
                "<p>Your payment has been successfully confirmed!</p>" +
                "<p>Thank you for completing your payment for the tour at ABC Travel Company.</p>" +
                "<p>We look forward to serving you during your tour.</p>" +
                "</body>" +
                "</html>";

        String subject = "Payment Confirmation for Your Tour";

        try {
            sendEmail(recipientEmail, subject, htmlMsg);
            return "Payment confirmation email sent to " + recipientEmail;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email";
        }
    }
    // Phương thức gửi email với nội dung HTML
    private void sendEmail(String recipientEmail, String subject, String htmlMsg) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(htmlMsg, true); // true để gửi email dưới dạng HTML

        emailSender.send(message);
    }

}