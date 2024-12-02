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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
