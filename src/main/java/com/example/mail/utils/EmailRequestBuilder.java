package com.example.mail.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class EmailRequestBuilder {
    private static final String senderName = "CÔNG TY TNHH ĐẦU TƯ THƯƠNG MẠI VÀ DỊCH VỤ QUỐC TẾ ECO-HHB";

    public static String createEmailRequestBody(String senderEmail, String toEmail, String subject, String body) {
        String escapedBody = body.replace("\n", "\\n").replace("\"", "\\\""); // Escape double quotes
        return String.format(
                "{\n" +
                        "  \"sender\": {\"name\": \"%s\", \"email\": \"%s\"},\n" +
                        "  \"to\": [{\"email\": \"%s\"}],\n" +
                        "  \"subject\": \"%s\",\n" +
                        "  \"textContent\": \"%s\"\n" +
                        "}",
                senderName, senderEmail, toEmail, subject, escapedBody
        );
    }

    public static String createEmailRequestBody(String senderEmail, String toEmail, String subject, String body, String attachmentFileName, String attachmentBase64) {
        String escapedBody = body.replace("\n", "\\n").replace("\"", "\\\""); // Escape double quotes

        return String.format(
                "{\n" +
                        "  \"sender\": {\"name\": \"%s\", \"email\": \"%s\"},\n" +
                        "  \"to\": [{\"email\": \"%s\"}],\n" +
                        "  \"subject\": \"%s\",\n" +
                        "  \"textContent\": \"%s\",\n" +
                        "  \"attachment\": [\n" +
                        "    {\n" +
                        "      \"name\": \"%s\",\n" +
                        "      \"content\": \"%s\"\n" +  // Base64 encoded content
                        "    }\n" +
                        "  ]\n" +
                        "}",
                senderName, senderEmail, toEmail, subject, escapedBody, attachmentFileName, attachmentBase64
        );
    }

    public static String encodeFileToBase64(String filePath) throws Exception {
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }

}
