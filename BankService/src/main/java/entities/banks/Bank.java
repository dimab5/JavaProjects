package entities.banks;

import entities.bankAccounts.BankAccountBase;
import entities.bankAccounts.CreditBankAccount;
import entities.bankAccounts.DebitBankAccount;
import entities.bankAccounts.DepositBankAccount;
import entities.clients.Client;
import entities.clients.IClient;
import lombok.Getter;
import models.bankAccounts.BankAccountModel;
import models.banks.BankModel;
import models.clients.ClientModel;
import models.operations.Operation;
import models.operations.OperationType;
import models.recipients.Recipient;
import models.results.OperationResult;
import services.notifyUpdates.INotifyStrategy;
import services.notifyUpdates.NotifyStrategy;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Bank implements IBank {
    private BankModel bank;
    private List<BankAccountBase> bankAccounts = new ArrayList<>();
    private List<IClient> clients = new ArrayList<>();
    private List<IClient> subscribers = new ArrayList<>();
    private INotifyStrategy notifyStrategy = new NotifyStrategy();

    public Bank(BankModel bank) {
        this.bank = bank;
    }

    @Override
    public void subscribe(IClient client) {
        subscribers.add(client);
    }

    @Override
    public void updateConditions(Double percent, Double commission) {
        bank = bank.changeCommission(commission);
        bank = bank.changePercent(percent);

        subscribers.stream().forEach(client -> notifyStrategy.notifySubscribe(client));
    }

    @Override
    public void getAccrueNotification() {
        bankAccounts.forEach(BankAccountBase::accrueInterest);

    }

    @Override
    public OperationResult depositMoney(BankAccountBase bankAccount, Double amount) {
        return bankAccount.depositMoney(amount);
    }

    @Override
    public OperationResult withdrawMoney(BankAccountBase bankAccount, Double amount) {
        return bankAccount.withdrawMoney(amount);
    }

    @Override
    public OperationResult transferMoney(BankAccountBase bankAccount, BankAccountBase recipient, Double amount) {
        return bankAccount.transferMoney(amount, recipient);
    }

    @Override
    public void cancelOperation(Integer bankAccountNumber, Long operationId) {
        BankAccountBase targetBankAccount = bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getBankAccount().bankAccountNumber() == bankAccountNumber)
                .findFirst()
                .orElse(null);


        if (targetBankAccount == null) {
            return;
        }

        Operation targetOperation = targetBankAccount.getOperationHistory().stream()
                .filter(operation -> operation.id().equals(operationId))
                .findFirst()
                .orElse(null);


        if (targetOperation == null) {
            return;
        }

        targetOperation = targetOperation.cancel();
        if (targetOperation.type() == OperationType.WITHDRAW) {
            BankAccountModel bankAccount = targetBankAccount.getBankAccount();
            targetBankAccount.setBankAccount(bankAccount.changeAmount(bankAccount.amount() +
                    targetOperation.amount()));
        }
        if (targetOperation.type() == OperationType.TRANSFER) {
            BankAccountModel bankAccount = targetBankAccount.getBankAccount();
            targetBankAccount.setBankAccount(bankAccount.changeAmount(bankAccount.amount() +
                    targetOperation.amount()));

            List<Recipient> recipients = targetBankAccount.getRecipients();
            Recipient targetRecipient = recipients.stream()
                    .filter(recipient -> recipient.id().equals(operationId))
                    .findFirst()
                    .orElse(null);


            if (targetRecipient == null) {
                return;
            }

            BankAccountModel bankAccountModel = targetRecipient.recipient();
            targetBankAccount.setBankAccount(bankAccount.changeAmount(bankAccount.amount() -
                    targetOperation.amount()));
        }
    }

    @Override
    public IClient createClient(String name, String surname) {
        clients.add(new Client(name, surname));

        return clients.get(clients.size() - 1);
    }

    @Override
    public BankAccountBase createBankAccount(
            Integer bankAccountNumber,
            Double amount,
            ClientModel client,
            String type) {
        BankAccountModel bankAccountModel = new BankAccountModel(
                bankAccountNumber,
                amount,
                bank.commission(),
                bank.percent(),
                client);

        BankAccountBase bankAccount;
        if (type.equals("deposit")) {
            bankAccount = new DepositBankAccount(bankAccountModel);
        }
        else if (type.equals("debit")) {
            bankAccount = new DebitBankAccount(bankAccountModel);
        }
        else {
            bankAccount = new CreditBankAccount(bankAccountModel);
        }

        bankAccounts.add(bankAccount);
        return bankAccount;
    }
}