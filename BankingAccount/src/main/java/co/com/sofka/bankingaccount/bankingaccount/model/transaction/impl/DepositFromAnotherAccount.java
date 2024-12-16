package co.com.sofka.bankingaccount.bankingaccount.model.transaction.impl;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DEPOSIT_FROM_ANOTHER_ACCOUNT")
@NoArgsConstructor
public class DepositFromAnotherAccount extends TransactionBase {

    public DepositFromAnotherAccount(BigDecimal amount, BankAccount account) {
        super(amount, account);
        this.setCost(BigDecimal.valueOf(1.5));
    }

    @Override
    public BigDecimal calculateImpact() {
        return getAmount().subtract(getCost());
    }
}
