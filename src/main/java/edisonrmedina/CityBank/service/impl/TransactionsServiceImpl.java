package edisonrmedina.CityBank.service.impl;

import edisonrmedina.CityBank.entity.bank.BankAccount;
import edisonrmedina.CityBank.entity.transaction.Transaction;
import edisonrmedina.CityBank.entity.transaction.TransactionCostStrategy.TransactionCostStrategy;
import edisonrmedina.CityBank.enums.TransactionType;
import edisonrmedina.CityBank.repository.TransactionRepository;
import edisonrmedina.CityBank.service.BankAccountService;
import edisonrmedina.CityBank.service.TransactionsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final BankAccountServiceImp bankAccountService;
    private final Map<TransactionType, TransactionCostStrategy> costStrategies;
    private final TransactionRepository transactionRepository;

    public TransactionsServiceImpl(BankAccountServiceImp bankAccountService, Map<TransactionType, TransactionCostStrategy> costStrategies, TransactionRepository transactionRepository) {
        this.bankAccountService = bankAccountService;
        this.costStrategies = costStrategies;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {

        // Validar si la cuenta bancaria existe
        BankAccount account = bankAccountService.getBankAccount(transaction.getBankAccount().getId());
        if (account == null) {
            throw new IllegalArgumentException("Cuenta bancaria no encontrada");
        }

        // Determinar el costo de la transacción
        TransactionCostStrategy strategy = costStrategies.get(transaction.getType());
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de transacción no soportado");
        }

        BigDecimal transactionCost = strategy.calculateCost(transaction.getAmount());
        transaction.setTransactionCost(transactionCost);

        // Calcular el nuevo saldo según el tipo de transacción
        BigDecimal newBalance = switch (transaction.getType()) {
            case WITHDRAW_ATM, PURCHASE_PHYSICAL, PURCHASE_ONLINE ->
                    account.getBalance().subtract(transaction.getAmount().add(transactionCost));
            case DEPOSIT_BRANCH, DEPOSIT_ATM, DEPOSIT_ACCOUNT ->
                    account.getBalance().add(transaction.getAmount().subtract(transactionCost));
        };

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la transacción");
        }

        account.updateBalance(newBalance);

        // Persistir la transacción y actualizar la cuenta bancaria
        transaction.setBankAccount(account);
        transactionRepository.save(transaction);
        bankAccountService.updateBankAccount(account);

        return transaction;
    }
}
