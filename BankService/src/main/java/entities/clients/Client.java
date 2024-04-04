package entities.clients;

import entities.banks.IBank;
import lombok.Getter;
import lombok.Setter;
import models.clients.ClientModel;


@Setter
@Getter
public class Client implements IClient {
    private ClientModel client;
    private String address = null;
    private String passport = null;

    public Client(String name, String surname) {
        client = new ClientModel(name, surname);
    }

    @Override
    public void subscribe(IBank bank) {
        bank.subscribe(this);
    }

    @Override
    public void notifyClient() {
        System.out.println("Conditions updated!");
    }

    @Override
    public ClientModel getClient() {
        return client;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPassport() {
        return passport;
    }
}