package com.example.mail.controllers;

import com.example.mail.requests.EmailSend;
import com.example.mail.responses.Response;
import com.example.mail.services.BrevoEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/email")
public class EmailController {
    @Autowired
    private BrevoEmailService brevoEmailService;

    @PostMapping(value = "/sending",produces = "application/json", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> sendEmail(@Valid @RequestBody EmailSend emailSend) {
        String toEmail = emailSend.getToEmail();
        String subject = emailSend.getSubject();
        String body = emailSend.getBody();
        brevoEmailService.sendEmail(toEmail, subject, body);
        return Response.success("success", "Email sent successfully");
    }

    @PostMapping(value = "/sending/file",produces = "application/json", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> sendEmailWithAttachment(@Valid @RequestBody EmailSend emailSend) throws Exception {
        String toEmail = emailSend.getToEmail();
        String subject = emailSend.getSubject();
        String body = emailSend.getBody();
        brevoEmailService.sendEmailWithAttachment(toEmail, subject, body);
        return Response.success("success", "Email sent successfully");
    }
}

