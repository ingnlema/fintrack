package com.ucu.fintrack.infrastructure.config;

import com.ucu.fintrack.domain.entities.Bank;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import com.ucu.fintrack.domain.repository.BankRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner init(BankAccountRepository bankAccountRepository, BankRepository bankRepository) {
        return args -> {

            Bank bank = new Bank(null, "Banco de la Nación", "Uruguay");
            bank = bankRepository.save(bank);


            BankAccount account = new BankAccount(null, "1234567890", bank, Currency.UYU, 0.0);

            bankAccountRepository.save(account);

            System.out.println("Datos iniciales cargados.");
        };
    }
}