package com.sofkau.usrv_accounts_manager.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransactionDTO {

    @NotBlank(message = "description no puede ser vacío")
    @NotNull(message = "description no puede ser null")
    private String description;

    @NotBlank(message = "amount no puede ser vacío")
    @NotNull(message = "amount no puede ser null")
    private BigDecimal amount;

    @NotBlank(message = "transactionType no puede ser vacío")
    @NotNull(message = "transactionType no puede ser null")
    private String transactionType;

    @NotBlank(message = "transactionFee no puede ser vacío")
    @NotNull(message = "transactionFee no puede ser null")
    private BigDecimal transactionFee;

    @NotBlank(message = "account no puede ser vacío")
    @NotNull(message = "account no puede ser null")
    private AccountDTO account;

    @NotBlank(message = "card no puede ser vacío")
    @NotNull(message = "card no puede ser null")
    private CardDTO card;


    private String website;
    private String marketName;

    private String atmName;
    private String operationType;

    private String branchName;

    private String accountReceiver;



    public TransactionDTO(String description, BigDecimal amount, String transactionType, BigDecimal transactionFee, AccountDTO account, CardDTO card) {
        this.description = description;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionFee = transactionFee;
        this.account = account;
        this.card = card;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public CardDTO getCard() {
        return card;
    }

    public void setCard(CardDTO card) {
        this.card = card;
    }


    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getAccountReceiver() {
        return accountReceiver;
    }

    public void setAccountReceiver(String accountReceiver) {
        this.accountReceiver = accountReceiver;
    }
}
