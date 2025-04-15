package com.ai.SpringAI.service;

import com.ai.SpringAI.entity.User;
import com.ai.SpringAI.repository.UserRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class OAuthUserService {
    private final UserRepository userRepository;

    public OAuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User processOAuthPostLogin(OidcUser oidcUser) {
        Optional<User> existingUser = userRepository.findByUsername(oidcUser.getEmail());
        User user;
        System.out.println("da vao");
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(oidcUser.getEmail());
            newUser.setEmail(oidcUser.getEmail());
            newUser.setAvatar(oidcUser.getPicture());
            newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            newUser.setLastLoginAt(new Timestamp(System.currentTimeMillis()));
            System.out.println("da luu");
            return userRepository.save(newUser);

        }
        else{
            user = existingUser.get();
            user.setLastLoginAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            System.out.println("khong luu");
        }
        System.out.println("no data");

        return existingUser.get();
    }
}