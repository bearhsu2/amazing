package idv.kuma.amazing.register.service;

import org.junit.jupiter.api.Test;

class EmailMessengerTest {

    @Test
    void name() throws MessengerException {
        new EmailMessenger().send(null);
    }
}