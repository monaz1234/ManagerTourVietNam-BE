package com.ManagerTourVietNam.repository.BookDetailReponsitory;

import com.ManagerTourVietNam.model.BookDetail.BookDetail;
import com.ManagerTourVietNam.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDetailReponsitory extends JpaRepository<BookDetail, String> {

    List<BookDetail> findByIdbookdetailContaining(String idbookdetail);
}
