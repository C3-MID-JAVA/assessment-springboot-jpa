package com.sofkau.usrv_accounts_manager.services.impl;

import com.sofkau.usrv_accounts_manager.dto.AccountDTO;
import com.sofkau.usrv_accounts_manager.mapper.DTOMapper;
import com.sofkau.usrv_accounts_manager.model.AccountModel;
import com.sofkau.usrv_accounts_manager.repository.AccountRepository;
import com.sofkau.usrv_accounts_manager.services.AccountService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private  final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDTO getAccount(String accountId) {
        return null;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Optional<AccountModel> account  = accountRepository
                .findByAccountNumber(accountDTO.getAccountNumber());
        if(account.isPresent()) {
            throw new RuntimeException("La cuenta ya existe");

        }

        AccountModel savedAccount = accountRepository.save(DTOMapper.toAccount(accountDTO));
        return DTOMapper.toAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO deleteAccount(String accountId) {
        return null;
    }

    @Override
    public ArrayList<AccountDTO> getAllAccounts() {
        return null;
    }
}
