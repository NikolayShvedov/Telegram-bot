package ru.nikolay.commonjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikolay.commonjpa.entity.AppDocument;

public interface AppDocumentDAO extends JpaRepository<AppDocument, Long> {
}
