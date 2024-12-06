package com.ManagerTourVietNam.repository.BookRepository;

import com.ManagerTourVietNam.model.Book.SequenceGenerator;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SequenceGeneratorRepository extends JpaRepository<SequenceGenerator, String> {


    @Modifying
    @Transactional
    @Query("UPDATE SequenceGenerator sg SET sg.seq_value = sg.seq_value + 1 WHERE sg.seq_name = :seqName")
    void incrementSequence(@Param("seqName") String seqName);

    @Query("SELECT sg.seq_value FROM SequenceGenerator sg WHERE sg.seq_name = :seqName")
    int getCurrentSequenceValue(@Param("seqName") String seqName);

//        @Modifying
//        @Transactional
//        @Query("UPDATE SequenceGenerator sg SET sg.seq_value = sg.seq_value + 1 WHERE sg.seq_name = :seqName")
//        void incrementSequence(@Param("seqName") String seqName);
//
//            @Query("SELECT sg.seq_value FROM SequenceGenerator sg WHERE sg.seq_name = :seqName")
//            int getCurrentSequenceValue(@Param("seqName") String seqName);


//    @Modifying
//    @Transactional
//    @Query("UPDATE SequenceGenerator sg SET sg.seq_value = sg.seq_value + 1 WHERE sg.seq_name = :seqName")
//    void incrementSequence(@Param("seqName") String seqName);
//
//    @Query("SELECT sg.seq_value FROM SequenceGenerator sg WHERE sg.seq_name = :seqName")
//    int getCurrentSequenceValue(@Param("seqName") String seqName);
//
//    // Lấy danh sách tất cả các ID hiện tại
//    @Query("SELECT b.idbook FROM Book b")
//    List<String> getAllBookIds();

}
