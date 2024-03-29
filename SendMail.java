package org.example;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import javax.mail.Session;
public class SendMail {
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("fintmailcheck@gmail.com", "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse("fintmailcheck@gmail.com", false));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(
                    "<h1>This is actual message embedded in HTML tags</h1>",
                    "text/html");
            msg.setText(body, "UTF-8");
            String filename = "\"C:\\Users\\SravyaKanamarlapudi\\Downloads\\javamailapi.pptx\"";
            String path = System.getProperty("user.dir"+"\\javamailapi.pptx\\main\\resources\\pdf.pptx");
            System.out.println(path);
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        final String fromEmail = "fintmailcheck@gmail.com"; //requires valid gmail id
        final String password = "dquddcqmzijwhjzc"; // correct password for gmail id
        final String toEmail = "fintmailcheck@gmail.com"; // can be any email id

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        SendMail.sendEmail(session, toEmail, "internship ppt reveiw\",\n" , "https://tinyurl.com/45p58b3b");
    }
}