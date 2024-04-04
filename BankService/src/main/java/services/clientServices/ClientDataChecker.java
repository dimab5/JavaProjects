package services.clientServices;

import entities.clients.IClient;

public class ClientDataChecker {
    boolean clientDataCheck(IClient client) {
        IClientDataChecker dataChecker = new AddressCheck();
        dataChecker.setNext(new PassportChecker());

        return dataChecker.checkCliendData(client);
    }
}