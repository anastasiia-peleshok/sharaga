package com.example.sharagasystem.service;

import java.io.File;

public interface EmailService {
    void sendHtmlEmail(String to, String subject, String htmlBody, String fileName, File attachmentFile);
}
