package com.ManagerTourVietNam.controller;
import java.sql.Timestamp;
public class CommentRequest {
    private String content;
    private String iduser;
    private String idtour;  // Thêm trường này để xác định tour
    private Timestamp created_at;
    // Getters và Setters
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at= created_at;
    }
}
