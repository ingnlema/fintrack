package com.ucu.fintrack.application.dto;

import com.ucu.fintrack.domain.entities.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionInput {

    @NotNull(message = "El ID de la cuenta no puede ser nulo.")
    private Long idAccount;

    @NotBlank(message = "El nombre de la cuenta no puede estar vacío.")
    private String nameAccount;

    @NotNull(message = "El monto no puede ser nulo.")
    @Positive(message = "El monto debe ser un valor positivo.")
    private BigDecimal amount;

    @NotBlank(message = "La moneda no puede estar vacía.")
    private String currency;

    @NotNull(message = "El tipo de transacción no puede ser nulo.")
    private TransactionType transactionType;

    @NotBlank(message = "La descripción no puede estar vacía.")
    private String description;

    private LocalDateTime date;

    private String category;

    private String notes;
}
