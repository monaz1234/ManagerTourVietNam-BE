package com.ManagerTourVietNam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcomment;
    @Column(nullable = false)
    private String content;
    @Column(name = "created_at")
    private Timestamp created_at ;// Cập nhật thời gian tạo
    @Column(nullable = false)
    private String iduser;
    @Column(nullable = false)
    private String idtour; // Thêm trường idtour để lưu ID của tour
    // Getter and setter
    public int getIdcomment() {
        return idcomment;
    }
    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    public String getIduser() {
        return iduser;
    }
    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
    public String getIdtour() {
        return idtour;
    }
    public void setIdtour(String idtour) {
        this.idtour = idtour;
    }
}
