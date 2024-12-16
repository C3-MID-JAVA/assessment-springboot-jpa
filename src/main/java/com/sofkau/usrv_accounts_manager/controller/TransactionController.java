package com.sofkau.usrv_accounts_manager.controller;


import com.sofkau.usrv_accounts_manager.dto.AccountDTO;
import com.sofkau.usrv_accounts_manager.dto.TransactionDTO;
import com.sofkau.usrv_accounts_manager.services.AccountService;
import com.sofkau.usrv_accounts_manager.services.CardService;
import com.sofkau.usrv_accounts_manager.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/make")
    public ResponseEntity<TransactionDTO> makeTransaction(@RequestBody TransactionDTO transactionDTO) {

        var response =  transactionService.createTransaction(transactionDTO);
        return response  != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
}
