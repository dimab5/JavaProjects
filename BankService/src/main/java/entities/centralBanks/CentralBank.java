package entities.centralBanks;

import entities.banks.Bank;
import entities.banks.IBank;
import models.banks.BankModel;

import java.util.ArrayList;
import java.util.List;


public class CentralBank implements ICentralBank {
    private List<IBank> banksList = new ArrayList<>();
    private static CentralBank instance;

    public static CentralBank getInstance() {
        if (instance == null) {
            instance = new CentralBank();
        }

        return instance;
    }

    @Override
    public IBank createBank(String name, Double percent, Double commission, Double maxSum) {
        IBank bank = new Bank(new BankModel(name, percent, commission, maxSum));
        banksList.add(bank);

        return bank;
    }

    @Override
    public void notifyBanks() {
        banksList.forEach(IBank::getAccrueNotification);
    }
}
