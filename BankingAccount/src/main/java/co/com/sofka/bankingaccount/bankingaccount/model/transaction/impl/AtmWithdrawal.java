package co.com.sofka.bankingaccount.bankingaccount.model.transaction.impl;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("ATM_WITHDRAWAL")
@NoArgsConstructor
public class AtmWithdrawal extends TransactionBase {
    public AtmWithdrawal(BigDecimal amount, BankAccount account){
        super(amount.multiply(BigDecimal.valueOf(-1)), account);
        this.setCost(BigDecimal.ONE); //Established Cost
    }

    @Override
    public BigDecimal calculateImpact(){
        return getAmount().subtract(getCost());
    }
}
