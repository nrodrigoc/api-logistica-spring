package com.nrodrigoc.logisticsapp.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrodrigoc.logisticsapp.rest.dto.EmailDTO;

public interface MailService {

    public void sendSimpleMail(EmailDTO dto);

    public void publishMessage(EmailDTO email);
}
