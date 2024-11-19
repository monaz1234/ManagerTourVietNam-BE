package com.ManagerTourVietNam.service.BookService;

import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.repository.BookRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(String id, Book bookDetails)throws UserPrincipalNotFoundException {
        return bookRepository.findById(id).map(book -> {
            book.setIdbook(bookDetails.getIdbook());
            book.setStatus(bookDetails.getStatus());
            if (bookDetails.getAccount() != null) {
                book.setAccount(bookDetails.getAccount());
            }if(bookDetails.getAccount() == null) {
                book.setAccount(null);
            }if (bookDetails.getTour() != null){
                book.setTour(bookDetails.getTour());
            }else {
                book.setTour(null);
            }
            return bookRepository.save(book);
        }).orElseThrow(() -> new UserPrincipalNotFoundException("User not found with id " + id));
    }

    public void DeleteBook(String id){
        bookRepository.deleteById(id);
    }

    public Optional<Book> findBookById(String id){
        return bookRepository.findById(id);
    }

    public Map<String, Object> getBooksWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pageUsers = bookRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("books", pageUsers.getContent());
        response.put("currentPage", pageUsers.getNumber());
        response.put("totalItems", pageUsers.getTotalElements());
        response.put("totalPages", pageUsers.getTotalPages());

        return response;
    }

    public List<Book> searchBooks(String query) {
        // Tìm kiếm theo từ khóa (có thể là iduser, name, hoặc email)
        return bookRepository.findByIdbookContainingOrTour_TournameContainingOrAccount_UsernameContaining(query, query, query);
    }


}
