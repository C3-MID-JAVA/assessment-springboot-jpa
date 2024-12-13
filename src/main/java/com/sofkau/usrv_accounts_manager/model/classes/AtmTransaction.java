package com.sofkau.usrv_accounts_manager.model.classes;

import com.sofkau.usrv_accounts_manager.Utils.ConstansTrType;
import com.sofkau.usrv_accounts_manager.model.abstracts.TransactionModel;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(ConstansTrType.ATM)
public class AtmTransaction extends TransactionModel {

    @Column(name = "atm_name")
    private String atmName;

    @Column(name = "operation_type")
    private String operationType;


    @Override
    public void processTransaction() {
        if(this.operationType.equals(ConstansTrType.ATM_DEBIT)) {
            setTransactionFee(BigDecimal.valueOf(1));

        } else {
            setTransactionFee(BigDecimal.valueOf(2));
        }
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
