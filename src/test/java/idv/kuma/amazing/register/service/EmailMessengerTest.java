package idv.kuma.amazing.register.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.io.IOException;

class EmailMessengerTest {

    @Test
    void name() throws MessengerException, MessagingException, IOException {
        Transporter mockedTransporter = Mockito.mock(Transporter.class);

        new EmailMessenger(mockedTransporter).send(null);


        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(mockedTransporter).send(messageCaptor.capture());
        Message actual = messageCaptor.getValue();

        Address[] from = actual.getFrom();
        Assert.assertEquals(1, from.length);
        Assert.assertEquals("bearhsu2@gmail.com", from[0].toString());

        Address[] recipients = actual.getAllRecipients();
        Assert.assertEquals(1, recipients.length);
        Assert.assertEquals("bearhsu2@gmail.com", recipients[0].toString());

        Assert.assertEquals("Registered successfully", actual.getSubject());
        Assert.assertEquals("Hi haha ！ Welcome to AmazingTalker.", ((Multipart) actual.getContent()).getBodyPart(0).getContent());

    }
}