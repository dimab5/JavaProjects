package services.notifyUpdates;

import entities.clients.IClient;

public interface INotifyStrategy {
    void notifySubscribe(IClient client);
}
