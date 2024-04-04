package models.operations;

import lombok.Builder;
import models.bankAccounts.BankAccountModel;
import models.results.OperationResult;


@Builder
public record Operation(
        Long id,
        OperationType type,
        Double amount,
        BankAccountModel sender,
        Boolean isCancelled,
        OperationResult result
)
{
    public Operation cancel() {
        return builder()
                .id(id())
                .type(type())
                .amount(amount())
                .sender(sender())
                .isCancelled(true)
                .result(result())
                .build();
    }
}