package com.sofkau.usrv_accounts_manager.services.impl;

import com.sofkau.usrv_accounts_manager.dto.TransactionDTO;
import com.sofkau.usrv_accounts_manager.mapper.DTOMapper;
import com.sofkau.usrv_accounts_manager.model.AccountModel;
import com.sofkau.usrv_accounts_manager.model.CardModel;
import com.sofkau.usrv_accounts_manager.model.abstracts.TransactionModel;
import com.sofkau.usrv_accounts_manager.repository.AccountRepository;
import com.sofkau.usrv_accounts_manager.repository.CardRepository;
import com.sofkau.usrv_accounts_manager.repository.TransactionRepository;
import com.sofkau.usrv_accounts_manager.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;


    public TransactionServiceImpl(TransactionRepository transactionRepository, CardRepository cardRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        TransactionModel  transactionModel = DTOMapper.toTransactionModel(transactionDTO);

        CardModel  card = cardRepository
                .findByCardNumber(transactionDTO.getCard().getCardNumber())
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
        AccountModel account  = accountRepository
                .findByAccountNumber(transactionDTO.getAccount().getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        transactionModel.processTransaction();
        account.setBalance(account.getBalance().subtract(transactionModel.getAmount()).subtract(transactionModel.getTransactionFee()));
        transactionModel.setAccount(account);
        transactionModel.setCard(card);

        TransactionModel savedTransaction = transactionRepository.save(transactionModel);

        return DTOMapper.toTransactionDTO(savedTransaction);
    }


}
