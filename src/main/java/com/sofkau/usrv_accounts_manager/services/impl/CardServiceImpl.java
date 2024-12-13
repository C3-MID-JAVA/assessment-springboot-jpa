package com.sofkau.usrv_accounts_manager.services.impl;



import com.sofkau.usrv_accounts_manager.Utils.Utils;
import com.sofkau.usrv_accounts_manager.dto.CardDTO;
import com.sofkau.usrv_accounts_manager.mapper.DTOMapper;
import com.sofkau.usrv_accounts_manager.model.AccountModel;
import com.sofkau.usrv_accounts_manager.model.CardModel;
import com.sofkau.usrv_accounts_manager.repository.AccountRepository;
import com.sofkau.usrv_accounts_manager.repository.CardRepository;
import com.sofkau.usrv_accounts_manager.services.CardService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardServiceImpl(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }


    private String generateUniqueCvv() {
        String code;
        do {
            code = Utils.generateCvvCode();
        } while (existsCvv(code));
        return code;
    }

    @Override
    public boolean existsCvv(String cvv) {
        return cardRepository.existsByCardCVV(cvv);
    }

    @Transactional
    @Override
    public CardDTO createCard(CardDTO cardDTO) {
        CardModel cardModel = DTOMapper.toCardModel(cardDTO);
        AccountModel account  = accountRepository
                .findByAccountNumber(cardDTO.getAccount().getAccountNumber()).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cardModel.setCardCVV(
                generateUniqueCvv()
        );
        cardModel.setAccount(account);

        CardModel savedCard = cardRepository.save(cardModel);
        return DTOMapper.toCardDTO(savedCard);
    }


}
