package com.ManagerTourVietNam.service.Account;


import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            account.setIdtypeuser(accountDataDetail.getIdtypeuser());
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
}
