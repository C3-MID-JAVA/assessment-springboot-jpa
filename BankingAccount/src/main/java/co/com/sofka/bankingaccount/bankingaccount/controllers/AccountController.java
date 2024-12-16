package co.com.sofka.bankingaccount.bankingaccount.controllers;

import co.com.sofka.bankingaccount.bankingaccount.dto.request.TransactionRequestDTO;
import co.com.sofka.bankingaccount.bankingaccount.dto.response.AccountResponseDTO;
import co.com.sofka.bankingaccount.bankingaccount.dto.response.AccountResponseTransactionDTO;
import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.impl.*;
import co.com.sofka.bankingaccount.bankingaccount.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/bankAccount")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<AccountResponseTransactionDTO> executeTransaction(@RequestBody @Valid TransactionRequestDTO transactionDTO){
        BankAccount account = accountService.getAccount(transactionDTO.getId());

        // Crear instancia específica según el tipo
        TransactionBase transaccion;
        switch (transactionDTO.getType()) {
            case "BRANCH_DEPOSIT":
                transaccion = new BranchDeposit(transactionDTO.getAmount(), account);
                break;
            case "ATM_DEPOSIT":
                transaccion = new AtmDeposit(transactionDTO.getAmount(), account);
                break;
            case "ATM_WITHDRAWAL":
                transaccion = new AtmWithdrawal(transactionDTO.getAmount(), account);
                break;
            case "DEPOSIT_FROM_ANOTHER_ACCOUNT":
                transaccion = new DepositFromAnotherAccount(transactionDTO.getAmount(), account);
                break;
            case "CARD_PHYSICAL_BUY":
                transaccion = new CardPhysicalBuy(transactionDTO.getAmount(), account);
                break;
            case "CARD_WEB_BUY":
                transaccion = new CardWebBuy(transactionDTO.getAmount(), account);
                break;
            default:
                throw new IllegalArgumentException("Tipo de transacción no válido");
        }

        BigDecimal saldoActualizado = accountService.executeTransaction(account.getId(), transaccion);
        return new ResponseEntity<>(new AccountResponseTransactionDTO(account.getId(),saldoActualizado), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AccountResponseDTO> getAccountWithTransaction() {
        return new ResponseEntity<>(accountService.getAccountWithTransaction(), HttpStatus.OK);
    }
}
