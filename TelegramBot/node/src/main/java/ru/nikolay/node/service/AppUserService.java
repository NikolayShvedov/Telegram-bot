package ru.nikolay.node.service;

import ru.nikolay.commonjpa.entity.AppUser;

public interface AppUserService {
    String registerUser(AppUser appUser);
    String setEmail(AppUser appUser, String email);
}
