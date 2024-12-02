package com.ManagerTourVietNam.controller.BookController;


import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.repository.BookRepository.BookRepository;
import com.ManagerTourVietNam.service.BookService.BookService;
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
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("api/books")
    public List<Book> GetAllBook(){
        return bookService.getAllBook();
    }

    @PostMapping("api/book")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PostMapping("api/book/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        // Tạo mã ID tự động

        //String generatedId = sequenceGeneratorService.generateBookId();
        if (book.getIdbook() == null || book.getIdbook().isEmpty()) {
            String generatedId = sequenceGeneratorService.generateBookId();
            book.setIdbook(generatedId);
        }
        // Gán mã ID cho Book
        //book.setIdbook(generatedId);

        // Lưu vào cơ sở dữ liệu
        Book savedBook = bookRepository.save(book);

        return ResponseEntity.ok(savedBook);
    }


//    @PostMapping("/create")
//    public ResponseEntity<Book> createBook(@RequestBody Book book) {
//        // Tạo mã ID tự động
//        String generatedId = sequenceGeneratorService.generateBookId();
//
//        // Gán mã ID cho Book
//        book.setIdbook(generatedId);
//
//        // Lưu vào cơ sở dữ liệu
//        Book savedBook = bookRepository.save(book);
//
//        return ResponseEntity.ok(savedBook);
//    }

    @PutMapping("api/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book bookDetail){
        try{
            Book updateBook = bookService.updateBook(id, bookDetail);
            return ResponseEntity.ok(updateBook);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // You can customize the error message as needed
        }

    }

    // Xóa người dùng
    @DeleteMapping("/api/book/{id}")
    public void deleteBook(@PathVariable String id){
        bookService.DeleteBook(id);
    }

    // Tìm kiếm thông tin người dùng theo id
    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable String id) {
        Optional<Book> book = bookService.findBookById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    // Lấy danh sách người dùng với phân trang
    @GetMapping("/api/book/phantrang")
    public ResponseEntity<Page<Book>> getBooks(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<Book> pageBooks = bookRepository.findAll(pageable);
        return ResponseEntity.ok(pageBooks);
    }

    @GetMapping("/api/book-ids")
    public List<String> getAllBookIds() {
        return bookRepository.findAll().stream()
                .map(Book::getIdbook)
                .collect(Collectors.toList());
    }

    @GetMapping("api/books/search")
    public ResponseEntity<List<Book>> searchUsers(@RequestParam String query) {
        List<Book> books = bookService.searchBooks(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }



}
