package app.spring.caminhoneiro.application.service;

import app.spring.caminhoneiro.rest.dto.EmailDTO;

public interface MailService {

    public void sendSimpleMail(EmailDTO dto);

    public void publishMessage(EmailDTO dto);

}
