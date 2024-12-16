package com.sofkau.usrv_accounts_manager.model;

import com.sofkau.usrv_accounts_manager.model.abstracts.TransactionModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "card_id")
    private String id;

    @Column(name = "card_name", nullable = false, length = 50)
    private String cardName;

    @Column(name = "card_number",  nullable = false, length = 16, unique = true)
    private String cardNumber;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "card_status", nullable = false)
    private String cardStatus;

    @Column(name = "card_expiry_date",nullable = false)
    private String cardExpiryDate;

    @Column(name = "card_cvv", nullable = false, unique = true, length = 4)
    private String cardCVV;

    @Column(name = "card_limit", nullable = false)
    private BigDecimal cardLimit;

    @Column(name = "card_holder_name", nullable = false)
    private String cardHolderName;

    @ManyToOne
    @JoinColumn(name = "account_number",referencedColumnName = "account_number", nullable = false)
    private  AccountModel account;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionModel> transactions;

}
