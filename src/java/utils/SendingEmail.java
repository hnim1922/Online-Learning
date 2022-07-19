/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Minh
 */
public class SendingEmail {

    private String userMail;
    private String myHash;

    public SendingEmail(String userMail, String myHash) {
        this.userMail = userMail;
        this.myHash = myHash;
    }

    public void sendMail(String text) {
        final String email = "blanknamem@gmail.com";
        final String pword = "minhtran213";

        Properties prop = new Properties();

        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
            message.setSubject("Verification Mail");
            message.setText("Verification link");
            message.setText("Your Verification Link: " + "http://localhost:8080/swp391-onlinelearn/"
                    + "\nYour Password:" + text);
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("SendingEmail:: " + e);
        }
    }
}
