package com.ManagerTourVietNam.repository;

import com.ManagerTourVietNam.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
