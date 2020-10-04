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

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

class EmailMessengerTest {


    public Transporter mockedTransporter;


    @Test
    void When_Error_Happened_Then_Throw_Exception() throws MessengerException, MessagingException, IOException {
        mockedTransporter = Mockito.mock(Transporter.class);
        doThrow(new MessagingException("FAKE_EXCEPTION")).when(mockedTransporter).send(any(Message.class));

        try {
            new EmailMessenger(mockedTransporter).send(
                    createRegisterData("Kuma", "SOME@EMAIL.ADDRESS")
            );
            fail("Should throw exception");
        } catch (MessengerException e) {
            Assert.assertEquals("Failed to send email to SOME@EMAIL.ADDRESS", e.getMessage());

        }


    }


    private RegisterData createRegisterData(String name, String toAddress) {
        return new RegisterData(name, toAddress, "AnyPassword", "AnyPassword");
    }


    @Test
    void When_All_Ok_Then_Email_Is_Sent() throws MessengerException, MessagingException, IOException {
        mockedTransporter = Mockito.mock(Transporter.class);

        new EmailMessenger(mockedTransporter).send(
                createRegisterData("Kuma", "SOME@EMAIL.ADDRESS")
        );

        check("bearhsu2@gmail.com",
                "SOME@EMAIL.ADDRESS",
                "Registered successfully",
                "Hi Kuma! Welcome to AmazingTalker.");

    }


    private void check(String fromAddress, String toAddress, String subject, String content) throws MessagingException, IOException {
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);

        Mockito.verify(mockedTransporter).send(messageCaptor.capture());
        Message actual = messageCaptor.getValue();

        Address[] from = actual.getFrom();
        Assert.assertEquals(1, from.length);
        Assert.assertEquals(fromAddress, from[0].toString());

        Address[] recipients = actual.getAllRecipients();
        Assert.assertEquals(1, recipients.length);
        Assert.assertEquals(toAddress, recipients[0].toString());

        Assert.assertEquals(subject, actual.getSubject());
        Assert.assertEquals(content, ((Multipart) actual.getContent()).getBodyPart(0).getContent());
    }
}