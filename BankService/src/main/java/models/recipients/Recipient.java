package models.recipients;

import models.bankAccounts.BankAccountModel;
import models.operations.OperationType;

public record Recipient(
        Long id,
        BankAccountModel recipient
)
{ }
