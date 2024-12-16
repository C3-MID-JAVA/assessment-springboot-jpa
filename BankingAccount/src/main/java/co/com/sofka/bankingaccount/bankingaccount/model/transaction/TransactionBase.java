package co.com.sofka.bankingaccount.bankingaccount.model.transaction;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
@Getter
@Setter
@NoArgsConstructor
public abstract class TransactionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name="account_id")
    private BankAccount account;

    public TransactionBase(BigDecimal amount, BankAccount account) {
        this.amount = amount;
        this.account = account;
    }

    public abstract BigDecimal calculateImpact();
}
