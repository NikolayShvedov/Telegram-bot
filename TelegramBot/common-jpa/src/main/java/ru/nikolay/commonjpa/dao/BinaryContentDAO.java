package ru.nikolay.commonjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikolay.commonjpa.entity.BinaryContent;

public interface BinaryContentDAO extends JpaRepository<BinaryContent, Long> {
}
