package ru.nikolay.restservice.service;

import org.springframework.core.io.FileSystemResource;
import ru.nikolay.commonjpa.entity.AppDocument;
import ru.nikolay.commonjpa.entity.AppPhoto;
import ru.nikolay.commonjpa.entity.BinaryContent;

public interface FileService {

    AppDocument getDocument(String id);
    AppPhoto getPhoto(String id);
    FileSystemResource getFileSystemResource(BinaryContent binaryContent);
}
