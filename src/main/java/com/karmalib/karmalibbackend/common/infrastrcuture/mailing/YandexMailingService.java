package com.karmalib.karmalibbackend.common.infrastrcuture.mailing;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Properties;

@Service
public class YandexMailingService implements IMailingService {
    @Value("${yandex.mail.email}")
    private String senderEmail;

    @Value("${yandex.mail.username}")
    private String username;

    @Value("${yandex.mail.password}")
    private String password;

    private JavaMailSender mailSender;

    @PostConstruct
    public void init() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost("smtp.yandex.com");
        mailSenderImpl.setPort(465); // Yandex SMTP SSL port
        mailSenderImpl.setUsername(username);
        mailSenderImpl.setPassword(password);

        Properties props = mailSenderImpl.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");

        this.mailSender = mailSenderImpl;
    }

    public void sendMail(EmailMessage emailMessage) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            if (emailMessage.getSender().isEmpty() || emailMessage.getSender().isBlank()) {
                helper.setFrom(senderEmail);
            } else {
            helper.setFrom(emailMessage.getSender());
            }
            helper.setTo(emailMessage.getRecipient());
            helper.setSubject(emailMessage.getSubject());

            // Check if content is HTML
            if (emailMessage.isHtml()) {
                helper.setText(emailMessage.getBody(), true); // Enable HTML content
            } else {
                helper.setText(emailMessage.getBody());
            }

            // Add attachments if any
            for (EmailMessage.Attachment attachment : emailMessage.getAttachments()) {
                helper.addAttachment(attachment.getFilename(), () ->
                        new ByteArrayInputStream(attachment.getContent()));
            }

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // Log exception or rethrow as a custom exception
        }
    }
}
