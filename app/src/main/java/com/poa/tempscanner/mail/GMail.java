package com.poa.tempscanner.mail;

import android.util.Log;

import com.poa.tempscanner.model.SMTPServerConfig;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GMail {



    List<String> toEmailList;
    String emailSubject;
    String emailBody;
    String attachFile;
    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    SMTPServerConfig smtpServerConfig;
    public GMail() {

    }

    public GMail(SMTPServerConfig smtpServerConfig,
                 List<String> toEmailList, String emailSubject, String emailBody, String attachFile) {
        this.smtpServerConfig = smtpServerConfig;
        this.toEmailList = toEmailList;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
        this.attachFile = attachFile;

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.host", smtpServerConfig.getServer());

        if (smtpServerConfig.isTLS()) {
            emailProperties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            emailProperties.put("mail.smtp.port", smtpServerConfig.getPort());
            emailProperties.remove("mail.smtp.socketFactory.port");
            emailProperties.remove("mail.smtp.socketFactory.class");
        }
        if (smtpServerConfig.isSSL()) {
            emailProperties.put("mail.smtp.socketFactory.port", smtpServerConfig.getPort());
            emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        emailProperties.put("mail.smtp.auth", "true");
        if (!smtpServerConfig.isSSL()) {
            emailProperties.put("mail.smtp.port", smtpServerConfig.getPort());
            emailProperties.remove("mail.smtp.socketFactory.port");
            emailProperties.remove("mail.smtp.socketFactory.class");
        }
        Log.i("GMail", "Mail server properties set.");
    }

    public GMail(SMTPServerConfig smtpServerConfig,
                 List<String> toEmailList, String emailSubject, String emailBody) {
        this.smtpServerConfig = smtpServerConfig;
        this.toEmailList = toEmailList;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.host", smtpServerConfig.getServer());

        if (smtpServerConfig.isTLS()) {
            emailProperties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            emailProperties.put("mail.smtp.port", smtpServerConfig.getPort());
            emailProperties.remove("mail.smtp.socketFactory.port");
            emailProperties.remove("mail.smtp.socketFactory.class");
        }
        if (smtpServerConfig.isSSL()) {
            emailProperties.put("mail.smtp.socketFactory.port", smtpServerConfig.getPort());
            emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        emailProperties.put("mail.smtp.auth", "true");
        if (!smtpServerConfig.isSSL()) {
            emailProperties.put("mail.smtp.port", smtpServerConfig.getPort());
            emailProperties.remove("mail.smtp.socketFactory.port");
            emailProperties.remove("mail.smtp.socketFactory.class");
        }
        Log.i("GMail", "Mail server properties set.");
    }


    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        if (smtpServerConfig.getFromEmail() != null)
            emailMessage.setFrom(new InternetAddress(smtpServerConfig.getFromEmail(), smtpServerConfig.getFromEmail()));
        else
            emailMessage.setFrom(new InternetAddress(smtpServerConfig.getUser(), smtpServerConfig.getUser()));
        for (String toEmail : toEmailList) {
            Log.i("GMail", "toEmail: " + toEmail);
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
        }

        emailMessage.setSubject(emailSubject);

        Multipart multipart = new MimeMultipart();

        MimeBodyPart attachPart = new MimeBodyPart();
        if (this.attachFile != null) {
            DataSource source = new FileDataSource(attachFile);
            attachPart.setDataHandler(new DataHandler(source));
            attachPart.setFileName("VISIT_PHOTO.jpg");

//Trick is to add the content-id header here
            attachPart.setHeader("Content-ID", "VISIT_PHOTO");
            attachPart.setHeader("X-Attachment-Id", "VISIT_PHOTO");

            multipart.addBodyPart(attachPart);
        }
        attachPart = new MimeBodyPart();
        attachPart.setContent(emailBody, "text/html");
        multipart.addBodyPart(attachPart);

        emailMessage.setContent(multipart);// for a html email
        // emailMessage.setText(emailBody);// for a text email

        Log.i("GMail", "Email Message created.");
        return emailMessage;
    }

    public void sendEmail() throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");
        transport.addTransportListener(new TransportListener() {
            @Override
            public void messageDelivered(TransportEvent transportEvent) {
                Log.i("GMail", "delivered: " + transportEvent.toString());

            }

            @Override
            public void messageNotDelivered(TransportEvent transportEvent) {
                Log.i("GMail", "not delivered: " + transportEvent.toString());

            }

            @Override
            public void messagePartiallyDelivered(TransportEvent transportEvent) {
                Log.i("GMail", "partially delivered: " + transportEvent.toString());

            }
        });
        transport.connect(smtpServerConfig.getServer(), smtpServerConfig.getUser(), smtpServerConfig.getPassword());
        Log.i("GMail", "allrecipients: " + emailMessage.getAllRecipients());
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());

        transport.close();
        Log.i("GMail", "Email sent successfully.");
    }

}
