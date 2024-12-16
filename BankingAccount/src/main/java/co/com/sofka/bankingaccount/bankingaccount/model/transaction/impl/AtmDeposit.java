package co.com.sofka.bankingaccount.bankingaccount.model.transaction.impl;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("ATM_DEPOSIT")
@NoArgsConstructor
public class AtmDeposit extends TransactionBase {
    public AtmDeposit(BigDecimal amount, BankAccount account){
        super(amount, account);
        this.setCost(BigDecimal.valueOf(2)); //Established Cost 2$
    }

    @Override
    public BigDecimal calculateImpact(){
        return getAmount().subtract(getCost());
    }
}
