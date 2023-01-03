package ru.nikolay.node.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.nikolay.commonjpa.entity.AppDocument;

public interface FileService {

    AppDocument processDoc(Message externalMessage);
}
