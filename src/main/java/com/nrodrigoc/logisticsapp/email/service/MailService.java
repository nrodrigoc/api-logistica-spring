package com.nrodrigoc.logisticsapp.email.service;

public interface MailService {

    void sendSimpleMail(String to, String subject, String content);
}
