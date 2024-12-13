package edisonrmedina.CityBank.entity.transaction;

import edisonrmedina.CityBank.entity.bank.BankAccount;
import edisonrmedina.CityBank.enums.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionType type; // "DEPOSIT", "WITHDRAWAL", "PURCHASE"
    private BigDecimal amount;
    private BigDecimal transactionCost;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;

    // Constructor, getters, setters, etc.

    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(TransactionType type, BigDecimal amount, BigDecimal transactionCost, BankAccount bankAccount) {
        this.type = type;
        this.amount = amount;
        this.transactionCost = transactionCost;
        this.bankAccount = bankAccount;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTransactionCost() {
        return transactionCost;
    }

    public void setTransactionCost(BigDecimal transactionCost) {
        this.transactionCost = transactionCost;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
