package com.ManagerTourVietNam.repository;

import com.ManagerTourVietNam.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

}
