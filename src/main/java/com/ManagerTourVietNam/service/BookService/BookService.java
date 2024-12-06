package com.ManagerTourVietNam.service.BookService;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.repository.BookRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ManagerTourVietNam.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public List<String> findMissingBookIds(List<String> existingIds) {
        List<Integer> allIds = new ArrayList<>();

        // Lấy các phần số từ idbook (ví dụ từ "B001" lấy ra 1)
        for (String id : existingIds) {
            allIds.add(Integer.parseInt(id.substring(1)));
        }

        // Tìm khoảng trống trong dãy số (bắt đầu từ 1)
        List<String> missingIds = new ArrayList<>();
        int lastId = Collections.max(allIds); // Lấy id lớn nhất hiện có
        for (int i = 1; i <= lastId; i++) {
            if (!allIds.contains(i)) {
                missingIds.add("B" + String.format("%03d", i)); // Tạo ID thiếu (ví dụ B003, B004...)
            }
        }

        return missingIds; // Trả về danh sách các ID bị thiếu
    }
    @Transactional
    public String generateNextBookId() {
        // Log the current existing IDs for debugging
        List<String> existingIds = bookRepository.findAll().stream()
                .map(Book::getIdbook)
                .collect(Collectors.toList());
        System.out.println("Existing Book IDs: " + existingIds);

        // Continue with finding missing IDs or generating the next one
        List<String> missingIds = findMissingBookIds(existingIds);

        // If there are any missing IDs, use the first missing one
        if (!missingIds.isEmpty()) {
            return missingIds.get(0); // Use the first missing ID
        }

        // If no missing IDs, generate the next one
        int maxId = existingIds.stream()
                .map(id -> Integer.parseInt(id.substring(1))) // Parse out the number from "B001"
                .max(Integer::compare)
                .orElse(0); // Start at 0 if no existing IDs
        return "B" + String.format("%03d", maxId + 1); // Return the next ID in the format B001, B002...
    }

    public Book createBook(Book book) {
        // Tạo ID mới cho book
        System.out.println("Received Book before saving: " + book); // Log dữ liệu nhận được
        if (book.getIdbook() == null || book.getIdbook().isEmpty()) {
            book.setIdbook(generateNextBookId());  // Gọi method tạo idbook
        }

        Book savedBook = bookRepository.save(book);
        System.out.println("Saved Book with ID: " + savedBook.getIdbook());
        return savedBook;

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
