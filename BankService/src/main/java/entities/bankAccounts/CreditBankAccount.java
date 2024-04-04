package entities.bankAccounts;

import models.bankAccounts.BankAccountModel;
import models.operations.Operation;
import models.operations.OperationType;
import models.recipients.Recipient;
import models.results.OperationResult;

import java.util.concurrent.ThreadLocalRandom;

public class CreditBankAccount extends BankAccountBase {
    public CreditBankAccount(BankAccountModel bankAccount) {
        super(bankAccount);
        bankAccount.changePercent(0D);
    }

    @Override
    public OperationResult withdrawMoney(Double amount) {
        bankAccount = bankAccount.changeAmount(bankAccount.amount() - amount);

        long id = ThreadLocalRandom.current().nextLong();
        operationHistory.add(new Operation(
                id,
                OperationType.WITHDRAW,
                amount,
                bankAccount,
                false,
                OperationResult.SUCCESS
        ));

        return OperationResult.SUCCESS;
    }

    @Override
    public OperationResult transferMoney(Double amount, BankAccountBase recipient) {
        recipient.depositMoney(amount);
        bankAccount = bankAccount.changeAmount(bankAccount.amount() - amount);

        long id = ThreadLocalRandom.current().nextLong();
        operationHistory.add(new Operation(
                id,
                OperationType.TRANSFER,
                amount,
                bankAccount,
                false,
                OperationResult.SUCCESS
        ));

        recipients.add(new Recipient(
                id,
                recipient.getBankAccount()
        ));

        return OperationResult.SUCCESS;
    }
}
