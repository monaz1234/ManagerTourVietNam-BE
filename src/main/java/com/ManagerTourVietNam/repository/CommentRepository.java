package com.ManagerTourVietNam.repository;
import com.ManagerTourVietNam.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // Phương thức tìm bình luận theo idtour
    List<Comment> findByIdtour(String idtour);
    // Phương thức tìm bình luận theo iduser (nếu cần)
    List<Comment> findByIduser(String iduser);
    // Bạn có thể thêm các phương thức tìm kiếm khác tùy theo yêu cầu
}
