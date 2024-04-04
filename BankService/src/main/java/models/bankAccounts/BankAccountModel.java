package models.bankAccounts;

import lombok.Builder;
import models.clients.ClientModel;

@Builder
public record BankAccountModel(
        Integer bankAccountNumber,
        Double amount,
        Double commission,
        Double percent,
        ClientModel client
)
{
    public BankAccountModel changeAmount(Double amountMoney) {
        return builder()
                .amount(amountMoney)
                .bankAccountNumber(bankAccountNumber())
                .commission(commission())
                .percent(percent())
                .build();
    }

    public BankAccountModel changeCommission(Double amountCommission) {
        return builder()
                .amount(amount())
                .bankAccountNumber(bankAccountNumber())
                .commission(amountCommission)
                .percent(percent())
                .build();
    }

    public BankAccountModel changePercent(Double amountPercent) {
        return builder()
                .amount(amount())
                .bankAccountNumber(bankAccountNumber())
                .commission(commission())
                .percent(amountPercent)
                .build();
    }
}
