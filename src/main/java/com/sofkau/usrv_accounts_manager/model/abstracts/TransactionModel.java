package com.sofkau.usrv_accounts_manager.model.abstracts;


import com.sofkau.usrv_accounts_manager.model.AccountModel;
import com.sofkau.usrv_accounts_manager.model.CardModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name = "transactions")
public abstract class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id")
    private String id;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "ammount")
    private BigDecimal amount;

    @Column(name = "transaction_type_showed", nullable = false)
    private String transactionType;

    @Column(name = "transaction_fee", nullable = false)
    private BigDecimal transactionFee;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }

    @ManyToOne
    @JoinColumn(name = "account_number",referencedColumnName = "account_number", nullable = false)
    private AccountModel account;

    @ManyToOne
    @JoinColumn(name = "card_number", referencedColumnName = "card_number",nullable = false)
    private CardModel card;

    public abstract void processTransaction();

}
