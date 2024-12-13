package com.sofkau.usrv_accounts_manager.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountDTO {

    @NotBlank(message = "accountNumber no puede ser vacío")
    @NotNull(message = "accountNumber no puede ser null")
    private String accountNumber;

    @NotBlank(message = "accountBalance no puede ser vacío")
    @NotNull(message = "accountBalance no puede ser null")
    private BigDecimal accountBalance;

    @NotBlank(message = "accountType no puede ser vacío")
    @NotNull(message = "accountType no puede ser null")
    private String accountType;

    @NotBlank(message = "accountOwner no puede ser vacío")
    @NotNull(message = "accountOwner no puede ser null")
    private String accountOwner;

    private List<TransactionDTO> transactions;
    private List<CardDTO> cards;


    public AccountDTO(List<TransactionDTO> transactions, String accountNumber, BigDecimal accountBalance, String accountType, String accountOwner, List<CardDTO> cards) {
        this.transactions = transactions != null ? transactions : new ArrayList<>();;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountOwner = accountOwner;
        this.cards = cards != null ? cards : new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }
}
