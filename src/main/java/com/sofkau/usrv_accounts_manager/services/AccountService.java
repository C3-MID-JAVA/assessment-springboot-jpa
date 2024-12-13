package com.sofkau.usrv_accounts_manager.services;

import com.sofkau.usrv_accounts_manager.dto.AccountDTO;

import java.util.ArrayList;

public interface AccountService {
    AccountDTO getAccount(String accountId);
    AccountDTO createAccount(AccountDTO account);
    AccountDTO updateAccount(AccountDTO account);
    AccountDTO deleteAccount(String accountId);
    ArrayList<AccountDTO> getAllAccounts();
}
