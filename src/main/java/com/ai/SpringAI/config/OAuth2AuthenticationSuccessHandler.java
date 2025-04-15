package com.ai.SpringAI.config;

import com.ai.SpringAI.entity.User;
import com.ai.SpringAI.service.OAuthUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler  implements org.springframework.security.web.authentication.AuthenticationSuccessHandler  {

    private JwtUtil jwtUtil;
    private OAuthUserService oAuthUserService;// Bạn tự viết JwtService để tạo token

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        String email = oidcUser.getEmail();
        User savedUser = oAuthUserService.processOAuthPostLogin(oidcUser);
        // Tạo JWT token từ email
        String token = JwtUtil.generateToken(savedUser.getEmail());
        System.out.println("da vao");
        // Redirect về frontend với token
        String redirectUrl = "http://localhost:3000/chat?token=" + token;
        response.sendRedirect(redirectUrl);
    }
}
