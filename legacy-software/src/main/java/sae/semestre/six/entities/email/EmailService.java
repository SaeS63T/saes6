package sae.semestre.six.entities.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private final String mailHost;
    private final int mailPort;
    private final String mailUsername;
    private final String mailPassword;
    private final String fromAddress;

    public EmailService(@Value("${hospital.mail.host}") String mailHost,
                        @Value("${hospital.mail.port}") int mailPort,
                        @Value("${hospital.mail.username}") String mailUsername,
                        @Value("${hospital.mail.password}") String mailPassword,
                        @Value("${hospital.mail.from}") String fromAddress) {

        this.mailHost = mailHost;
        this.mailPort = mailPort;
        this.mailUsername = mailUsername;
        this.mailPassword = mailPassword;
        this.fromAddress = fromAddress;

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailHost);
        sender.setPort(mailPort);
        sender.setUsername(mailUsername);
        sender.setPassword(mailPassword);
        
        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        this.mailSender = sender;
    }
    
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            
            mailSender.send(message);
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
} 