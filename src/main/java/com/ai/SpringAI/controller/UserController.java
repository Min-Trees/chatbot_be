package com.ai.SpringAI.controller;
import com.ai.SpringAI.config.JwtUtil;
import com.ai.SpringAI.service.OAuthUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private OAuthUserService userService;
    @GetMapping("/")
    public void home(HttpServletResponse response) throws  IOException {
        response.sendRedirect("/oauth2/authorization/google"); // Chuyển hướng đến Google Login
    }

    @GetMapping("/login-success")
    public Map<String, String> loginSuccess(@RequestParam(value = "token", required = false) String token) {
        Map<String, String> response = new HashMap<>();
        if (token != null) {
            response.put("token", token);
            response.put("message", "Login successful");
        } else {
            response.put("error", "Token not found");
        }
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OidcUser user) {

        userService.processOAuthPostLogin(user);

        Map<String, Object> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("username", user.getName());
        response.put("avatar", user.getPicture());
        return response;
    }
}
