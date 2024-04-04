package services.clientServices;

import entities.clients.Client;
import entities.clients.IClient;

public class AddressCheck extends ClientDataCheckerBase {
    @Override
    public boolean checkCliendData(IClient client) {
        if (client.getAddress() == null) {
            return false;
        }

        return next.checkCliendData(client);
    }
}
