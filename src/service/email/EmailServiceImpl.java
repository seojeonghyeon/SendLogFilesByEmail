package service.email;

import dto.EmailDto;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/*
 * Javax Mail
 * Licenses : CDDL/GPLv2+CE
 * URL : https://javaee.github.io/javamail/LICENSE
 *
 * Javax Activation
 * Licenses : COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Version 1.0
 * URL : https://glassfish.dev.java.net/public/CDDLv1.0.html
 */
public class EmailServiceImpl implements EmailService{

    private volatile static EmailServiceImpl uniqueInstance;
    private Properties prop;
    private Session session;

    private static final String bodyEncoding = "UTF-8";

    public static EmailServiceImpl getInstance(EmailDto emailDto){
        if(uniqueInstance == null){
            synchronized (EmailServiceImpl.class){
                if(uniqueInstance == null) uniqueInstance = new EmailServiceImpl(emailDto);
            }
        }
        return uniqueInstance;
    }

    public EmailServiceImpl(EmailDto emailDto){
        prop = new Properties();

        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailDto.getSendEmail(), emailDto.getSendEmailPassword());
            }
        });

    }

    @Override
    public void sendEmail(EmailDto emailDto) {

        String[] attachedFiles = emailDto.getGetPaths();

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailDto.getSendEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getReceiveEmail()));
            message.setSubject(emailDto.getTitleText());
            message.setText(emailDto.getBodyText(), bodyEncoding, "html");

            MimeBodyPart attachPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            for (int i=0; i < attachedFiles.length; i++) {
                FileDataSource fileDataSource = new FileDataSource(attachedFiles[i]);
                attachPart = new MimeBodyPart();
                attachPart.setDataHandler(new DataHandler(fileDataSource));
                attachPart.setFileName(fileDataSource.getName());
                multipart.addBodyPart(attachPart);
            }

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
