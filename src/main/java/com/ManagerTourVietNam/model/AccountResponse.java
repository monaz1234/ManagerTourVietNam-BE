package com.ManagerTourVietNam.model;
public class AccountResponse {
    private String username;
    public AccountResponse(String username) {
        this.username = username;
    }
    // Getter
    public String getUsername() {return username;}
    // Setter
    public void setUsername(String username) {this.username = username;}
}
