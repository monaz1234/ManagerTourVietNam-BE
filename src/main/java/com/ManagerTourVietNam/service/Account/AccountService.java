package com.ManagerTourVietNam.service.Account;


import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account addAccount(Account account){
        return accountRepository.save(account);
    }

    public Account updateAccount(String id, Account accountDataDetail){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()){
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

    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }

    public Optional<Account> findAccountById(String id){
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

//    public Optional<Account> findByUsernameAndPassword(String username, String password) {
//        return accountRepository.findByUsernameAndPassword(username, password);
//    }

    public Account validateLogin(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<String> findIdUserByUsername(String username) {
        return accountRepository.findByUsername(username).map(Account::getUser).map(User::getIduser);
    }

}
