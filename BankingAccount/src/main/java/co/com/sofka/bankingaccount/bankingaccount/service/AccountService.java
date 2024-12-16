package co.com.sofka.bankingaccount.bankingaccount.service;

import co.com.sofka.bankingaccount.bankingaccount.dto.TransactionDTO;
import co.com.sofka.bankingaccount.bankingaccount.dto.response.AccountResponseDTO;
import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import co.com.sofka.bankingaccount.bankingaccount.repository.AccountRepository;
import co.com.sofka.bankingaccount.bankingaccount.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public BankAccount getAccount(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not founded!"));
    }

    public BigDecimal executeTransaction(Long id, TransactionBase transaction){
        BankAccount account = getAccount(id);

        BigDecimal impact = transaction.calculateImpact();
        account.applyTransaction(impact);

        transaction.setAccount(account);
        transactionRepository.save(transaction);
        accountRepository.save(account);

        return account.getBalance();
    }

    public AccountResponseDTO getAccountWithTransaction() {
        BankAccount cuenta = getAccount(Long.valueOf(1));

        List<TransactionDTO> transacciones = cuenta.getTransactions().stream()
                .map(t -> new TransactionDTO(t.getId(), t.getClass().getSimpleName(), t.getAmount(), t.getCost()))
                .collect(Collectors.toList());

        return new AccountResponseDTO(cuenta.getId(), cuenta.getBalance(), transacciones);
    }
}
