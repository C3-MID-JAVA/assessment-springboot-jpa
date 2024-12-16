package com.sofkau.usrv_accounts_manager.repository;


import com.sofkau.usrv_accounts_manager.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardModel, String> {
    boolean existsByCardCVV(String cardCVV);
    Optional<CardModel> findByCardNumber(String cardNumber);
}
