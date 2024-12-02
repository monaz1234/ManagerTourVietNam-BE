package com.ManagerTourVietNam.service.Account;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(String id, Account accountDataDetail) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setUsername(accountDataDetail.getUsername());
            account.setTypeUser(accountDataDetail.getTypeUser());
            account.setUser(accountDataDetail.getUser());
            account.setPassword(accountDataDetail.getPassword());
            account.setStatus(accountDataDetail.getStatus());
            account.setImage(accountDataDetail.getImage());
            return accountRepository.save(account);

        }
        return null;
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    public Optional<Account> findAccountById(String id) {
        return accountRepository.findById(id);
    }

    public Map<String, Object> getAccountsWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Account> pageAccounts = accountRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("accounts", pageAccounts.getContent());
        response.put("currentPage", pageAccounts.getNumber());
        response.put("totalItems", pageAccounts.getTotalElements());
        response.put("totalPages", pageAccounts.getTotalPages());

        return response;
    }

    public List<Account> searchAccounts(String query) {
        return accountRepository.findByIdaccountContainingIgnoreCaseOrUsernameContainingIgnoreCase(query, query);
    }

    // public Optional<Account> findByUsernameAndPassword(String username, String
    // password) {
    // return accountRepository.findByUsernameAndPassword(username, password);
    // }

    public Account validateLogin(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }


    public Optional<String> getIdTypeUserByUsername(String username) {
        return accountRepository.findIdTypeUserByUsername(username);
    }





        public String getIdUserFromAccount(Account account) {
            if (account.getUser() != null) {
                return account.getUser().getIduser();
            }
            return null; // Trả về null nếu không có user liên kết
        }

    public String getIdUserByAccountId(String accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            return getIdUserFromAccount(accountOptional.get());
        }
        return null; // Không tìm thấy account
    }


    public String getIdUserByAccountIdNew(String iduser) {
        // Tìm Account theo iduser
        Optional<Account> accountOptional = accountRepository.findByUserIduser(iduser);
        if (accountOptional.isPresent()) {
            return getIdUserFromAccount(accountOptional.get());
        }
        return null; // Không tìm thấy account
    }



    public Optional<String> findIdUserByUsername(String username) {
        return accountRepository.findByUsername(username).map(Account::getUser).map(User::getIduser);
    }

    public String registerAccount(Account newAccount) {
        Optional<Account> existingAccount = accountRepository.findAccountByUsername(newAccount.getUsername());
        if (existingAccount.isPresent()) {
            return "Username đã tồn tại. Vui lòng chọn tên khác.";
        }
        accountRepository.save(newAccount);
        return "Đăng ký thành công!";
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found for username: " + username));
    }






}
