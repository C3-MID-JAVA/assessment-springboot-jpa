package com.sofkau.usrv_accounts_manager.repository;

import com.sofkau.usrv_accounts_manager.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountModel, String> {
    Optional<AccountModel> findByAccountNumber(String accountNumber);

}
