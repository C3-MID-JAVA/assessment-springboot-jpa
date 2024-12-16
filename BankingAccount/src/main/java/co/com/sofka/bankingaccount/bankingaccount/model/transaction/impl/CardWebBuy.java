package co.com.sofka.bankingaccount.bankingaccount.model.transaction.impl;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import co.com.sofka.bankingaccount.bankingaccount.model.transaction.TransactionBase;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CARD_WEB_BUY")
@NoArgsConstructor
public class CardWebBuy extends TransactionBase {
    public CardWebBuy (BigDecimal amount, BankAccount account){
        super(amount.multiply(BigDecimal.valueOf(-1)), account);
        this.setCost(BigDecimal.valueOf(5)); //Value for secure
    }

    @Override
    public BigDecimal calculateImpact(){
        return getAmount().subtract(getCost());
    }
}
