package ru.nikolay.node.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikolay.node.entity.RawData;

public interface RawDataDAO extends JpaRepository<RawData, Long> {
}
