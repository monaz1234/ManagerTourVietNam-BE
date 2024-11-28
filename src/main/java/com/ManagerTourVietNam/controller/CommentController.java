package com.ManagerTourVietNam.controller;
import com.ManagerTourVietNam.model.Comment;
import com.ManagerTourVietNam.model.CommentResponse;
import com.ManagerTourVietNam.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/comments/{idtour}")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest request) {
        // Lưu bình luận với idtour
        try {
            System.out.println("Received request to add comment: " + request);
            Comment savedComment = commentService.saveComment(
                    request.getContent(),
                    request.getIduser(),
                    request.getIdtour(),
                    request.getCreated_at()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
        } catch (Exception e) {
            e.printStackTrace(); // In chi tiết lỗi ra console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable String idtour) {
        try {
            if (idtour == null || idtour.isEmpty()) {
                return ResponseEntity.badRequest().body(null); // Trả về lỗi nếu idtour không có giá trị
            }
            List<CommentResponse> comments = commentService.getCommentsByTourId(idtour);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            e.printStackTrace(); // In chi tiết lỗi ra console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
