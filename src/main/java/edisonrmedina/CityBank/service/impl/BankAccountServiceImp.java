package edisonrmedina.CityBank.service.impl;

import edisonrmedina.CityBank.entity.bank.BankAccount;
import edisonrmedina.CityBank.repository.BankAccountRepository;
import edisonrmedina.CityBank.service.BankAccountService;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImp implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImp(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount getBankAccount(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank account not found"));
    }

    public void updateBankAccount(BankAccount account) {
        if (account == null || account.getId() == null) {
            throw new IllegalArgumentException("La cuenta bancaria no puede ser nula o carecer de ID");
        }

        bankAccountRepository.save(account); // Actualiza el registro en la base de datos
    }

    public BankAccount register(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }
}
