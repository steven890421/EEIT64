package com.example.util;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class EmailSender {
	
	public void sendMail(String email) {

        // SMTP 設定
        String host = "smtp.gmail.com";
        int port = 587;
        String username = "steven890421@gmail.com";
        String password = "hfwvsixqxdtzygss";

        // 收件人和寄件人
        String to = email;
        String from = "steven890421@gmail.com";

        // 郵件主旨和內容
        String resetPasswordUrl = "http://localhost:8080/updatePW?email=" + email;
        String subject = "食材行情小幫手 重設密碼";
        String content = "請點選以下連結重設密碼 <br/><a href=\"" + resetPasswordUrl + "\">重設密碼</a>";

        // 設定 SMTP 屬性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // 建立 Session 物件
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 建立 MimeMessage 物件
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=UTF-8");

            // 發送郵件
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
	}
    
}
