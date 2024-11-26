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

    public DeleteAccountOutput execute(Long idAccount) {
        if (idAccount == null) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio.");
        }

        if (!bankAccountRepository.findById(idAccount).isPresent()) {
            throw new IllegalArgumentException("La cuenta con ID " + idAccount + " no existe.");
        }

        bankAccountRepository.deleteById(idAccount);

        DeleteAccountOutput output = new DeleteAccountOutput();
        output.setMessage("Account successfully deleted");
        output.setAccountId(String.valueOf(idAccount));

        return output;
    }
}

