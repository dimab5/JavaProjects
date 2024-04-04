package entities.bankAccounts;

import models.bankAccounts.BankAccountModel;
import models.operations.Operation;
import models.operations.OperationType;
import models.recipients.Recipient;
import models.results.OperationResult;

import java.util.concurrent.ThreadLocalRandom;


public class DepositBankAccount extends BankAccountBase {
    private Boolean is_access = true;

    public DepositBankAccount(BankAccountModel bankAccount) {
        super(bankAccount);
        bankAccount = bankAccount.changeCommission(0D);
    }

    public void unlock() {
        is_access = true;
    }

    public void lock() {
        is_access = false;
    }

    @Override
    public OperationResult withdrawMoney(Double amount) {
        long id = ThreadLocalRandom.current().nextLong();

        if (!is_access) {
            operationHistory.add(new Operation(
                    id,
                    OperationType.WITHDRAW,
                    amount,
                    bankAccount,
                    false,
                    OperationResult.FAIL
            ));

            return OperationResult.FAIL;
        }

        operationHistory.add(new Operation(
                id,
                OperationType.WITHDRAW,
                amount,
                bankAccount,
                false,
                OperationResult.SUCCESS
        ));

        bankAccount = bankAccount.changeAmount(bankAccount.amount() - amount);
        return OperationResult.SUCCESS;
    }

    @Override
    public OperationResult transferMoney(Double amount, BankAccountBase recipient) {
        long id = ThreadLocalRandom.current().nextLong();

        if (!is_access) {
            operationHistory.add(new Operation(
                    id,
                    OperationType.TRANSFER,
                    amount,
                    bankAccount,
                    false,
                    OperationResult.FAIL
            ));

            return OperationResult.FAIL;
        }

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

        recipient.depositMoney(amount);
        bankAccount = bankAccount.changeAmount(bankAccount.amount() - amount);
        return OperationResult.SUCCESS;
    }
}
