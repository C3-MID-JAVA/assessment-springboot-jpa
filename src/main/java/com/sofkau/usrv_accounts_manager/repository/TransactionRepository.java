package com.sofkau.usrv_accounts_manager.repository;


import com.sofkau.usrv_accounts_manager.model.abstracts.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionModel, String> {

}
