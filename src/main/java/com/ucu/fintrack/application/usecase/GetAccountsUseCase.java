package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.AccountSummaryOutput;
import com.ucu.fintrack.application.dto.GetAccountsOutput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.repository.BankAccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
@Service
public class GetAccountsUseCase {

    private final BankAccountRepository bankAccountRepository;

    public GetAccountsUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public GetAccountsOutput execute() {
        List<BankAccount> accounts = bankAccountRepository.findAll();

        List<AccountSummaryOutput> accountSummaries = accounts.stream().map(account -> {
            AccountSummaryOutput summary = new AccountSummaryOutput();
            summary.setIdAccount(String.valueOf(account.getId()));
            summary.setBalance(BigDecimal.valueOf(account.getInitialBalance()));
            summary.setCurrency(account.getCurrency().name());
            summary.setUsername(account.getUser().getUsername());
            summary.setNameAccount(account.getNameAccount());
            return summary;
        }).collect(Collectors.toList());

        GetAccountsOutput output = new GetAccountsOutput();
        output.setAccounts(accountSummaries);

        return output;
    }
}
