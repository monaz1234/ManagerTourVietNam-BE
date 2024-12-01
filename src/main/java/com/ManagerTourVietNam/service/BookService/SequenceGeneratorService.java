package com.ManagerTourVietNam.service.BookService;

import com.ManagerTourVietNam.repository.BookRepository.BookRepository;
import com.ManagerTourVietNam.repository.BookRepository.SequenceGeneratorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SequenceGeneratorService {
    @Autowired
    private SequenceGeneratorRepository sequenceGeneratorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public String generateBookId() {
        // Lấy danh sách tất cả các ID hiện có
        List<String> existingIds = bookRepository.getAllBookIds();

        // Tìm mã trống
        for (int i = 1; i <= existingIds.size() + 1; i++) {
            String candidateId = "B" + String.format("%03d", i);
            if (!existingIds.contains(candidateId)) {
                return candidateId; // Trả về mã trống đầu tiên
            }
        }

        // Nếu không tìm thấy mã trống, tạo mã mới dựa trên sequence
        String sequenceName = "book_seq";

        // Tăng giá trị của sequence
        sequenceGeneratorRepository.incrementSequence(sequenceName);

        // Lấy giá trị mới nhất
        int currentValue = sequenceGeneratorRepository.getCurrentSequenceValue(sequenceName);

        // Format thành ID mong muốn (B010, B100, ...)
        return "B" + String.format("%03d", currentValue);
    }

}
