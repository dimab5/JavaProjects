package services.clientServices;

import entities.clients.IClient;

public interface IClientDataChecker {
    IClientDataChecker setNext(IClientDataChecker next);
    boolean checkCliendData(IClient client);
}
