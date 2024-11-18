package com.ManagerTourVietNam.repository.BookRepository;

import com.ManagerTourVietNam.model.Book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
//    Page<Book> findByNameContaining(String name, Pageable pageable);
List<Book> findByIdbookContaining(String idbook);




}
