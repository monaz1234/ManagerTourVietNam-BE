package com.ManagerTourVietNam.repository;

import com.ManagerTourVietNam.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Page<Account> findByUsernameContaining(String username, Pageable pageable);

    List<Account> findByIdaccountContainingIgnoreCaseOrUsernameContainingIgnoreCase(String idaccount, String username);

    // Tìm kiếm tài khoản theo username và password
//    Optional<Account> findByUsernameAndPassword(String username, String password);

    Account findByUsernameAndPassword(String username, String password);

}
