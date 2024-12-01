package com.ManagerTourVietNam.service.BookService;

import com.ManagerTourVietNam.repository.BookDetailReponsitory.BookDetailReponsitory;
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

    @Autowired
    private BookDetailReponsitory bookDetailRepository;

    @Transactional
    public String generateBookId() {
        return generateId("B", "book_seq", bookRepository.getAllBookIds());
    }

    @Transactional
    public String generateBookDetailId() {
        return generateId("D", "bookdetail_seq", bookDetailRepository.getAllBookDetailIds());
    }

    private String generateId(String prefix, String sequenceName, List<String> existingIds) {
        // Tìm mã trống
        for (int i = 1; i <= existingIds.size() + 1; i++) {
            String candidateId = prefix + String.format("%03d", i);
            if (!existingIds.contains(candidateId)) {
                return candidateId;
            }
        }

        // Nếu không tìm thấy mã trống, tạo mã mới dựa trên sequence
        sequenceGeneratorRepository.incrementSequence(sequenceName);
        int currentValue = sequenceGeneratorRepository.getCurrentSequenceValue(sequenceName);
        return prefix + String.format("%03d", currentValue);
    }

}
