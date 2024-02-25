package com.email.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String to, String subject, String message) {

        boolean flag = false;

        String from="nimbolkarofficial02@gmail.com";

        // variable for gmail
        String host="smtp.gmail.com";

        // get system properties
        Properties properties=System.getProperties();
        //System.out.println("PROPERTIES"+properties);

        //setting important information to properties object

        //setting hosts
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // to get session object

        Session session=Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("nimbolkarofficial02@gmail.com","mmtbhyncxzaiaidi");
            }
        });

        session.setDebug(true);

        // composing email

        MimeMessage msg=new MimeMessage(session);

        try {

            //from
            msg.setFrom(from);

            // to
            msg.addRecipient(Message.RecipientType.TO, 	new InternetAddress(to));

            // subject
            msg.setSubject(subject);

            //message
            msg.setText(message);

            //send email
            Transport.send(msg);

            System.out.println("Email is sent successfully");

            flag=true;

        }catch(Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
