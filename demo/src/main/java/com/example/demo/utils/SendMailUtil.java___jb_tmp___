package com.example.demo.utils;

import com.example.demo.model.EmailTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * javamail发送邮件，发送的邮箱需要开启授权，密码为授权码，否则报如下错误；
 * 异常javax.mail.AuthenticationFailedException: 535 Error: aut
 */
public class SendMailUtil {
    private static Logger log = LoggerFactory.getLogger(SendMailUtil.class);

    public static boolean send(EmailTO to) {
        final String user = to.getSender();
        final String password = to.getPassword();
        log.info("SendMailUtils >>>> Start Send Mail");
        //step1:创建一个程序与邮件服务会话对象 Session
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", to.getHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "25");
        //step2:指定验证为true
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.timeout", "1000");

        //step3:验证账号及密码，密码需要第三方授权码
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try {
            //step4:创建一个Message,它相当于是邮件内容
            MimeMessage message = new MimeMessage(session);//创建默认的MimeMessage
            // senderNick=javax.mail.internet.MimeUtility.encodeText("Juihai");//设置发件人昵称
            //step5:设置发送者
            message.setFrom(new InternetAddress(user));
            // message.addRecipients(Message.RecipientType.TO,new InternetAddress("接收者邮箱"));//创建单个收件人
            //step6:组装地址
            String[] tos = to.getToAdress();
            if (null != tos && tos.length != 0) {
                InternetAddress[] toAddress = new InternetAddress[tos.length];
                for (int i = 0; i < tos.length; i++) {
                    toAddress[i] = new InternetAddress(tos[i]);
                }
                //step7:设置发送方式与接收者
                message.setRecipients(Message.RecipientType.TO, toAddress);
                //step8:设置主题
                message.setSubject(to.getTitle());//设置邮件主题
                //message.setText(content);//发送纯文本内容
                //step9:设置内容
                message.setContent(to.getContent(), "text/html;charset=utf-8");//发送html邮件内容
                //step10:创建Transport用于将邮件发送
                Transport.send(message);//发送email
                log.info("send email success！");
                return true;
            }
        } catch (AddressException e) {
            e.printStackTrace();
            log.info(">>>>send email fail" + e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            log.info(">>>>send email fail" + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        //996520368@qq.com liu123456
        String[] address = {"996520368@qq.com"};
        EmailTO email = new EmailTO();
        email.setContent("hello world!");
        email.setTitle("hello world");
        email.setSender("1562265312@qq.com");
        email.setPassword("gvyqetisnreqhhih");
        email.setHost("smtp.qq.com");
        email.setToAdress(address);
        SendMailUtil.send(email);
    }
}
