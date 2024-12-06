package com.ManagerTourVietNam.repository;

import com.ManagerTourVietNam.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Page<Account> findByUsernameContaining(String username, Pageable pageable);
    Optional<Account> findByUser_Iduser(String iduser);
    List<Account> findByIdaccountContainingIgnoreCaseOrUsernameContainingIgnoreCase(String idaccount, String username);

    Account findByUsernameAndPassword(String username, String password);

    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Optional<Account> findAccountByUsername(@Param("username") String username);




    @Query("SELECT a.typeUser.idtypeuser FROM Account a WHERE a.username = :username")
    Optional<String> findIdTypeUserByUsername(@Param("username") String username);



    Optional<Account> findByUsername(String username);

    Optional<Account> findByUserIduser(String iduser);

}
