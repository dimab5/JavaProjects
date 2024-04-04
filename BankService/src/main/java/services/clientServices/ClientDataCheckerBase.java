package services.clientServices;

import entities.clients.IClient;

public abstract class ClientDataCheckerBase implements IClientDataChecker {
    protected IClientDataChecker next;
    public IClientDataChecker setNext(IClientDataChecker next) {
        this.next = next;
        return next;
    }

    public abstract boolean checkCliendData(IClient client);
}
