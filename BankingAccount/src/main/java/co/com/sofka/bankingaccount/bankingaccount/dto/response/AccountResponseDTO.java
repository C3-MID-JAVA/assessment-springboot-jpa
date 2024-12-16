package co.com.sofka.bankingaccount.bankingaccount.dto.response;

import co.com.sofka.bankingaccount.bankingaccount.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private BigDecimal balance;
    private List<TransactionDTO> transactions;
}
