package co.com.sofka.bankingaccount.bankingaccount.model.transaction.impl;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CARD_PHYSICAL_BUY")
@NoArgsConstructor
public class CardPhysicalBuy extends TransactionBase {
    public CardPhysicalBuy(BigDecimal amount, BankAccount account){
        super(amount, account);
        this.setCost(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal calculateImpact(){
        return getAmount();
    }
}
