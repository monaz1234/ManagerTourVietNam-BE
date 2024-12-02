package com.ManagerTourVietNam.controller.BookDetail;

import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.model.BookDetail.BookDetail;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.BookDetailReponsitory.BookDetailReponsitory;
import com.ManagerTourVietNam.service.BookDetailService.BookDerailService;
import com.ManagerTourVietNam.service.BookService.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookDetailController {
    @Autowired
    private BookDerailService bookDerailService;

    @Autowired
    private BookDetailReponsitory bookDetailReponsitory;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("api/bookdetails")
    public List<BookDetail> getbookdetails(){
        return bookDerailService.getAllBookDetails();
    }


    @PostMapping("api/bookdetail/create")
    public ResponseEntity<BookDetail> createBookDetail(@RequestBody BookDetail bookDetail) {
        if (bookDetail.getIdbookdetail() == null || bookDetail.getIdbookdetail().isEmpty()) {
            String generatedId = sequenceGeneratorService.generateBookDetailId();
            bookDetail.setIdbookdetail(generatedId);  // Tạo ID tự động
        }
        BookDetail savedBookDetail = bookDetailReponsitory.save(bookDetail);
        return ResponseEntity.ok(savedBookDetail);
    }


//    @PostMapping("api/bookdetail")
//    public BookDetail addbookdetails(@RequestBody BookDetail bookDetail){
//        return bookDerailService.addbookDetail(bookDetail);
//    }

    @PutMapping("api/bookdetail/{id}")
    public ResponseEntity<BookDetail> updatedBookDetail(@PathVariable String id, @RequestBody BookDetail bookDetail) {
        try {
            BookDetail updatedBookDetail = bookDerailService.updateBookDetail(id, bookDetail);
            return ResponseEntity.ok(updatedBookDetail);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/api/bookdetail/{id}")
    public void deleteBookDetail(@PathVariable String id){
        bookDerailService.deleteBookDetail(id);
    }

    @GetMapping("/api/bookdetail/{id}")
    public ResponseEntity<BookDetail> findBookDetailById(@PathVariable String id) {
        Optional<BookDetail> bookDetail = bookDerailService.findbookDetailById(id);
        return bookDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/api/bookdetail/phantrang")
    public ResponseEntity<Page<BookDetail>> getBookDetails(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<BookDetail> pageBookDetails = bookDetailReponsitory.findAll(pageable);
        return ResponseEntity.ok(pageBookDetails);
    }

    @GetMapping("/api/bookdetail-ids")
    public List<String> getAllUBookDetailIds() {
        return bookDetailReponsitory.findAll().stream()
                .map(BookDetail::getIdbookdetail)
                .collect(Collectors.toList());
    }

    @GetMapping("api/bookdetail/search")
    public ResponseEntity<List<BookDetail>> searchBookDetail(@RequestParam String query) {
        List<BookDetail> bookDetails = bookDerailService.searchBookDetails(query);
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }

    // API để lấy thông tin BookDetail theo Book (idbook)
    @GetMapping("/api/bookdetailbyidbook/{idbook}")
    public List<BookDetail> getBookDetailsByBook(@PathVariable("idbook") Book idbook) {
        return bookDerailService.findBookDetailByIdbook(idbook);
    }
}
