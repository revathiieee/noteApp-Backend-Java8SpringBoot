package com.note.api.noteitapi.mail;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class FeedbackMailSender implements FeedbackSender {

    private JavaMailSenderImpl javaMailSender;

    public FeedbackMailSender(Environment environment) {


        javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(environment.getProperty("spring.mail.host"));
        javaMailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        javaMailSender.setUsername((environment.getProperty("spring.mail.username")));
        javaMailSender.setPassword((environment.getProperty("spring.mail.password")));
    }

    @Override
    public void sendFeedback(String from, String name, String feedback) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo("techragesh@gmail.com");
        mailMessage.setSubject("Feedback about Note Api");
        mailMessage.setText(feedback);

        this.javaMailSender.send(mailMessage);


    }
}
