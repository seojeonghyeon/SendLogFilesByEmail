package service.email;

import dto.EmailDto;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.LinkedList;
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
    private Session session;

    private static final String bodyEncoding = "UTF-8";
    private LinkedList<String> getPaths;

    public static EmailServiceImpl getInstance(){
        if(uniqueInstance == null){
            synchronized (EmailServiceImpl.class){
                if(uniqueInstance == null) uniqueInstance = new EmailServiceImpl();
            }
        }
        return uniqueInstance;
    }

    @Override
    public void sendEmail(EmailDto emailDto) {
        try {
            LinkedList<String> attachedFiles = emailDto.getGetPaths();
            session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailDto.getSendEmail(), emailDto.getSendEmailPassword());
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailDto.getSendEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getReceiveEmail()));
            message.setSubject(emailDto.getTitleText());
            message.setText(emailDto.getBodyText(), bodyEncoding, "html");

            MimeBodyPart attachPart;
            Multipart multipart = new MimeMultipart();

            for (String attachedFile : attachedFiles) {
                getFileList(attachedFile);
                for(String filePath : getPaths) {
                    FileDataSource fileDataSource = new FileDataSource(filePath);
                    attachPart = new MimeBodyPart();
                    attachPart.setDataHandler(new DataHandler(fileDataSource));
                    attachPart.setFileName(fileDataSource.getName());
                    multipart.addBodyPart(attachPart);
                }
            }
            message.setContent(multipart);
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private void getFileList(String getOpenPathName){
        getPaths = new LinkedList<>();
        File getOpenPath = new File(getOpenPathName);
        File[] fileList = getOpenPath.listFiles();
        for(File file : fileList){
            if(file.isFile()){
                String getFilePath = file.getPath();
                if(file.getName().charAt(0)!='.') {
                    getPaths.add(getFilePath);
                    System.out.println("fileName : " + getFilePath);
                }
            }
        }
    }
    public Properties getProperties(){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

}
