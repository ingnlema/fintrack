package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.DeleteAccountOutput;
import com.ucu.fintrack.domain.repository.BankAccountRepository;

import org.springframework.stereotype.Service;
@Service
public class DeleteAccountUseCase {

    private final BankAccountRepository bankAccountRepository;

    public DeleteAccountUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public DeleteAccountOutput execute(String idAccount) {
        if (idAccount == null || idAccount.isBlank()) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio.");
        }

        Long accountId;
        try {
            accountId = Long.parseLong(idAccount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID de la cuenta debe ser un número válido.");
        }
        if (!bankAccountRepository.findById(accountId).isPresent()) {
            throw new IllegalArgumentException("La cuenta con ID " + idAccount + " no existe.");
        }
        bankAccountRepository.deleteById(accountId);

        DeleteAccountOutput output = new DeleteAccountOutput();
        output.setMessage("Account successfully deleted");
        output.setAccountId(idAccount);

        return output;
    }
}
