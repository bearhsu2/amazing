package idv.kuma.amazing.register.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailMessenger implements Messenger {


    @Override
    public void send(RegisterData registerData) throws MessengerException {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("21918183b53b73", "725ffa6a76d9fe");
            }
        });

        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bearhsu2@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse("bearhsu2@gmail.com"));
            message.setSubject("Registered successfully");

            String msg = "Hi haha ！ Welcome to AmazingTalker.";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {

            throw new MessengerException("Failed to send email to: " + "bearhsu2@gmail.com");
        }
    }

}
