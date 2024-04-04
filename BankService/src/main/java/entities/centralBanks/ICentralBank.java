package entities.centralBanks;

import entities.banks.IBank;
import models.banks.BankModel;
import models.results.OperationResult;

public interface ICentralBank {
    IBank createBank(String name, Double percent, Double commission, Double maxSum);
    void notifyBanks();
}
