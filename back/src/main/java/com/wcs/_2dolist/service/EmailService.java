package com.wcs._2dolist.service;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Value;
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


    public void sendRegistrationEmail(String email, String registrationToken, String frontUrl) {
        String url = this.domainName + "/registration/verify/";

        if(frontUrl != null && !frontUrl.isEmpty()){
            url = frontUrl;
        }
        Email from = new Email(emailFrom);
        String subject = "Activate Your Account: Finish Your Registration Process";
        Email to = new Email(email);
        Content content = new Content(
                "text/plain",
                "To complete your registration and activate your account, please click the link: " +
                        url +
                        registrationToken);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        TrackingSettings trackingSettings = new TrackingSettings();

        ClickTrackingSetting clickTrackingSetting = new ClickTrackingSetting();
        clickTrackingSetting.setEnable(false);
        clickTrackingSetting.setEnableText(false);
        trackingSettings.setClickTrackingSetting(clickTrackingSetting);

        OpenTrackingSetting openTrackingSetting = new OpenTrackingSetting();
        openTrackingSetting.setEnable(false);
        trackingSettings.setOpenTrackingSetting(openTrackingSetting);

        SubscriptionTrackingSetting subscriptionTrackingSetting = new SubscriptionTrackingSetting();
        subscriptionTrackingSetting.setEnable(false);
        trackingSettings.setSubscriptionTrackingSetting(subscriptionTrackingSetting);

        GoogleAnalyticsSetting googleAnalyticsSetting = new GoogleAnalyticsSetting();
        googleAnalyticsSetting.setEnable(false);
        trackingSettings.setGoogleAnalyticsSetting(googleAnalyticsSetting);

        mail.setTrackingSettings(trackingSettings);

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            //TODO: make handling of this exception correctly with http response
            try {
                throw ex;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

