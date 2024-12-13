package com.sofkau.usrv_accounts_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CardDTO {
    @NotBlank(message = "cardName no puede ser vacío")
    @NotNull(message = "cardName no puede ser null")
    private String cardName;
    @NotBlank(message = "cardNumber no puede ser vacío")
    @NotNull(message = "cardNumber no puede ser null")
    private String cardNumber;
    @NotBlank(message = "cardType no puede ser vacío")
    @NotNull(message = "cardType no puede ser null")
    private String cardType;
    @NotBlank(message = "cardStatus no puede ser vacío")
    @NotNull(message = "cardStatus no puede ser null")
    private String cardStatus;
    @NotBlank(message = "cardExpiryDate no puede ser vacío")
    @NotNull(message = "cardExpiryDate no puede ser null")
    private String cardExpiryDate;
    @NotBlank(message = "cardLimit no puede ser vacío")
    @NotNull(message = "cardLimit no puede ser null")
    private BigDecimal cardLimit;
    @NotBlank(message = "cardHolderName no puede ser vacío")
    @NotNull(message = "cardHolderName no puede ser null")
    private String cardHolderName;

    private AccountDTO account;
    private List<TransactionDTO> transactions;

    public CardDTO(String cardName, String cardNumber, String cardType, String cardStatus, String cardExpiryDate, BigDecimal cardLimit, String cardHolderName, AccountDTO account, List<TransactionDTO> transactions) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardStatus = cardStatus;
        this.cardExpiryDate = cardExpiryDate;
        this.cardLimit = cardLimit;
        this.cardHolderName = cardHolderName;
        this.account = account;
        this.transactions = transactions != null ? transactions : new ArrayList<>();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public BigDecimal getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(BigDecimal cardLimit) {
        this.cardLimit = cardLimit;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
