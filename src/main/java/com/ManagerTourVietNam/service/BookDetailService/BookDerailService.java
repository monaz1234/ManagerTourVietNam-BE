package com.ManagerTourVietNam.service.BookDetailService;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.model.BookDetail.BookDetail;
import com.ManagerTourVietNam.repository.BookDetailReponsitory.BookDetailReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookDerailService {
    @Autowired
    private BookDetailReponsitory bookdetailReponsitory;

    public List<BookDetail> getAllBookDetails(){
        return bookdetailReponsitory.findAll();
    }

    public BookDetail addbookDetail(BookDetail bookDetail){
        return bookdetailReponsitory.save(bookDetail);
    }

    @Transactional
    public String generateNextBookDetailId() {
        List<String> existingIds = bookdetailReponsitory.findAll().stream()
                .map(BookDetail::getIdbookdetail)
                .collect(Collectors.toList());

        List<String> missingIds = findMissingIds(existingIds, "D");
        if (!missingIds.isEmpty()) {
            return missingIds.get(0);
        }

        int maxId = existingIds.stream()
                .map(id -> Integer.parseInt(id.substring(1))) // Parse số từ ID (vd: từ "D001" -> 1)
                .max(Integer::compare)
                .orElse(0);

        return "D" + String.format("%03d", maxId + 1);
    }

    private List<String> findMissingIds(List<String> existingIds, String prefix) {
        List<Integer> allIds = existingIds.stream()
                .map(id -> Integer.parseInt(id.substring(1))) // Lấy số
                .sorted()
                .collect(Collectors.toList());

        List<String> missingIds = new ArrayList<>();
        for (int i = 1; i <= Collections.max(allIds); i++) {
            if (!allIds.contains(i)) {
                missingIds.add(prefix + String.format("%03d", i));
            }
        }
        return missingIds;
    }


    public BookDetail updateBookDetail(String id, BookDetail bookDetailDataDetail){
        Optional<BookDetail> optionalBookDetail = bookdetailReponsitory.findById(id);
        if (optionalBookDetail.isPresent()){
            BookDetail bookDetail = optionalBookDetail.get();
            bookDetail.setIdbook(bookDetailDataDetail.getIdbook());
            bookDetail.setTime_book(bookDetailDataDetail.getTime_book());
            bookDetail.setParticipant(bookDetailDataDetail.getParticipant());
            bookDetail.setQuantity(bookDetailDataDetail.getQuantity());
            bookDetail.setPromotion_code(bookDetailDataDetail.getPromotion_code());
            return bookdetailReponsitory.save(bookDetail);

        }
        return null;
    }

    public void deleteBookDetail(String id){
        bookdetailReponsitory.deleteById(id);
    }

    public Optional<BookDetail> findbookDetailById(String id){
        return bookdetailReponsitory.findById(id);
    }

    public Map<String, Object> getBookDetailsWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<BookDetail> pageBookDetails = bookdetailReponsitory.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("bookdetails", pageBookDetails.getContent());
        response.put("currentPage", pageBookDetails.getNumber());
        response.put("totalItems", pageBookDetails.getTotalElements());
        response.put("totalPages", pageBookDetails.getTotalPages());

        return response;
    }

    public List<BookDetail> searchBookDetails(String query) {
        return bookdetailReponsitory.findByIdbookdetailContaining(query);
    }


    // Tìm BookDetail theo Book (idbook)
    public List<BookDetail> findBookDetailByIdbook(Book idbook) {
        return bookdetailReponsitory.findBookDetailByIdbook(idbook);
    }


}
