package edisonrmedina.CityBank.controller;

import edisonrmedina.CityBank.dto.BankAccountDTO;
import edisonrmedina.CityBank.dto.CreateBankAccountDTO;
import edisonrmedina.CityBank.mapper.Mapper;
import edisonrmedina.CityBank.service.BankAccountService;
import edisonrmedina.CityBank.service.impl.BankAccountServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    private final BankAccountServiceImp bankAccountServiceImp;

    public BankAccountController(BankAccountServiceImp bankAccountServiceImp) {
        this.bankAccountServiceImp = bankAccountServiceImp;
    }


    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDTO> getBankAccount(@PathVariable Long id) {

        return bankAccountServiceImp.getBankAccount(id) != null
                ? ResponseEntity.ok(Mapper.bankAccountToDTO(bankAccountServiceImp.getBankAccount(id)))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<BankAccountDTO> createBankAccount(@Valid @RequestBody CreateBankAccountDTO createBankAccountDTO) {
        var bankAccountDTO = BankAccountDTO.builder().accountHolder(createBankAccountDTO.getAccountHolder()).balance(createBankAccountDTO.getBalance()).build();
        var createdBankAccount = bankAccountServiceImp.register(Mapper.dtoToBankAccount(bankAccountDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(Mapper.bankAccountToDTO(createdBankAccount));
    }

}
