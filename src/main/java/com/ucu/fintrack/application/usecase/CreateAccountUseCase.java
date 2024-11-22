package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.CreateAccountInput;
import com.ucu.fintrack.application.dto.CreateAccountOutput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.User;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import com.ucu.fintrack.domain.repository.UserRepository;


import java.math.BigDecimal;
import org.springframework.stereotype.Service;
@Service
public class CreateAccountUseCase {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public CreateAccountUseCase(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public CreateAccountOutput execute(CreateAccountInput input) {
        if (input.getInitialBalance() == null || input.getInitialBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El balance inicial no puede ser negativo o nulo.");
        }
        if (input.getCurrency() == null || input.getCurrency().isBlank()) {
            throw new IllegalArgumentException("La moneda es obligatoria.");
        }
        if (input.getUsername() == null || input.getUsername().isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio.");
        }
        if (input.getNameAccount() == null || input.getNameAccount().isBlank()) {
            throw new IllegalArgumentException("El nombre de la cuenta es obligatorio.");
        }

        Currency currency;
        try {
            currency = Currency.valueOf(input.getCurrency().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La moneda proporcionada no es válida: " + input.getCurrency());
        }

        User user = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("El usuario especificado no existe."));

        BankAccount account = new BankAccount();
        account.setInitialBalance(input.getInitialBalance().doubleValue());
        account.setCurrency(currency);
        account.setNameAccount(input.getNameAccount());
        account.setUser(user);

        BankAccount savedAccount = bankAccountRepository.save(account);

        CreateAccountOutput output = new CreateAccountOutput();
        output.setInitialBalance(BigDecimal.valueOf(savedAccount.getInitialBalance()));
        output.setCurrency(savedAccount.getCurrency().name());
        output.setUsername(user.getUsername());
        output.setNameAccount(savedAccount.getNameAccount());

        return output;
    }
}
