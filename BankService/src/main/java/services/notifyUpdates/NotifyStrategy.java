package services.notifyUpdates;

import entities.clients.IClient;

public class NotifyStrategy implements INotifyStrategy {
    @Override
    public void notifySubscribe(IClient client) {
        client.notifyClient();
    }
}
