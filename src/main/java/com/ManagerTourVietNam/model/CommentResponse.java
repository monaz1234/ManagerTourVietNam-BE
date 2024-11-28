package com.ManagerTourVietNam.model;
import java.sql.Timestamp;
public class CommentResponse {
    private int idcomment;
    private String content;
    private Timestamp created_at;
    private String iduser;
    private String idtour;
    private AccountResponse account;
    // Getters
    public int getIdcomment() {
        return idcomment;
    }
    public String getContent() {
        return content;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public String getIduser() {
        return iduser;
    }
    public String getIdtour() {
        return idtour;
    }
    public AccountResponse getAccount() {
        return account;
    }
    // Setters
    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
    public void setIdtour(String idtour) {
        this.idtour = idtour;
    }
    public void setAccount(AccountResponse account) {
        this.account = account;
    }
}
