package com.ManagerTourVietNam.controller.AccountController;


import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.AccountRepository;
import com.ManagerTourVietNam.service.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

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


    // Lấy danh sách người dùng với phân trang
    @GetMapping("/api/account/phantrang")
    public ResponseEntity<Page<Account>> getAccount(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<Account> pageAccounts = accountRepository.findAll(pageable);
        return ResponseEntity.ok(pageAccounts);
    }


    @GetMapping("/api/account-ids")
    public List<String> getAllAccountIds() {
        return accountRepository.findAll().stream()
                .map(Account::getIdaccount)
                .collect(Collectors.toList());
    }

    @GetMapping("api/accounts/search")
    public ResponseEntity<List<Account>> searchAccounts(@RequestParam String query) {
        List<Account> accounts = accountService.searchAccounts(query);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }


//    @PostMapping("/api/account/login")
//    public ResponseEntity<?> login(@RequestBody Account account) {
//        Optional<Account> foundAccount = accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());
//        if (foundAccount.isPresent()) {
//            return ResponseEntity.ok(foundAccount.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thông tin đăng nhập không chính xác.");
//        }
//    }

    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginPayload) {
        String username = loginPayload.get("username");
        String password = loginPayload.get("password");

        Account account = accountService.validateLogin(username, password);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thông tin đăng nhập không chính xác.");
        }
    }

}
