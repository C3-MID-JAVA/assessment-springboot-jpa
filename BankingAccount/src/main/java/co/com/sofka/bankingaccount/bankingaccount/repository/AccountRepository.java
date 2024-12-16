package co.com.sofka.bankingaccount.bankingaccount.repository;

import co.com.sofka.bankingaccount.bankingaccount.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {
}
