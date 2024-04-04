package entities.banks;

import entities.bankAccounts.BankAccountBase;
import entities.clients.Client;
import entities.clients.IClient;
import lombok.AllArgsConstructor;
import models.clients.ClientModel;
import models.results.OperationResult;


@AllArgsConstructor
public class BankProxy implements IBank {
    private IBank bank;
    private IClient client;

    @Override
    public void subscribe(IClient client) {
        bank.subscribe(client);
    }

    @Override
    public void updateConditions(Double percent, Double commission) {
        bank.updateConditions(percent, commission);
    }

    @Override
    public void getAccrueNotification() {
        bank.getAccrueNotification();
    }

    @Override
    public OperationResult depositMoney(BankAccountBase bankAccount, Double amount) {
        return bank.depositMoney(bankAccount, amount);
    }

    @Override
    public OperationResult withdrawMoney(BankAccountBase bankAccount, Double amount) {
        if (((Bank)bank).getBank().maxSum() < amount &&
                (((Client)client).getAddress() == null || ((Client)client).getPassport() == null)) {
            return OperationResult.FAIL;
        }

        return bank.withdrawMoney(bankAccount, amount);
    }

    @Override
    public OperationResult transferMoney(BankAccountBase bankAccount, BankAccountBase recipient, Double amount) {
        if (((Bank)bank).getBank().maxSum() < amount &&
                (((Client)client).getAddress() == null || ((Client)client).getPassport() == null)) {
            return OperationResult.FAIL;
        }

        return bank.transferMoney(bankAccount, recipient, amount);
    }

    @Override
    public void cancelOperation(Integer bankAccountNumber, Long operationId) {
        bank.cancelOperation(bankAccountNumber, operationId);
    }

    @Override
    public IClient createClient(String name, String surname) {
        return bank.createClient(name, surname);
    }

    @Override
    public BankAccountBase createBankAccount(
            Integer bankAccountNumber,
            Double amount,
            ClientModel client,
            String type) {
        return createBankAccount(
                bankAccountNumber,
                amount,
                client,
                type
        );
    }
}