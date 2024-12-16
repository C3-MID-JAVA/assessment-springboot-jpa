package com.sofkau.usrv_accounts_manager.model.classes;

import com.sofkau.usrv_accounts_manager.Utils.ConstansTrType;
import com.sofkau.usrv_accounts_manager.model.abstracts.TransactionModel;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(ConstansTrType.BETWEEN_ACCOUNT)
public class AccountDeposit extends TransactionModel {

    private String accountReceiver;


    @Override
    public void processTransaction() {
        setTransactionFee(BigDecimal.valueOf(1.5));
    }

    public String getAccountReceiver() {
        return accountReceiver;
    }

    public void setAccountReceiver(String accountReceiver) {
        this.accountReceiver = accountReceiver;
    }
}
