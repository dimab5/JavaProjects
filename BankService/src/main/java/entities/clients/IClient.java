package entities.clients;

import entities.banks.IBank;
import models.clients.ClientModel;


public interface IClient {
    void subscribe(IBank bank);
    void notifyClient();
    ClientModel getClient();
    String getAddress();
    String getPassport();
}
