package ru.nikolay.commonjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikolay.commonjpa.entity.AppPhoto;

public interface AppPhotoDAO extends JpaRepository<AppPhoto, Long> {
}
