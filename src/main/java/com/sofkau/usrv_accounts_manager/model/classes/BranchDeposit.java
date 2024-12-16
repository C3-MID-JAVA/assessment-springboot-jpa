package com.sofkau.usrv_accounts_manager.model.classes;

import com.sofkau.usrv_accounts_manager.Utils.ConstansTrType;
import com.sofkau.usrv_accounts_manager.model.abstracts.TransactionModel;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(ConstansTrType.BRANCH_DEPOSIT)
public class BranchDeposit extends TransactionModel {
    @Column(name = "branch_name")
    private String branchName;

    @Override
    public void processTransaction() {
        setTransactionFee(BigDecimal.valueOf(0));
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
