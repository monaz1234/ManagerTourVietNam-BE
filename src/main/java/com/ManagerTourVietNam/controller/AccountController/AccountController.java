package com.ManagerTourVietNam.controller.AccountController;


import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.service.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("api/accounts")
    public List<Account> GetAllAccount(){
        return accountService.getAllAccounts();
    }

    @PostMapping("api/account")
    public Account addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @PutMapping("api/account/{id}")
    public Account editAccount(@PathVariable String id, @RequestBody Account account){
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("api/account/{id}")
    public void deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
    }

    @GetMapping("api/account/{id}")
    public Optional<Account> findAccountById(@PathVariable String id){
        return accountService.findAccountById(id);
    }

}
