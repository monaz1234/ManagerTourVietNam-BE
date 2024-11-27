package com.ManagerTourVietNam.service;
import com.ManagerTourVietNam.model.*;
import com.ManagerTourVietNam.repository.AccountRepository;
import com.ManagerTourVietNam.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    public CommentService(CommentRepository commentRepository, AccountRepository accountRepository) {
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
    }
    public Comment saveComment(String content, String iduser, String idtour, Timestamp created_at) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setIduser(iduser);
        comment.setIdtour(idtour); // Gán idtour
        comment.setCreated_at(created_at);
        return commentRepository.save(comment);
    }

    public List<CommentResponse> getCommentsByTourId(String idtour) {
        return commentRepository.findByIdtour(idtour).stream()
                .map(comment -> {
                    Account account = accountRepository.findByUser_Iduser(comment.getIduser()).orElse(null);
                    CommentResponse response = new CommentResponse();
                    response.setIdcomment(comment.getIdcomment());
                    response.setContent(comment.getContent());
                    response.setCreated_at(comment.getCreated_at());
                    response.setIduser(comment.getIduser());
                    response.setIdtour(comment.getIdtour());
                    if (account != null) {
                        response.setAccount(new AccountResponse(account.getUsername()));
                    }
                    return response;
                }).collect(Collectors.toList());
    }

}