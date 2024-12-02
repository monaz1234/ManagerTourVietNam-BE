package com.ManagerTourVietNam.repository.BookDetailReponsitory;

import com.ManagerTourVietNam.model.Book.Book;
import com.ManagerTourVietNam.model.BookDetail.BookDetail;
import com.ManagerTourVietNam.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDetailReponsitory extends JpaRepository<BookDetail, String> {

    List<BookDetail> findByIdbookdetailContaining(String idbookdetail);

    // Sử dụng @Query để tìm BookDetail theo idbook
    @Query("SELECT bd FROM BookDetail bd WHERE bd.idbook = :idbook")
    List<BookDetail> findBookDetailByIdbook(@Param("idbook") Book idbook);

}
