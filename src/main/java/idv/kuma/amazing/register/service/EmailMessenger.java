package idv.kuma.amazing.register.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailMessenger implements Messenger {

    private Transporter transportExecutor = new Transporter();


    public EmailMessenger() {
    }


    EmailMessenger(Transporter transportExecutor) {
        this.transportExecutor = transportExecutor;
    }


    @Override
    public void send(RegisterData registerData) throws MessengerException {


        Session session = createSession();

        try {
            Message message = createMessage(session, "bearhsu2@gmail.com", "bearhsu2@gmail.com", "Registered successfully", "Hi haha ！ Welcome to AmazingTalker.");

            transportExecutor.send(message);

        } catch (MessagingException e) {

            throw new MessengerException("Failed to send email to " + "bearhsu2@gmail.com");
        }
    }


    private Session createSession() {
        Properties prop = generateProperties();
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("21918183b53b73", "725ffa6a76d9fe");
            }
        });

        session.setDebug(true);
        return session;
    }


    private Message createMessage(Session session, String from, String to, String subject, String msg) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        return message;
    }


    private Properties generateProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        return prop;
    }

}
