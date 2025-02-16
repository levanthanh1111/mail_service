package com.example.mail.requests;

import jakarta.validation.constraints.NotNull;

public class EmailSend {
    @NotNull(message = "toEmail is required")
    private String toEmail;
    @NotNull(message = "subject is required")
    private String subject;
    @NotNull(message = "body is required")
    private String body;

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
