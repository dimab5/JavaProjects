package entities.banks;

import entities.bankAccounts.BankAccountBase;
import entities.clients.IClient;
import models.clients.ClientModel;
import models.results.OperationResult;


public interface IBank {
    void subscribe(IClient client);
    void updateConditions(Double percent, Double commission);
    void getAccrueNotification();
    OperationResult depositMoney(BankAccountBase bankAccount, Double amount);
    OperationResult withdrawMoney(BankAccountBase bankAccount, Double amount);
    OperationResult transferMoney(BankAccountBase bankAccount,
                                  BankAccountBase recipient,
                                  Double amount);
    void cancelOperation(Integer bankAccountNumber, Long operationId);
    IClient createClient(String name, String surname);
    BankAccountBase createBankAccount(
            Integer bankAccountNumber,
            Double amount,
            ClientModel client,
            String type);
}
