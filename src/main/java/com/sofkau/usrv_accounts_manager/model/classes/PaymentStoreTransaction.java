package com.sofkau.usrv_accounts_manager.model.classes;

import com.sofkau.usrv_accounts_manager.Utils.ConstansTrType;
import com.sofkau.usrv_accounts_manager.model.abstracts.TransactionModel;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(ConstansTrType.STORE_PURCHASE)
public class PaymentStoreTransaction extends TransactionModel {


    @Column(name = "market_name")
    private String marketName;


    @Override
    public void processTransaction() {
        setTransactionFee(BigDecimal.valueOf(0));
    }


    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

}
