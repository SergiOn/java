package com.springliquibase.authorizationservice.service.impl;

import com.springliquibase.authorizationservice.message.request.LoginRequest;
import com.springliquibase.authorizationservice.model.User;
import com.springliquibase.authorizationservice.repository.UserRepository;
import com.springliquibase.authorizationservice.security.SecurityUtility;
import com.springliquibase.authorizationservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public boolean isUserNotExist(User user) {
        return userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).isEmpty();
    }

    @Override
    public boolean isLoginSuccess(LoginRequest user) {
        return userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail())
                .map(userDB -> SecurityUtility.passwordEncoder().matches(user.getPassword(), userDB.getPassword()))
                .orElse(false);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createDefaultUser(User user) {
        return userRepository.findByUsername(user.getUsername())
                .map(userByRepository -> {
                    log.info("User with username {} already exists. Nothing will be done.", userByRepository.getUsername());
                    return userByRepository;
                })
                .orElseGet(() -> {
                    String encryptedPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
                    user.setPassword(encryptedPassword);

                    return userRepository.save(user);
                });
    }

}
