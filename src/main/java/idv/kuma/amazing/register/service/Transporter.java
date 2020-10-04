package idv.kuma.amazing.register.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class Transporter {

    public void send(Message message) throws MessagingException {
        Transport.send(message);
    }
}
