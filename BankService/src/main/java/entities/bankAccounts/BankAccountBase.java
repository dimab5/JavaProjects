package entities.bankAccounts;

import lombok.Getter;
import lombok.Setter;
import models.bankAccounts.BankAccountModel;
import models.operations.Operation;
import models.operations.OperationType;
import models.recipients.Recipient;
import models.results.OperationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Getter
@Setter
public abstract class BankAccountBase {
    protected BankAccountModel bankAccount;
    protected Double percents = 0D;
    protected Double commission = 0D;
    protected List<Operation> operationHistory = new ArrayList<>();
    protected List<Recipient> recipients = new ArrayList<>();

    public BankAccountBase(BankAccountModel bankAccount) {
        this.bankAccount = bankAccount;
    }

    public OperationResult depositMoney(Double amount) {
        bankAccount = bankAccount.changeAmount(bankAccount.amount() + amount);

        long id = ThreadLocalRandom.current().nextLong();
        operationHistory.add(new Operation(
                id,
                OperationType.DEPOSIT,
                amount,
                bankAccount,
                false,
                OperationResult.SUCCESS
        ));

        return OperationResult.SUCCESS;
    }

    public void accrueInterest() {
        bankAccount = bankAccount.changeAmount(bankAccount.amount() - commission);
        bankAccount = bankAccount.changeAmount(bankAccount.amount() + percents);
    }

    public abstract OperationResult withdrawMoney(Double amount);

    public abstract OperationResult transferMoney(Double amount, BankAccountBase recipient);
}