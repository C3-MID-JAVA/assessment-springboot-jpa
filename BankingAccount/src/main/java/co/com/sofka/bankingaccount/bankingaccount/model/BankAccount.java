package co.com.sofka.bankingaccount.bankingaccount.model;

import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String accountNumber;

    private BigDecimal balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<TransactionBase> transactions;

    public void applyTransaction(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
