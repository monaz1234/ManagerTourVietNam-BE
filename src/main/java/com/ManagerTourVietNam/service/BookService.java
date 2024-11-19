package com.ManagerTourVietNam.service;

import com.ManagerTourVietNam.model.Book;
import com.ManagerTourVietNam.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
}
