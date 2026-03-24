package com.arka.core.service;

import com.arka.core.model.User;
import com.arka.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DevUserService {

    private static final String DEV_USER_EMAIL = "dev@arka.local";
    private static final String DEV_USER_DISPLAY_NAME = "Dev User";

    private final UserRepository userRepository;

    public DevUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateDevUser() {
        return userRepository.findByEmail(DEV_USER_EMAIL)
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(DEV_USER_EMAIL);
                    user.setDisplayName(DEV_USER_DISPLAY_NAME);
                    return userRepository.save(user);
                });
    }
}