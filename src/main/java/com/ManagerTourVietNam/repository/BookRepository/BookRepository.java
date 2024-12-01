package com.ManagerTourVietNam.repository.BookRepository;

import com.ManagerTourVietNam.model.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
//    Page<Book> findByNameContaining(String name, Pageable pageable);
        List<Book> findByIdbookContainingOrTour_TournameContainingOrAccount_UsernameContaining(
        String idbook, String tourname, String username);


        @Query("SELECT b.idbook FROM Book b")
        List<String> getAllBookIds();







}
