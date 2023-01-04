package ru.nikolay.service;

import ru.nikolay.dto.MailParams;

public interface MailSenderService {
    void send(MailParams mailParams);
}
