package ru.nikolay.node.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.nikolay.commonjpa.entity.AppDocument;
import ru.nikolay.commonjpa.entity.AppPhoto;

public interface FileService {

    AppDocument processDoc(Message telegramMessage);
    AppPhoto processPhoto(Message telegramMessage);
}
