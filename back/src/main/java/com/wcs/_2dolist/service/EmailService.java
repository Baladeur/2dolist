package com.wcs._2dolist.service;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.sendgrid.*;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String apiKey;
    @Value("${sendgrid.email.from}")
    private String emailFrom;
    @Value("${domain.name}")
    private String domainName;

    public EmailService(JavaMailSender javaMailSender) {
    }

    public void sendRegistrationEmail(String email, String registrationHash) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Complete Your Registration");
//        message.setText("Please click on the following link to complete your registration: " + registrationLink);
//        javaMailSender.send(message);

//        Email from = new Email("arseniit@gmail.com");
        Email from = new Email(emailFrom);
        String subject = "Activate Your Account: Finish Your Registration Process";
        Email to = new Email(email);
        Content content = new Content(
                "text/plain",
                "To complete your registration and activate your account, please click the link: " +
                        domainName +
                        "/registration/" + registrationHash);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            try {
                throw ex;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

