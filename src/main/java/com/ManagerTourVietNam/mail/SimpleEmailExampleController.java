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
                "<h1>Chào mừng bạn đến với Tour Du Lịch Việt Nam!</h1>" +
                "<img src='https://www.example.com/logo.png' alt='Logo' style='width: 200px; margin: 20px 0;'>" +
                "<p>Chúng tôi xin chúc mừng bạn đã đặt tour thành công!</p>" +
                "<p>Xin vui lòng đóng tiền tại công ty du lịch ABC theo địa chỉ: <strong>ABC XYZ</strong></p>" +
                "<p>Cảm ơn bạn đã tin tưởng và lựa chọn dịch vụ của chúng tôi.</p>" +
                "</body>" +
                "</html>";
        String subject = "Xác Nhận Đặt Tour Thành Công";
        try {
            sendEmail(recipientEmail, subject, htmlMsg);
            return "Email Sent to " + recipientEmail;
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