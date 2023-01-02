package ru.nikolay.commonjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikolay.commonjpa.entity.AppUser;

public interface AppUserDAO extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByTelegramUserId(Long id);
}
