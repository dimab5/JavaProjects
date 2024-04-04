package services.clientServices;

import entities.clients.Client;
import entities.clients.IClient;

public class PassportChecker extends ClientDataCheckerBase {
    @Override
    public boolean checkCliendData(IClient client) {
        if (client.getPassport() == null) {
            return false;
        }

        return next.checkCliendData(client);
    }
}
