package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.GetAccountOutput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.repository.BankAccountRepository;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class GetAccountUseCase {

    private final BankAccountRepository bankAccountRepository;

    public GetAccountUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public GetAccountOutput execute(Long idAccount) {
        if (idAccount == null || idAccount <= 0) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio y debe ser un número positivo.");
        }

        BankAccount account = bankAccountRepository.findById(idAccount)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta especificada no existe."));

        GetAccountOutput output = new GetAccountOutput();
        output.setIdAccount(String.valueOf(account.getId()));
        output.setBalance(BigDecimal.valueOf(account.getInitialBalance()));
        output.setCurrency(account.getCurrency().name());
        output.setUsername(account.getUser().getUsername());
        output.setNameAccount(account.getNameAccount());

        return output;
    }
}
