package edisonrmedina.CityBank.entity.bank;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "bank")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountHolder;
    private BigDecimal balance;

    // Constructor, getters, setters, etc.

    public BankAccount() {
        this.balance = BigDecimal.ZERO;
    }

    public BankAccount(String accountHolder, BigDecimal balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void updateBalance(BigDecimal amount) {
        this.balance = amount;
    }

}
