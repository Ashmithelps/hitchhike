package com.example.hitchhikking_app.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hitchhikking_app.model.User;
import com.example.hitchhikking_app.repository.UserRepository;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        logger.info("Saving user: {}", user);

        if (userRepository.findByUsername(user.getUsername()) != null) {
            logger.error("User with username '{}' already exists!", user.getUsername());
            throw new RuntimeException("User already exists!");
        }

        userRepository.save(user);
        logger.info("User saved successfully: {}", user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User findById(Long friendId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
