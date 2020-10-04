package idv.kuma.amazing.register.service;

public interface Messenger {
    void send(RegisterData registerData) throws MessengerException;
}
