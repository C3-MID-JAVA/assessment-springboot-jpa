package co.com.sofka.bankingaccount.bankingaccount.model.transaction.impl;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("BRANCH_DEPOSIT")
@NoArgsConstructor
public class BranchDeposit extends TransactionBase {
    public BranchDeposit(BigDecimal amount, BankAccount account){
        super(amount,account);
        this.setCost(BigDecimal.ZERO); //Without Cost
    }

    @Override
    public BigDecimal calculateImpact(){
        return getAmount();
    }
}
