package com.example.mail.services;

import com.example.mail.utils.EmailRequestBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BrevoEmailService {

    @Value("${application.email.brevo.url}")
    private String apiUrl;
    @Value("${application.email.brevo.api-key}")
    private String apiKey;
    @Value("${application.email.sender}")
    private String senderEmail;
    private final RestTemplate restTemplate = new RestTemplate();

    public void sendEmail(String toEmail, String subject, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.set("Content-Type", "application/json");
        String requestJson = EmailRequestBuilder.createEmailRequestBody(senderEmail, toEmail, subject, body);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to send email: " + toEmail);
        }
    }

    public void sendEmailWithAttachment(String toEmail, String subject, String body) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.set("Content-Type", "application/json");

        String base64Attachment = EmailRequestBuilder.encodeFileToBase64("D:\\books\\english\\toeic.docx");

        String requestJson = EmailRequestBuilder.createEmailRequestBody(senderEmail, toEmail, subject, body, "attachment.docx", base64Attachment);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to send email: " + toEmail);
        }
    }
}
