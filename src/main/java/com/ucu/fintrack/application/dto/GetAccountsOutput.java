package com.ucu.fintrack.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetAccountsOutput {
    private List<AccountSummaryOutput> accounts;
}
