package com.ManagerTourVietNam.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InvoicePDFGenerator {

    public void generateInvoice(String name, String idHoaDon, String idTour, int soLuongChoNgoi,
                                double tongGiaTien, String phuongThucThanhToan) {
        String dirIUploadImageVehicle = System.getProperty("user.dir") + "/public/pdf";
        String filePath = dirIUploadImageVehicle + "/hoadon_" + idHoaDon + ".pdf";

        // Kiểm tra và tạo thư mục nếu chưa tồn tại
        File directory = new File(dirIUploadImageVehicle);
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu cần
        }

        Document document = new Document();
        try {
            // Đường dẫn font hỗ trợ tiếng Việt
            String fontPath = "src/main/resources/fonts/arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12, Font.NORMAL);

            // Tạo file PDF
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Thêm nội dung vào file PDF với font tiếng Việt
            document.add(new Paragraph("Hóa Đơn Đặt Tour Du Lịch", font));
            document.add(new Paragraph("----------------------------", font));
            document.add(new Paragraph("Tên khách hàng: " + name, font));
            document.add(new Paragraph("ID Hóa Đơn: " + idHoaDon, font));
            document.add(new Paragraph("ID Tour: " + idTour, font));
            document.add(new Paragraph("Số lượng chỗ ngồi: " + soLuongChoNgoi, font));
            document.add(new Paragraph("Tổng giá tiền: " + tongGiaTien + " VND", font));
            document.add(new Paragraph("Phương thức thanh toán: " + phuongThucThanhToan, font));

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
