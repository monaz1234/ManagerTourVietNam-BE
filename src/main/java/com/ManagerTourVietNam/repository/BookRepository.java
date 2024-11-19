package com.ManagerTourVietNam.repository;


import com.ManagerTourVietNam.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
