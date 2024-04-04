import entities.bankAccounts.BankAccountBase;
import entities.bankAccounts.CreditBankAccount;
import entities.bankAccounts.DepositBankAccount;
import entities.banks.IBank;
import entities.centralBanks.CentralBank;
import entities.centralBanks.ICentralBank;
import entities.clients.Client;
import entities.clients.IClient;
import models.bankAccounts.BankAccountModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class lab1Tests {
    @Test
    void testDepositMoney() {
        ICentralBank centralBank = CentralBank.getInstance();

        IBank bank = centralBank.createBank(
                "bank",
                10D,
                2D,
                100000D);

        IClient sender = new Client("dima", "borisov");

        BankAccountBase bankAccountSender = new DepositBankAccount(
                new BankAccountModel(
                        1,
                        0D,
                        10D,
                        2D,
                        sender.getClient()));

        bank.depositMoney(bankAccountSender, 1000D);
        assertEquals(bankAccountSender.getBankAccount().amount(), 1000D);
    }

    @Test
    void testWithdrawMoney() {
        ICentralBank centralBank = CentralBank.getInstance();

        IBank bank = centralBank.createBank(
                "bank",
                10D,
                2D,
                100000D);

        IClient sender = new Client("dima", "borisov");

        BankAccountBase bankAccountSender = new CreditBankAccount(
                new BankAccountModel(
                        1,
                        0D,
                        10D,
                        2D,
                        sender.getClient()));

        bank.withdrawMoney(bankAccountSender, 1000D);
        assertEquals(bankAccountSender.getBankAccount().amount(), -1000D);
    }

    @Test
    void testTransferMoney() {
        ICentralBank centralBank = CentralBank.getInstance();

        IBank bank = centralBank.createBank(
                "bank",
                10D,
                2D,
                100000D);

        IClient sender = new Client("dima", "borisov");
        IClient recepient = new Client("neDima", "neBorisov");

        BankAccountBase bankAccountSender = new DepositBankAccount(
                new BankAccountModel(
                        1,
                        1000D,
                        10D,
                        2D,
                        sender.getClient()));

        BankAccountBase bankAccountRecepient = new DepositBankAccount(
                new BankAccountModel(
                        1,
                        1000D,
                        10D,
                        2D,
                        sender.getClient()));

        bank.transferMoney(bankAccountSender, bankAccountRecepient, 1000D);
        assertEquals(bankAccountSender.getBankAccount().amount(), 0);
        assertEquals(bankAccountRecepient.getBankAccount().amount(), 2000D);
    }

    @Test
    void testCancelOperation() {
        ICentralBank centralBank = CentralBank.getInstance();

        IBank bank = centralBank.createBank(
                "bank",
                10D,
                2D,
                100000D);

        IClient sender = new Client("dima", "borisov");

        BankAccountBase bankAccountSender = new CreditBankAccount(
                new BankAccountModel(
                        1,
                        0D,
                        10D,
                        2D,
                        sender.getClient()));

        bank.withdrawMoney(bankAccountSender, 1000D);
        assertEquals(bankAccountSender.getBankAccount().amount(), -1000D);

        Long id = bankAccountSender.getOperationHistory().get(
                bankAccountSender.getOperationHistory().size() - 1).id();

        bank.cancelOperation(1, id);
    }
}